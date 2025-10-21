import java.time.LocalDate;
public class Receipt {
	private LocalDate today = LocalDate.now();
	private String paymentDetail;
	private Address address;
	private double amount;
	
	public Receipt(String paymentDtl, Address addr, double amnt) {
		this.paymentDetail = paymentDtl;
		this.address = addr;
		this.amount = amnt;
	}
	
	//check if each character is a digit (only useful if GUI not used)
	private boolean IscreditCardPayment(String input) {
	   for (char c : input.toCharArray()) {
	      if (!Character.isDigit(c)) {
	         return false;
	      }
	   }
	  return true;  
	}
	
	public String getReceipt() {
		String fullAddress = this.address.toString();
		String finalReceipt;
		if (IscreditCardPayment(this.paymentDetail)) {
			finalReceipt = this.amount + " paid by Credit Card using "
			+ this.paymentDetail + " on " + this.today + " ,\nand the billing address is \n" + fullAddress;
			return finalReceipt;		
			
		}else {
			finalReceipt = this.amount + " paid via PayPal using "
			+ this.paymentDetail + " on " + this.today + " ,\nand the billing address is \n" + fullAddress;
			return finalReceipt;
	
		}
		
	}
}
