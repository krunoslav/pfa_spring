package hr.ponge.pfa.service.env.tenant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.PfaRollbackException;
import hr.ponge.pfa.model.Tenant;
import hr.ponge.pfa.service.base.MessageListener;
import hr.ponge.pfa.service.base.PfaMessage;
import hr.ponge.pfa.service.util.DbUtil;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TenantServiceTest implements MessageListener{
	static ApplicationContext applicationContext;
	private List<PfaMessage>messages=new ArrayList<PfaMessage>();
	private static final XLogger log = XLoggerFactory.getXLogger(Test.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	applicationContext= new ClassPathXmlApplicationContext("spring/application-context.xml");
		DbUtil dbUtil= (DbUtil) applicationContext.getBean("dbUtil");
		InputStream in = TenantServiceTest.class.getClassLoader()
                .getResourceAsStream("bringup/bringup.sql");
		
		StringWriter writer = new StringWriter();
		IOUtils.copy(in, writer, "UTF-8");
		String bringupScript = writer.toString();
	int res=	dbUtil.executeSqlScript(bringupScript);
		log.info("EXECUTE BRINGUP RESULT "+res);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		applicationContext=null;
	}

	@Before
	public void setUp() throws Exception {
		messages.clear();
	}

	@After
	public void tearDown() throws Exception {
		messages.clear();
	}

	@Test
	public void testCreateEntity() {
		TenantBL tenantBL = (TenantBL) applicationContext.getBean("tenantBl");
		tenantBL.registerMessageListener(this);
		try {
			Tenant ten = tenantBL.createEntity();
			assertNotNull(ten);
			assertNotNull(ten.getCreationDate());
		} catch (PfaException e) {
			log.error(e.getErrorCode(), e);
			fail(e.getErrorCode());
		}
		
	}

	@Test
	public void testReadEntity() {
		TenantBL tenantBL = (TenantBL) applicationContext.getBean("tenantBl");
		tenantBL.registerMessageListener(this);
		TenantFilterOptions filter=new TenantFilterOptions();
		
		try {
			List<Tenant> rez = tenantBL.readEntity(filter);
			assertNotNull(rez);
			assertEquals(1, rez.size());
			Tenant tenant=rez.get(0);
			assertEquals(tenant.getName(), "PONGE");
		} catch (PfaException e) {
			log.error(e.getErrorCode(), e);
			fail(e.getErrorCode());
		}
	}

	@Test
	public void testSaveEntity() {
		TenantBL tenantBL = (TenantBL) applicationContext.getBean("tenantBl");
		tenantBL.registerMessageListener(this);
		try {
			Tenant ten = tenantBL.createEntity();
			ten.setName("PERICA");
			ten=tenantBL.saveEntity(ten);
			Assert.assertNotEquals(0, ten.getId());
			try{
			messages.clear();
			ten.setName("N");
			ten=tenantBL.saveEntity(ten);
			fail("NO ROLLBACK WHER SHOULD BE ONE");
			}catch(PfaRollbackException r){
				for(PfaMessage m:messages){
					log.info(m.getMessageKey());
				}
			}
			
		} catch (PfaException e) {
			log.error(e.getErrorCode(), e);
			fail(e.getErrorCode());
		}
		
	}

	@Test
	public void testDeleteEntity() {
		
	}

	@Override
	public void messageCallback(PfaMessage message) {
		messages.add(message);
		
	}

}
