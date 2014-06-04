package hr.ponge.pfa.service.base;

import org.springframework.stereotype.Repository;


public class ReadOutLimitsDTO {

	private int firstRecord;

	
	public int getFirstRecord() {
		return firstRecord;
	}

	
	public void setFirstRecord(int param) {
		this.firstRecord = param;

	}

	private int maxRecords = 0;

	
	public int getMaxRecords() {
		return maxRecords;
	}

	
	public void setMaxRecords(int param) {
		this.maxRecords = param;

	}

}
