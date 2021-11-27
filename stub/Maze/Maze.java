/*
 * Author: Jordan Petersen
 * Class; Data Structures
 * Assignment: Homework 4
 * Last modified: 11/25
 * 
 * 
 * 
 */

package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze; // will not need to work on this class

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     */

    // Problem # 1 starts here
    public boolean findMazePath(int x, int y) {
       
    	
    	// first need to check if the inputs are valid -- they cannot have a negative amount of rows or columns
    	// also need to check if the existing rows/columns are valid
    	
        if ((x < 0 ||  y < 0) ||  maze.getNRows() < 0 || maze.getNCols() < 0 ) { 

//        	System.out.println("The cell that you have searched is out of bounds. (either x or y are < 0"
  //      			+ "OR the grid is returning a negative amount of columns/rows");
        	return false;
        	
        	
        	
        	// other condition: if the background of a current cell does not have NON_BACKGROUND
        		// return false
        	
        	// per stub, all colors that can be a part of the path will be non_background color
        	
        } else if (!maze.getColor(x, y).equals("NON_BACKGROUND")){
        	
        	//System.out.println("The background color is not equal to NON_BACKGROUND");
        	return false;
        } else if((x == (maze.getNRows()-1) && y == (maze.getNRows()-1)))  { // test to see if current cell is the exit cell
        	// exit node == (getNCols()-1, getNRows()-1) per the stub
        	//recolor to PATH
        	
        	maze.recolor(x,y, PATH); // because if it's on the path then we color it PATH
        	
       // 	System.out.println("Congratulations, you have made it to the end of the maze!");
        	return true;
        	
        } else { // final condition on stub
        	maze.recolor(x, y, PATH);
        	
        	// // explore each of the 4 neighbors, then advance the target cell using recursion
        	// 4 cases for neighbors/definitions of the neighbors: (x+1), y, (x-1, y), (x, y+1), (x, y-1): 
        	
        	
        	if(		findMazePath(x+1, y) || 
        			findMazePath(x-1, y) ||
        			findMazePath(x, y+1) || 
        			findMazePath(x, y-1)) {
        		
      //  		System.out.println("Success! A neighboring cell containing a path has been found!");
        		return true;
        	} else { // if we get here, it's a dead end.
        		maze.recolor(x,  y, TEMPORARY);
     //   		System.out.println("The cell you have reached leads to a dead end, returning false");
        	
        		return false;
        	}
        	
        	
        	
        }
        	
        	
        	
    }
    
   
    //------ Problem 2------
    
    // create a recursive algo
	    // Problem 2 -- implement a recursive algorithm by modifying the solution to return the list of all solutions that 
		// solve the maze. if no solutions, resulting list should be empty list as the only element
		// also need to define PairInt with the following:
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
		return null;
    	
    	
    	
    	 
    }
    
    
    /* Stack classes - Java (first time!)
     * Reference link: https://www.geeksforgeeks.org/stack-class-in-java/
     * 
     * */	
   
    
    
    // ----- Helper Methods for findAllMazePaths----- 
	public void findMazePathStackBased (int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		// create a new ArrayList to build a stack off of
		
		
		// error checks - will need to determine if we're at the end node or have invalid inputs, which would be negative
		
		
		if (x < 0 || y < 0 || y > maze.getNCols() -1 || x >  maze.getNRows() -1) {
			System.out.println("Coordinates are invalid");
			return;// stop the rest of the function from executing
			} else if ( y == maze.getNCols() -1 && x == maze.getNRows() -1) { // coordinates are in bound
				
				
				
				
				
				
			} 
		
		
		
		
		
		
		
		
		
		// Stack ZyBook: https://learn.zybooks.com/zybook/STEVENSCS570PeyrovianFall2021/chapter/5/section/1?content_resource_id=49817874
		
		// Stack.push puts an item oto the top of the stack
		
		
		
		// check to see if an exit is found - if it is, send a trace/add to the result var
		
		// we know that the exit point is the cell getNCols()-1 && getNRows()-1
		
		
		
		
	//	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		
		// x & y are coordinates being visited by the program
		
		// result is list of successful paths recorded up to now
		// trace is the trace of the current path being explored
		
		 
		
		
		
		
	}
 
    
    // ADD METHOD FOR PROBLEM 3 HERE
    
    
    //public ArrayList<PairInt> findMazePathMin()
    
    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
