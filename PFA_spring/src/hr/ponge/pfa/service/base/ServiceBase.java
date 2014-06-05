package hr.ponge.pfa.service.base;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.PfaRollbackException;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = {PfaException.class,PfaRollbackException.class})
public abstract class ServiceBase implements Service {

	private MessageListener messageListener;

	@Override
	public void registerMessageListener(MessageListener listener) {
		this.messageListener = listener;
	}

	protected List<PfaMessage> messages = new ArrayList<PfaMessage>();
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public PfaMessage createError(String errorCode, String errorMessageKey,
			String... params) {

		return createMessage(PfaMessage.MESSAGE_TYPE_ERROR, errorCode,
				errorMessageKey, params);
	}
	
	

	@Transactional(propagation = Propagation.SUPPORTS)
	public PfaMessage createErrorAndSend(String errorCode, String errorMessageKey,
			String... params) {

		return createAndSendMessage(PfaMessage.MESSAGE_TYPE_ERROR, errorCode,
				errorMessageKey, params);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public PfaMessage createMessage(int messageType, String errorCode,
			String errorMessageKey, String... params) {

		PfaMessage er = new PfaMessage(messageType);
		er.setCode(errorCode);
		er.setMessageKey(errorMessageKey);
		er.setParams(params);

		return er;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public PfaMessage createAndSendMessage(int messageType, String errorCode,
			String errorMessageKey, String... params) {
		PfaMessage message = createMessage(messageType, errorCode,
				errorMessageKey, params);
		sendMessage(message);
		return message;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void sendMessage(PfaMessage message) {
		messages.add(message);
		if (messageListener != null) {
			messageListener.messageCallback(message);
		}
	}
	
	
	
	
	protected boolean checkForRollbackErrors(List<PfaMessage> messages){
		for(PfaMessage m:messages){
			if(m.getMessageType()==PfaMessage.MESSAGE_TYPE_ERROR){
				return true;
			}
		}
		return false;
	}
	
	

}
