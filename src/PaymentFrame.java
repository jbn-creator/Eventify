import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PaymentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailInput;
	private JTextField cardNbrInput;
	private JTextField securityCodeInput;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
    public void logOut() {
    	this.dispose();
    	MainFrame frame = new MainFrame();
    }
    
	public PaymentFrame(double totalAmount, Customer cstmr) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 350, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel pageName = new JLabel("Payment menu");
		pageName.setBounds(247, 6, 121, 27);
		contentPane.add(pageName);
		
		JLabel optionLabel = new JLabel("Choose a payment methode:");
		optionLabel.setBounds(40, 73, 373, 16);
		contentPane.add(optionLabel);
		
		JButton PaypalOption = new JButton("PayPal");
		PaypalOption.setBounds(40, 93, 95, 39);
		contentPane.add(PaypalOption);
		
		JButton CreditCardOpt = new JButton("Credit Card");
		CreditCardOpt.setBounds(147, 93, 95, 39);
		contentPane.add(CreditCardOpt);
		
		JPanel creditCardPanel = new JPanel();
		creditCardPanel.setBounds(50, 144, 512, 208);
		contentPane.add(creditCardPanel);
		creditCardPanel.setVisible(false);
		creditCardPanel.setLayout(null);
		
		JLabel securityCode = new JLabel("3-digit security code:");
		securityCode.setBounds(6, 30, 145, 16);
		creditCardPanel.add(securityCode);
		
		JLabel errorMessageCreditC = new JLabel("");
		errorMessageCreditC.setVisible(true);
		errorMessageCreditC.setBounds(6, 49, 410, 16);
		creditCardPanel.add(errorMessageCreditC);
		
		cardNbrInput = new JTextField(6);
		cardNbrInput.setBounds(153, 1, 130, 26);
		cardNbrInput.setDocument(new JTextFieldLimit(6));
		cardNbrInput.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	             String value = cardNbrInput.getText();
	             int l = value.length();
	             if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
	            	cardNbrInput.setEditable(true);
	            	errorMessageCreditC.setText("");
	             } else {
	            	cardNbrInput.setEditable(false);
	            	errorMessageCreditC.setText("* Enter only numeric digits(0-9)");
	             }
	          }
	       });
		creditCardPanel.add(cardNbrInput);
		cardNbrInput.setColumns(10);
		
		JLabel creditCardNbr = new JLabel("6-digit card number:");
		creditCardNbr.setBounds(6, 6, 145, 16);
		creditCardPanel.add(creditCardNbr);
		
		securityCodeInput = new JTextField(3);
		securityCodeInput.setDocument(new JTextFieldLimit(3)); //using the JTextFieldLimit to set a limit on the number of digits
		securityCodeInput.setBounds(153, 25, 130, 26);
		securityCodeInput.addKeyListener(new KeyAdapter() {
			//only accepting digits
	         public void keyPressed(KeyEvent ke) {
	             String value = cardNbrInput.getText();
	             int l = value.length();
	             if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
	            	cardNbrInput.setEditable(true);
	            	errorMessageCreditC.setText("");
	             } else {
	            	cardNbrInput.setEditable(false);
	            	errorMessageCreditC.setText("* Enter only numeric digits(0-9)");
	             }
	          }
	       });
		creditCardPanel.add(securityCodeInput);
		securityCodeInput.setColumns(10);
		
		
		JTextArea receiptTextCreditC = new JTextArea();
		receiptTextCreditC.setBounds(6, 93, 500, 109);
		receiptTextCreditC.setEditable(false);
		receiptTextCreditC.setFocusable(false);
		receiptTextCreditC.setOpaque(false);
		receiptTextCreditC.setLineWrap(true);
		receiptTextCreditC.setWrapStyleWord(true);
		creditCardPanel.add(receiptTextCreditC);
		JButton receiptBtnCreditC = new JButton("see my Receipt");

		receiptBtnCreditC.setBounds(-1, 65, 152, 29);
		creditCardPanel.add(receiptBtnCreditC);
		
		JScrollPane scrollPane = new JScrollPane(receiptTextCreditC);
		scrollPane.setBounds(6, 93, 500, 109);
		scrollPane.setVisible(false);
		creditCardPanel.add(scrollPane);
		
		receiptBtnCreditC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (receiptBtnCreditC.getText().strip() == "see my Receipt") {
					scrollPane.setVisible(true);
					receiptBtnCreditC.setText("Hide my Receipt");
				}else {
					scrollPane.setVisible(false);
					receiptBtnCreditC.setText("see my Receipt");
				}
				
			}
		});
		
		JButton resetCardNbr = new JButton("reset");
		resetCardNbr.setBounds(295, 1, 78, 29);
		creditCardPanel.add(resetCardNbr);
		resetCardNbr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardNbrInput.setText("");
			}
		});
		
		JButton resetSecurityNbr = new JButton("reset");
		resetSecurityNbr.setBounds(295, 25, 78, 29);
		resetSecurityNbr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				securityCodeInput.setText("");
				
			}
		});
		creditCardPanel.add(resetSecurityNbr);
		
		JButton submitCCDetails = new JButton("submit");
		submitCCDetails.setBounds(373, 1, 78, 45);
		creditCardPanel.add(submitCCDetails);

		
		JPanel paypalPanel = new JPanel();
		paypalPanel.setVisible(false);
		paypalPanel.setBounds(50, 144, 512, 208);
		contentPane.add(paypalPanel);
		paypalPanel.setLayout(null);
		
		JLabel PaypalEmail = new JLabel("Enter your paypal email address: ");
		PaypalEmail.setBounds(6, 6, 214, 16);
		paypalPanel.add(PaypalEmail);
		
		emailInput = new JTextField();
		emailInput.setBounds(232, 1, 167, 26);
		paypalPanel.add(emailInput);
		emailInput.setColumns(10);
		
		JButton resetEmail = new JButton("reset");
		resetEmail.setBounds(395, 1, 78, 29);
		resetEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emailInput.setText("");
			}
		});
		paypalPanel.add(resetEmail);
			
		JLabel errorMessage = new JLabel("");
		errorMessage.setVisible(true);
		errorMessage.setBounds(6, 22, 410, 16);
		paypalPanel.add(errorMessage);
		
		JButton submitEmail = new JButton("submit");
		submitEmail.setBounds(395, 30, 78, 29);
		paypalPanel.add(submitEmail);
		
		JTextArea receiptPayPal = new JTextArea();
		receiptPayPal.setBounds(6, 68, 500, 134);
		receiptPayPal.setEditable(false);
		receiptPayPal.setFocusable(false);
		receiptPayPal.setOpaque(false);
		receiptPayPal.setLineWrap(true);
		receiptPayPal.setWrapStyleWord(true);
		paypalPanel.add(receiptPayPal);
		
		JScrollPane scrollPanePayPal = new JScrollPane(receiptPayPal);
		scrollPanePayPal.setBounds(6, 68, 500, 134);
		scrollPanePayPal.setVisible(false);
		paypalPanel.add(scrollPanePayPal);
		
		
		JButton receiptBtn = new JButton("see my Receipt");
		receiptBtn.addActionListener(new ActionListener() {
			//toggel between see/hide
			public void actionPerformed(ActionEvent e) {
				if (receiptBtn.getText().strip() == "see my Receipt") {
					scrollPanePayPal.setVisible(true);
					receiptBtn.setText("Hide my Receipt");
				}else {
					scrollPanePayPal.setVisible(false);
					receiptBtn.setText("see my Receipt");
				}
				
			}
		});
		receiptBtn.setBounds(0, 40, 152, 29);
		paypalPanel.add(receiptBtn);
		
		JLabel totalReceipt = new JLabel("Total: " + totalAmount);
		totalReceipt.setBounds(257, 45, 255, 16);
		contentPane.add(totalReceipt);
		
		
		JButton goToMainMenu = new JButton("Go back to customer menu");
		goToMainMenu.setBounds(373, 58, 174, 49);
		goToMainMenu.setVisible(false);
		//log out 
		goToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    logOut();
			}
		});
		contentPane.add(goToMainMenu);
		
		
		PaypalOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paypalPanel.setVisible(true);
				creditCardPanel.setVisible(false);
			}
		});
		
		
		CreditCardOpt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			creditCardPanel.setVisible(true);
			paypalPanel.setVisible(false);
			
		}
	});
		
		submitCCDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				//only make a payment if the card number and security code are valid
				if (cardNbrInput.getText().strip().length() == 6 && securityCodeInput.getText().strip().length() == 3) {
					int cardNumber = Integer.parseInt(cardNbrInput.getText().strip());
					int securityCode = Integer.parseInt(securityCodeInput.getText().strip());
					CreditCardPayment ccpayment = new CreditCardPayment(cstmr, cardNumber, securityCode);
					Receipt cstmrReceipt = ccpayment.processPayment(totalAmount, cstmr.getAddress());
					receiptTextCreditC.setText(cstmrReceipt.getReceipt());
					goToMainMenu.setVisible(true);		
					
				}else {										
					errorMessageCreditC.setText("Please ensure your credit card details are valid");
				}		
			}
		});
		
		submitEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//only accept an email if it contains @
				String email = emailInput.getText().strip();
						if (!email.contains("@")) {
							errorMessage.setText("this is not a valid e-mail, please ensure it has '@'");
						}else {
							PaypalPayment payPalPay = new PaypalPayment(cstmr, email);
							Receipt cstmrReceipt = payPalPay.processPayment(totalAmount, cstmr.getAddress());
							receiptPayPal.setText(cstmrReceipt.getReceipt());
							goToMainMenu.setVisible(true);							
						}
			}
		});
		
		
	}
}
