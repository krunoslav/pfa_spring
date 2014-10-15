package hr.ponge.pfa.service.base;

public class PfaMessage {
	
	public static final int MESSAGE_TYPE_INFO=1;
	public static final int MESSAGE_TYPE_WARN=2;
	public static final int MESSAGE_TYPE_ERROR=3;
	
	
	
	public PfaMessage(int messageType){
		if(messageType<MESSAGE_TYPE_INFO
				|| messageType>MESSAGE_TYPE_ERROR){
			throw new RuntimeException("Message type "+messageType+" not supported");
		}
		
		this.messageType=messageType;
	}
	
	
	public PfaMessage(int messageType,String messageKey){
		this(messageType);
		setMessageKey(messageKey);
	}
	
	

	private int messageType=0;
	

	public int getMessageType() {
		return messageType;
	}


	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	private String code;

	
	public String getCode() {

		return code;
	}

	
	public void setCode(String param) {
		this.code = param;

	}

	private String messageKey;

	
	public String getMessageKey() {

		return messageKey;
	}

	
	public void setMessageKey(String param) {
		this.messageKey = param;

	}

	private String[] params;

	
	public String[] getParams() {
		return params;
	}

	
	public void setParams(String[] param) {
		this.params = param;

	}

	private String stackTrace;

	
	public String getStackTrace() {
		return stackTrace;
	}

	
	public void setStackTrace(String param) {
		this.stackTrace = param;

	}


}
