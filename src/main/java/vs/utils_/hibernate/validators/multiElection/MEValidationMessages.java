package vs.utils_.hibernate.validators.multiElection;

import java.util.ArrayList;
import java.util.List;

public class MEValidationMessages {
	private Integer partyId;
	private List<String> messages = new ArrayList<String>();
	private boolean spoiltVote;

	public MEValidationMessages() {
	}
		
	public boolean isSpoiltVote() {
		return spoiltVote;
	}

	public void setSpoiltVote(boolean spoiltVote) {
		this.spoiltVote = spoiltVote;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public void setOneMessage(String message) {
		this.messages.add(message);
	}

}
