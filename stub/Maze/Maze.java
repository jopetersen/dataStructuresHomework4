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
        //first need to check if the inputs are valid -- they cannot have a negative amount of rows or columns
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
    



        // also check if the current cell being analyzed is the exit cell, paint color path using
        	//	recolor
        	// return true
        
        
        // otherwise cell is assumed to be part of the path
        
        // calls the wrapper method Maze.findMazePath()



        // return true if a path is found after clicking solve

        // current cell is part of path

        //paint color PATH
        //explore each neighbor to determine if they're part of a path

        // USE RECURSION HERE, 1 call/neighbor
        // If exploring at least one of the neighbor is successful,
        // path == solved, otherwise it's a dead end

        // change color to temporary


    // ADD METHOD FOR PROBLEM 2 HERE
    
    // ADD METHOD FOR PROBLEM 3 HERE
    

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
