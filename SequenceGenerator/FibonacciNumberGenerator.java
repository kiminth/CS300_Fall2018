//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Sequence Generator
// Files:           NumberGenerator.java, ArithmeticNumberGenerator.java,
//						GeometricNumberGenerator.java, 
//						FibonacciNumberGenerator.java, SequenceIterator.java,
//						Sequence.java
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
 * Generates any number from a Fibonacci Sequence
 *
 */
 
public class FibonacciNumberGenerator implements NumberGenerator {

	/**
	 * Computes the number of index n in a Fibonacci Sequence 
	 * iteratively.
	 * Time complexity: O(n)
	 */
	@Override
	public int generateNumber(int n) {
		// Time complexity: O(n)
		// computes the number of index n in a Fibonacci sequence
		// iteratively. (Do not use a recursive algorithm here!)
		
		// Declare variables
		int numFirst = 0;
		int numSecond = 1;
		int temp;
		
		// The first two numbers of the sequence are 0, 1
		// for the Fibonacci Sequence 
		if (n <= 1){
			return n;
		}
		
		// Compute the number if index is 2 or greater
		while (n >= 2){
			temp = numFirst + numSecond;
			numFirst = numSecond;
			numSecond = temp;
			n = n-1;
		}
			
		return numSecond;
	}
}