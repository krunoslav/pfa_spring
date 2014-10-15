package hr.ponge.pfa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import hr.ponge.pfa.service.base.MessageListener;
import hr.ponge.pfa.service.base.PfaMessage;

@Component
@Scope("request")
public class WebMessageListener implements MessageListener {
	
	private List<PfaMessage>messages=new ArrayList<PfaMessage>();

	@Override
	public void messageCallback(PfaMessage message) {
		messages.add(message);
		
	}

	public List<PfaMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<PfaMessage> messages) {
		this.messages = messages;
	}
	
	
	public List<PfaMessage>getErrorMessages(){
		List<PfaMessage>errors=new ArrayList<PfaMessage>();
		for(PfaMessage m:messages){
			if(m.getMessageType()==PfaMessage.MESSAGE_TYPE_ERROR){
				errors.add(m);
			}
		}
		return errors;
	}
	
	public List<PfaMessage>getNonErrorMessages(){
		List<PfaMessage>nonErrors=new ArrayList<PfaMessage>();
		for(PfaMessage m:messages){
			if(m.getMessageType()!=PfaMessage.MESSAGE_TYPE_ERROR){
				nonErrors.add(m);
			}
		}
		return nonErrors;
	}
	
	public List<PfaMessage>getWarnMessages(){
		List<PfaMessage>nonErrors=new ArrayList<PfaMessage>();
		for(PfaMessage m:messages){
			if(m.getMessageType()==PfaMessage.MESSAGE_TYPE_WARN){
				nonErrors.add(m);
			}
		}
		return nonErrors;
	}
	
	public List<PfaMessage>getInfoMessages(){
		List<PfaMessage>nonErrors=new ArrayList<PfaMessage>();
		for(PfaMessage m:messages){
			if(m.getMessageType()==PfaMessage.MESSAGE_TYPE_INFO){
				nonErrors.add(m);
			}
		}
		return nonErrors;
	}
	

	
	
}
