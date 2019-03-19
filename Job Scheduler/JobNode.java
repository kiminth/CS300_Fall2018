//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Job Scheduler Version 1.0
// Files:           WaitingListADT.java, JobNode.java, JobLList.java,
//						JobScheduler.java
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
 * This file is for an object job node for the linked list
 *
 */
public class JobNode {

	// Class Fields
	private static int jobCount = 0; // number of jobs already created
	
	// Object Fields
	private int jobId; 			// unique job identifier
	private float arrivalTime;  // arrival time in seconds
	private int userId;			// identifier of the user that created the job
	private int priority; 		// job priority
	private int timeToLive; 	// job Time To Live in seconds
	private String description; // job description

	private JobNode next; // reference to the next job in the linked list
	
	// Constructor using fields
	/**
	 * Constructor creates new JobNode, initializing objects fields 
	 * using the parameters, and assigns a unique jobId
	 * 
	 * @param arrivalTime
	 * @param userId
	 * @param priority
	 * @param ttl
	 * @param description
	 */
	public JobNode(float arrivalTime, int userId, int priority, 
			int ttl, String description) {
		
		// Initialize fields
		this.arrivalTime = arrivalTime;
		this.userId = userId;
		this.priority = priority;
		this.timeToLive = ttl;
		this.description = description;
		
		// Creates a unique jobId using the job counter
		jobCount += 1;
		jobId = jobCount;
		
	}
	
	/**
	 * Constructor creates new JobNode, initializing objects fields 
	 * using the parameters.
	 * Used for making copy, as it does not increment job count
	 * 
	 * @param arrivalTime
	 * @param userId
	 * @param priority
	 * @param ttl
	 * @param description
	 * @param jobId 
	 */
	public JobNode(float arrivalTime, int userId, int priority, 
			int ttl, String description, int jobId) {
		
		// Initialize fields
		this.arrivalTime = arrivalTime;
		this.userId = userId;
		this.priority = priority;
		this.timeToLive = ttl;
		this.description = description;
		this.jobId = jobId;
		
	}

	/**
	 * @return the jobId
	 */
	public int getJobId() {
		return jobId;
	}

	/**
	 * @return the arrivalTime
	 */
	public float getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @return the timeToLive
	 */
	public int getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the next
	 */
	public JobNode getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(JobNode next) {
		this.next = next;
	}
	
	/**
	 * This method returns a new reference to a copy of the current JobNode
	 * 
	 * @return a new reference to a copy of this (instanceof JobNode)
	 */
	public JobNode copy() {
		// Create new JobNode that is a copy of the current JobNode
		JobNode copy = new JobNode(arrivalTime, userId, priority, 
				timeToLive , description, jobId);

		// Return a reference to the copy
		return copy;
		
	}
	
}
