package hr.ponge.pfa.web.env.user;

import hr.ponge.pfa.service.env.user.UserFilterOptions;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	private static final XLogger log = XLoggerFactory
			.getXLogger(UserController.class);

	
	@RequestMapping(value = "/user",method=RequestMethod.GET)
	public String newUsersPage(Model model) {
		log.entry(model);
		
		model.addAttribute("userFilterOptions", new UserFilterOptions());
		return log.exit("user/read");
	}
	
	
	@RequestMapping(value = "/user",method=RequestMethod.POST)
	public String readUsers(@ModelAttribute("userFilterOptions")UserFilterOptions filterOptions,
			Model model) {
		log.entry(filterOptions,model);
		
		
		return log.exit("user/read");
	}

	
	
	

}
