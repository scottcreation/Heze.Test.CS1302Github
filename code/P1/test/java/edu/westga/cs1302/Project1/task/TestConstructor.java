package edu.westga.cs1302.Project1.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.P1.model.Task;

class TestTask {
    @Test
    public void TestTaskAllVaildParameters() {
        Task t = new Task("Task 1", "new Task", "MEDIUM");
        assertEquals("Task 1", t.getName());
        assertEquals("new Task", t.getDescription());
        assertEquals("MEDIUM", t.getPriority());
        assertEquals("Task 1", t.toString());
    }

    @Test
    public void TestWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "desc", "LOW"));
    }

    @Test
    public void TestWhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Task("   ", "desc", "HIGH"));
    }

    @Test
    public void TestWhenDescriptionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Task("name", null, "LOW"));
    }

    @Test
    public void TestWhenPriorityIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Task("name", "desc", null));
    }

    @Test
    public void TestWhenPriorityIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Task("name", "desc", "   "));
    }

    @Test
    public void TestChangeInDescription() {
        Task t = new Task("name", "desc1", "HIGH");
        t.setDescription("desc2");
        assertEquals("desc2", t.getDescription());
    }

    @Test
    public void TestChangeInDescriptionToNull() {
        Task t = new Task("name", "desc1", "HIGH");
        assertThrows(IllegalArgumentException.class, () -> t.setDescription(null));
    }
	

}
