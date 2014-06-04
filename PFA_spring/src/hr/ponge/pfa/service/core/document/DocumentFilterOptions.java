package hr.ponge.pfa.service.core.document;

import hr.ponge.pfa.service.base.EntityFilterOptions;

public class DocumentFilterOptions extends EntityFilterOptions {

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

	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isUserIdSpecified() {
		if (userId == null) {
			return false;
		} else {
			return true;
		}
	}
	
	private String description;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDescriptionSpecified() {
		if (userId == null) {
			return false;
		} else {
			return true;
		}
	}
}
