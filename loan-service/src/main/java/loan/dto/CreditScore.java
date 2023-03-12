package loan.dto;
 
//DTO(data transfer object) class.
//we need this class bcz we are returning the object from creditscore .
public class CreditScore { 
	
	private String pancard;
	private String personName;
	private int creditScore;
	public String getPancard() {
		return pancard;
	}                                                 //2
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	

}
