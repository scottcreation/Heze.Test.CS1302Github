package edu.westga.cs1302.bill.view;


import java.util.ArrayList;
import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

/** Supports displaying the information contained in a Bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class BillView {

	/** Return a String containing the list of bill items and total for the bill.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param bill the bill to be viewed
	 * 
	 * @return a String containing the list of bill items and total for the bill
	 */
	public static String getText(Bill bill) {
		String text = "ITEMS" + System.lineSeparator();
		
		 BillItem[] itemArray = new BillItem[bill.getItems().size()];
	        for (int i = 0; i < bill.getItems().size(); i++) {
	            itemArray[i] = bill.getItems().get(i);
	        }
		
		for (BillItem item : itemArray) {
			text += item.getName() + " - " + item.getAmount() + System.lineSeparator();
		}
		double subTotal = BillCalculator.calculateSubtotal(itemArray);
		double tax = BillCalculator.calculateTax(itemArray);
		double tip = BillCalculator.calculateTip(itemArray);
		double total = BillCalculator.calculateTotal(itemArray);
		
		text += System.lineSeparator();
		text += "SUBTOTAL - $" + subTotal + System.lineSeparator();
		text += "TAX - $" + BillView.roundToNearestHundredth(tax) + System.lineSeparator();
		text += "TIP - $" + BillView.roundToNearestHundredth(tip) + System.lineSeparator();
		text += "TOTAL - $" + BillView.roundToNearestHundredth(total);
		
		return text;
	}
	
	private static double roundToNearestHundredth(double value) {
		return (int) (value * 100) / 100.0;
	}
}
