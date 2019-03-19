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
 * This class tests the MazeRunnerStack
 */
public class TestStack {

	public static void main(String[] args) {
		if (runTests()){
			System.out.println("All tests have passed!");
		} else {
			System.out.println("Not all tests have passed.");
		}
	}

	/**
	 * Calls all test methods, checking if each pass
	 * 
	 * @return boolean if all tests passed
	 */
	public static boolean runTests(){
		// Declare variables 
		Boolean pass = true;
		
		// Check all tests
		if (!testPopFromEmpty()){
			System.out.println("testPopFromEmpty() has failed.");
			pass = false;		
		}
		
		if (!testPeekFromEmpty()){
			System.out.println("testPeekFromEmpty() has failed.");
			pass = false;		
		}
		
		if (!testPushNullItem()){
			System.out.println("testPushNullItem() has failed.");
			pass = false;
		}
		
		if (!testPushToSeeIfInitialized()){
			System.out.println("testPushToSeeIfInitialized() has failed.");
			pass = false;
		} 
		
		if (!testStackNotQueueImplement()){
			System.out.println("testStackNotQueueImplement() has failed.");
			pass = false;
		}
		
		if (!testPopLastItemOnStack()){
			System.out.println("testPopLastItemOnStack() has failed.");
			pass = false;
		}
		
		if (!testPeekOnStackTop()){
			System.out.println("testPeekOnStackTop() has failed.");
			pass = false;
		}
		
		if (!testIsEmptyOnStack()){
			System.out.println("testIsEmptyOnEmptyStack() has failed.");
			pass = false;
		}
		
		if (!testContainsOnStack()){
			System.out.println("testContainsOnStack() has failed.");
			pass = false;
		}
		
		if (!testMazeRunnerStackConstructor()){
			System.out.println("testMazeRunnerStackConstructor() has failed.");
			pass = false;
		}
		return pass;
				
	}

	/**
	 * Calls peek on an empty MazeRunnerStack.
	 * Expect the method to return an EmptyStackException.
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testPopFromEmpty(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		
		try {
			testStack.pop();
		} catch (EmptyStackException exception) {
			return true;
		} catch (Exception exception){
			System.out.println("Failure: pop from an empty stack." 
				+ "Expected EmptyStackException. Got [" + exception + "]");
		}	
		
		return false;
	}

	/**
	 * Calls peek on an empty MazeRunnerStack.
	 * Expect the method to return an EmptyStackException.
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testPeekFromEmpty(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		
		try {
			testStack.peek();
		} catch (EmptyStackException exception) {
			return true;
		} catch (Exception exception){
			System.out.println("Failure: peek from an empty stack." 
				+ "Expected EmptyStackException. Got [" + exception + "]");
		}
		
		return false;
	}
	
	/**
	 * Passes a null position into the push method of a new MazeRunnerStack.
	 * Expect the method to return an IllegalArgumentException.
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testPushNullItem(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		Position testNull = null;
		try {
			testStack.push(testNull);
		} catch (IllegalArgumentException exception) {
			return true;
		} catch (Exception exception){
			System.out.println("Failure: Push null item onto stack." 
				+ "Expected IllegalArgumentException. "
				+ "Got [" + exception + "]");
		}
		
		return false;
	}
	
	/**
	 * Passes a new position into the push method of a new MazeRunnerStack.
	 * Expect the method to initialize the the stack using the new position 
	 * since the stack is empty.
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testPushToSeeIfInitialized(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		Position testNull = new Position(1,1);
		try {
			testStack.push(testNull);
			testStack.peek();
			testStack.pop();
			
		} catch (NullPointerException exception) {
			System.out.println("Failure: Push new item onto stack, "
					+ "but did not initialize." 
					+ "Got [NullPointerException]");
			return false;
			
		} catch (Exception exception){
			System.out.println("Failure: Push item onto stack." 
				+ "Got [" + exception + "]");
		} 
		
		return true;
	}
	
	/**
	 * Test to see if the stack implementation is a actually a queue 
	 * by testing "last in, first out" for stack,
	 * or if it actually a queue, "last in, last out", 
	 * or simply not a working implementation of either 
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testStackNotQueueImplement(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		// Adds new positions into stack
		Position test1 = new Position(1,1);
		Position test2 = new Position(2,2);
		Position test3 = new Position(2,2);
		
		Position lastOut;
		Position firstOut;
		
		try {
			// Push new positions into stack
			testStack.push(test1);
			testStack.push(test2);
			testStack.push(test3);
			
			// Pop positions from stack, 
			// storing the first and last ones
			firstOut = testStack.pop();
			testStack.pop();
			lastOut = testStack.peek();
			
			// Check "last in, first out" to determine if stack
			if (firstOut == test3){
				return true;
			} else if (lastOut == test3){
				System.out.println("Failure: Stack implementation "
						+ "is actually an "
						+ "implementation of a Queue.");
				return false;
			} else {
				System.out.println("Failure: Stack implementation "
						+ "is neither an "
						+ "implementation of a Queue or Stack.");
				return false;
			}
			
			
		} catch (NullPointerException exception) {
			System.out.println("Failure: Push new item onto stack,"
					+ " but did not initialize." 
					+ "Got [NullPointerException]");
			return false;
			
		} catch (Exception exception){
			System.out.println("Failure: Push, peek, or pop." 
				+ "Got [" + exception + "]");
			return false;
		} 
	
	}
	
	/**
	 * Test to see if an implementation that cannot 
	 * pop() the last item in the stack
	 * throwing an EmptyStackException instead.
	 * If the stack can pop the last item in the stack,
	 * then the test past.
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testPopLastItemOnStack(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		// Adds new positions into stack
		Position test1 = new Position(1,1);
		
		try {
			// Push a new item into the stack
			if (testPushToSeeIfInitialized()){
				testStack.push(test1);
				testStack.pop();
			}
			
		} catch (EmptyStackException exception){
			return false;
			
		} catch (Exception exception){
			System.out.println("Failure: Push or Pop item onto stack. " 
					+ "Got [" + exception + "]");
		}
		
		return true;
		
	}
	
	/**
	 * Test to see if peek returns the
	 * last item added to the stack (top)
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testPeekOnStackTop(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		// Adds new positions into stack
				Position test1 = new Position(1,1);
				Position test2 = new Position(2,2);
				Position test3 = new Position(2,2);
				
		try {
			// Push new positions into stack
			testStack.push(test1);
			testStack.push(test2);
			testStack.push(test3);
			
			// Peek on the stack to see if top is 
			// the last position added on the stack
			if (testStack.peek() == test3){
				return true;
			} else {
				System.out.println("Failure: Peek did not return the "
						+ "top of the stack.");
				return false;
			}
			
		} catch (Exception exception){
			System.out.println("Failure: Push or Peek item on stack. " 
					+ "Got [" + exception + "]");
		}
		
		return false;
		
	}
	
	/**
	 * Test to see if isEmpty returns true
	 * on an empty stack and false 
	 * on an initialized stack
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testIsEmptyOnStack(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		try {
			// Check Empty Stack
			if (!testStack.isEmpty()){
				System.out.println("The stack was empty, as it wasn't initialized, so"
						+ " it should return true, but instead it returned false.");
				return false;
			}
				
			testStack.push(new Position(0,0));
			// Check not empty stack
			if (testStack.isEmpty()){
				System.out.println("The stack was initialize, so"
						+ " it should return false, but instead it returned true.");
				return false;
			}
			
		} catch (Exception exception){
			System.out.println("Failure: " 
					+ "Got [" + exception + "]");
		}
		
		return true;
	}
	
	/**
	 * Test to see if contains returns true
	 * on an a Position that should be in the stack
	 * and false on a Position that should not 
	 * be in the stack
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testContainsOnStack(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		// Adds new positions into stack
		Position test1 = new Position(1,1);
		Position test2 = new Position(2,2);
		Position test3 = new Position(2,2);
		Position test4 = new Position(3,3);
		
		try {
			// Push new positions into stack
			testStack.push(test1);
			testStack.push(test2);
			
			// Test if the pair (2,2) is found (which the stack contains)
			if (!testStack.contains(test3)){
				System.out.println("contains() should return true as there"
						+ " should be another position with 2,2");
				return false;
			}
			
			// Test if the pair (3,3) is found 
			// (which the stack does not contain)
			if (testStack.contains(test4)){
				System.out.println("contains() should return false, "
						+ "since the stack does not contain any "
						+ "position with 3,3");
				return false;
			}
			
			return true;
		} catch(Exception exception){
			System.out.println("Failure: " 
					+ "Got [" + exception + "]");
			return false;
		}
	}
	
	/**
	 * Test Maze Runner Stack constructor
	 * 
	 * @return boolean if Test Passed
	 */
	private static boolean testMazeRunnerStackConstructor(){
		MazeRunnerStack testStack = new MazeRunnerStack();
		MazeRunnerStack testStack2 = new MazeRunnerStack();
		Boolean pass = true;
		Position check = new Position(2,2);
		
		// The maze Runner stack should be instances of both
		// maze runner stack and stackADT
		if (testStack instanceof MazeRunnerStack 
				&& testStack instanceof StackADT){
			pass = true;
		} else {
			System.out.println("The testStack created using the no-arg"
					+ " constructor of MazeRunnerStack should be "
					+ "and instance of MazeRunnerStack and StackADT.");
			pass = false;
		}
		
		
		// Checking if the object popped from the second test stack is the same 
		// item pushed into the first stack	
		testStack2.push(new Position(1,1));
		testStack.push(check);
		if(check == testStack2.pop()){
			System.out.println("The Maze Runner Stack implementation should"
					+ " not be static.");
			pass = false;
		}
		
		return pass;
	}
	
}
