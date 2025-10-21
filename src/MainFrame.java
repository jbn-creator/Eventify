import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String[] personListString;//list of all the users but in string
	private static ArrayList<LiveEvent> eventList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws FileNotFoundException{
		File persons = new File ("userAccount.txt");
		Scanner fileScanner = new Scanner(persons);

		ArrayList<Users> listOfUsers = new ArrayList<Users>(); //stores all the users from the file
		ArrayList<String> personlistAL = new ArrayList<String>(); 
		
		while (fileScanner.hasNext()) {
			personlistAL.add(fileScanner.nextLine());  
		}
		
		String personList[] = new String[personlistAL.size()];
		
		for (int i = 0; i < personlistAL.size(); i++) {
			personList[i] = personlistAL.get(i);
		}
		
		
		fileScanner.close();
		File events = new File ("Stock.txt");
		Scanner fileScanner2 = new Scanner(events);

		ArrayList<LiveEvent> eventlistAL = new ArrayList<LiveEvent>();
		String dataCollected;
		while (fileScanner2.hasNext()) {
			//populating the list containing all the events
			dataCollected = (fileScanner2.nextLine()); 
			String[] b = dataCollected.split(",");
			
			int eventId = Integer.parseInt(b[0]);
			String eventType= b[2].strip();
			String eventName= b[3].strip();
			AgeRestrictionCategory ageRestriction = AgeRestrictionCategory.valueOf(b[4].toUpperCase().strip());
			int quantityOfTicket = Integer.parseInt(b[5].strip());
			double perfFee = Double.parseDouble(b[6].strip());
			double ticketPrice = Double.parseDouble(b[7].strip());
			
			if (b[1].strip().equals("Music")) {
				int nmbrPerformers = Integer.parseInt(b[8].strip());
				Music musicToAdd = new Music(eventType, eventId, eventName, perfFee, quantityOfTicket, ageRestriction, ticketPrice, nmbrPerformers);
				eventlistAL.add(musicToAdd);
			} else {
				String language = b[8].strip();
				Performance perfToAdd = new Performance(eventType, eventId, eventName, perfFee, quantityOfTicket, ageRestriction, ticketPrice, language);
				eventlistAL.add(perfToAdd);
				
			}
					
			
		}
		
		fileScanner2.close();
		personListString = personList;
		eventList = eventlistAL;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame()   {
		ArrayList<Users> listOfUsers = new ArrayList<Users>(); // ArrayList to store all the users.
		String userType[] = new String[personListString.length]; // array used for the ComboBox for logging in.
		
		
		for (int i = 0; i< personListString.length; i++) {
			
			String[] b = personListString[i].split(","); 
			userType[i] = b[1]; //sufficient for list of user names for log in.
			
			//populating Users ArrayList
			
			int ID = Integer.parseInt(b[0].strip());
			String userName = b[1].strip();
			String name = b[2].strip();
			int houseNbr = Integer.parseInt(b[3].strip());
			
			//checking if customer OR Admin.
			if (b[6].toLowerCase().strip().equals("customer")) {
				Customer cstmr = new Customer(ID, userName, name, new Address(houseNbr, b[4], b[5]));
				listOfUsers.add(cstmr);
			}else {
				Admin admin = new Admin(ID, userName, name, new Address(houseNbr, b[4], b[5]));
				listOfUsers.add(admin);
			}
			
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JComboBox usernameList = new JComboBox(userType);
		usernameList.setBounds(124, 106, 106, 27);
		contentPane.add(usernameList);
		
		JButton submitUsrNameBTN = new JButton("Submit");
		submitUsrNameBTN.setBounds(232, 105, 88, 29);
		
		//transitioning frame to customer or admin page.
		submitUsrNameBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userChose = usernameList.getSelectedItem().toString().strip();
				for (Users usrs : listOfUsers) {				
					if (userChose.equals(usrs.getUsername())) {
						if (usrs.getCategory().ordinal() == 0) {
							Customer cstmr = (Customer) usrs;
							CustomerFrame customerFrame = new CustomerFrame(cstmr, eventList);
							customerFrame.setVisible(true);
						}else {
							Admin admn = (Admin) usrs;
							AdminFrame adminFrame = new AdminFrame(admn, eventList);
							adminFrame.setVisible(true);
						}
					}
				}
			}
		});
		contentPane.add(submitUsrNameBTN);
		
		JLabel pageNameLabel = new JLabel("login page");
		pageNameLabel.setBounds(193, 6, 75, 16);
		contentPane.add(pageNameLabel);
		
		JLabel welcomeLabel = new JLabel("Welcome !");
		welcomeLabel.setBounds(203, 44, 197, 27);
		contentPane.add(welcomeLabel);
	}

}
