package hr.ponge.pfa;

import hr.ponge.pfa.service.base.PfaMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author kruno
 * 
 */
public class PfaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PERSISTENCE_ERROR = "E0050";

	public static final String GENERAL_ERROR = "E0001";

	public static final String USER_AUTHENTICATION_FAILED = "E1000";
	public static final String USER_NOT_AUTHORIZED_FOR_OPERATION = "E1001";
	public static final String CLIENT_VERSION_NOT_SUPORTED = "E1002";
	public static final String METHOD_NOT_MAPPED = "E1003";
	public static final String ERROR_CALL_BL_METHOD = "E1004";
	public static final String REQUEST_VALIDATION_ERROR = "E1005";
	public static final String VALIDATION_FAILED_ROLLBACK = "E1006";
	public static final String ENTITY_NOT_IN_CONTEXT = "E1007";

	private String errorCode;

	private List<PfaMessage> messages = new ArrayList<PfaMessage>();

	public PfaException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;

	}

	public PfaException(String errorCode, Throwable cause,
			List<PfaMessage> messages) {
		super(cause);
		this.errorCode = errorCode;
		this.messages = messages;
	}

	public PfaException(String errorCode, List<PfaMessage> messages) {
		this.errorCode = errorCode;
		this.messages = messages;
	}

	public PfaException(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<PfaMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<PfaMessage> messages) {
		this.messages = messages;
	}

}
