//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Boarding Scheduler
// Files:           Passenger.java, BoardingScheduler.java, BoardingHeap.java
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
 * Creates a heap of passengers
 *
 */
public class BoardingHeap {
    //You may store additional private fields as needed
    private Passenger[] heap; //array of passengers currently in the heap
    private int ARRAY_MULTIPLIER = 2; //holds a mulipler for the size for the array
    private int size; // the number of objects in the heap
    
    //This should be your only constructor
    /**
	 * No-arg constructor
	 */
    public BoardingHeap() {
    	// Create empty heap
    	heap = new Passenger[10];
    	this.size = 0;
    }
    
    /**
     * This method enqueues a passenger based on their priority
     * 
	 * @param passenger to be enqueued
	 */
    public void enqueue(Passenger p) {
    	// Index 0 is unused, so start at 1
    	final int indexStart = 1;
    	
    	// New temperary heap for expanding heap if is full
    	Passenger[] temp;
    	Passenger swap;
    	
    	// If it is the first in the heap, add it into the 
    	// first index
    	if (isEmpty()){
			heap[indexStart] = p;
			size++;
			return;
		}
    	
    	// If the heap is full, expand the current heap and 
    	// copy over informtion
    	if (heap[heap.length - 1] != null){
    		// Create a new expanded heap
    		temp = new Passenger[heap.length * ARRAY_MULTIPLIER];
    		// Copy over elements into new array
    		for (int h = 0; h < heap.length; h++){
    			temp[h] = heap[h];
    		}
    		// Assign heap to new expanded heap
    		this.heap = temp;
    	}
    	
    
    	// Find the index that the new Passenger should be added,
    	// starting from the last (lowest priority) object
    	
    	// Place object at the end of the heap and swap
    	heap[size + 1] = p;
    	for(int i = size; i >= 1; i--){ // O(n)
    		if (p.compareTo(heap[i]) > 0){
    			swap = heap[i];
    			heap[i] = heap[i + 1];
    			heap[i + 1] = swap;
    			// If the passenger is swapped, their done time increases
    			swap.setDoneTimeEstimate(swap.getDoneTimeEstimate() + 1);
    		}
    		
    	}
    	// Increment size
    	size++;  	
    }
    
    /**
	 * This method dequeues one passenger (highest priority)
	 */
	public Passenger dequeue() {
		// Declare variables
    	Passenger boarding;	// The passenger dequeuing
    	final int bestIndex = 1; // Highest priority is at the top
    	Passenger shift; // Used for shifting passengers
    	
    	// If there is nothing to dequeue, return null
    	if (isEmpty()){
    		return null;
    	}
    	
    	// Remove the first passenger in the heap (highest priority)
    	boarding = heap[bestIndex];
    	
    	// Shift all elemnts in the heap to fill the gap
    	for (int i = 1; i < size; i++){
    		shift = heap[i + 1];
			heap[i] = heap[i + 1];
			heap[i + 1] = shift;
    	}
    	
    	//heap[tempIndex] = null;
    	size--;
    	
    	return boarding;
    }
    
	/**
	 * This method checks if the heap is empty or not
	 * 
	 * @return boolean if empty or not
	 */
    public boolean isEmpty() { 
    	if (size == 0){
    		return true;
    	} else {
    		return false;
    	}
    	
    }
}