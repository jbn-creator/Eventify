import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
public class Basket {
	private ArrayList<LiveEvent> elements;
	public Basket() {
		this.elements = new ArrayList<LiveEvent>();
	}
	
	
	public void addToBasket(LiveEvent addMe) {
		elements.add(addMe);
		System.out.println("The event: " + addMe.getEventName() + " was successfully added to your basket!");
	}
	
	public void deleteBasket() {
		elements.clear();
	}
	
	public String viewBasket() {
		String returnValue = "";
		if (this.elements.isEmpty()) {
			returnValue += "You're Basket is empty";
		}
		else {
			System.out.println("Here is your basket: ");
			for (LiveEvent event : this.elements) {
				if (event.getEventCategory().ordinal() == 0) {
					Music musicElement = (Music) event;
					returnValue += musicElement.toString();	
					returnValue += "\n\n";
				}
				else {
					Performance performanceElement = (Performance) event;
					returnValue += performanceElement.toString();	
					returnValue += "\n\n";
				}
			}
		}
		return returnValue;
	}
	
	public ArrayList<LiveEvent> getBasketElements(){
		return this.elements;
	}
		
	public boolean basketContainsSoldOutElements() {
        for (int i = 0; i < elements.size(); i++) {
            LiveEvent current = elements.get(i);

            // Skip if this eventID was already checked earlier in the list
            boolean seenBefore = false;
            for (int j = 0; j < i; j++) {
                if (elements.get(j).getEventID() == current.getEventID()) {
                    seenBefore = true;
                    break;
                }
            }
            if (seenBefore) {
                continue;
            }

            // Counting how many times this event appears in the basket
            // starts at 1 because the checking happens before we add the event (see CustomerFrame.java line 212)
            int requestedCount = 1; 
            for (LiveEvent e : elements) {
                if (e.getEventID() == current.getEventID()) {
                    requestedCount++;
                }
            }

            // If there is more requested than what's in the stock, return false
            if (requestedCount > current.getQuantityInStock()) {
                return true;
            }
        }


        return false;
	  
	}
	public double getAmount() {
		double sum = 0;
		for (LiveEvent events: this.getBasketElements()) {
			sum += events.getTicketPrice();
		}
		return sum;
	}
	
	public void stockUpdate(String eventsList) throws IOException {
		try {				
        	
	    //this code is responsible for updating the stock
		//first we include each value in the stock in the inputBuffer variable
		// we also store the previous elements of the basket in an array called oldElementsBasket for easier replacement
        BufferedReader file = new BufferedReader(new FileReader(eventsList));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        String[] oldElementsBasket = new String[this.elements.size()];
        int i = 0;
        while ((line = file.readLine()) != null) {
      
        	//populating oldElementsBasket and inputBuffer
        	inputBuffer.append(line);
            inputBuffer.append('\n');
            String[] stockElement = line.split(",");
        	for (LiveEvent evt : this.elements) {
        		if (Integer.parseInt(stockElement[0]) == evt.getEventID()) {
        			oldElementsBasket[i] = line;
        			i++;
        			
        		}
    		}
            
        }
        file.close();
        String inputStr = inputBuffer.toString();

        //now this part aims to update the number of tickets of each elements in the stock
		for (LiveEvent evt : this.elements) {
			int newQuantity = evt.getQuantityInStock() - 1;
			evt.setQuantityInStock(newQuantity);
		}
			
		//replacing elements of the oldBasket with updated elements's stock 
    	for (int j = 0; j< oldElementsBasket.length; j++) {
    		if (this.elements.get(j).getEventCategory().ordinal() == 0) {
				Music msc = (Music)this.elements.get(j);
    			inputStr = inputStr.replace(oldElementsBasket[j].strip(), msc.toStringTextFile());
  
    		}else {
    			Performance perf = (Performance)this.elements.get(j);
    			inputStr = inputStr.replace(oldElementsBasket[j].strip(), perf.toStringTextFile());
    	
    		}
    	}
    	
    	// now we write in the file the new values
        FileOutputStream fileOut = new FileOutputStream(eventsList);
        fileOut.write(inputStr.getBytes());
        fileOut.close();
        
		return;
		}catch(IOException e1) {
			System.out.println("Error: " + e1);
		}
		
	}
}
