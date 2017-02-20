package vs.admin.features.password;

import java.util.List;

import javax.persistence.Entity;


public class Password {

	
	private String password;
	private int randNumber;
	private StringBuilder stringBuilder;
	private List<Integer> numberList;
	
	public Password(){
	
	}
	
	public Password(String password, int randNumber, StringBuilder stringBuilder, List<Integer> numberList) {
		super();
		this.password = password;
		this.randNumber = randNumber;
		this.stringBuilder = stringBuilder;
		this.numberList = numberList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRandNumber() {
		return randNumber;
	}

	public void setRandNumber(int randNumber) {
		this.randNumber = randNumber;
	}

	public StringBuilder getStringBuilder() {
		return stringBuilder;
	}

	public void setStringBuilder(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}

	public List<Integer> getNumberList() {
		return numberList;
	}

	public void setNumberList(List<Integer> numberList) {
		this.numberList = numberList;
	}
	
	
	
	
}
