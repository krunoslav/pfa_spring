package hr.ponge.pfa.service.env.user;

import hr.ponge.pfa.service.base.EntityFilterOptions;

public class UserFilterOptions extends EntityFilterOptions {

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
		} else {
			return true;
		}
	}

	private String surname;

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isSurnameSpecified() {
		if (surname == null) {
			return false;
		} else {
			return true;
		}
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isUsernameSpecified() {
		if (username == null) {
			return false;
		} else {
			return true;
		}
	}

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAddressSpecified() {
		if (address == null) {
			return false;
		} else {
			return true;
		}
	}

	private Long tenantId;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	
	public boolean isTenantIdSpecified() {
		if (tenantId == null) {
			return false;
		} else {
			return true;
		}
	}

}
