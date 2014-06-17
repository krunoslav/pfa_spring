package hr.ponge.pfa.web;

import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final XLogger log = XLoggerFactory
			.getXLogger(HomeController.class);
	
	@RequestMapping({"/","/home"})
	public String showHomePage(Map<String, Object> model) {
	log.entry(model);
		
		return log.exit("home");
	}

	
}
