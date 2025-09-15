package edu.westga.cs1302.bill.model;


/**
 * Class for calculating subtotal, tax, tip, and total 
 * for an array of BillItem Objects
 * 
 * 
 * @author Hezekiah Scott
 * 
 */
public class BillCalculator {
	
	
	private static final double TAX_RATE = 0.07;
	private static final double TIP_RATE = 0.15;
	
	/** 
	 * Calculates subtotal of all given bill items
	 * 
	 * @precondition item != null
	 * @postcondition none
	 * 
	 * 
	 * @param items- an array containing all billed items
	 * @return the subtotal of all items
	 */
	public static double calculateSubtotal(BillItem[] items) {
		if (items == null) {
			throw new IllegalArgumentException("Array can't be null");
		}
		double subtotal = 0.0;
		
		for (BillItem item: items) {
			if (item == null) {
				throw new IllegalArgumentException("Item can't be null");
			}
			subtotal += item.getAmount();
			
		}
		return subtotal;
	}
	
	/** 
	 * Calculates tax of all given bill items
	 * 
	 * @precondition item != null
	 * @postcondition none
	 * 
	 * 
	 * @param items- an array containing all billed items
	 * @return the tax amount of all items
	 */
	public static double calculateTax(BillItem[] items) {
		double tax =  TAX_RATE;
		double taxValue = calculateSubtotal(items) * tax;
		
		return taxValue;
		
	}
	/** 
	 * Calculates tip amount based on all given bill items
	 * 
	 * @precondition item != null
	 * @postcondition none
	 * 
	 * 
	 * @param items- an array containing all billed items
	 * @return the tip amount of bill based on billed items
	 */
	public static double calculateTip(BillItem[] items) {
		double tip =  TIP_RATE;
		double tipValue = calculateSubtotal(items) * tip;
		
		return tipValue;
	}
	
	public static double calculateTotal(BillItem[] items) {
		double tax = calculateTax(items);
		double tip = calculateTip(items);
		double subTotal = calculateSubtotal(items);
		
		return subTotal + tax + tip;
	}
	
}
