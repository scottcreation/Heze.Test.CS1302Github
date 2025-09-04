package edu.westga.cs1302.lab2.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

class TestAddItem {

	@Test
	void testAddNullItem() {
		Bill bill = new Bill();
		
		assertThrows(IllegalArgumentException.class, () -> {
			bill.addItem(new BillItem("", 0));
		});
	}	
	}

