package View;

import java.util.ArrayList;

import edu.westga.cs1302.lab1.model.Bill;
import edu.westga.cs1302.lab1.model.BillItem;

public class Text {

	private ArrayList<BillItem> list;
	
	public Text(Bill listOfItems) {
	this.list = listOfItems.getBillList();
	
	
	}
	
	public ArrayList<BillItem> getBill(ArrayList<BillItem> bill){
		return this.list;
	}
	/** Return a String containing the list of bill items and total for the bill.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a String containing the list of bill items and total for the bill
	 */
	public String getText() {
		String text = "ITEMS" + System.lineSeparator();
		double subTotal = 0.0;
		for (BillItem item : this.list) {
			text += item.getName() + " - " + item.getAmount() + System.lineSeparator();
			subTotal += item.getAmount();
 		}
		
		text += System.lineSeparator();
		text += "SUBTOTAL - $" + subTotal + System.lineSeparator();
		double tax = subTotal * Bill.TAX_RATE;
		double tip = subTotal * Bill.TIP_RATE;
		text += "TAX - $" + tax + System.lineSeparator();
		text += "TIP - $" + tip + System.lineSeparator();
		text += "TOTAL - $" + (subTotal + tip + tax);
		
		return text;
	}

}
