import java.util.ArrayList;

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
public class Main {
	
	private PApplet processing;
	private PImage backgroundImage;
	private ArrayList<DormGUI> guiObjects;
	
	// Max number of furniture that LoadButton will be allowed to load at once.    
	private final static int MAX_LOAD_FURNITURE = 100; 

	
	// Constructor
	public Main(PApplet processing) {
		
		// Initialize parameters
		this.processing = processing;
		this.backgroundImage = processing.loadImage("images/background.png");				
		this.guiObjects = new ArrayList<DormGUI>();
		
		// Add button objects
		guiObjects.add(new CreateFurnitureButton("Bed", 50, 24, processing));
		guiObjects.add(new CreateFurnitureButton ("Sofa", 150, 24, processing));
		guiObjects.add(new CreateFurnitureButton("Dresser", 250, 24 , processing));
		guiObjects.add(new CreateFurnitureButton ("Desk", 350, 24, processing));
		guiObjects.add(new CreateFurnitureButton("Sink", 450, 24 , processing));
		guiObjects.add(new ClearButton (550, 24, processing));
		guiObjects.add(new SaveButton (650, 24, processing));
		guiObjects.add(new LoadButton (750, 24, processing));
				
	}

	public static void main(String[] args) {
		Utility.startApplication();
		
	}
	

	/**
	 * This method is constantly being called in order to update the
	 * appearence of the program
	 * 
	 */
	public void update() {
		
		// Place background color of window
		processing.background(100, 150, 250);
		
		// Place Room Background image
		processing.image(backgroundImage, processing.width/2, processing.height/2);
	
		// Calls the update function of all guiObjects
		for (int i = 0; i < guiObjects.size(); i++){
			guiObjects.get(i).update();
		}
				
	}
	

	/**
	 * This method is called whenever the user clicks,
	 * calling the MouseDown() methods of each object in the guiObjects
	 * ArrayList.
	 * 
	 */
	public void mouseDown() {	
		// Declare temporary object array to store furniture
		Furniture[] tempFurniture;
		
		// Calls the mouseDown method of guiObject the user is clicking
		for (int j = 0; j < guiObjects.size(); j++){
			if (guiObjects.get(j).isMouseOver()){
				tempFurniture = extractFurnitureFromGUIObjects();	
				
				// If it is a furniture, invoke the mouse down of furniture, only moving 
				// one furniture a time (instead of moving multiple at once)
				if (guiObjects.get(j) instanceof Furniture){
					guiObjects.get(j).mouseDown(tempFurniture);
					break;
					
				// If it is not a furniture, invoke it's mouse down method
				} else {
					guiObjects.get(j).mouseDown(tempFurniture);		
					replaceFurnitureInGUIObjects(tempFurniture);
				}				
				
			}
		}
		
		
	}
	

	/**
	 * This method is called whenever the user's mouse is released, 
	 * calling the mouseUp() methods of each object in the guiObjects 
	 * ArrayList.
	 * 
	 */
	public void mouseUp() {		
		for (int j = 0; j < guiObjects.size(); j++){
			guiObjects.get(j).mouseUp();
		}
	}
	
	/**
	 * This method is called whenever the user presses a key,
	 * and modifies the corresponding furniture object depending on what
	 * key is pressed (D for delete, R for rotate)
	 * 
	 */	
	public void keyPressed() {
		
		// If "D" is pressed, the furniture object the user is hovering over will be 'deleted',
		// removing the object from the guiObjects array list
		if (processing.key == 'd' || processing.key == 'D'){
			for (int k = 0; k < guiObjects.size(); k++){ 
				if (guiObjects.get(k) instanceof Furniture
						&& guiObjects.get(k).isMouseOver()){
					guiObjects.remove(k);
					break;
				}
			}
				
		}
		
		// If "R" is pressed, the furniture will rotate
		if (processing.key == 'r' || processing.key == 'R'){
			for (int j = 0; j < guiObjects.size(); j++){ 
				if (guiObjects.get(j) instanceof Furniture
						&& guiObjects.get(j).isMouseOver()){
					((Furniture) guiObjects.get(j)).rotate();
					break;
				}
			}
				
		}
	}

	/**
	 * This method creates a new Furniture[] for the old mouseDown() methods
	 * to make use of.  It does so by copying all Furniture references from
	 * this.guiObjects into a temporary array of size MAX_LOAD_FURNITURE.
	 * @return that array of Furniture references.
	 */
	private Furniture[] extractFurnitureFromGUIObjects() {
	    Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
	    int nextFreeIndex = 0;
	    for(int i=0;i<guiObjects.size() && nextFreeIndex < furniture.length;i++)
	        if(guiObjects.get(i) instanceof Furniture)
	            furniture[nextFreeIndex++] = (Furniture)guiObjects.get(i);        
	    return furniture;        
	}    
	
	/**
	 * This method first removes all Furniture references from this.guiObjects,
	 * and then adds back in all of the non-null references from it's parameter.
	 * @param furniture contains the only furniture that will be left in 
	 *   this.guiObjects after this method is invoked (null references ignored).
	 */
	private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
	    for(int i=0;i<guiObjects.size();i++)
	        if(guiObjects.get(i) instanceof Furniture)
	            guiObjects.remove(i--);
	    for(int i=0;i<furniture.length;i++)
	        if(furniture[i] != null)
	            guiObjects.add(furniture[i]);
	}
}


