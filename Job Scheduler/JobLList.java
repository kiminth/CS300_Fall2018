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
 * This file is an implements the interface for a job linked-list, using 
 * using job nodes
 *
 */
public class JobLList implements WaitingListADT<JobNode>{

	// Declare object fields
	private JobNode head;
	private int size;
	
	// Constructor using no arguments
	/**
	 * Constructor creates new JobLList, initializing objects fields 
	 * 
	 */
	public JobLList (){
		this.size = 0;
		this.head = null;
	}
	
	/**
	 * The newJob is added at the end of the list, if its priority equals 0
	 * If the newJob has a higher priority (priority equals 1), 
	 * then it should be added before all the already scheduled jobs of lower priority 0 
	 * and after the already scheduled jobs of the same priority 1.
	 * 
	 * @param arrivalTime
	 **/
	@Override
	public void schedule(JobNode newJob) {
		
		// Declare variables
		int tempPriority = newJob.getPriority();
		JobNode checkNode = head;
		
		// If this is the first node of the list,
		// set it to head
		if (head == null){
			head = newJob;
			size++;
			return;
		}
		
		if (tempPriority == 0){
			
			// If there is only one node in the list,
			// the node is automatically placed afterwards
			if (checkNode.getNext() == null){
				checkNode.setNext(newJob);
				size++;
				return;
			}
			
			// If priority equals 0, 
			// add the newJob to the end of the list 
			while (checkNode != null){				
				if (checkNode.getNext() == null){
					checkNode.setNext(newJob);
					newJob.setNext(null);
					break;
				}
				checkNode = checkNode.getNext();
			}
		
		} else {
			
			// Places highest priority (1) to head if head is of low priority
			if (tempPriority > head.getPriority()){
				newJob.setNext(head);
				head = newJob;
				size++;
				return;
			}
			
			// If there is only one node in the list,
			// and head node is of high priority,
			// the node is automatically placed afterwards
			if (checkNode.getNext() == null){
				checkNode.setNext(newJob);
				newJob.setNext(null);
				size++;
				return;
			}
					
			// If newJob priority is high, 
			// add newJob after high priority jobs
			// but before lower priority
			while (checkNode != null){
				
				// if all JobNodes in list have priority 1, 
				// then add node to the end
				if (checkNode.getNext() == null){
					checkNode.setNext(newJob);
					newJob.setNext(null);
					break;
				}
				
				
				if (checkNode.getNext().getPriority() == 0){
					newJob.setNext(checkNode.getNext());
					checkNode.setNext(newJob);
					break;
				}
								
				checkNode = checkNode.getNext();
			}
		}
		
		// Increment size
		size++;

		
	}

	/**
	 * This method checks to see if the JobLList is empty
	 * by checking if there is a head node (which is the first 
	 * node of the list)
	 * 
	 * @param boolean value
	 **/
	@Override
	public boolean isEmpty() {
		if (head == null){
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * This method returns the size of the list
	 * 
	 * @return size
	 **/
	@Override
	public int size() {			
		return size;
	}

	/**
	 * This method removes all the obsolete jobs from the list.
	 * A job (here an instanceof JobNode) is obsolete if: 
	 * its arrivalTime + its timeToLive < cleaningTime
	 * 
	 * @param cleaningTime
	 **/
	@Override
	public int clean(float cleaningTime) {
		// Declare variables
		JobNode checkNode = head;
		JobNode followNode = null;
		int cleannedObjects = 0;
		
		// If the list is empty, return 0 cleaned objects
		// since there's nothing to clean
		if (isEmpty()){
			return cleannedObjects;
		}	
		
		// If the list only contains a head node, 
		// and the head node is obsolete,
		// Clear the list and return 
		if (head.getNext() == null){
			if ((head.getArrivalTime() + head.getTimeToLive()) < cleaningTime){
				clear();
				cleannedObjects++;
				return cleannedObjects;
			}
		} else {
			followNode = checkNode;
			checkNode = checkNode.getNext();
			
		}
		
		// Check if the list for obsolete nodes/jobs
		while (checkNode != null){
			if ((checkNode.getArrivalTime() 
					+ checkNode.getTimeToLive()) < cleaningTime){		
				
				//Check to see if the current node is the last node in the list
				if (checkNode.getNext() == null){	
					followNode.setNext(null);
					checkNode = null;
					size--;
					cleannedObjects++;
					break;
				}
								
				// Remove obsolete node/job
				
				followNode.setNext(checkNode.getNext());
				checkNode = followNode.getNext();
		
				size--;
				cleannedObjects++;
							
			} else {
				followNode = checkNode;
				checkNode = followNode.getNext();
			}	
		}	
		
		// Check if the head node is obsolete
		if ((head.getArrivalTime() + head.getTimeToLive()) < cleaningTime){
			
			// If head is the last node in the list
			if (head.getNext() == null){
				head = null;
				size = 0;
				cleannedObjects++;
				return cleannedObjects;
				
			} else {
				// Remove the head node
				head = head.getNext();
				size--;
				cleannedObjects++;
			}
			
		}
		
		return cleannedObjects;
	}

	/**
	 * This method removes all the items from the list
	 * 
	 **/
	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	/**
	 * This method returns a new reference to a duplicate copy of the list
	 * 
	 * @return newList
	 **/
	@Override
	public WaitingListADT<JobNode> duplicate() {
		// Create new JobList
		JobLList newList = new JobLList();
		
		JobNode checkNode = head;
		
		if (checkNode != null){
			// Create a node to keep track of the duplicating
			JobNode duplicate = checkNode.copy();
			
			// Set the head of the new list
			newList.setHead(duplicate);
			
			// Duplicate the rest of the list 
			while (checkNode.getNext() != null){
				duplicate.setNext(checkNode.getNext().copy());
				duplicate = duplicate.getNext();
				checkNode = checkNode.getNext();
			}
		}
		
		// Set the size of the copy list to the same as the duplicate list
		newList.setSize(size);
		
		return newList;
	}
	
	/**
	 * Creates a String that contains information on the 
	 * JobLList (isEmpty, size) and the JobNodes 
	 * (jobID, description, userID, priority)
	 * 
	 * @return String value to display
	 */
	@Override
	public String toString(){
		String value = "";
		JobNode checkNode = head;
		
		value += "Job List is empty: " + isEmpty() + "\n";
		value += "The size is: " + size + " job(s)." + "\n";
		
		if (!isEmpty()){
			while (checkNode != null){
				value += "job #" + checkNode.getJobId() + " : " 
						+ checkNode.getDescription() + " (UID "
						+ checkNode.getUserId() + ") "
						+ checkNode.getPriority() + "\n";
				checkNode = checkNode.getNext();
			}
			
		}
		
		return value;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(JobNode head) {
		this.head = head;
	}
	
	/**
	 * @param size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
