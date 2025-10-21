import java.io.IOException;

public class CreditCardPayment implements PaymentMethod{
	Basket basketOfPurchaser;
	int cardNumber;
	int securityCode;
	Customer customer;
	
	
	 
	public CreditCardPayment(Customer cstmr, int cardNum, int securityCode) {
		if (String.valueOf(cardNum).length() != 6) {
			throw new IllegalArgumentException("Please enter a valid card number (6 digit) ");
		}
		
		if (String.valueOf(securityCode).length() != 3) {
			throw new IllegalArgumentException("Please enter a valid security code (3 digits)");
		}
		this.basketOfPurchaser = cstmr.myBasket;
		this.cardNumber = cardNum;	
		this.securityCode = securityCode;
		this.customer = cstmr;
		
	
	}


	
	public Receipt processPayment(double amount, Address fullAddress) {
		try {
			this.basketOfPurchaser.stockUpdate("Stock.txt");
			}catch (IOException e1) {
				System.out.println("Error: " + e1);
			}
		this.basketOfPurchaser.deleteBasket();
		String cardNumberAsString = String.valueOf(this.cardNumber);
		Receipt receipt = new Receipt(cardNumberAsString, fullAddress, amount);
		return receipt;
	}
}
