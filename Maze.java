// Name: Ruohan Sun
// USC NetID: 6539994087
// CS 455 PA3 
// Spring 2018

import java.util.LinkedList;

/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls

 */

public class Maze {
   
	public static final boolean FREE = false;
	public static final boolean WALL = true;
   
	private boolean[][] mazeData; 
	private boolean[][] visited;	//record whether a position has been visited or not
	private MazeCoord startLoc;
	private MazeCoord exitLoc;
	private LinkedList<MazeCoord> res; //store the final path from startLoc to exitLoc

	/**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments above for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param exitLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

	 */
	public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
		this.mazeData=mazeData;
		this.startLoc=startLoc;
		this.exitLoc=exitLoc;
		res=new LinkedList<MazeCoord>();
		visited=new boolean[numRows()][numCols()];
	}


	/**
      Returns the number of rows in the maze
      @return number of rows
	 */
	public int numRows() {
		return mazeData.length;   
	}

   
	/**
      Returns the number of columns in the maze
      @return number of columns
	 */   
	public int numCols() {
		return mazeData[0].length;   
	} 

   
	/**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
	 */
	public boolean hasWallAt(MazeCoord loc) {
		return mazeData[loc.getRow()][loc.getCol()];   
	}
   

	/**
      Returns the entry location of this maze.
	 */
	public MazeCoord getEntryLoc() {
		return startLoc;  
	}
   
   
	/**
     Returns the exit location of this maze.
	 */
	public MazeCoord getExitLoc() {
		return exitLoc;   
	}

   
	/**
      Returns the path through the maze. First element is start location, and
      last element is exit location.  If there was not path, or if this is called
      before a call to search, returns empty list.

      @return the maze path
    */
	public LinkedList<MazeCoord> getPath() {
		return res;   
	}


	/**
		Find a path from start location to the exit location (see Maze
		constructor parameters, startLoc and exitLoc) if there is one.
		Client can access the path found via getPath method.
      
		@return whether a path was found.
	 */
	public boolean search()  {  
		return pathSolver(startLoc); 
	}
	
	/**
		Search the maze recursively to find whether or not there is a path from currentLoc to exitLoc
		Save the positions that the path will go through in the LinkedList.
		@param currentLoc
		@return
	 */
	private boolean pathSolver(MazeCoord currentLoc) {
		//whether or not the currentLoc is valid, whether or not it has been visited
		if(currentLoc.getCol()<0 || currentLoc.getCol()>numCols()-1 || currentLoc.getRow()<0 || 
				currentLoc.getRow()>numRows()-1 || visited[currentLoc.getRow()][currentLoc.getCol()] ) {
			return false;
	   }
		//whether or not the currentLoc is a wall
		if(mazeData[currentLoc.getRow()][currentLoc.getCol()]) {
			return false;
		}
		//whether or not we reach the exitLoc
		if(currentLoc.getRow()==exitLoc.getRow() && currentLoc.getCol()==exitLoc.getCol()) {
			res.add(currentLoc);
			return true;
		}
		//set the currentLoc as visited
		visited[currentLoc.getRow()][currentLoc.getCol()]=true;
		res.add(currentLoc);
		//continue searching into the four adjacent positions
		if(pathSolver(new MazeCoord(currentLoc.getRow()-1, currentLoc.getCol()))) {
			return true;
		}
		if(pathSolver(new MazeCoord(currentLoc.getRow()+1, currentLoc.getCol()))) {
			return true;
		}
		if(pathSolver(new MazeCoord(currentLoc.getRow(), currentLoc.getCol()-1))) {
			return true;
		}
		if(pathSolver(new MazeCoord(currentLoc.getRow(), currentLoc.getCol()+1))) {
			return true;
		}
		//if we can't reach the exitLoc from the currentLoc, we delete the currentLoc from the path list
		res.removeLast();
		return false;
	}

}