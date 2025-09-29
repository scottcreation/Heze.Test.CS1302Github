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
	  * Displays details of selected task
	  * 
	  * @precondition taskList != null 
	  * @postcondition displayDesc and displayPrior show chosen task or thrown error
	  * @Throws numberFormatException
	  */
	 @FXML
	 public void displaySelectedTask() {
		 try {
			 Task selectedTask = this.taskList.getSelectionModel().getSelectedItem();
	
		        if (selectedTask == null) {
		        	Alert alert = new Alert(Alert.AlertType.ERROR);
					 alert.setTitle("Error");
					 alert.setHeaderText("No Selection");
					 alert.setContentText("Please choose a task from the list");
					 alert.showAndWait();   
		            return;
		        }
		        if (this.indexField == null) {
		        	Alert alert = new Alert(Alert.AlertType.ERROR);
		        	 alert.setTitle("Error");
					 alert.setHeaderText("Index Control Missing");
					 alert.setContentText("Index field is not available.");
					 alert.showAndWait();
	                return;
	            }
	            String idxText = this.indexField.getText();
	            if (idxText == null || idxText.isBlank()) {
	            	Alert alert = new Alert(Alert.AlertType.ERROR);
					 alert.setTitle("Error");
					 alert.setHeaderText("No Index");
					 alert.setContentText("Enter a task index (starting at 0)");
					 alert.showAndWait();
	                return;
	            }
	            int idx = Integer.parseInt(idxText.trim());
	            int size = this.taskList.getItems().size();
	            if (idx < 0 || idx >= size) {
	            	Alert alert = new Alert(Alert.AlertType.ERROR);
					 alert.setTitle("Error");
					 alert.setHeaderText("Out of Range");
					 alert.setContentText("Index must be between 0 and "+ (size - 1) + ".");
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
