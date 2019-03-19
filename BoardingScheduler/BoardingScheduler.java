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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Checks in passengers (enqueue) and boards passengers (dequeue)
 *
 */
public class BoardingScheduler {

	public static BoardingHeap heap; // Heap to hold passengers that have checked in
	public static int currentTime; // Holds the current time step
	public static BoardingHeap boardingHeap; // Heap to hold passengers that have boarded

	/**
	 * Main Class, used for testing purposes
	 * 
	 */
	public static void main(String[] args) {
		//TEST 
		checkIn("sample1.txt");

	}

	/**
	 * This method checks in passengers by enqueuing, and
	 * then boards them by dequeuing
	 * 
	 * @param Iterator to read passengers from a file
	 * @param The start time for boarding
	 */
	public static void boardFlight(Iterator<Passenger> passengers, int start){
		// Declare fields
		BoardingScheduler boarding = new BoardingScheduler();
		currentTime = start;
		heap = new BoardingHeap();
		boardingHeap = new BoardingHeap();

		// Store the interator in a temporary variable
		Iterator<Passenger> iter = passengers;
		Passenger temp; // enqueue
		Passenger temp2; // dequeue

		// Starting boarding
		System.out.println(currentTime + " Boarding begins\n");

		while (iter.hasNext()){
			temp = iter.next();
			temp2 = temp; // initialize temp2 to a passenger

			if (temp.getTime() <= currentTime){
				// If it is ther current timestep, enqueue 
				temp.setStartTime(currentTime);
				temp.setDoneTimeEstimate(boarding.calculateDoneTimeEstimate(temp));
				heap.enqueue(temp);	
			} else {
				// If no passengers arrive at the current time:
				// Increment current time until it matches the current passenger,
				// as long as there is a next passenger, else increment current time 
				// by 1.
				// Dequeue one passenger if possible for every timestep
				// incrementation, since only one passenger
				// can be dequeued per time step
				// Then enqueue current passenger
				if (iter.hasNext()){
					while (temp.getTime() != currentTime){
						if (!heap.isEmpty()){
							temp2 = heap.dequeue();
							boarding.boardingHeap.enqueue(temp2);
							System.out.println(currentTime + " "
									+ temp2.getName() + " " + temp2.getSeat()
									+ " (done " + temp2.getDoneTimeEstimate() + ")");
						}
						currentTime++;
					}
					
				} else {
					if (!heap.isEmpty()){
						temp2 = heap.dequeue();
						boarding.boardingHeap.enqueue(temp2);
						System.out.println(currentTime + " "
								+ temp2.getName() + " " + temp2.getSeat()
								+ " (done " + temp2.getDoneTimeEstimate() + ")");
					}
					currentTime++;
				}

				temp.setStartTime(currentTime);
				temp.setDoneTimeEstimate(boarding.calculateDoneTimeEstimate(temp));
				heap.enqueue(temp);
			}	

			// If there are no more passengers coming, dequeue the remaining passengers
			if (!iter.hasNext()){
				while (!heap.isEmpty()){
					temp2 = heap.dequeue();				
					System.out.println(currentTime + " "
							+ temp2.getName() + " " + temp2.getSeat()
							+ " (done " + temp2.getDoneTimeEstimate() + ")");
					currentTime++;
				}

				System.out.println((temp2.getDoneTimeEstimate() + 1) 
						+ " All passengers have boarded!");
			}		
		}		
	}


	/**
	 * Reads in a file containing a list of flight passengers in the order they
	 * check in and runs the boardFlight() method with those passengers.
	 * @author Tina, Alexi
	 * @param flight is the name of the input file in the project directory
	 */
	public static void checkIn(String flight) {
		File f = new File(flight);
		try {
			Scanner s = new Scanner(f);
			ArrayList<Passenger> passengers = new ArrayList<Passenger>();
			while(s.hasNextLine()) {
				//Data are separated by commas and possibly also whitespace.
				String[] line = s.nextLine().split("\\s*,\\s*");
				if (line.length == 3) //Default preferredBoarding 0 constructor
					passengers.add(new Passenger(line[0],
							Integer.parseInt(line[1]),
							line[2]));
				else //Use the preferredBoarding number if given
					passengers.add(new Passenger(line[0],
							Integer.parseInt(line[1]),
							line[2],
							Integer.parseInt(line[3])));
			}
			s.close();
			// TODO
			boardFlight(passengers.iterator(), 1);
		} catch (IOException e) {
			System.out.println("Error: Unable to load file " + flight);
		}
	}

	/**
	 * Calculates done time estimate for p
	 * 
	 * @param Passenger p
	 */
	public int calculateDoneTimeEstimate(Passenger p) {
		// Declare fields
		final int EXTRA_TIME = 5; // The extra time it takes for a passenger to sit down
		int doneTime;
		Passenger inTheDamnWay;

		if (boardingHeap.isEmpty()){
			doneTime = p.getStartTime() + EXTRA_TIME;
			p.setDoneTimeEstimate(doneTime);
			return doneTime;
		} else {
			inTheDamnWay = boardingHeap.dequeue();
			// If a passenger p2 with a seat that is in the same row,
			// or in an 'earlier' (lower numbered) row has begun boarding already
			if (p.getSeatNum() >= inTheDamnWay.getSeatNum()){
				doneTime = inTheDamnWay.getDoneTimeEstimate() + EXTRA_TIME;
			} else {
				doneTime = p.getStartTime() + EXTRA_TIME;
			}
		}

		return doneTime;
	}

}
