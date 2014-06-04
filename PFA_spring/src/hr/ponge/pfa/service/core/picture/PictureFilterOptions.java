package hr.ponge.pfa.service.core.picture;

import hr.ponge.pfa.service.base.EntityFilterOptions;

public class PictureFilterOptions extends EntityFilterOptions {

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

	private String md5Code;

	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}

	public boolean isMd5CodeSpecified() {
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
}
