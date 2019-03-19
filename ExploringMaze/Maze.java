//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Exploring A Maze
// Files:           StackADT.java, MazeRunnerStack.java
//					TestStack.java, Maze.java
//
// Course:          CS 300 Spring 2018
//
// Author:          Kimberly Inthavong
// Email:           inthavong@wisc.edu
// Lecturer's Name: Mouna Kacem
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
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
/**
 * This class creates a maze using the maze data, 
 * solves the maze (if possible),
 * and can display the solution
 */
public class Maze {

	// Declare Object Fields
	private MazeRunnerStack path;
	private Boolean solved;
	private char[][] mazeInfo;
	private int[] start;
	private int[] finish;

	/**
	 * Main method. Used to test mazes.
	 */
	public static void main(String[] args) {
		// TESTING PURPOSES
		// TEST MAZE 1
		char[][] testMazeInfo = { { 'L', '.', '|' }, { 'L', '_', '_' } };

		// Display Maze Information
		for (int i = 0; i < testMazeInfo.length; i++) {
			for (int j = 0; j < testMazeInfo[i].length; j++) {
				System.out.print(testMazeInfo[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");

		Maze testMaze = new Maze(testMazeInfo);
		testMaze.setStart(0, 0);
		testMaze.setFinish(0, 2);

		// Display unsolved maze
		testMaze.displayMaze();

		testMaze.solveMaze();

		// Display solved maze
		testMaze.displayMaze();

		// TEST MAZE 2
		char[][] testMazeInfo2 = { { 'L', '.', '|', '.' },
				{ '|', '|', '_', '|' }, { 'L', '_', '|', '_' }, 
				{ '|', '_', '_', '|' }, { 'L', '_', '_', '_' } };
		// Display maze information
		System.out.println("\n");
		for (int m = 0; m < testMazeInfo2.length; m++) {
			for (int n = 0; n < testMazeInfo2[m].length; n++) {
				System.out.print(testMazeInfo2[m][n]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");

		Maze testMaze2 = new Maze(testMazeInfo2);
		testMaze2.setStart(0, 0);
		testMaze2.setFinish(3, 3);

		// Display unsolved maze
		testMaze2.displayMaze();

		testMaze2.solveMaze();

		// Display solved maze
		testMaze2.displayMaze();
	}

	/**
	 * Constructor w/ parameters
	 * 
	 * @param mazeInfo,
	 *            an array of characters describing how each tile looks
	 */
	public Maze(char[][] mazeInfo) {
		this.path = null;
		this.solved = null;
		this.mazeInfo = mazeInfo;
		this.start = new int[2];
		this.finish = new int[2];
	}

	/**
	 * Initializes where the starting tile is on the maze
	 * 
	 * @param row
	 * @param col
	 */
	public void setStart(int row, int col) {
		start[0] = row;
		start[1] = col;
	}

	/**
	 * Initializes where the finishing tile is on the maze
	 * 
	 * @param row
	 * @param col
	 */
	public void setFinish(int row, int col) {
		finish[0] = row;
		finish[1] = col;
	}

	/**
	 * Displays the maze Both the clear starting maze and the solved maze,
	 * including path to solve
	 * 
	 */
	public void displayMaze() {
		// The String[][] holds the maze string to be printed.
		// The row of the array correspond to every row of tiles
		// in the maze, and
		// the column indicates the walls [0] and floor [1]
		// of the current tile.
		String[][] maze = new String[mazeInfo.length + 2][2];
		// Holds the index of the current row of the maze
		int currRow = 0;
		boolean pathTile;
		Position[] solution = null;
		int count;

		// Create top of maze
		maze[currRow][0] = "";
		for (int k = 0; k < mazeInfo[currRow].length; k++) {
			if (k == 0) {
				maze[currRow][1] = "+";
			}
			maze[currRow][1] += "---+";
		}
		currRow++;

		// If a solution was found to the maze,
		// store the path in an array
		// in order to display path
		if (solved != null && solved == true) {
			count = path.getSize();
			solution = new Position[path.getSize()];
			while (!path.isEmpty()) {
				solution[count - 1] = path.pop();
				count--;
			}
			System.out.print("Solution is:");
		}

		// If no solution was found for the maze
		// after attempt
		if (solved != null && solved == false) {
			System.out.print("No solution could be found.");
		}

		// Create rest of maze according to code in mazeInfo
		for (int i = 0; i < mazeInfo.length; i++) {
			for (int j = 0; j < mazeInfo[i].length; j++) {

				// Initialize array
				if (j == 0) {
					maze[currRow][0] = "";
					maze[currRow][1] = "";
				}

				// Initialize pathTile boolean
				pathTile = false;

				// Check to see if tile is a part of the solution
				if (solved != null && solved == true) {
					for (int s = 0; s < solution.length; s++) {
						if (i == solution[s].row && j == solution[s].col) {
							pathTile = true;
							break;
						}
					}
				}

				// Checking what type of tile it is,
				// and adding special characters for
				// certain tile cases (Start/Finish/Solution Path)
				switch (mazeInfo[i][j]) {
				case 'L':
					if (i == start[0] && j == start[1]) {
						maze[currRow][0] += "| S ";
					} else if (i == finish[0] && j == finish[1]) {
						maze[currRow][0] += "| F ";
					} else if (pathTile == true) {
						maze[currRow][0] += "| * ";
					} else {
						maze[currRow][0] += "|   ";
					}

					maze[currRow][1] += "+---";
					break;

				case '|':
					if (i == start[0] && j == start[1]) {
						maze[currRow][0] += "| S ";
					} else if (i == finish[0] && j == finish[1]) {
						maze[currRow][0] += "| F ";
					} else if (pathTile == true) {
						maze[currRow][0] += "| * ";
					} else {
						maze[currRow][0] += "|   ";
					}

					maze[currRow][1] += "+   ";
					break;

				case '_':
					if (i == start[0] && j == start[1]) {
						maze[currRow][0] += "  S ";
					} else if (i == finish[0] && j == finish[1]) {
						maze[currRow][0] += "  F ";
					} else if (pathTile == true) {
						maze[currRow][0] += "  * ";
					} else {
						maze[currRow][0] += "    ";
					}

					maze[currRow][1] += "+---";
					break;

				case '.':
					if (i == start[0] && j == start[1]) {
						maze[currRow][0] += "  S ";
					} else if (i == finish[0] && j == finish[1]) {
						maze[currRow][0] += "  F ";
					} else if (pathTile == true) {
						maze[currRow][0] += "  * ";
					} else {
						maze[currRow][0] += "    ";
					}

					maze[currRow][1] += "+   ";
					break;

				}

				// Adding the right wall at the end of every row
				if (j == mazeInfo[i].length - 1) {
					maze[currRow][0] += "|";
					maze[currRow][1] += "+";
				}
			}
			currRow++;
		}

		// Display Maze
		for (int i = 0; i < maze.length - 1; i++) {
			if (maze[i][0] != null) {
				// Print floors
				System.out.println(maze[i][0]);
			}
			// Print walls
			System.out.println(maze[i][1]);
		}

		// Display solution path if path was found
		if (solved != null && solved == true) {
			System.out.print("Path is:");
			for (int s = 0; s < solution.length; s++) {
				System.out.print(" [" + solution[s].row 
						+ "," + solution[s].col + "]");
				if (s != solution.length - 1) {
					System.out.print(" -->");
				}
			}
		}

	}

	/**
	 * Solves the maze using the right-hand method, 
	 * Storing the clear path (no
	 * back-tracking) in the path MazeRunnerStack.
	 * 
	 */
	public void solveMaze() {
		// Create a constant to hold the max number of steps that can occur 
		final int MAX_STEP = mazeInfo.length * mazeInfo[0].length * 4;
		// Create a variable to record which way we are "facing"
		int facing = 90; // start facing right
		int[] curr = { this.start[0], this.start[1] }; // hold current position
		int steps = 0; // Step counter
		this.path = new MazeRunnerStack(); // initialize maze runner stack

		// Push starting position
		path.push(new Position(this.start[0], this.start[1]));

		// Use the right-hand method to traverse maze until finish is reached
		while (curr[0] != this.finish[0] || curr[1] != this.finish[1]) {
			// If right-hand method does not work for solving puzzle
			if (steps >= MAX_STEP) {
				solved = false;
				break;
			}
			
			// Determine movement depending on.
			// When facing right
			if (facing == 90) {
				// Check if you can move right
				if (mazeInfo[curr[0]][curr[1]] == 'L' 
						|| mazeInfo[curr[0]][curr[1]] == '_') {
					// Try to move forward
					// Check to see if you would be moving out of bounds
					// (right)
					if (curr[1] + 1 >= mazeInfo[0].length) {
						// Turn left
						facing = 0;
					} else {
						// Check the tile to the 'right' to see if there
						// will be a wall in the way
						if (mazeInfo[curr[0]][curr[1] + 1] != 'L' 
								&& mazeInfo[curr[0]][curr[1] + 1] != '|') {
							// Move forward
							curr[1] = curr[1] + 1;
							if (path.contains(new Position(curr[0], curr[1]))) {
								path.pop();
							} else {
								path.push(new Position(curr[0], curr[1]));
							}
							steps++;
						} else {
							// turn left
							facing = 0;
						}
					}
				} else {
					// Try moving right
					if (curr[0] + 1 < mazeInfo.length){
						curr[0] = curr[0] + 1;
						if (path.contains(new Position(curr[0], curr[1]))) {
							path.pop();
						} else {
							path.push(new Position(curr[0], curr[1]));
						}
						// Turn right
						facing = 180;
						steps++;
					} else if (curr[1] + 1 >= mazeInfo[0].length) {
						// Turn left
						facing = 180;
					} else {
						// Try moving right
						// Check the tile to the 'right' to see if there
						// will be a wall in the way
						if (mazeInfo[curr[0]][curr[1] + 1] != 'L' 
								&& mazeInfo[curr[0]][curr[1] + 1] != '|') {
							// Move forward
							curr[1] = curr[1] + 1;
							if (path.contains(new Position(curr[0], curr[1]))) {
								path.pop();
							} else {
								path.push(new Position(curr[0], curr[1]));
							}
						}
						// Turn right
						facing = 180;
					}
					steps++;
				}
				continue;
			}

			// When facing down
			if (facing == 180) {
				// Try moving right 
				if (curr[1] - 1 >= 0){
					if (mazeInfo[curr[0]][curr[1]] != '|' 
						&& mazeInfo[curr[0]][curr[1]] != 'L'){
						// Move right
						curr[1] = curr[1] - 1;
						if (path.contains(new Position(curr[0], curr[1]))) {
							path.pop();
						} else {
							path.push(new Position(curr[0], curr[1]));
						}
						facing = 270;
						steps++;
					// Try moving forward
					} else if (mazeInfo[curr[0]][curr[1]] == '|' 
							|| mazeInfo[curr[0]][curr[1]] == '.') {
						// Check to see if you would be moving out of bounds
						// (bottom)
						if (curr[0] + 1 >= mazeInfo.length) {
							// turn right
							facing = 270;
						} else {
							// Move forward
							curr[0] = curr[0] + 1;
							if (path.contains(new Position(curr[0], curr[1]))) {
								path.pop();
							} else {
								path.push(new Position(curr[0], curr[1]));
							}
							steps++;
						}
					} else {
						// Turn left
						facing = 90;
					}
				// Try moving forward
				} else if (mazeInfo[curr[0]][curr[1]] == '|' 
						|| mazeInfo[curr[0]][curr[1]] == '.') {
					// Check to see if you would be moving out of bounds
					// (bottom)
					if (curr[0] + 1 >= mazeInfo.length) {
						// turn right
						facing = 270;
					} else {
						// Move forward
						curr[0] = curr[0] + 1;
						if (path.contains(new Position(curr[0], curr[1]))) {
							path.pop();
						} else {
							path.push(new Position(curr[0], curr[1]));
						}
						steps++;
					}
				} else {
					// Turn left
					facing = 90;
				}
				continue;
			}

			// When facing left
			if (facing == 270) {
				// Try moving 'right' 
				if (curr[0] - 1 >= 0){
					if (mazeInfo[curr[0] - 1][curr[1]] != 'L' 
							&& mazeInfo[curr[0] - 1][curr[1]] != '_'){
						curr[0] = curr[0] - 1;
						if (path.contains(new Position(curr[0], curr[1]))) {
							path.pop();
						} else {
							path.push(new Position(curr[0], curr[1]));
						}
						facing = 0;
						steps++;
					// Try moving forward
					} else if (mazeInfo[curr[0]][curr[1]] == 'L' 
							|| mazeInfo[curr[0]][curr[1]] == '|') {
						// Turn right
						facing = 180;
					} else {
						// Check to see if you would be moving out of bounds (left)
						if (curr[1] - 1 < 0) {
							// Turn left
							facing = 180;
						} else {
							// move forward
							curr[1] = curr[1] - 1;
							if (path.contains(new Position(curr[0], curr[1]))) {
								path.pop();
							} else {
								path.push(new Position(curr[0], curr[1]));
							}
							steps++;
						}
					}
				// Checking the tile 'left' the current position is an 'L' or
				// '|',
				// meaning the there is a wall blocking moving forward
				} else if (mazeInfo[curr[0]][curr[1]] == 'L' 
						|| mazeInfo[curr[0]][curr[1]] == '|') {
					// Turn right
					facing = 0;
				} else {
					// Check to see if you would be moving out of bounds (left)
					if (curr[1] - 1 < 0) {
						// Turn right
						facing = 0;
					} else {
						// move forward
						curr[1] = curr[1] - 1;
						if (path.contains(new Position(curr[0], curr[1]))) {
							path.pop();
						} else {
							path.push(new Position(curr[0], curr[1]));
						}
						steps++;
					}
				}
				continue;
			}		
			
			// When facing up
			if (facing == 0) {
				// Check to see if you can turn right
				if (curr[1] + 1 < mazeInfo[0].length) {
					// If no wall is blocking, turn right
					if (mazeInfo[curr[0]][curr[1] + 1] != 'L' 
							&& mazeInfo[curr[0]][curr[1] + 1] != '|') {
						// Turn right
						facing = 90;
						curr[1] = curr[1] + 1;
						
						if (path.contains(new Position(curr[0], curr[1]))) {
							path.pop();
						} else {
							path.push(new Position(curr[0], curr[1]));
						}

						steps++;
					} else if (curr[0] - 1 < 0) {
						// Check to see if you would be moving out of bounds
						// (top)
						// Turn left since right and forward not possible
						facing = 270;
					} else {
						// Checking the tile 'above' the current position 
						// is an 'L'
						// or '_',
						// meaning the there is a wall blocking moving forward
						if (mazeInfo[curr[0] - 1][curr[1]] == 'L' 
								|| mazeInfo[curr[0] - 1][curr[1]] == '_') {
							// Turn right
							facing = 90;
							steps++;
						} else {
							// Move forward
							curr[0] = curr[0] - 1;
							if (path.contains(new Position(curr[0], curr[1]))) {
								path.pop();
							} else {
								path.push(new Position(curr[0], curr[1]));
							}
							steps++;
						}
					}
					// Try to go forward
				} else if (curr[0] - 1 < 0) {
					// Check to see if you would be moving out of bounds (top)
					// Turn left since right and forward not possible
					facing = 270;
				} else {
					// Checking the tile 'above' the current position is an 'L'
					// or '_',
					// meaning the there is a wall blocking moving forward
					if (mazeInfo[curr[0] - 1][curr[1]] == 'L' 
							|| mazeInfo[curr[0] - 1][curr[1]] == '_') {
						// Turn right
						facing = 90;
					} else {
						// Move forward
						curr[0] = curr[0] - 1;
						if (path.contains(new Position(curr[0], curr[1]))) {
							path.pop();
						} else {
							path.push(new Position(curr[0], curr[1]));
						}
						steps++;
					}
				}
				continue;
			}
		}

		// The maze is solved if it was able to make it through without
		// exceeding max step
		if (solved == null || solved != false) {
			solved = true;
		} else {
			path = null;
		}
	}
}
