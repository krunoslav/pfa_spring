package hr.ponge.pfa.web.env.tenant;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import hr.ponge.pfa.model.Tenant;
import hr.ponge.pfa.service.base.PfaMessage;
import hr.ponge.pfa.service.env.tenant.TenantFilterOptions;

public class TenantWebModel {

	
	private TenantFilterOptions filterOptions = new TenantFilterOptions();

	public TenantFilterOptions getFilterOptions() {
		return filterOptions;
	}

	public void setFilterOptions(TenantFilterOptions filterOptions) {
		this.filterOptions = filterOptions;
	}

	private List<Tenant> tenantList = new ArrayList<Tenant>();

	public List<Tenant> getTenantList() {
		return tenantList;
	}

	public void setTenantList(List<Tenant> tenantList) {
		this.tenantList = tenantList;
	}

	@Valid
	private Tenant tenant;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	private List<PfaMessage> messages = new ArrayList<PfaMessage>();

	public List<PfaMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<PfaMessage> messages) {
		this.messages = messages;
	}
	
	public boolean hasMessages(){
		if(messages.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean hasWarnMessages(){
		if(getWarnMessages().size()>0){
			return true;
		}
		return false;
	}
	
	public List<PfaMessage> getWarnMessages(){
		List<PfaMessage>warn=new ArrayList<PfaMessage>();
		for(PfaMessage m:messages){
			if(m.getMessageType()==PfaMessage.MESSAGE_TYPE_WARN){
				warn.add(m);
			}
		}
		return warn;
	}
	
	public boolean hasInfoMessages(){
		if(getInfoMessages().size()>0){
			return true;
		}
		return false;
	}
	
	public List<PfaMessage> getInfoMessages(){
		List<PfaMessage>warn=new ArrayList<PfaMessage>();
		for(PfaMessage m:messages){
			if(m.getMessageType()==PfaMessage.MESSAGE_TYPE_INFO){
				warn.add(m);
			}
		}
		return warn;
	}
}
