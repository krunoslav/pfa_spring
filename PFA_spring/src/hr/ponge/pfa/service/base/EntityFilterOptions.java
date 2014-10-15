package hr.ponge.pfa.service.base;


public class EntityFilterOptions {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long param) {
		this.id = param;

	}

	public boolean isIdSpecified() {
		if (id == null) {
			return false;
		} else {
			return true;
		}

	}
	
	private ReadOutLimitsDTO readOutLimitsDTO;

	public ReadOutLimitsDTO getLimits() {
		return readOutLimitsDTO;
	}

	public void setLimits(ReadOutLimitsDTO param) {
		this.readOutLimitsDTO = param;

	}

	public boolean isLimitsSpecified() {
		if (readOutLimitsDTO == null) {
			return false;
		} else {
			return true;
		}
	}
	
}
