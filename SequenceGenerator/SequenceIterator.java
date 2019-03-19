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
import java.util.Iterator;

/**
 * An Iterator that generates and steps through a sequence
 *
 */
 
public class SequenceIterator implements Iterator<Integer>{
    private NumberGenerator generator; // a NumberGenerator object
    			// that generates and returns a number of index n in a 
    			// sequence of numbers
    
    private int size;  // size of the sequence
    private int index; // index of the next number to be generated in the sequence
    
    /**
     * Constructs a SequenceIterator with given number generator and size
     * This constructs initializes also the index to 0
     * @param generator
     * @param size
     */
    public SequenceIterator(NumberGenerator generator, int size) {
        // Initialize fields
    	this.generator = generator;
    	this.size = size;
    	index = 0;
    }
    
    /**
     * Checks if there if the next number is sequence is true
     * by comparing the next index and the size of the sequence
     * 
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        if (index >= size){
        	return false;
        }
        
        return true;
    }
    
    /**
     * Generates the next number in a sequence, incrementing
     * the index to the next number.
     * 
     * @return nextNum : the next number in the sequence
     */
    @Override
    public Integer next() {
    	// Generate number
    	int nextNum = generator.generateNumber(index);
    	// Increment index
    	index++;
    	
    	return nextNum;
    }
 
}