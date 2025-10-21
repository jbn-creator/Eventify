
public class Address {
	private int houseNumber;
	private String postCode;
	private String city;
	

	public Address(int houseNumber, String postCode, String city){
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
	}
	
	
	public int getHouseNumber() {
		return this.houseNumber;
	}
	
	
	public String getPostCode() {
		return this.postCode;
	}
	
	
	public String getCity() {
		return this.city;
	}
	
	
	
	@Override
	public String toString() {
		String fa = "House number: " + this.houseNumber + "; Post code: " + this.getPostCode() + "; City: " +this.getCity();
		return fa;
	}
}
