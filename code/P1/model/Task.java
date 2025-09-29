	package edu.westga.cs1302.P1.model;

	/**
 	* Allows for creation of a task with name, description and priority
 	* 
 	* @author Hezekiah Scott
 	* @version I don't know in all honesty
 	*/
	public class Task {

	private final String name;
	private final String priority;
	private String description;
	
	/**
 	* 
 	* 
 	* Creates A New Task 
 	* 
 	* @precondition name != null && !name.isBlank() 
 	*    && description != null && priority != null 
 	*    && !priority.isBlank()
 	*  
 	* @postcondition getName().equals(name) &&
 	* 				  getDescription().equals(description) &&
 	* 				  getPriority().equals(priority)
 	* 
 	* @param name the task name
 	* @param description the task description
 	* @param priority the task priority ("LOW", "MEDIUM", or "HIGH")
 	* @throws IllegalArgumentException if name, description or priority are null or if blank/empty
 	* 
 	*/
	
	public Task(String name, String description, String priority) {
	        if (name == null || name.isBlank()) {
	            throw new IllegalArgumentException("name must not be null or blank");
	        }
	        if (description == null) {
	            throw new IllegalArgumentException("description must not be null");
	        }
	        if (priority == null || priority.isBlank()) {
	            throw new IllegalArgumentException("priority must not be null or blank");
	        }
	        this.name = name;
	        this.description = description;
	        this.priority = priority;
	    }
	  
	  /**
	     * Gets the task name.
	     *
	     * @precondition none
	     * @postcondition none
	     * @return the name
	   */
	  public String getName() {
	        return this.name;
	    }

	    /**
	     * Gets the task description.
	     *
	     * @precondition none
	     * @postcondition none
	     * @return the description
	     */
	    public String getDescription() {
	        return this.description;
	    }

	    /**
	     * Updates the task description.
	     *
	     * @precondition description != null
	     * @postcondition getDescription().equals(description)
	     * @param description the new description
	     * @throws IllegalArgumentException if description is null
	     */
	    public void setDescription(String description) {
	        if (description == null) {
	            throw new IllegalArgumentException("description must not be null");
	        }
	        this.description = description;
	    }

	    /**
	     * Gets the task priority.
	     *
	     * @precondition none
	     * @postcondition none
	     * @return the priority
	     */
	    public String getPriority() {
	        return this.priority;
	    }

	    /**
	     * Returns the task name as a string.
	     *
	     * @precondition none
	     * @postcondition none
	     * @return the task name
	     */
	    public String toString() {
	        return this.name;
	    }
}
