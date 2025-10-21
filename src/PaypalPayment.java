import java.io.IOException;

public class PaypalPayment implements PaymentMethod {
	//Update the event stock,
	//make the basket empty
	//Generate a receipt.
	Basket basketOfPurchaser;
	String emailOfPurchaser;
	Customer customer;
	

	 
	public PaypalPayment(Customer cstmr, String email) {
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Please ensure you have entered a valid email address");
		}
		
		this.basketOfPurchaser = cstmr.myBasket;
		this.emailOfPurchaser = email;	
		this.customer = cstmr;
		
	}
	


	public Receipt processPayment(double amount, Address fullAddress) {
		try {
		this.basketOfPurchaser.stockUpdate("Stock.txt");
		}catch (IOException e1) {
			System.out.println("Error: " + e1);
		}
		this.basketOfPurchaser.deleteBasket();
		Receipt receipt = new Receipt(this.emailOfPurchaser, fullAddress, amount);
		return receipt;
		
	}
	
	// then use the interface Payment Method using the customer address
}