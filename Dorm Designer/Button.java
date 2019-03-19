//
// Title:           Dorm Designer 5000
// Files:           Main.java, Furniture.java, CreateFurnitureButton.java, 
//						ClearButton.java, SaveButton.java, LoadButton.java, 
//						Button.java, DormGUI.java
// Course:          CS 300 Spring 2018
//
// Author:          Kimberly Inthavong
// Email:           inthavong@wisc.edu
// Lecturer's Name: Mouna Kacem
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class Button implements DormGUI{
	// Declare fields
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	protected PApplet processing;
	private float[] position;
	protected String label;
	 
	// Button Constructor
	public Button(float x, float y, PApplet processing){
		this.processing = processing;
		this.position = new float[]{x, y};
		this.label = "Button";
	}
	 
	public void update(){
		// Setting rectangle color,
		// Button changes grey tone depending if the mouse is over it
		if (isMouseOver()){
			processing.fill(100);
		} else {
			processing.fill(200);
		}
		
		// Create rectangle
		processing.rect((position[0] - (WIDTH/2)),
				(position[1] + (HEIGHT/2)),
				(position[0] + (WIDTH/2)),
				(position[1] - (HEIGHT/2)));
		
		// Setting text color
		processing.fill(0);
		
		// Create button text
		processing.text(label, position[0], position[1]);
		
	}
	
	// Prints a statement that the user is clicking
	// Classes that inherit the Button class will be overriding this method
	public void mouseDown(Furniture[] furniture){
		System.out.println("A button was pressed");
	}
	
	public void mouseUp(){
		// Does nothing when called
	}
	
	// Checks to see if the user is over the object
	public boolean isMouseOver(){
		if (processing.mouseX < (position[0] + 	WIDTH / 2)
				&& processing.mouseX > (position[0] - WIDTH / 2)
				&& processing.mouseY < (position[1] + HEIGHT / 2)
				&& processing.mouseY > (position[1] - HEIGHT / 2)) {
			return true;
		} else {
			return false;
		}
	}
	
}
