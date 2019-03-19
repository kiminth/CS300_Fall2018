//
// Title:           Cheese Eater
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
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
			
		// Declare and Initialize variables
		int numberOfWalls = 20;
		char[][] room = new char[10][20];
		Random randGen = new Random();
		int[][] cheesePositions = new int[10][2];
		int mouseX = 0;
		int mouseY = 0;
		int mouseCount = 0;
		char move;
		int[] mouseMove = new int[2];
		int simRun;
		int simRunCount = 0;
		int cheeseEaten = 0;
		
		// Create Scanner object 
		Scanner input = new Scanner(System.in);
		
		// Invoke method to place walls
		placeWalls(room, numberOfWalls, randGen);
		
		// Invoke method to place cheese
		placeCheeses(cheesePositions, room, randGen);
		
		// Place Mouse into initial random position
		while (mouseCount < 1){
			mouseX = randGen.nextInt(room[0].length);
			mouseY = randGen.nextInt(room.length);
			if (room[mouseY][mouseX] != '#' ||
					room[mouseY][mouseX] != '%'){
				room[mouseY][mouseX] = '@';	
				mouseCount += 1;
			}
		}
		
		// Welcome message for user
		System.out.print("Welcome to the Cheese Eater simulation." + "\n"
				+ "==================================================" + "\n");
		
		// Prompt user to input the number of steps the sumulation should be run
		System.out.print("Enter the number of steps for this simulation to run: ");
		simRun = input.nextInt();
		
		System.out.println("\nThe mouse has eaten " + cheeseEaten + " cheese!");
		
		// Display room
		printRoom(room, cheesePositions, mouseX, mouseY);
		
		
		mouseMove[0] = mouseX;
		mouseMove[1] = mouseY;
		
		while (simRunCount < simRun) {	
			

			// Prompt the user for what direction they want the mouse to move
			System.out.print("Enter the next step you'd like the mouse to take (WASD): ");
			move = input.next().charAt(0);
		
					
			// Move the mouse according to user input
			if (moveMouse(mouseMove[0], mouseMove[1], room, move) != null){
				mouseMove = moveMouse(mouseMove[0], mouseMove[1], room, move);
				simRunCount += 1;	
			}
			
			// Check if mouse ate a cheese
			if (tryToEatCheese(mouseMove[0], mouseMove[1], cheesePositions)){
				cheeseEaten += 1;
			}
			
			System.out.println("\nThe mouse has eaten " + cheeseEaten + " cheese!");
			
			// Display room with updated mouse move
			printRoom(room, cheesePositions, mouseMove[0], mouseMove[1]);		
					
		}
		
		// End Program message
		System.out.print("==================================================" + "\n"
				+ "Thank you for running the Cheese Eater simulation.");
		
	}
	
	/*
	 * The purpose of this method is to initial room, including placing walls into the room
	 * 
	 * */
	public static void placeWalls(char[][] room, int numberOfWalls, Random randGen) {
		
		// Declare variables
		int wallXPosition;
		int wallYPosition;
		int wallCount = 0;
	
		
		// Initialize empty room
		for (int a = 0; a < room.length; a++){
			for (int b = 0; b < room[0].length; b++){
				room[a][b] = '.';
			}
			
		}
		
				
		// Place walls into room
		while (wallCount < numberOfWalls){
			wallXPosition = randGen.nextInt(room.length);
			wallYPosition = randGen.nextInt(room[0].length);
			
			if (room[wallXPosition][wallYPosition] != '#'){
				room[wallXPosition][wallYPosition] = '#';
				wallCount += 1;
			}
			
		}
		
		// Place empty spaces
		for (int a = 0; a < room.length; a++){
			for (int b = 0; b < room[0].length; b++){
				if (room[a][b] != '#'){
					room[a][b] = '.';
				}
			}
			
		}
		
		
		
	}
	
	/*
	 * The purpose of this method is to find random coordinates for cheese,
	 * storing them in the cheesePositions array.
	 * Also, verify there are no duplicate coordinates and the cheese
	 * does not overlap a wall.
	 * */
	public static void placeCheeses(int[][] cheesePositions, char[][] room, Random randGen) {
		// Declare variables
		int tempX;
		int tempY;
		int j = 0;
		int duplicate;
		
		// Checking for duplicates
		while (j < cheesePositions.length){
			
			tempX = randGen.nextInt(room[1].length);
			tempY = randGen.nextInt(room.length);
			
			duplicate = 0;
			
			//Check if there is a wall already at the coordinates 
			if (room[tempY][tempX] != '#') {
				for (int a = 0; a < cheesePositions.length; a++){
					if (tempX == cheesePositions[a][0]){
						if (tempY == cheesePositions[a][1]){
							duplicate += 1;
						}
					} 			
				}
				
				if (duplicate > 0){
					tempX = randGen.nextInt(room[1].length);
					tempY = randGen.nextInt(room.length);
				} else {
					// Add verified coordinates to cheese position array
					cheesePositions[j][0] = tempX;
					cheesePositions[j][1] = tempY;
					j += 1;
				}				
			}
		}

	}
	
	/*
	 * The purpose of this method is to display the room, with priority for the mouse,
	 * then cheese, then the rest of the room (empty spaces and walls).
	 * */
	public static void printRoom(char[][] room, int[][] cheesePositions, int mouseX, int mouseY) {
		
		int cheeseCount = 0;
		
		// Remove old mouse
		for (int a = 0; a < room.length; a++){
			for (int b = 0; b < room[0].length; b++){
				if (room[a][b] == '@'){
					room[a][b] = '.';
				}
			}
			
		}
		
		// Place mouse in new position
		room[mouseY][mouseX] = '@';
		
		
		// Place cheese
		while (cheeseCount < cheesePositions.length){
			if (cheesePositions[cheeseCount][0] != -1){
				if (room[cheesePositions[cheeseCount][1]][cheesePositions[cheeseCount][0]] != '@'){
					room[cheesePositions[cheeseCount][1]][cheesePositions[cheeseCount][0]] = '%';
					
				}
			}
			cheeseCount += 1;
		}
		
		
		// Display room
		for (int i = 0; i < room.length; i++){
			for (int j = 0; j < room[0].length; j++){
				System.out.print(room[i][j]);
				if (j == room[0].length - 1){
					System.out.println("\n");
				}
			}
			
		}
		

		
	}
	
	/*
	 * The purpose of this method is to move the mouse according to the user's 
	 * inputed movement direction. 
	 * If the user's move char is a valid input, mouse is still within bounds of the room,
	 * and the mouse isn't attempting to occupy a space with a wall, then 
	 * return updated mouse coordinates. 
	 * */
	public static int[] moveMouse(int mouseX, int mouseY, char[][] room, char move) {
		// Declare variables
		int[] mousePosition = new int[2];
		
		mousePosition[0] = mouseX;
		mousePosition[1] = mouseY;
		
		// Move mouse according to direction
		if (move == 'w' || move == 'W'){
			mousePosition[1] -= 1;
		} else if (move == 's' || move == 'S'){
			mousePosition[1] += 1;
		} else if (move == 'a' || move == 'A'){
			mousePosition[0] -= 1;
		} else if (move == 'd' || move == 'D'){
			mousePosition[0] += 1;
		} else {
			System.out.println("WARNING: Didn't recognize move command: " + move);
			return null;
		}
		
		// Check if mouse is outside the bounds of the room array
		if (mousePosition[0] >= room[0].length || mousePosition[1] >= room.length 
				|| mousePosition[0] < 0 || mousePosition[1] < 0 ){
			System.out.println("WARNING: Mouse cannot move outside the room");
			return null;
		}
		
		// Check if mouse is moved into a position occupied by a wall
		if (room[mousePosition[1]][mousePosition[0]] == '#'){
			System.out.println("WARNING: Mouse cannot move into wall");
			return null;
		}
		
		
		return mousePosition;
	}

	/*
	 * The purpose of this lab is to check if the mouse is eating cheese
	 * */
	public static boolean tryToEatCheese(int mouseX, int mouseY, int[][] cheesePositions) {
		
		// Check if the mouse is on a cheese space
		for (int i = 0; i < cheesePositions.length; i++){			
			if (mouseX == cheesePositions[i][0] && mouseY == cheesePositions[i][1]){
				
				cheesePositions[i][0] = -1;
				cheesePositions[i][1] = -1;
				return true;
			}
		}
		
		return false;
	}

}


