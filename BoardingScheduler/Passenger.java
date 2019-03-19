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
 * Creates a Passenger, including their information (name, time, seat, etc.)
 *
 */
public class Passenger implements Comparable<Passenger> {
	// Declare fields
	private String name; // Name of passenger
	private int time; // Time a passenger checked in, minutes after the airport is opened
	private String seat; // The seat number of passenger
	private int preferredBoarding; // Indicates extra priority that a passenger may have 
	
	private int startTime; // The estimated time the passenger begins boarding

	private int doneTimeEstimate; // The estimated time the passenger boarding completion
	private int seatNum; // The seat, numeric part
	
	// Constructors 
    public Passenger(String name, int time, String seat){
    	// Initialize fields 
    	this.name = name;
    	this.time = time;
    	this.seat = seat;
    	this.preferredBoarding = 0; // Default is 0 
    	setSeatNumAndRow();
    }
    
    public Passenger(String name, int time, String seat, int preferredBoarding){
    	// Initialize Fields
    	this.name = name;
    	this.time = time;
    	this.seat = seat;
    	this.preferredBoarding = preferredBoarding;
    	setSeatNumAndRow();
    }
 
    /**
     * This method sets the seatNum, which is only the numerical 
     * part of the seat (so for "15A", seatNum would be "15")
     * seatNum is important for calculating priority.
     * 
     */
    private void setSeatNumAndRow(){
    	char[] seatArray;
    	String seatString = "";
    	
    	seatArray = seat.toCharArray();
    	// Concat the numbers in the seat number to a string
    	for(int i = 0; i < seatArray.length - 1; i++){
    		seatString += seatArray[i];
    	}
    	
    	// Set seatNum
    	seatNum = Integer.parseInt(seatString);
    }

    /**
	 * @return the seatNumber
	 */
	public int getSeatNum(){
    	return seatNum;
    }
    
	/**
	 * @param the done time estimate to be set
	 */
    public void setDoneTimeEstimate(int estimate) {
    	this.doneTimeEstimate = estimate;
    }
    
    /**
	 * @return the done time
	 */
    public int getDoneTimeEstimate(){
    	return doneTimeEstimate;
    }
    
    /**
	 * @return the preferred boarding number
	 */
    public int getPreferredBoarding(){
    	return this.preferredBoarding;
    }
    
    /**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the seat
	 */
	public String getSeat() {
		return seat;
	}

	/**
	 * @param seat the seat to set
	 */
	public void setSeat(String seat) {
		this.seat = seat;
	}

	/**
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
     * This method compares two passengers depending on their preferredBoarding
     * number, seat number, and estimated time when they will be done boarding.
     * Higher return value (1) indicates that the current passenger is 
     * of higher priority, and lower return value (0) indicates 
     * the other passenger has higher priority.
     * 
     * @return either 1 (high) or 0 (low) value 
     */
    public int compareTo(Passenger other) {
    	// Passengers with a "preferredBoarding" number will have higher 
    	// priority, higher numbers indicating higher priority
		if (this.preferredBoarding > other.getPreferredBoarding()){
			return 1;
		}
    	    	
		// Passengers with a lower estimated time have higher priority
		
		
		if( this.preferredBoarding == other.getPreferredBoarding()){
			if (this.doneTimeEstimate < other.getDoneTimeEstimate()){
				return 1;
			}
		}
		
		return 0;
    
    } //Required for implementing Comparable
}
