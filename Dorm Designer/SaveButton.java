import java.io.FileNotFoundException;
import java.io.PrintWriter;

//
//Title:           Dorm Designer 5000
//Files:           Main.java, Furniture.java, CreateFurnitureButton.java, 
//						ClearButton.java, SaveButton.java, LoadButton.java, 
//						Button.java, DormGUI.java
//Course:          CS 300 Spring 2018
//
//Author:          Kimberly Inthavong
//Email:           inthavong@wisc.edu
//Lecturer's Name: Mouna Kacem
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates 
//strangers, etc do.  If you received no outside help from either type of 
//source, then please explicitly indicate NONE.
//
//Persons:         NONE
//Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class SaveButton extends Button implements DormGUI{

	// Declare fields
	private PrintWriter fileOut ;
	
	// Constructor
	public SaveButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		super.label = "Save";
		
	}
	
	@Override
	public void mouseDown(Furniture[] furniture) { 
		
		// Declare variables 
		String furnitureInfo = "";
		
		// If the button is pressed, save all the furniture information
		// (type, xposition, yposition, and rotation) into the 
		// file (RoomData.ddd)
		if (isMouseOver()){
			
			try {
				// Create PrintWriter
				this.fileOut = new PrintWriter("RoomData.ddd");
			
				// Compile all furniture information from the 
				// Furniture[] into a String
				for (int i = 0; i < furniture.length; i++){
					if (furniture[i] != null){
						furnitureInfo += furniture[i].getFurnitureType() + ": ";
						furnitureInfo += furniture[i].getFurniturePosition()[0] + ", ";
						furnitureInfo += furniture[i].getFurniturePosition()[1] + ", ";
						furnitureInfo += furniture[i].getFurnitureOrientation() + "\n";
					}
				}
				
				// Write out the furniture information into the file
				fileOut.println(furnitureInfo);
				
				// Close PrintWriter
				fileOut.close();
			
			} catch (FileNotFoundException e) {
				// If file does not exist, print warning statement
				System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
			}
			
		}

	} 
	
}
