import java.util.ArrayList;

public abstract class Users {
	//I decided to have my users as a abstract class
	private int userId;
	private String username;
	private String name;
	private Address fullAddress;
	private userCategory category; //admin or customer
	
	public Users(int Id, String username, String name, Address fullAddress, userCategory category) {
		this.userId = Id;
		this.username = username;
		this.name = name;
		this.fullAddress = fullAddress;
		this.category = category;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Address getAddress() {
		return this.fullAddress;
	}
	
	public userCategory getCategory() {
		return this.category;
	}
	
	public ArrayList<LiveEvent> sortEventsByPrice(ArrayList<LiveEvent> events) {
		for (int i = 0; i < events.size() - 1; i ++) {
			for (int j = 0; j < events.size() - 1 - i; j++) {
				if (events.get(j).getTicketPrice() > events.get(j + 1).getTicketPrice()) {
					LiveEvent tempEvent = events.get(j);
					events.set(j, events.get(j + 1));
					events.set(j + 1, tempEvent);
				}
			}
		}
		return events;
	}
			
	public String toString() {
		String value = "Customer ID: " + this.userId+ "; username: " + this.username + "; name: " + this.name
		+ "; " + this.fullAddress.toString() + "; category of user: " + this.category.name().toLowerCase();
		return value;
	}
}
