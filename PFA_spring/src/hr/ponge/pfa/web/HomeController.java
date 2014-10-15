package hr.ponge.pfa.web;

import java.util.Locale;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final XLogger log = XLoggerFactory
			.getXLogger(HomeController.class);
	
	@Autowired
	  private MessageSource messages;
	 
	  

	
	
	@RequestMapping({"/","/home"})
	public String showHomePage(Map<String, Object> model) {
	log.entry(model);
	String m = messages.getMessage("home.documentTemplates", null, new Locale("hr"));
	log.info("MESSAGE "+m);
		return log.exit("home");
	}

	
}
