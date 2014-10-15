package hr.ponge.pfa.web.env.tenant;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.Tenant;
import hr.ponge.pfa.service.base.PfaMessage;
import hr.ponge.pfa.service.env.tenant.TenantBL;
import hr.ponge.pfa.service.env.tenant.TenantFilterOptions;
import hr.ponge.pfa.web.WebMessageListener;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("mod")
public class TenantController {
	private static final XLogger log = XLoggerFactory
			.getXLogger(TenantController.class);

	@Autowired
	TenantBL tenantBL;

	@RequestMapping(value = "/tenant", method = RequestMethod.GET)
	public String newUsersPage(Model model, WebMessageListener listener) {
		log.entry(model);
		TenantWebModel webModel = new TenantWebModel();
		TenantFilterOptions fopt = new TenantFilterOptions();

		List<Tenant> rez = null;
		try {
			tenantBL.registerMessageListener(listener);
			rez = tenantBL.readEntity(fopt);
		} catch (PfaException e) {

		}
		webModel.setMessages(listener.getMessages());
		webModel.setTenantList(rez);
		webModel.setFilterOptions(fopt);

		model.addAttribute("mod", webModel);

		return log.exit("tenant");
	}

	@RequestMapping(value = "/tenant", params = { "filter" }, method = RequestMethod.POST)
	public String filterTenants(@ModelAttribute("mod") TenantWebModel mod,
			BindingResult br, WebMessageListener listener) {
		log.entry(mod, br);
		TenantFilterOptions fopt = mod.getFilterOptions();
		List<Tenant> rez = new ArrayList<Tenant>();
		try {
			tenantBL.registerMessageListener(listener);
			rez = tenantBL.readEntity(fopt);
		} catch (PfaException e) {

		}
		addMessagesToModel(mod, br, listener);

		mod.setTenantList(rez);

		return log.exit("tenant");
	}

	private void addMessagesToModel(TenantWebModel mod, BindingResult br,
			WebMessageListener listener) {
		for (PfaMessage m : listener.getErrorMessages()) {
			ObjectError o = new ObjectError("",
					new String[] { m.getMessageKey() }, m.getParams(),
					m.getMessageKey());

			br.addError(o);
		}

		mod.setMessages(listener.getNonErrorMessages());
	}

	@RequestMapping(value = "/tenant", params = { "new" }, method = RequestMethod.POST)
	public String newTenant(@ModelAttribute("mod") @Valid TenantWebModel mod,
			BindingResult br, WebMessageListener listener) {
		try {

			tenantBL.registerMessageListener(listener);
			Tenant t = tenantBL.createEntity();
			mod.setTenant(t);

		} catch (PfaException e) {

		}
		addMessagesToModel(mod, br, listener);
		return log.exit("tenant");
	}

	@RequestMapping(value = "/tenant", params = { "save" }, method = RequestMethod.POST)
	public String saveTenant(@ModelAttribute("mod") @Valid TenantWebModel mod,
			BindingResult br, WebMessageListener listener) {

		if (br.hasErrors()) {
			return log.exit("tenant");
		}
		Tenant entity = mod.getTenant();
		try {

			tenantBL.registerMessageListener(listener);
			entity = tenantBL.saveEntity(entity);
			mod.setTenant(entity);
			listener.messageCallback(new PfaMessage(
					PfaMessage.MESSAGE_TYPE_INFO, "entitySaved"));
		} catch (PfaException e) {
			log.debug("TRANASCTION ROLLBACK ERROR CODE" + e.getErrorCode());
		}
		addMessagesToModel(mod, br, listener);
		return log.exit("tenant");
	}

	@RequestMapping(value = "/tenant", params = { "delete" }, method = RequestMethod.POST)
	public String deleteTenant(@ModelAttribute("mod") TenantWebModel mod,
			BindingResult br, WebMessageListener listener) {
		if (br.hasErrors()) {
			return log.exit("tenant");
		}
		Tenant entity = mod.getTenant();
		try {

			tenantBL.registerMessageListener(listener);
			tenantBL.deleteEntity(entity);
			mod.setTenant(entity);

		} catch (PfaException e) {
			log.debug("TRANASCTION ROLLBACK ERROR CODE" + e.getErrorCode());
		}
		addMessagesToModel(mod, br, listener);
		return log.exit("tenant");
	}

}
