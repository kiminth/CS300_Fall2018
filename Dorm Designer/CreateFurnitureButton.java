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
public class CreateFurnitureButton extends Button{
	private String type;
	
	public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
		super(x, y, processing);
		this.type = type;
		super.label = "Create " + type;
	}
	
	@Override
	public void mouseDown(Furniture[] furniture) {
		if (isMouseOver()){
			for (int j = 0; j < furniture.length; j++){ 
				if (furniture[j] == null){
					furniture[j] = new Furniture(type.toLowerCase(), processing);
					break;
				}
			}
		}
	} 
	
}
