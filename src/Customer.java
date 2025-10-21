import java.util.ArrayList;

public class Customer extends Users {
	Basket myBasket = new Basket();
	public Customer(int Id, String username, String name, Address fullAddress){
		super(Id, username, name,fullAddress, userCategory.CUSTOMER);
	}
	

	
	public String viewEventsAttribute(ArrayList<LiveEvent> events) {
		ArrayList<LiveEvent> sortedEvents = this.sortEventsByPrice(events);
		String returnString = "\n" ;
		for (LiveEvent b : sortedEvents) {
			if (b.getEventCategory().ordinal() == 0) {
				Music musicElement = (Music) b;
				returnString += musicElement.toString() + "\n\n";
			
			}
			else {
				Performance performanceElement = (Performance) b;
				returnString += performanceElement.toString() + "\n\n";
			}
		}
		return returnString;
	}
	
	//search by ID
	public LiveEvent searchA (ArrayList<LiveEvent> events, int searchedEventID) {
		for (LiveEvent b : events) {
			if (b.getEventID() == searchedEventID) {
				if (b.getEventCategory().ordinal() == 0) {
					Music musicElement = (Music) b;
					return musicElement;
				}
				else {
					Performance performanceElement = (Performance) b;
					return performanceElement;
				}
			}
		}
		
		return null;
	}
	
	//search by language onl
	public Performance searchB (ArrayList<LiveEvent> events, String searchPerformanceLanguage) {
		for (LiveEvent b : events) {
			if (b.getEventCategory().ordinal() == 1) {
				Performance performanceElement = (Performance) b;
				if (performanceElement.getLanguage().equals(searchPerformanceLanguage.strip())) {
					return performanceElement;
				}
				
			}
		}
		return null;
	}
	

}	
	