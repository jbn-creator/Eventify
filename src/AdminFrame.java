import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AdminFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel DTMtable;
    Admin admn;
    ArrayList<LiveEvent> events;
    private JTextField eventIdResult;
    private JTextField eventNameResult;
    private JTextField eventTypeResult;
    private JTextField quantityOfTicketsResult;
    private JTextField performanceFeeResult;
    private JTextField eventTicketPriceResult;
    private JTextField eventAdditionalInfo;
    private boolean decimalPointUsedPerformanceFee = false;
    private boolean decimalPointUsedTicketPrice = false;

    /**
     * Launch the application.
     * 
     * @throws FileNotFoundException
     */


    public void logOut() {
    	this.dispose();
    	MainFrame frame = new MainFrame();
    }
    /**
     * Create the frame.
     */
    public AdminFrame(Admin admin, ArrayList<LiveEvent> evts) {
        this.admn = admin;
        this.events = evts;
        this.admn.sortEventsByPrice(evts);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String eventIDList[] = new String[evts.size()];
        for (int i = 0; i < evts.size(); i++) {
            eventIDList[i] = Integer.toString(evts.get(i).getEventID());
        }

  
        String[] categoriesComboValue = {"Music", "Performance"};
        String[] ageRestrictionComboValue = {"All", "Adults"};
        
        
        setBounds(130, 100, 1300, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel menuName = new JLabel("Admin menu");
        menuName.setBounds(536, 6, 110, 16);
        contentPane.add(menuName);

        JLabel greeting = new JLabel("Hello " + admin.getName() + "!");
        greeting.setBounds(48, 59, 946, 16);
        contentPane.add(greeting);

        JLabel detailsLabel = new JLabel("My Details:");
        detailsLabel.setBounds(48, 87, 110, 16);
        contentPane.add(detailsLabel);

        JLabel detailsValueLabel = new JLabel(admin.toString());
        detailsValueLabel.setBounds(121, 81, 1145, 29);
        contentPane.add(detailsValueLabel);

        JButton viewAllEv = new JButton("view all events");
        viewAllEv.setBounds(38, 127, 336, 29);
        contentPane.add(viewAllEv);

        JButton addEvent = new JButton("Add an event");
        addEvent.setBounds(386, 127, 366, 29);
        contentPane.add(addEvent);

        JLabel LabelDescribingtextAreaDisplay = new JLabel("");
        LabelDescribingtextAreaDisplay.setBounds(49, 180, 785, 16);
        LabelDescribingtextAreaDisplay.setVisible(false);
        contentPane.add(LabelDescribingtextAreaDisplay);

        JPanel addEventPanel = new JPanel();
        addEventPanel.setBounds(48, 217, 946, 401);
        contentPane.add(addEventPanel);
        addEventPanel.setVisible(false);
        addEventPanel.setLayout(null);

        JButton addEventToStock = new JButton("Add the event to the stock");
        addEventToStock.setBounds(197, 281, 256, 68);
        addEventPanel.add(addEventToStock);

        JLabel eventIDLbl = new JLabel("Event ID: ");
        eventIDLbl.setBounds(197, 34, 61, 16);
        addEventPanel.add(eventIDLbl);

        JLabel evCategoryLbl = new JLabel("Event category: ");
        evCategoryLbl.setBounds(197, 62, 100, 16);
        addEventPanel.add(evCategoryLbl);

        JLabel evNameLbl = new JLabel("Event Name: ");
        evNameLbl.setBounds(197, 90, 100, 16);
        addEventPanel.add(evNameLbl);

        JLabel evTypeLbl = new JLabel("Event type: ");
        evTypeLbl.setBounds(197, 118, 100, 16);
        addEventPanel.add(evTypeLbl);

        JLabel ageRestriction = new JLabel("Age restriction: ");
        ageRestriction.setBounds(197, 146, 101, 16);
        addEventPanel.add(ageRestriction);

        JLabel evQtyTicketsLbl = new JLabel("Quantity of Ticket: ");
        evQtyTicketsLbl.setBounds(197, 174, 122, 16);
        addEventPanel.add(evQtyTicketsLbl);

        JLabel evPerfFeeLbl = new JLabel("Performance fee: ");
        evPerfFeeLbl.setBounds(197, 202, 109, 16);
        addEventPanel.add(evPerfFeeLbl);

        JLabel evTicketPrLbl = new JLabel("Ticket price: ");
        evTicketPrLbl.setBounds(197, 233, 122, 16);
        addEventPanel.add(evTicketPrLbl);

        JLabel additionalInfo = new JLabel("Please select event category");
        additionalInfo.setBounds(197, 261, 223, 16);
        addEventPanel.add(additionalInfo);

        JComboBox comboBoxCategories = new JComboBox(categoriesComboValue);
        comboBoxCategories.setBounds(368, 58, 223, 27);
        addEventPanel.add(comboBoxCategories);

        JLabel errorMsg = new JLabel("");
        errorMsg.setBounds(197, 361, 743, 16);
        addEventPanel.add(errorMsg);
 
        eventIdResult = new JTextField(5);
        eventIdResult.setDocument(new JTextFieldLimit(5));
        eventIdResult.addKeyListener(new KeyAdapter() {
        	
        	//only accepting integer 
        	
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!Character.isDigit(c)) {
        			e.consume();
        			errorMsg.setText("ensure the ID only contains Integers");
        		}else {
        			errorMsg.setText("");
        		}
        	}
        });
        eventIdResult.setBounds(368, 29, 341, 26);
        addEventPanel.add(eventIdResult);
        eventIdResult.setColumns(10);

        eventNameResult = new JTextField();
        eventNameResult.setBounds(368, 85, 341, 26);
        addEventPanel.add(eventNameResult);
        eventNameResult.setColumns(10);

        eventTypeResult = new JTextField();
        eventTypeResult.setBounds(368, 113, 341, 26);
        eventTypeResult.addKeyListener(new KeyAdapter() {  
        	
        	//accepting spaces and alphabetical letters only
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!Character.isAlphabetic(c) && c != ' ') {
        			e.consume();
        			errorMsg.setText("ensure the type of the is entered correctly (letters only)");
        		}else {
        			errorMsg.setText("");
        		}
        	}     	
        });
        addEventPanel.add(eventTypeResult);
        eventTypeResult.setColumns(10);

        JComboBox ageRestrictionCombo = new JComboBox(ageRestrictionComboValue);
        ageRestrictionCombo.setBounds(368, 142, 223, 27);
        addEventPanel.add(ageRestrictionCombo);

        quantityOfTicketsResult = new JTextField();
        quantityOfTicketsResult.addKeyListener(new KeyAdapter() {
        	
        	//accepting numbers only
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!Character.isDigit(c)) {
        			e.consume();
        			errorMsg.setText("ensure the quantity of tickets only contains Integers");
        		}else {
        			errorMsg.setText("");
        		}
        	}
        });
        quantityOfTicketsResult.setBounds(368, 169, 341, 26);
        addEventPanel.add(quantityOfTicketsResult);
        quantityOfTicketsResult.setColumns(10);

        performanceFeeResult = new JTextField();
        performanceFeeResult.setBounds(368, 192, 341, 26);
        performanceFeeResult.addKeyListener(new KeyAdapter() {
        	
        	//only accepting numbers or one decimal point because the fee is a float
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (performanceFeeResult.getText().contains(".")) {
        			decimalPointUsedPerformanceFee = true;
        		}else {
        			decimalPointUsedPerformanceFee = false;
        		}
        			
        		
        		//logic for only accepting one decimal point 
        		if (c == '.') {
        			if (!decimalPointUsedPerformanceFee) {
        				decimalPointUsedPerformanceFee = true;
        			}else {
        				e.consume();
	        			errorMsg.setText("You have already included a decimal point");
        			}
        		}else if(!Character.isDigit(c)){
        			e.consume();
        			errorMsg.setText("ensure the performance fee only contains Integers or one decimal point");
        		}else {
        			errorMsg.setText("");
        		}
        	}
        });
        addEventPanel.add(performanceFeeResult);
        performanceFeeResult.setColumns(10);

        eventTicketPriceResult = new JTextField();
        eventTicketPriceResult.setBounds(368, 228, 341, 26);
        addEventPanel.add(eventTicketPriceResult);
        eventTicketPriceResult.addKeyListener(new KeyAdapter() {
        	
        	//logic for only accepting one decimal point as well as only accepting digits
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (eventTicketPriceResult.getText().contains(".")) {
        			decimalPointUsedTicketPrice = true;
        		}else {
        			decimalPointUsedTicketPrice = false;
        		}
        			
        		if (c == '.') {
        			if (!decimalPointUsedTicketPrice) {
        				decimalPointUsedTicketPrice = true;
        			}else {
        				e.consume();
	        			errorMsg.setText("You have already included a decimal point");
        			}
        		}else if(!Character.isDigit(c)){
        			e.consume();
        			errorMsg.setText("ensure the ticket price only contains Integers or one decimal point");
        		}else {
        			errorMsg.setText("");
        		}
        	}
        });
        eventTicketPriceResult.setColumns(10);

        eventAdditionalInfo = new JTextField();
        eventAdditionalInfo.setVisible(false);
        eventAdditionalInfo.setBounds(378, 256, 331, 26);
        addEventPanel.add(eventAdditionalInfo);
        
        
	    eventAdditionalInfo.addKeyListener(new KeyAdapter() {   
	    	
	       // depending on class nature, only accept an integer or a character
	       @Override
	        public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (comboBoxCategories.getSelectedItem().toString().equals("Music")){
	        		if (!Character.isDigit(c)) {
	        			e.consume();
	        			errorMsg.setText("the number of bands/DJs can only be integers");
	        		}else {
	        			errorMsg.setText("");
	        		}
        		}else {
        			if (!Character.isAlphabetic(c)) {
	        			e.consume();
	        			errorMsg.setText("ensure the language is entered correctly (letters only)");
	        		}else {
	        			errorMsg.setText("");
	        		}
        		}
	        }
        });
        eventAdditionalInfo.setColumns(10);

        JButton submitEventType = new JButton("submit");
        submitEventType.setBounds(592, 57, 117, 29);
        addEventPanel.add(submitEventType);

        JButton submitAgeRestriction = new JButton("submit");
        submitAgeRestriction.setBounds(592, 141, 117, 29);
        addEventPanel.add(submitAgeRestriction);
        
        JButton resetAllBtn = new JButton("Reset all values");
        resetAllBtn.setBounds(457, 281, 256, 68);
        addEventPanel.add(resetAllBtn);

         JTextArea resultAreaAllEv = new JTextArea();
         resultAreaAllEv.setBounds(49, 209, 817, 195);
         resultAreaAllEv.setText(admin.viewEventsAttribute(events));
         resultAreaAllEv.setEditable(false);
         resultAreaAllEv.setFocusable(false);
         resultAreaAllEv.setOpaque(false);
         resultAreaAllEv.setLineWrap(true);
         resultAreaAllEv.setWrapStyleWord(true);
         contentPane.add(resultAreaAllEv);
        
         JScrollPane scrollPaneAllEv = new JScrollPane(resultAreaAllEv);
         scrollPaneAllEv.setBounds(49, 208, 1217, 405);
         scrollPaneAllEv.setVisible(false);
         contentPane.add(scrollPaneAllEv);
         
         JButton logOutBtn = new JButton("Log out");
         logOutBtn.setVisible(false);
         logOutBtn.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 logOut();
         	 }
         });
         logOutBtn.setBounds(1092, 42, 174, 48);
         contentPane.add(logOutBtn);
        
        

        addEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        
         viewAllEv.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
		         LabelDescribingtextAreaDisplay.setVisible(true);
		         LabelDescribingtextAreaDisplay.setText("List of all available events: ");
		         scrollPaneAllEv.setVisible(true);
		         addEventPanel.setVisible(false);
         	 }
         });
         
         addEvent.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 LabelDescribingtextAreaDisplay.setVisible(true);
                 LabelDescribingtextAreaDisplay.setText("Add an event to the stock: ");
                 scrollPaneAllEv.setVisible(false);
                 addEventPanel.setVisible(true);
             }
         });

         submitEventType.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 eventAdditionalInfo.setText("");
            	 eventAdditionalInfo.setVisible(true);
            	 
            	 
            	 if (comboBoxCategories.getSelectedItem().toString().equals("Performance")) {
            		 additionalInfo.setText("Language: ");
            	 }else {
            		 additionalInfo.setText("Number of bands/DJs: ");
            	 }
             }
         });
         
         addEventToStock.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 
            	 //storing the values for creating a new event
            	 String ID = eventIdResult.getText().trim();
            	 String evName = eventNameResult.getText().trim();
            	 String evType = eventTypeResult.getText().trim();
            	 String qtyTicket = quantityOfTicketsResult.getText().trim();
            	 String perfFee = performanceFeeResult.getText().trim();
            	 String ticketPrice = eventTicketPriceResult.getText().trim();
            	 String additionalInfo = eventAdditionalInfo.getText().trim();
            	 String restriction = ageRestrictionCombo.getSelectedItem().toString();
            	 
            	 
            	 //checking wether all the value are not empty using the canAdd method in Admin class
            	 if (admn.canAdd(evts, ID, evName, evType, qtyTicket, perfFee, ticketPrice, additionalInfo)) {
            		 int evtID = Integer.parseInt(ID);
            		 int quantityTickets = Integer.parseInt(qtyTicket);
            		 double performanceFee = Double.parseDouble(perfFee);
            		 double tktPrice = Double.parseDouble(ticketPrice);
            		 if(comboBoxCategories.getSelectedItem().toString() == "Music") {
            			 int nbrPerformer = Integer.parseInt(additionalInfo);
               			 Music musicToAdd = new Music(evType, evtID, evName, performanceFee, quantityTickets, admn.getAgeRestrictionFromString(restriction), tktPrice,nbrPerformer);
            			 try {
							admn.addEvent(evts, musicToAdd);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            		 }else {
            			 Performance performanceToAdd = new Performance(evType, evtID, evName, performanceFee, quantityTickets, admn.getAgeRestrictionFromString(restriction), tktPrice,additionalInfo);
            			 try {
							admn.addEvent(evts, performanceToAdd);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            		 }
            		 errorMsg.setText("The event " + evName + " was successfully added!");
            		 admn.sortEventsByPrice(evts);
            		 logOutBtn.setVisible(true);
            		   
            	 }else {
            		 errorMsg.setText("The event " + evName + " could not be added. Please ensure all the fields are answered \nand/or the ID isn't already registered");
            	 }
            	 
            	 //reset everything
            	 eventIdResult.setText("");
            	 eventNameResult.setText("");
            	 eventTypeResult.setText("");
            	 quantityOfTicketsResult.setText("");
            	 performanceFeeResult.setText("");
            	 eventTicketPriceResult.setText("");
            	 eventAdditionalInfo.setText("");
            	 
             }
         });
         
         resetAllBtn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 eventIdResult.setText("");
            	 eventNameResult.setText("");
            	 eventTypeResult.setText("");
            	 quantityOfTicketsResult.setText("");
            	 performanceFeeResult.setText("");
            	 eventTicketPriceResult.setText("");
            	 eventAdditionalInfo.setText("");
            	 errorMsg.setText("");
            	
             }
         });
       
    }
}
