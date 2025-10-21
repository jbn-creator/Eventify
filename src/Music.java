
public class Music extends LiveEvent {
	private String eventType;
	private int performerCount;
	public Music(String eventType, int eventID,String eventName, double performanceFee, int quantityInStock, AgeRestrictionCategory restriction, double ticketPrice, int performerCount) {
		super(eventID, eventName,  LiveEventCategory.MUSIC, performanceFee, quantityInStock, restriction, ticketPrice);
		String errorMsg = "The event " + eventID + ": " + eventName + " could not be added because ";
		if (eventType.length() == 0 ){
			errorMsg += "the type of the performance was not entered correctly.";
			throw new IllegalArgumentException(errorMsg);
		}else if (performerCount < 1) {
			errorMsg += "the number of performers must be greater than 0";	
			throw new IllegalArgumentException(errorMsg);
		}else {
			this.performerCount = performerCount;
			this.eventType = eventType;
		}
	}
	
	public int getPerformerCount() {
		return this.performerCount;
	}
	
	public String getEventType() {
		return this.eventType;
	}
	
	@Override
	public String toString() {//performance fee won't be included because it can only be shown to admin
		String representation = "Event ID: " + this.getEventID() 
		+ ", Event Category: " + this.getEventCategory() + ", Event Type: " 
		+ this.getEventType() + ", Event Name: " + this.getEventName() 
		+ ", Age Restriction: " + this.getAgeRestriction() + ", Quantity Of Tickets: " 
		+ this.getQuantityInStock() + ", Ticket Price: " + this.getTicketPrice()
		+ ", Performer Count: " + this.getPerformerCount();
		return representation;
	}
	
	public String toStringTextFile() {
		String representation = this.getEventID() + ", " 
		+ this.getEventCategory().toString().toLowerCase()  + ", " + this.getEventType()
		+ ", " + this.getEventName() + ", " + this.getAgeRestriction().toString().toLowerCase() 
		+ ", " + this.getQuantityInStock() + ", " + this.getPerformanceFee() + ", " + this.getTicketPrice()
		+ ", " + this.getPerformerCount();
	
		return representation;
	}

}



