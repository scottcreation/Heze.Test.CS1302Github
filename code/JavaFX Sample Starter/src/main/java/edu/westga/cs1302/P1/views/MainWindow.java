package edu.westga.cs1302.P1.views;

import edu.westga.cs1302.P1.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML
	 private TextField name;
	 private TextArea desc;
	 private ComboBox<String> pBox;
	 private ListView<Task> taskList;
	 
	@FXML
	private TextArea displayDesc;
	private TextField displayPrior;
	private TextField indexField;
	 
	 
	 
	 
	
    
  /**
     * Perform any needed initialization of UI components and underlying objects.
     */
	 @FXML
    public void initialize() {
		 this.pBox.getItems().clear();
		 this.pBox.getItems().add("LOW");
		 this.pBox.getItems().add("MEDIUM");
		 this.pBox.getItems().add("HIGH");
		 this.pBox.setValue("MEDIUM");
		 
		 if (this.indexField != null) {
		        this.indexField.setOnAction(e -> this.handleRemoveTask());
		    }
    }
	 
	 
	 /**
	 * Handles clicking of Add Task button
	 * 
	 *  @precondition name != null && desc != null && pBox != null && taskList != null
	 *  @postcondition new Task is put into the list 
	 *  @throws IllegalArgumentException
	 */
	 
	 @FXML
	 public void addTask() {
		 try {
			 String givenName = this.name.getText();
			 String givenDesc = this.desc.getText();
			 String givenPrio = this.pBox.getValue();
			 
			 if (this.desc == null) {
				 givenDesc = "";
			 }
		 
		 Task newTask = new Task(givenName, givenDesc, givenPrio);
		 
		 this.taskList.getItems().add(newTask);
		 
		 this.desc.clear();
		 this.name.clear();
		 this.pBox.setValue("MEDIUM");
		 } catch (IllegalArgumentException error) {
			 Alert alert = new Alert(Alert.AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Invalid Value for Task");
			 alert.setContentText("name, description or Priority for task is invalid");
			 alert.showAndWait();
		 }		 
	 }
	 /**
	 * Allows for removal of task at the typed index
	 * 
	 * @precondition indexField available and index to be removed is selected
	 * @precondition the task at the given index is removed; description and priority displayed are cleared
	 * @throws NumberFormatException
	 */
	 
	 @FXML
	 public void handleRemoveTask() {
	     try {
	         String idxText = this.indexField.getText();
	         if (idxText == null || idxText.isBlank()) {
	        	 Alert alert = new Alert(Alert.AlertType.ERROR);
				 alert.setTitle("Error");
				 alert.setHeaderText("No Index");
				 alert.setContentText("Enter a task index (starting at 0).");
				 alert.showAndWait();
	             return;
	         }
	         int idx = Integer.parseInt(idxText.trim());
	         int size = this.taskList.getItems().size();
	         if (idx < 0 || idx >= size) {
	        	 Alert alert = new Alert(Alert.AlertType.ERROR);
				 alert.setTitle("Error");
				 alert.setHeaderText("Out of Range");
				 alert.setContentText("Index must be between 0 and " + (size - 1) + ".");
				 alert.showAndWait();
	             return;
	         }
	         this.taskList.getItems().remove(idx);
	         this.displayDesc.clear();
	         this.displayPrior.clear();
	     } catch (NumberFormatException numberError) {
	    	 Alert alert = new Alert(Alert.AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Invalid Index");
			 alert.setContentText("Index must be a valid number");
			 alert.showAndWait(); 
	     }
	 }
	 /**
	  * Displays details of selected task
	  * 
	  * @precondition taskList != null 
	  * @postcondition displayDesc and displayPrior show chosen task or thrown error
	  * @Throws numberFormatException
	  */
	 
	 @FXML
	 public void displaySelectedTask() {
		 try {
			 Task selectedTask = this.getTaskByIndexFromProject();
	
		        if (selectedTask == null) {
		        	Alert alert = new Alert(Alert.AlertType.ERROR);
					 alert.setTitle("Error");
					 alert.setHeaderText("No Selection");
					 alert.setContentText("Please choose a task from the list");
					 alert.showAndWait();   
		            return;
		        } 
		     
			 this.populateSelected(selectedTask);
			 } catch (NumberFormatException numberException) {
			 Alert alert = new Alert(Alert.AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Invalid Value");
			 alert.setContentText("Please choose a valid value");
			 alert.showAndWait(); 
		 }
	 }
	 /**
	     * Update the description of the task at the index given in {@code indexField} (3.C).
	     * The new description is read from {@code dispDesc} (per Part 3.A).
	     *
	     * @precondition indexField contains a valid index; dispDesc not null
	     * @postcondition the chosen Task's description equals dispDesc.getText(), or an alert is shown
	     */
	 
	 @FXML
	    public void handleUpdateDescription() {
	        try {
	            Task selected = this.getTaskByIndexFromProject();
	            if (selected == null) {
	                return;
	            }

	            String newDesc = this.displayDesc.getText();
	            if (newDesc == null) {
	            	Alert alert = new Alert(Alert.AlertType.ERROR);
	   			 alert.setTitle("Error");
	   			 alert.setHeaderText("Invalid Description");
	   			 alert.setContentText("Description cannot be null.");
	   			 alert.showAndWait();
	                return;
	            }

	            selected.setDescription(newDesc);
	            this.populateSelected(selected);

	        } catch (NumberFormatException numberError) {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
				 alert.setTitle("Error");
				 alert.setHeaderText("Invalid Index");
				 alert.setContentText("Please enter a valid whole number.");
				 alert.showAndWait();
	        } catch (IllegalArgumentException error) {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
				 alert.setTitle("Error");
				 alert.setHeaderText("Invalid Description");
				 alert.setContentText("Please enter a valid description");
				 alert.showAndWait();
	        }
	    }
	 /**
	     * Helper to read a Task from the list using the number in indexField.
	     *
	     * @precondition indexField not null; taskList available
	     * @postcondition returns the Task if found; otherwise shows an alert and returns null
	     * @return the task at index, or null if invalid
	     */
	
	 private Task getTaskByIndexFromProject() {
	 if (this.indexField == null) {
     	Alert alert = new Alert(Alert.AlertType.ERROR);
     	 alert.setTitle("Error");
			 alert.setHeaderText("Index Control Missing");
			 alert.setContentText("Index field is not available.");
			 alert.showAndWait();
         return null;
     }
     String idxText = this.indexField.getText();
     if (idxText == null || idxText.isBlank()) {
     	Alert alert = new Alert(Alert.AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("No Index");
			 alert.setContentText("Enter a task index (starting at 0)");
			 alert.showAndWait();
         return null;
     }
     int idx = Integer.parseInt(idxText.trim());
     int size = this.taskList.getItems().size();
     if (idx < 0 || idx >= size) {
     	Alert alert = new Alert(Alert.AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Out of Range");
			 alert.setContentText("Index must be between 0 and " + (size - 1) + ".");
			 alert.showAndWait();
         return null;
     }
     return this.taskList.getItems().get(idx);
	}
		/**
		     * Populate the read-only task details.
		     *
		     * @precondition displayDesc != null && dispPrior != null
		     * @postcondition displayDesc/displayPrior show task or clear null
		     * @param task the task to show clears fields if null
		     */
		    
	 private void populateSelected(Task task) {
		        if (task == null) {
		            this.displayDesc.clear();
		            this.displayPrior.clear();
		            return;
		        }
		        this.displayDesc.setText(task.getDescription());
		        this.displayPrior.setText(task.getPriority());
		    }	
}
