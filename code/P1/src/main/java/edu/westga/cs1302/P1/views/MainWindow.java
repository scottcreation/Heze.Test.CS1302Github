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
	 private ComboBox<String>pBox;
	 private ListView<Task> taskList;
	 
	 
	 
	 
	
    
    /**
     * Perform any needed initialization of UI components and underlying objects.
     */
	 @FXML
    public void initialize() {
		 pBox.getItems().clear();
		 pBox.getItems().add("LOW");
		 pBox.getItems().add("MEDIUM");
		 pBox.getItems().add("HIGH");
		 pBox.setValue("MEDIUM");
		 
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
			 
			 if (desc == null) {
				 givenDesc = "";
			 }
		 
		 Task newTask = new Task(givenName, givenDesc, givenPrio);
		 
		 this.taskList.getItems().add(newTask);
		 
		 this.desc.clear();
		 this.name.clear();
		 this.pBox.setValue("MEDIUM");
		 } catch(IllegalArgumentException error) {
			 Alert alert = new Alert(Alert.AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Invalid Value for Task");
			 alert.setContentText("name, description or Priority for task is invalid");
			 alert.showAndWait();
		 }		 
	 }
	 
	
}
