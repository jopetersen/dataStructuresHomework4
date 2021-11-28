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
import java.lang.module.FindException;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/


public class Maze implements GridColors {

	
	// maze overview site: https://www.geeksforgeeks.org/java-program-for-rat-in-a-maze-backtracking-2/
    /** The maze */
    private TwoDimGrid maze; // will not need to work on this class

    public Maze(TwoDimGrid m) {
        maze = m;
    }

   // create a wrapper method (which calls findMazePath) for problem 1 & sets the starting index to (0,0) the starting index
   public boolean findMazePath() {
        return findMazePath(0, 0); 
        // Wrapper method - I was still a bit confused as to why we would use them, so I found these resources:
		// https://stackoverflow.com/questions/1736234/what-is-meant-by-implement-a-wrapper-method
        
        
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
 
  
   
   // can't get the coordinates to print to the console.. tried redoing this method but it didn't solve the issue. 
   
    
   
   public boolean findMazePath(int x, int y) {
       
    	
    	// first need to check if the inputs are valid -- they cannot have a negative amount of rows or columns
    	// also need to check if the existing rows/columns are valid
    	
    	 if((x == (maze.getNCols()-1) && y == (maze.getNRows()-1)))  { // test to see if current cell is the exit cell
         	// exit node == (getNCols()-1, getNRows()-1) per the stub
         	// recolor to PATH
         	
    		 
    		 // editor's note: originally had both x & y set to Maze.getNRows, no columns.
         	maze.recolor(x,y, PATH); // because if it's on the path then we color it PATH
         	
       //  	System.out.println("Congratulations, you have made it to the end of the maze!"); // console commands for testing
         	return true;
         	
    	 }
        	
    	
        	
        	// per stub, all colors that can be a part of the path will be non_background color
        		// remember, NON_BACKGROUND shouldn't be in quotes
        	
        	
        	else if (maze.getNCols() <= x || y >= maze.getNRows()  || (x < 0 ||  y < 0) ) { // number of rows shouldn't exceed the number of x axis (think data tables)
				// number of columns shouldn't be  higher than the # of Y axis of 
        	// 	System.out.println("The cell that you have searched is out of bounds. (either x or y are < 0"
        		//		+ "OR the grid is returning a negative amount of columns/rows");
        		// if current point out of bound of grid, then return false

        		return false;
        	} else if (!maze.getColor(x, y).equals(NON_BACKGROUND)){
        	
       // 	System.out.println("The background color is not equal to NON_BACKGROUND"); //debugging notes - this was originally not working because I had
        			// NON_BACKGROUND in quotes
        	return false;
        } 
         else { // final condition on stub
        	
        	
        	// // explore each of the 4 neighbors, then advance the target cell using recursion
        	// 4 cases for neighbors/definitions of the neighbors: (x+1), y, (x-1, y), (x, y+1), (x, y-1): 
        	 maze.recolor(x, y, PATH);
        	 	// if any of these conditions are true, they are a part of the path & need to be added to the chain
        	if(		findMazePath(x, y-1) ||
        			findMazePath(x, y+1) ||
        			findMazePath(x+1, y) || 
        			findMazePath(x-1, y) == true) {
        		
        //		System.out.println("Success! A neighboring cell containing a path has been found!");
        		return true;
        	} else { // if we get here, it's a dead end.
        		maze.recolor(x,  y, TEMPORARY);
        //		System.out.println("The cell you have reached leads to a dead end, returning false");
        	
        		return false;
        	}
        	
        }
          	
    } 
    

   
   
    //------ Problem 2------
    
   
	    // Problem 2 -- implement a recursive algorithm by modifying the solution to return the list of all solutions that 
		// solve the maze. if no solutions, resulting list should be empty list as the only element
	// also need to define PairInt with the following:
    
   
   
	// method is predefined by stub - on top of p6 - this is the wrapper
	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {

		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();

		Stack<PairInt> trace = new Stack<>();

		findMazePathStackBased(0, 0, result, trace);

		return result;

	}

	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {

// if x and y are out of bounds or not equal to red, then  the find method terminates
		if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1
				|| (!maze.getColor(x, y).equals(NON_BACKGROUND))) {
			return;

// if exit is found, then push the exit point to trace, and then add to result
		} else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			trace.push(new PairInt(x, y)); // exit point added to trace
			ArrayList<PairInt> cur = new ArrayList<>(trace); //
//cur.addAll(trace); // add all elements to list cur from trace
			result.add(cur);
			trace.pop(); // After visited this point, need to remove from trace
			maze.recolor(x, y, NON_BACKGROUND); // recolor this point to Non_background for re-visiting
			return;
		} else {

// if the point is not exit, then recursion is executed
			trace.push(new PairInt(x, y)); // push this point to trace
			maze.recolor(x, y, PATH); // recolor this point to PATH before recursion
			findMazePathStackBased(x, y + 1, result, trace);
			findMazePathStackBased(x, y - 1, result, trace);
			findMazePathStackBased(x + 1, y, result, trace);
			findMazePathStackBased(x - 1, y, result, trace);
// After tried all possible paths of current point, recolor it to non_background
// implement backtracking
			maze.recolor(x, y, NON_BACKGROUND);
			trace.pop();
			return;
		}

	}

    
    // ----- Helper Methods for findAllMazePaths----- 
	// public void findMazePathStackBased (int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		// create a new ArrayList to build a stack off of
    
    
	
    /*
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    
	// x & y are coordinates being visited by the program
    // result is the list of coordinaates
    // trace is the trace of all of the current paths being explored
    
		// System.out.println(result); result was bugging out, so I tried to just print a raw 
    	
    		// result, then realized that my class definition was messed up..
		// error checks - will need to determine if we're at the end node or have invalid inputs, which would be negative
	
		
		if (x < 0 || y < 0 || y > maze.getNCols() -1 || x >  maze.getNRows() -1) {
			System.out.println("Coordinates are invalid");
			return;// stop the rest of the function from executing
			
			//  check to see if coordinates are in bound
			
		} else if ( y == maze.getNCols() -1 && x == maze.getNRows() -1) { //method: create a new stack, which will be returned eventually. If this is condition is met
					//we've reached the edit coordinates, which we need to add to the bottom of the stack
			// we know that the exit point is the cell getNCols()-1 && getNRows()-1
			
				//System.out.println("Success, the coordinates that have been entered are within the boundaries of the maze");
				// this is where we'll use trace, which  is the trace of the path currently being explored. 
				
				 // tempStack is where we add all of the coordinates to
				// create a pairInt from the coordinates given
				
				// this is the last coordinate in the maze, so we add to it with a stack (at the bottom of trace)
				
				// declare, instanciate, and add a resource to the top of the new pairint
				trace.push(new PairInt(x,y)); // originally forgot to use new, growth. 
				ArrayList<PairInt> tempStack = new ArrayList<>(trace);
				result.add(tempStack); // .add wasn't working due to arraylist, but  I found addall in Oracle's documentation
				trace.pop(); // now that we've added the exit coordinates to the index, we can remove the element in it
				
				maze.recolor(x, y, NON_BACKGROUND);				
				// resources on Stacks: 
				    // Reference link: https://www.geeksforgeeks.org/stack-class-in-java/
					// Stack ZyBook: https://learn.zybooks.com/zybook/STEVENSCS570PeyrovianFall2021/chapter/5/section/1?content_resource_id=49817874
				
				
				// Stack.push puts an item on the top of the stack
				
				
				// All cells that can be part of
					// the path will be in the NON_BACKGROUND color.
				
				
				// recolor this cell to have a NON-BACKGROUND color per the stub
				
				
				
				
				return; 
		
				
				
				
			} else { 
			// use recursion to determine if any of the neighbors are part of the path, then recolor it
				// repeat the operations done above
				
		// modifying code from solution # 1, but this time relying on my stack to do the hard work		
				trace.push(new PairInt(x,y)); // originally forgot to use new, growth. 
				// neighbors to a given cell are x-1 , x+1, y+1, y-1 (when one variable is added/subtracted from the other stays constant
				maze.recolor(x, y, PATH); 
				
				findMazePathStackBased(x, y-1, result, trace); 
				findMazePathStackBased(x, y+1, result, trace); 
				findMazePathStackBased(x-1, y, result, trace);
				findMazePathStackBased(x+1, y, result, trace); 
				
				
				maze.recolor(x, y, NON_BACKGROUND);// if any of these are true (part of the path) then we recolor them to NON_BACKGROUND
				trace.pop(); // remove the new item from the trace to re-empty it. 
				
				return;
			}
		

		 
		
		
		
		
	}
 */
    
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


/* Learning resources: 
 	*Wrappers
 		*https://stackoverflow.com/questions/1736234/what-is-meant-by-implement-a-wrapper-method 
 * 
 * 
 * 
 * 
 * 
 */


