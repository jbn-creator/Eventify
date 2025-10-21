import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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

public class CustomerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel DTMtable;
	Customer cstmr;
	ArrayList<LiveEvent> events;

	
	public void logOut() {
	    this.dispose();
	    MainFrame frame = new MainFrame();
	}
	  
	
	public CustomerFrame(Customer cstmr, ArrayList<LiveEvent> evts) {
		this.cstmr = cstmr;
		this.events = evts;
		this.cstmr.sortEventsByPrice(evts);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String eventIDList[] = new String[evts.size()];
		for (int i = 0; i < evts.size(); i++) {
			eventIDList[i] = Integer.toString(evts.get(i).getEventID());
		}
		
		ArrayList<Performance> perfLanguages = new ArrayList<Performance>(); 
		for (LiveEvent ev : evts) {
			if (ev instanceof Performance) {
				perfLanguages.add((Performance) ev);
			}
		}
		
		String perfLangList[] = new String[perfLanguages.size()];
		
		for (int i = 0; i < perfLanguages.size(); i++) {
			perfLangList[i] = perfLanguages.get(i).getLanguage();
		}
		
		
		setBounds(250, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel menuName = new JLabel("Customer menu");
		menuName.setBounds(447, 6, 110, 16);
		contentPane.add(menuName);
		
		JLabel greeting = new JLabel("Hello " + cstmr.getName() + "!");
		greeting.setBounds(48, 59, 96, 16);
		contentPane.add(greeting);
		
		JLabel detailsLabel = new JLabel("My Details:");
		detailsLabel.setBounds(48, 87, 110, 16);
		contentPane.add(detailsLabel);
		
		JLabel detailsValueLabel = new JLabel(cstmr.toString());
		detailsValueLabel.setBounds(130, 87, 864, 16);
		contentPane.add(detailsValueLabel);
		
		JButton viewAllEv = new JButton("view all events");
		viewAllEv.setBounds(38, 127, 117, 29);
		contentPane.add(viewAllEv);
		
		JLabel searchIDLabel = new JLabel("Search by event ID:");
		searchIDLabel.setBounds(167, 132, 125, 16);
		contentPane.add(searchIDLabel);
		
		JComboBox searchIDComboBox = new JComboBox(eventIDList);
		searchIDComboBox.setBounds(291, 128, 96, 27);
		contentPane.add(searchIDComboBox);
		
		JLabel searchLanguageLabel = new JLabel("Filter Performance by language:");
		searchLanguageLabel.setBounds(536, 132, 208, 16);
		contentPane.add(searchLanguageLabel);
		
		JButton submitSearchID = new JButton("Submit");
		submitSearchID.setBounds(386, 127, 72, 29);
		contentPane.add(submitSearchID);
	
		
		JLabel LabelDescribingtextAreaDisplay = new JLabel("List of all available events: ");
		LabelDescribingtextAreaDisplay.setBounds(49, 180, 508, 16);
		contentPane.add(LabelDescribingtextAreaDisplay);
		
		JComboBox searchLanguageComboBox = new JComboBox(perfLangList);
		searchLanguageComboBox.setBounds(740, 128, 117, 27);
		contentPane.add(searchLanguageComboBox);
		
		JButton submitFilterLang = new JButton("Submit");
		submitFilterLang.setBounds(869, 127, 72, 29);
		contentPane.add(submitFilterLang);
		
		JTextArea searchResultArea = new JTextArea();
		searchResultArea.setBounds(49, 209, 817, 195);
		searchResultArea.setText(cstmr.viewEventsAttribute(events));
		searchResultArea.setEditable(false);
		searchResultArea.setFocusable(false);
		searchResultArea.setOpaque(false);
		searchResultArea.setLineWrap(true);
		searchResultArea.setWrapStyleWord(true);
		contentPane.add(searchResultArea);
		
		JScrollPane searchScrollPane = new JScrollPane(searchResultArea);
		searchScrollPane.setBounds(49, 208, 945, 196);
		contentPane.add(searchScrollPane);
		
		JLabel labelAddEventBasket = new JLabel("Add event to Basket:");
		labelAddEventBasket.setBounds(49, 432, 137, 16);
		contentPane.add(labelAddEventBasket);
		
		JButton submitAddBasket = new JButton("submit");
		submitAddBasket.setBounds(302, 427, 85, 29);
		contentPane.add(submitAddBasket);
		
		JComboBox addingEventBasketComboBox = new JComboBox(eventIDList);
		addingEventBasketComboBox.setBounds(182, 428, 96, 27);
		contentPane.add(addingEventBasketComboBox);
		
		
		JPanel basketSection = new JPanel();
		basketSection.setBounds(48, 465, 946, 178);
		contentPane.add(basketSection);
		basketSection.setVisible(false);
		basketSection.setLayout(null);
		
		JLabel basketIntro = new JLabel("My Basket:");
		basketIntro.setBounds(6, 6, 74, 16);
		basketSection.add(basketIntro);
		
		JTextArea basketTextArea = new JTextArea();
		basketTextArea.setBounds(2, 2, 903, 100);
		basketTextArea.setText(cstmr.myBasket.viewBasket());
		basketSection.add(basketTextArea);
		basketTextArea.setEditable(false);
		basketTextArea.setFocusable(false);
		basketTextArea.setOpaque(false);
		basketTextArea.setLineWrap(true);
		basketTextArea.setWrapStyleWord(true);
		
		JScrollPane scrollPaneBasketArea = new JScrollPane(basketTextArea);
		scrollPaneBasketArea.setBounds(6, 27, 937, 75);
		basketSection.add(scrollPaneBasketArea);
		
		JButton deleteBasketBTN = new JButton("Delete Basket");
		deleteBasketBTN.setBounds(263, 104, 167, 52);
		basketSection.add(deleteBasketBTN);

		JButton viewBasketBtn = new JButton("view Basket");
		viewBasketBtn.setBounds(866, 427, 117, 29);
		viewBasketBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (viewBasketBtn.getText().strip() == "view Basket") {
					basketSection.setVisible(true);
					viewBasketBtn.setText("Hide Basket");
				}else {
					basketSection.setVisible(false);
					viewBasketBtn.setText("view Basket");
				}
				
			}
		});
		contentPane.add(viewBasketBtn);
		
		
		
		submitSearchID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idChosen = Integer.parseInt(searchIDComboBox.getSelectedItem().toString());
				LabelDescribingtextAreaDisplay.setText("List of events with the ID, " + searchIDComboBox.getSelectedItem().toString() + ":");
				searchResultArea.setText(cstmr.searchA(evts, idChosen).toString());
			}
		});
		
		submitFilterLang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LabelDescribingtextAreaDisplay.setText("List of performance with the language " + searchLanguageComboBox.getSelectedItem().toString() + ":");
				searchResultArea.setText(cstmr.searchB(evts, searchLanguageComboBox.getSelectedItem().toString()).toString());
			}
		});
		
		viewAllEv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LabelDescribingtextAreaDisplay.setText("List of all available events: ");
				searchResultArea.setText(cstmr.viewEventsAttribute(events));
			}
		});
		
		submitAddBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// the following long line tries to get a string from the combobox ID options, 
				//and transform it into a LiveEvent to be added to the basket
				if (!cstmr.myBasket.basketContainsSoldOutElements()) {
					cstmr.myBasket.addToBasket(cstmr.searchA(evts, Integer.parseInt(addingEventBasketComboBox.getSelectedItem().toString())));
					basketTextArea.setText(cstmr.myBasket.viewBasket());
				}else {
					basketTextArea.setText("One of the elements in your basket do not have sufficient tickets");
					cstmr.myBasket.deleteBasket();
				}
			}
		});
		
		deleteBasketBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cstmr.myBasket.deleteBasket();
				basketTextArea.setText(cstmr.myBasket.viewBasket());
			}
		});
		JButton logOutBtn = new JButton("Log out");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  logOut();
			}
		}); 
		logOutBtn.setVisible(false);
		logOutBtn.setBounds(782, 23, 137, 52);
		contentPane.add(logOutBtn);
		
		JButton goToPaymentBTN = new JButton("Proceed to Payment");
		goToPaymentBTN.setBounds(527, 104, 167, 52);
		goToPaymentBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cstmr.myBasket.getBasketElements().isEmpty()) {			
					PaymentFrame frame = new PaymentFrame(cstmr.myBasket.getAmount(), cstmr);
					frame.setVisible(true);
					logOutBtn.setVisible(true);
				}else {
					basketTextArea.setText("Your basket is empty, you can't proceed to payments.");
				}
			}
		});
		
		basketSection.add(goToPaymentBTN);
		
	
		
		
		//addingEventBasketComboBox.getSelectedItem()
	}
}
