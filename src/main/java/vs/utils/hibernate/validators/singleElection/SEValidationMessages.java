package vs.utils.hibernate.validators.singleElection;

import java.util.ArrayList;
import java.util.List;

public class SEValidationMessages {
	private Integer candidateId;
	private List<String> messages = new ArrayList<String>();
	private boolean spoiltVote;

	public SEValidationMessages() {
	}

	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public boolean isSpoiltVote() {
		return spoiltVote;
	}

	public void setSpoiltVote(boolean spoiltVote) {
		this.spoiltVote = spoiltVote;
	}

	public void setOneMessage(String message) {
		this.messages.add(message);
	}

}
