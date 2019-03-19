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
 * Creates a sequence of numbers
 *
 */
 
public class Sequence implements Iterable<Integer>{
    private NumberGenerator generator; // a NumberGenerator object
			// that generates and returns a number of index n in a 
			// sequence of numbers
    private int size; // Number of items in the sequence
    
    /**
     * Creates a Sequence of Integers with a given instance of
     * NumberGenerator and a given size
     * @param generator
     * @param size
     * @throws IllegalArgumentException if size is negative
     */
    public Sequence(NumberGenerator generator, int size) 
    		throws IllegalArgumentException{
    	// Checks to see if the size is negative
    	if (size < 0){
    		throw new IllegalArgumentException();
    	}
    	
    	// Initialize fields
    	this.generator = generator;
    	this.size = size;
    }
    
    /**
     * Create and return a reference to an Object of 
     * type Iterator<Integer> to iterate over that sequence
     * 
     * returns new SequenceIterator 
     */
    @Override
    public Iterator<Integer> iterator() {
        return new SequenceIterator(generator, size);
    }
     
    /**
     * This method returns a String representation of the 
     * sequence
     * 
     * @return sequence
     */
    @Override
    public String toString(){
    	// Overrides the toString method of java.lang.Object 
    	// class to return a String representation of the sequence.
    	// The different numbers of the sequence would be 
    	// separated by a single space
        
    	String sequence = ""; // String to hold sequence
    	Iterator<Integer> iterator = iterator(); // Iterator for sequence
    	
    	// Using the iterator, generate sequence
        while (iterator.hasNext()){
        	sequence += iterator.next() + " ";
        }
        
        return sequence;
    }
 
    /**
     * Main Class, used for testing purposes
     * 
     */
    public static void main(String[] args) {
        // Optional test code could come here
    	System.out.println("****************************************");
    	System.out.println("           Sequence Generator");
    	System.out.println("****************************************");
    	         
    	System.out.println("==========Arithmetic Sequence==========");
    	Sequence sequence = new Sequence(new ArithmeticNumberGenerator(2,2),4);
    	System.out.println(sequence.toString());
    	         
    	System.out.println("==========Geometric Sequence==========");
    	sequence = new Sequence(new GeometricNumberGenerator(2,2),10);
    	System.out.println(sequence.toString());
    	         
    	System.out.println("==========Fibonacci Sequence==========");
    	sequence = new Sequence(new FibonacciNumberGenerator(),20);
    	System.out.println(sequence.toString());
    }
    
}   
