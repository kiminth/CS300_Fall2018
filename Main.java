//
// Title:           Dorm Designer 2000
// Files:           Main.java
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
public class Main {

	public static void main(String[] args) {
		// Yay build path works woo
		Utility.startApplication();
		
	}
	

	public static void setup(Data data) {
		
		// Place background color of window
		data.processing.background(100,150,250);

		// Store background image into a data field
		data.backgroundImage = data.processing.loadImage("images/background.png");
		
		// Store bed image into data field (so that update has access through Data data)
		data.bedImage = data.processing.loadImage("images/bed.png");
		
		// Create an array to store bed positions, with null references to undefined beds
		data.bedPositions = new float[6][];
		
		// Initialize the drag bed index
		data.dragBedIndex = -1;

	}
	
	
	public static void update(Data data) {
		
		// Declare variables
		float[] temp;
		int i = 0;
		
		// Place background color of window
		data.processing.background(100,150,250);
		
		// Place Room Background image
		data.processing.image(data.backgroundImage, data.processing.width/2, data.processing.height/2);
	
		// Prints all non-null positions of beds into the room
		while (i < data.bedPositions.length){
			if (data.bedPositions[i] != null){
				temp = data.bedPositions[i];
				data.processing.image(data.bedImage, temp[0], temp[1]);
			}
			
			i += 1;
			
		}
		
		// Checks to see if the bed is being selected (via mouseDown/mouseUp) 
		// If a bed is being selected, update the bed position to correspond with the position of the mouse
		if (data.dragBedIndex != -1){
			data.bedPositions[data.dragBedIndex][0] = data.processing.mouseX;
			data.bedPositions[data.dragBedIndex][1] = data.processing.mouseY;
		}
				
	}
	
	public static void mouseDown(Data data) {
		int j = 0;
		
		// Check if user selected a bed while mouse is down (mouse button is pressed/held)
		for (j = 0; j < data.bedPositions.length; j++){
			if (data.bedPositions[j] != null){				
				if (data.processing.mouseX < (data.bedPositions[j][0] + data.bedImage.width / 2)
						&& data.processing.mouseX > (data.bedPositions[j][0] - data.bedImage.width / 2)
						&& data.processing.mouseY < (data.bedPositions[j][1] + data.bedImage.height / 2)
						&& data.processing.mouseY > (data.bedPositions[j][1] - data.bedImage.height / 2)) {
					data.dragBedIndex = j;
					break;
				} else {
					data.dragBedIndex = -1;
				}
			} 
			
		}
		
		
	}
	
	public static void mouseUp(Data data) {
		// When the mouse is released, the bed position will stop corresponding with the position of the mouse
		// so the bed will only be moved by the user while the user is holding down the mouse
		data.dragBedIndex = -1;
	}
	
	
	public static void keyPressed(Data data) {
		
		// If "B" is pressed, a new bed is added to the bedPositions array to the lowest index null reference
		if (data.processing.key == 'b' || data.processing.key == 'B'){
			for (int j = 0; j < data.bedPositions.length; j++){ 
				if (data.bedPositions[j] == null){
					data.bedPositions[j] = new float[] {data.processing.width/2, data.processing.height/2};
					break;
				}
			}
				
		}
	}
	}


