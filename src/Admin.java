

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends Users {
	
	public Admin(int Id, String username, String name, Address fullAddress){
		super(Id, username, name,fullAddress, userCategory.ADMIN);
	}
	
	
	@Override
	public String toString() {
		String value = "Customer ID: " + this.getUserId() + "; username: " + this.getUsername() + "; name: " + this.getName()
		+ " ;" + this.getAddress().toString() + "; category of user: " + this.getCategory();
		return value;
	}
	

	public String viewEventsAttribute(ArrayList<LiveEvent> events) {
		ArrayList<LiveEvent> sortedEvents = this.sortEventsByPrice(events);
		String returnString = "\n" ;
		
		//performance fee is added on top of the to string, because the admin can also see the perf fee but not the cstmr
		for (LiveEvent evt : sortedEvents) {
			//use ordinal to check what type of event it is, so we can have the appropriate info
			if (evt.getEventCategory().ordinal() == 0) {
				Music musicElement = (Music) evt;
				returnString += musicElement.toString() + ", performance fee: " + musicElement.getPerformanceFee() + "\n\n";
			}
			else {
				Performance performanceElement = (Performance) evt;
				returnString += performanceElement.toString() + ", performance fee: " + performanceElement.getPerformanceFee() + "\n\n";
			}
		}
		return returnString;
	}
	


	// These 2 methods simplify object creation in the GUI by converting strings
	// (selected from combo boxes) into corresponding enum constants.
	public AgeRestrictionCategory getAgeRestrictionFromString(String ageRest) {
		if (ageRest.strip().equals("All")) {
			return AgeRestrictionCategory.ALL;
		}else {
			return AgeRestrictionCategory.ADULTS;
		}
	}
	
	public LiveEventCategory getLiveEventCategoryFromString(String liveEvCateg) {
		if (liveEvCateg.strip().equals("Music")) {
			return LiveEventCategory.MUSIC;		
		}else {
			return LiveEventCategory.PERFORMANCE;
		}
	}
	
	
	
	//checking whether all the fields are properly entered. Simplifies Creating events
	public boolean canAdd(ArrayList<LiveEvent> events, String ID, String evName, String evType, String qtyTicket, String perfFee, String ticketPrice, String additionalInfo) {
		if (ID.length() == 5 && evName.length() > 0 && evType.length() > 0 && qtyTicket.length() > 0 && perfFee.length() > 0 && ticketPrice.length() > 0 && additionalInfo.length() > 0) {
			for (LiveEvent evt : events) {
				if (evt.getEventID() == Integer.parseInt(ID)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	public void addEvent(ArrayList<LiveEvent> events, LiveEvent eventToAdd) throws IOException {
		String errorMsg = "The event " + eventToAdd.getEventID() + ": " + eventToAdd.getEventName() + " could not be added because ";
		String successMsg = "The event " + eventToAdd.getEventID() + ":" + eventToAdd.getEventName() + " was successfully added to the stock !";
		
		for (LiveEvent evt : events) {
			if (evt.getEventID() == eventToAdd.getEventID()) {
				errorMsg += "an event in the stock possesses a matching ID.";
				System.out.println(errorMsg);
				return;
			}
		}
	
	
		if (eventToAdd.getEventName().length() == 0 ) {
			errorMsg += "the name of the event was not entered correctly.";
			System.out.println(errorMsg);
			return;
		}
		else if(eventToAdd.getPerformanceFee() < 0) {
			errorMsg += "the performance fee can not be negative.";
			System.out.println(errorMsg);
			return;
		}
		else if(eventToAdd.getTicketPrice() < 0) {
			errorMsg += "the ticket price of the event can not be negative.";
			System.out.println(errorMsg);
			return;
		}
		else if(eventToAdd.getQuantityInStock() < 0) {
			errorMsg += "the quantity in stock can not be negative.";
			System.out.println(errorMsg);
			return;
		}
		else {
			events.add(eventToAdd);
			
			FileWriter fileToWrite = new FileWriter("Stock.txt", true);
			BufferedWriter bw = new BufferedWriter(fileToWrite);		
			if (eventToAdd.getEventCategory().ordinal() == 0) {
				Music musicElement = (Music) eventToAdd;
				bw.write("\n" + musicElement.toStringTextFile());
				System.out.println("Successfully added " + musicElement.toStringTextFile());
			}
			else {
				Performance performanceElement = (Performance) eventToAdd;
				bw.write("\n" + performanceElement.toStringTextFile());
				System.out.println("Successfully added " + performanceElement.toStringTextFile());
			}
							
			bw.close();
			
			System.out.println(successMsg);
			return;
		}
		
	}


}	
	
