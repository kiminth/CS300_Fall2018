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
public class Furniture implements DormGUI{

	private PApplet processing;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;
	private String furnitureType;
	
	// Constructor initializes the fields of a new bed object positioned in the center of the display
	public Furniture(String furnitureType, PApplet processing) { 
		this.processing = processing;
		this.position = new float[]{processing.width/2, processing.height/2};
		this.isDragging = false;
		this.rotations = 0;
		this.furnitureType = furnitureType;
		this.image = processing.loadImage("images/" + furnitureType + ".png");
		
	}
	
	// draws this furniture at its current position
	public void update() {
		// Drags the furniture; the position of the bed
		// follows the position of the mouse
		if (isDragging){
			position[0] = processing.mouseX;
			position[1] = processing.mouseY;
		}
		
		processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
		
	}
	
	
	// used to start dragging the furniture, when the mouse is over this bed when it is pressed
	public void mouseDown(Furniture[] furniture) { 		
		if (isMouseOver()) {
			isDragging = true;		
		}
			
	}
	// used to indicate that the furniture is no longer being dragged
	public void mouseUp() { 
		isDragging = false;
	}
	
	// helper method to determine whether the mouse is currently over this furniture
	public boolean isMouseOver() { 
		
		// if the furniture is horizontal
		if ((rotations % 2) == 0){
			if (processing.mouseX < (position[0] + image.width / 2)
					&& processing.mouseX > (position[0] - image.width / 2)
					&& processing.mouseY < (position[1] + image.height / 2)
					&& processing.mouseY > (position[1] - image.height / 2)) {
				return true;
			} else {
				return false;
			}
			
		} else {
			
		// if the furniture is vertical	
			if (processing.mouseX < (position[0] + image.height / 2)
					&& processing.mouseX > (position[0] - image.height / 2)
					&& processing.mouseY < (position[1] + image.width / 2)
					&& processing.mouseY > (position[1] - image.width / 2)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	// Changes the orientation of the furniture by incrementing 
	// the rotation 
	public void rotate() {
		rotations += 1;
	}
	
	// Accessors/Getters and Mutators/Setters
	// Access Furniture Type
	public String getFurnitureType() {
		return furnitureType;
	}
	// Access Furniture Position
	public float[] getFurniturePosition() {
		return position;
	}
	// Access Furniture Orientation
	public int getFurnitureOrientation(){
		return rotations;
	}
	// Modify furniture position	
	public void setFurniturePosition(float x, float y) {
		this.position = new float[]{x, y};
	}
	// Modify furniture orientation
	public void setFurnitureOrientation(int rotations){
		this.rotations = rotations;
	}
}
