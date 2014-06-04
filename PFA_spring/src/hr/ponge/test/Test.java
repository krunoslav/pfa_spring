package hr.ponge.test;

import java.util.ArrayList;
import java.util.List;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.Tenant;
import hr.ponge.pfa.service.base.MessageListener;
import hr.ponge.pfa.service.base.PfaMessage;
import hr.ponge.pfa.service.env.tenant.TenantBL;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test implements MessageListener {

	private static final XLogger log = XLoggerFactory.getXLogger(Test.class);

	public static void main(String[] args) {
		Test test = new Test();
		test.test(args);
	}

	List<PfaMessage> messages = new ArrayList<PfaMessage>();

	public void test(String[] args) {
		log.entry(args);
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"resource/spring/application-context.xml");

		// new ClassPathXmlApplicationContext("/application-context.xml");

		log.info("Spring context initialized.");
		TenantBL tenantBL = (TenantBL) applicationContext.getBean("tenantBl");

		tenantBL.registerMessageListener(this);

		try {
			messages.clear();
			Tenant ten = tenantBL.createEntity();
			ten.setName("PERO");
			tenantBL.saveEntity(ten);

		} catch (PfaException e) {
			// ROLLBACK
			log.error("ERROR CODE:"+e.getErrorCode());
			for (PfaMessage m : messages) {
				log.error("ERROR MESSAGE:"+m.getMessageKey());
			}
		}

		log.exit();
	}

	@Override
	public void messageCallback(PfaMessage message) {
		messages.add(message);

	}

}
