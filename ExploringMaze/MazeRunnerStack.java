
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
import java.util.EmptyStackException;

/**
 * This class is for creating a stack, implementing our StackADT and helper
 * class Position
 */
public class MazeRunnerStack implements StackADT<Position> {

	// Declare fields
	private final int MAX_SIZE = 100;
	private int size;
	private PositionNode bottom;
	private PositionNode top;

	/**
	 * No-Parameter Constructor
	 * 
	 */
	public MazeRunnerStack() {
		// Initialize size
		this.size = 0;
	}

	/**
	 * Pushes item to the top of the stack Throws 
	 * an IllegalArgumentException if
	 * if user attempts to push an null item onto the stack
	 * 
	 * @param item to push
	 *            
	 */
	@Override
	public void push(Position item) {
		PositionNode temp = new PositionNode(item);
		
		// throws IllegalArgumentException if user attempts
		// push an null item onto the stack
		if (item == null) {
			throw new IllegalArgumentException();
		}

		// throws StackOverflowError if user attempts to
		// exceed the max size the stack can hold
		if (size == MAX_SIZE) {
			throw new StackOverflowError();
		}

		// Checks if this is the first item on the stack
		if (bottom == null && top == null) {
			bottom = temp;
			top = bottom;
			size++;
			return;
		}

		// If the only other node is bottom, set its next to bottom,
		// else set it to the top of the stack
		temp.next = top;

		top = temp;

		size++;

	}

	/**
	 * Removes the top item from the stack and returns it
	 * 
	 * @return top of the stack Position
	 */
	@Override
	public Position pop() throws EmptyStackException {
		// Declare objects
		PositionNode pop = top;

		// Throw exception if the stack is empty
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		// If there is only one object in the stack
		if (size == 1 || top == bottom) {
			bottom = null;
		}

		// Reassign top
		top = top.next;

		size--;

		return pop.curr;
	}

	/**
	 * Returns the top item from the stack without removing it
	 * 
	 * @return Position of top of stack
	 */
	@Override
	public Position peek() throws EmptyStackException {
		// Throw exception if the stack is empty
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		return top.curr;
	}

	/**
	 * Returns true if the stack is empty, otherwise returns false
	 * 
	 * @param boolean
	 */
	@Override
	public boolean isEmpty() {
		if (bottom == null) {
			return true;
		}

		return false;
	}

	/**
	 * Reports whether the Position p can be found within the stack
	 * 
	 * @param p
	 * @return boolean
	 */
	public boolean contains(Position p) {
		PositionNode check = top;

		// Checks if the stack is empty
		if (isEmpty()) {
			return false;
		}

		// Loops through the stack, comparing to see if
		// p can be found
		while (check != null) {
			if (check.curr.equals(p)) {
				return true;
			}

			check = check.next;
		}

		return false;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

}

/**
 * This is a helper class that creates a Position to be held in the stack 
 * through the Position Node. Contains data on coordinates.
 * 
 */
class Position {
	// Declare fields
	int col;
	int row;

	/**
	 * Constructor w/ parameters
	 * 
	 * @param row
	 * @param col
	 */
	Position(int row, int col) {
		this.col = col;
		this.row = row;
	}

	/**
	 * Compares the row and col of the 
	 * current Position object with 
	 * the passed in Position other
	 * 
	 * @param other
	 */
	boolean equals(Position other) {
		return this.col == other.col && this.row == other.row;
	}
}

/**
 * This is a helper class that creates a PositionNode to be held in the stack.
 * holds the current Position object and the next Position Node it points to.
 *  
 */
class PositionNode{
	// Declare fields
	Position curr;
	PositionNode next;
	
	/**
	 * Constructor w/ parameters
	 * 
	 * @param curr
	 */
	PositionNode(Position curr) {
		this.curr = curr;
		next = null;
	}
}
