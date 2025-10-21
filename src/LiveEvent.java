
public abstract class LiveEvent {
	private int eventID;
	private String eventName;
	private LiveEventCategory liveEventCategory;
	private double performanceFee;
	private int quantityInStock;
	private AgeRestrictionCategory restriction;
	private double ticketPrice;
	
	public LiveEvent(int eventID,String eventName, LiveEventCategory liveEventCategory, double performanceFee, int quantityInStock, AgeRestrictionCategory restriction,
	double ticketPrice){
		
		String errorMsg = "The event " + eventID + ": " + eventName + " could not be added because ";
		if (eventName.length() == 0 ) {
			errorMsg += "the name of the event was not entered correctly.";
			throw new IllegalArgumentException(errorMsg);
		}
		else if(performanceFee < 0) {
			errorMsg += "the performance fee can not be negative.";
			throw new IllegalArgumentException(errorMsg);
		}
		else if(ticketPrice < 0) {
	
		}
		else if(quantityInStock < 0) {
			errorMsg += "the quantity in stock can not be negative.";
			throw new IllegalArgumentException(errorMsg);
		}
		else {
			this.eventID = eventID;
			this.eventName = eventName;
			this.liveEventCategory = liveEventCategory;
			this.performanceFee = performanceFee;
			this.quantityInStock = quantityInStock;
			this.restriction = restriction;
			this.ticketPrice = ticketPrice;
		}	
	}
	

	public int getEventID() {
		return this.eventID;
	}
	
	public String getEventName() {
		return this.eventName;
	}
	
	public AgeRestrictionCategory getAgeRestriction() {
		return this.restriction;
	}
	
	public int getQuantityInStock() {
		return this.quantityInStock;
	}
	
	public void setQuantityInStock(int newQuantityInStock) {
		this.quantityInStock = newQuantityInStock;
	}
	
	public double getPerformanceFee() {
		return this.performanceFee;
	}
	
	public double getTicketPrice() {
		return this.ticketPrice;
	}
	
	public LiveEventCategory getEventCategory() {
		return this.liveEventCategory;
	}

	
	public abstract String toString();
	
	
}
