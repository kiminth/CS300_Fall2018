import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
public class LoadButton extends Button implements DormGUI{
	
	// Declare fields
	private File file = new File("RoomData.ddd");
	private Scanner scnr;

	// Constructor
	public LoadButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		super.label = "Load";

	}

	@Override
	public void mouseDown(Furniture[] furniture) {
		// loads the furniture array with the previously 
		// saved furniture from the file

		// Initialize variables
		int fCount = 0;
		String temp = "";
		String[] furnitureString = new String[4];
		

		// Clear current furniture array
		for (int i = 0; i < furniture.length; i++) {
			if (furniture[i] != null) {
				furniture[i] = null;
			}
		}
		
		
		try {
			this.scnr = new Scanner(file);

			if (isMouseOver()) {

				// Read the file to obtain necessary information
				// to create the saved furniture
				while (scnr.hasNextLine()) {
					temp = scnr.nextLine();
					if (temp.length() > 0) {
						
						furnitureString = temp.split(",|:");
						
						// Check to see if a furniture name is not present or is not proceeded by a colon 
						// and then three comma separated numbers
						if (furnitureString.length < 4){
							System.out.println("WARNING: Found incorrectly formatted line in file: " 
									+ temp);
							continue;
						}
						
						// Check to see that the furniture contains a type 
						// and that the image type is available 
						
						File tempFile = new File("images/" +  furnitureString[0].trim() + ".png");
						if (!tempFile.exists()){
							System.out.println("WARNING: Could not find an image for a furniture object of type: "
									+ furnitureString[0].trim());
							continue;
						}
						
						// Create new furniture object to be stored in array
						furniture[fCount] = new Furniture(furnitureString[0].trim(), processing);
						
						// Set furniture's position 
						furniture[fCount].setFurniturePosition(Float.parseFloat(furnitureString[1].trim()),
								Float.parseFloat(furnitureString[2].trim()));
						
						// Set furniture's orientation 
						furniture[fCount].setFurnitureOrientation((int) Float.parseFloat(furnitureString[3].trim()));

						fCount++;
						
						// If the number of furniture is more than the furniture array
						// throw an exception
						if (fCount > furniture.length){
							throw new IndexOutOfBoundsException();
						}

					}

				}

			}

		} catch (FileNotFoundException e) {
			// If file does not exist, print warning statement
			System.out.println("WARNING: Could not load room contents from file " + file + ".");

		} catch (IndexOutOfBoundsException i){
			System.out.println("WARNING: Unable to load more furniture.");
		}

	}
	
}
