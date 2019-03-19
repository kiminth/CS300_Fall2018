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
 * Generates any number of any arithmetic sequence
 *
 */
 
public class ArithmeticNumberGenerator implements NumberGenerator {
    private final int init; // first term in the sequence
    private final int diff; // common difference
    
    /**
     * Constructs an arithmetic number generator with given
     * start value init and common difference diff
     * @param init start value
     * @param diff common difference
     * @throws IllegalArgumentException if any of the input arguments
     * is illegal
     */
    public ArithmeticNumberGenerator(int init, int diff) 
    		throws IllegalArgumentException{
    	// Check if input arguments are illegal by checking 
    	// if the inputs are negative
    	if (init < 0 || diff < 0){
    		throw new IllegalArgumentException();
    	}
    	
    	// Initialize fields
    	this.init = init;
    	this.diff = diff;   	
    }
    
 
    /**
     * Generates the number of index n
     * in an arithmetic sequence recursively.
     * Time Complexity: O(n) 
     * 
     */
    @Override
    public int generateNumber(int n) {
		// Time Complexity: O(n)
    	// This method generates the number of index n
    	// in an arithmetic sequence recursively
 
    	// Base Case
    	if (n == 0) {
    		return init;
    	}
    	
    	return generateNumber(n-1) + diff;
    }
}