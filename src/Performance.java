
public class Performance extends LiveEvent {
	private String eventType;
	private String language;
	public Performance(String eventType, int eventID,String eventName, double performanceFee, int quantityInStock, AgeRestrictionCategory restriction, double ticketPrice, String language) {
		super(eventID, eventName, LiveEventCategory.PERFORMANCE, performanceFee, quantityInStock, restriction, ticketPrice);
		String errorMsg = "The event " + eventID + ": " + eventName + " could not be added because ";
		if (eventType.length() == 0 ){
			errorMsg += "the type of the performance was not entered correctly.";
			throw new IllegalArgumentException(errorMsg);
		}else if (language.length() == 0) {
			errorMsg += "the language of the performance was not entered correctly.";	
			throw new IllegalArgumentException(errorMsg);
		}else {
			this.language = language;
			this.eventType = eventType;
		}
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public String getEventType() {
		return this.eventType;
	}
	
	@Override
	public String toString() {
		String representation = "Event ID: " + this.getEventID() 
		+ ", Event Category: " + this.getEventCategory() + ", Event Type: " 
		+ this.getEventType() + ", Event Name: " + this.getEventName() 
		+ ", Age Restriction: " + this.getAgeRestriction() + ", Quantity Of Tickets: " 
		+ this.getQuantityInStock() + ", Ticket Price: " + this.getTicketPrice()
		+ ", Language: " + this.getLanguage();
	
		return representation;
	}

	
	public String toStringTextFile() {
		String representation = this.getEventID() + ", " 
		+ this.getEventCategory().toString().toLowerCase() + ", " + this.getEventType() 
		+ ", " + this.getEventName() + ", " + this.getAgeRestriction().toString().toLowerCase() 
		+ ", " + this.getQuantityInStock() + ", " + this.getPerformanceFee() + ", " + this.getTicketPrice()
		+ ", " + this.getLanguage();
	
		return representation;
	}
	
}

