package vs.admin_.candidate;

public class CandidateDataPackage {

	private String text;
	private int id;
	private char delimiter;
	private boolean hasHeader;

	CandidateDataPackage() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}

	public boolean isHasHeader() {
		return hasHeader;
	}

	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
	}
	
	
	

}
