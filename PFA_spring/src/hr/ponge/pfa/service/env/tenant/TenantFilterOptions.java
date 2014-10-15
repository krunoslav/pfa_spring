package hr.ponge.pfa.service.env.tenant;

import hr.ponge.pfa.service.base.EntityFilterOptions;

public class TenantFilterOptions extends EntityFilterOptions {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String param) {
		this.name = param;

	}

	public boolean isNameSpecified() {
		if (name == null) {
			return false;
		}
		if (name.trim().length() == 0) {
			return false;
		}

		return true;
	}

}
