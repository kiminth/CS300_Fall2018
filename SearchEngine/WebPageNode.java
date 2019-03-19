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

/**
 * Creates an node that stores the weblink, id, and 
 * the left and right child of the node. Used for binary search tree
 * in Search Engine.
 * 
 * @author Kimberly
 *
 */
public class WebPageNode{
 
	// Declare fields
    private final String id; 		// The id of the web page
	private final String webLink;   // The web link of the web page
    private WebPageNode leftChild;  // The leftChild of the the current WebPageNode
    private WebPageNode rightChild; // The rightChild of the the current WebPageNode
 
    /**
     * Constructor to create a webPage node
     * 
     * @param id
     * @param webLink
     */
    public WebPageNode(String id, String webLink) {
    	this.id = id;
    	this.webLink = webLink;
    	this.leftChild = null;
    	this.rightChild = null;
    }
    
	// Add public setters and getters methods
    /**
     * Gets left child of node
     * 
     * @return leftChild
     */
	public WebPageNode getLeftChild() {
		return this.leftChild;
	}
	
	/**
	 * Sets the left child of node
	 * 
	 * @param leftChild
	 */
	public void setLeftChild(WebPageNode leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * Gets the right child of node
	 * 
	 * @return rightChild
	 */
	public WebPageNode getRightChild() {
		return this.rightChild;
	}
	
	/**
	 * Sets the right child of node
	 * 
	 * @param rightChild
	 */
	public void setRightChild(WebPageNode rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * Gets the id of node 
	 * 
	 * @return id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Gets the web link of node
	 * 
	 * @return webLink
	 */
	public String getWebLink() {
		return this.webLink;
	}
}