package edu.westga.cs1302.lab2.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

class TestBillItemConstructor {

	@Test
	void testAddEmptyBillItemName() {
		Bill bill = new Bill();
		BillItem item1 = new BillItem("",1);
		
		bill.addItem(item1);
		assertEquals(bill.getItems().get(0), item1);
	}

	@Test
	void testOneBelowMin() {
		assertThrows(IllegalArgumentException.class,() -> {
			new BillItem("Prop", -1);
		});
	}
	
	@Test
	void testAtMin() {
		BillItem item1 = new BillItem("Prop",1);
		assertEquals(1,item1.getAmount());
		
	}
	
	@Test
	void testOneAboveMin() {
		BillItem item1 = new BillItem("Prop",2);
		assertEquals(2,item1.getAmount());
		
	}
}
