//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Search Engine 
// Files:           SearchEngine.java, WebPageNode.java
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

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a search engine that can store, search, print
 * and count webLinks and id in a 
 * binary search tree, making use of WebPageNode.
 * 
 * @author Kimberly
 *
 */
public class SearchEngine {
	
	// Declare fields
	private WebPageNode root; // root of the BST-based search engine

	/**
	 * No-arg constructor
	 */
	public SearchEngine(){
		// creates an empty search engine
		root = null;
	}

	/**
	 * Main method, used to run program that asks for user input, until
	 * user quits.
	 * 
	 */
	public static void main(String[] args) {
		// Declare fields
		SearchEngine engine = new SearchEngine(); // New instance of Search Engine
		Scanner scnr = new Scanner(System.in); // Scanner
		String userInput = ""; // Holds the users choice input
		ArrayList<String> temp; // Holds array of web pages
		String[] webPages; // Holds the full user input
		String find; // Used for searching for a web link
		
		// Program loops, providing user with different options, until
		// user chooses the quit option
		while (!userInput.equals("q")){
			// Display options for user
			System.out.println("\n=========================== "
					+ "Search Engine ============================"
					+ "\n1. Enter 'i <id> <webLink>' to insert a web page in the search engine"
					+ "\n2. Enter 's <id>' to search a web link in the search engine"
					+ "\n3. Enter 'p' to print all the web page ids in the search engine"
					+ "\n4. Enter 'c' to get the count of all web pages in the search engine"
					+ "\n5. Enter 'q' to quit the program"
					+ "\n======================================================================");
			
			// Retrieve user input 
			System.out.print("\nPlease enter your command:");
			webPages = scnr.nextLine().trim().split("\\s+");
			// Obtain user choice
			userInput = webPages[0].toLowerCase();
			
			switch (userInput){
				case "i":
					// Insert webpage (printing appropriate error messages for 
					// incorrect input
					try{
						engine.insert(webPages[1], webPages[2]);
					} catch (IllegalArgumentException e){
						System.out.println("WARNING: failed to insert duplicate web page: <"
								+ webPages[1] + ">.");
					} catch (IndexOutOfBoundsException i){
						System.out.println("WARNING: failed to insert web page: "
								+ "Id/web link can't be blank!");
					}
					
					break;
					
				case "s":
					// Search for requested web page id in SearchEngine
					try {
						find = webPages[1];
						System.out.println(find + " - " + engine.searchWebPage(find));
					} catch (IndexOutOfBoundsException i){
						System.out.println("WARNING: Invalid syntax for search operation: "
								+ "Id link can't be blank!");
					}
					
					break;
					
				case "p":
					// Displays all webpages that are stored, displaying an 
					// error message if the Search Engine is empty
					if (engine.isEmpty()){
						System.out.println("Search Engine is empty.");
					} else {
						temp = engine.getAllWebPages();
						while (!temp.isEmpty()){
							if (temp.size() == 1){
								System.out.print(temp.remove(0));
							} else {
								System.out.print(temp.remove(0) + ", ");
							}
						}					
					}
					System.out.print("\n");
					
					break;
					
				case "c": 
					// Displays a count of how many pages are stored in the Search Engine
					System.out.println(engine.getWebPageCount());
					break;
					
				case "q":
					// Ends the program
					System.out.print("============================== "
							+ "END ===================================");
					break;
					
				default: 
					System.out.println("WARNING: Unrecognized command.");
					break;
			}
		}
		
		// Close scanner
		scnr.close();
	}

	/**
	 * Checks if the search engine is empty
	 * 
	 * @return true if the search engine if empty, false otherwise
	 */
	public boolean isEmpty() {
		if (root == null){
			return true;
		}
		
		return false;
	}

	/**
	 * This method inserts an instance of WebPageNode from the given id
	 * and weblink into the search engine conforming to the search order
	 * property of a BTS.
	 * This method also throws an exception if the user tries to insert
	 * an entry with a duplicate id.
	 * 
	 * @param id
	 * @param webLink
	 */
	public void insert(String id, String webLink) {
		// If the tree is empty, initialize the root
		if (root == null){
			root = new WebPageNode(id, webLink);
			return;
		}
		
		// A duplicate id will throw an exception in the helper method,
		// This should be thrown from this method as well 
		try {
			root = insertHelper(root, id, webLink);
		} catch (IllegalArgumentException e){
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * This method traverses (recurrsively) down the tree till
	 * the end it reaches the end (depending on right/left, comparing 
	 * id)
	 * 
	 * @param node
	 */
	public WebPageNode insertHelper(WebPageNode node, String id, String webLink){
		// Once current node is null, reaching the end of the tree
		if (node == null){
			node = new WebPageNode(id, webLink);
			return node;
		}
		
		// Checks for a duplicate Id while traversing down the tree
		// Throws an exception if duplicate is found
		if (node.getId().compareTo(id) == 0){
			throw new IllegalArgumentException();
		}
		
		// Traverse left
		if (node.getId().compareTo(id) > 0){
			node.setLeftChild(insertHelper(node.getLeftChild(), id, webLink));
		} 
		
		// Traverse right
		if (node.getId().compareTo(id) < 0){
			node.setRightChild(insertHelper(node.getRightChild(), id, webLink));
		}
		
		return node;
	}
	
	/**
	 * This method searches for a webPageNode with the given id in the 
	 * current search engine and returns the related weblink if that 
	 * webPageNode is present. Otherwise, a warning message starting with
	 * "No web link found" should be returned back, for instance:
	 * "No web link found for the web page <id>".
	 * 
	 * @param id
	 * @return String
	 */
	public String searchWebPage(String id) { // A look-up method that
		// Declare variable
		String temp; // Holds corresponding weblink if the id is found
		String errorMessage = "No web link found for the web page <" + id + ">.";
		// Error message used if no id is found
		
		// Call helper method
		temp = searchHelper(root, id);
		
		// Checks if the webPage was found
		if(temp == ""){
			return errorMessage;
		} else {
			return temp;
		}
				
	}
	
	/**
	 * This helper method recursively traverses the tree to search for
	 * the given id to find
	 * 
	 * @param node
	 * @param idFind is the id that is being searched for
	 * @return String (if the id was found) else null
	 */
	public String searchHelper(WebPageNode node, String idFind){
		// If the id is found, return its corresponding weblink
		if (node.getId().compareTo(idFind) == 0){
			return node.getWebLink();
		}
		
		// Traverse left 
		if (node.getId().compareTo(idFind) > 0){
			if (node.getLeftChild() != null){
				return searchHelper(node.getLeftChild(), idFind);
			}
		} 
		
		// Traverse right
		if (node.getId().compareTo(idFind) < 0){
			if (node.getRightChild() != null){
				return searchHelper(node.getRightChild(), idFind);
			}	
		}
		
		// Return empty string
		return "";

	} 
	
	/**
	 * This method calculates the number of nodes in the tree
	 * 
	 * @return number of webPageNodes
	 */
	public int getWebPageCount() { 
		// Call helper method
		int totalNumber = countHelper(root);
				
		return totalNumber;
		
	}
		
	/**
	 * This helper method counts the number of nodes in the tree
	 * by traversing it recursively
	 * 
	 * @param current node
	 * @return int
	 */
	private int countHelper(WebPageNode node) {
		// If current node is null, then return 0
		if (node == null){
			return 0;
		}
		
		int total = 1; 
		
		// Travel down left branches
		if (node.getLeftChild() != null){
			total += countHelper(node.getLeftChild());
		}
		
		// Travel down right branches
		if (node.getRightChild() != null){
			total += countHelper(node.getRightChild());
		}
		
		return total;
		
	}
	
	/**
	 * This method returns an ArrayList of String that stores all the 
	 * id fields of the webPageNodes present in the current search engine,
	 * sorted in alphabetical order from left to right.
	 * 
	 * @return ArrayList<String> of web pages in alphabetical order
	 */
	public ArrayList<String> getAllWebPages() {
		// Create an arrayList to store and return 
		ArrayList<String> list = new ArrayList<String>();
		
		// Call helper method
		return getAllWebPagesHelper(root, list);
		
	}
	
	/**
	 * This helper method traverses the node tree, storing all the 
	 * elements in an ArrayList, alphabetically.
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	public ArrayList<String> getAllWebPagesHelper(WebPageNode node, ArrayList<String> list) {
		// Once current node is null, reaching end of tree
		if (node == null){
			return list;
		}
		
		// Travel down left branches
		if (node.getLeftChild() != null){
			getAllWebPagesHelper(node.getLeftChild(), list);
		}
		
		// Visit the node
		list.add(node.getId());
		
		// Travel down right branches
		if (node.getRightChild() != null){
			getAllWebPagesHelper(node.getRightChild(), list);
		}
		
		
		return list;
		
	}
}
