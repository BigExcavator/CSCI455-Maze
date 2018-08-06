// Name: Ruohan Sun
// USC NetID: 6539994087
// CS 455 PA3
// Spring 2018

import java.util.LinkedList;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
import java.awt.*;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent{

	private static final int START_X = 10; // top left of corner of maze in frame
	private static final int START_Y = 10;
	private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
	private static final int BOX_HEIGHT = 20;
	private static final int INSET = 2;   // how much smaller on each side to make entry/exit inner box
   
	private static final Color WALL=Color.BLACK; //the designed color for each component in maze 
	private static final Color FREE=Color.WHITE;
	private static final Color START=Color.YELLOW;
	private static final Color EXIT=Color.GREEN;
	private static final Color LINE=Color.BLUE;
   
	private Maze maze;

   
	/**
		Constructs the component.
		@param maze   the maze to display
	 */
	public MazeComponent(Maze maze) 
	{   
		this.maze=maze;
	}

   
	/**
		Draws the current state of maze including the path through it if one has
		been found.
		@param g the graphics context
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		//paint each wall with black and each free position with white
		for(int i=0; i<maze.numRows(); i++) {
			for(int j=0; j<maze.numCols(); j++) {
				if(maze.hasWallAt(new MazeCoord(i, j)) == true) {
					g2.setColor(WALL);
					g2.fillRect(START_X+j*BOX_WIDTH, START_Y+i*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
				}
				else {
					g2.setColor(FREE);
					g2.fillRect(START_X+j*BOX_WIDTH, START_Y+i*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
				}
			}
		}
		//paint the startLoc with yellow and the exitLoc with green
		g2.setColor(START);
		g2.fillRect( maze.getEntryLoc().getCol()*BOX_WIDTH+START_X+INSET/2,  maze.getEntryLoc().getRow()*BOX_HEIGHT+START_Y+INSET/2, BOX_WIDTH-INSET, BOX_HEIGHT-INSET);
		g2.setColor(EXIT);
		g2.fillRect( maze.getExitLoc().getCol()*BOX_WIDTH+START_X+INSET/2,  maze.getExitLoc().getRow()*BOX_HEIGHT+START_Y+INSET/2, BOX_WIDTH-INSET, BOX_HEIGHT-INSET);
		//draw a black boundary around the maze area 
		g2.setColor(WALL);
		g2.drawRect(START_X,START_X,BOX_WIDTH* maze.numCols(), BOX_HEIGHT*maze.numRows());
		//paint the path iff there exist a path
		if (maze.getPath().size() > 0 ){
			drawPath(g2);
		}
	}
   
	/**
		draw the path from the startLoc to exitLoc
		@param g2
	 */
	private void drawPath(Graphics2D g2) {
		LinkedList<MazeCoord> list = maze.getPath();
	   
		for(int i=0; i< list.size()-1; i++) {
			//draw a line from a point and to the adjacent point on the path
			Line2D.Double line = new Line2D.Double(START_X + 0.5 * BOX_WIDTH + BOX_WIDTH *list.get(i).getCol(), START_Y + 0.5 * BOX_HEIGHT + BOX_HEIGHT *list.get(i).getRow(), 
					START_X + 0.5 * BOX_WIDTH + BOX_WIDTH *list.get(i+1).getCol(), START_Y + 0.5 * BOX_HEIGHT + BOX_HEIGHT *list.get(i+1).getRow());
			g2.setColor(LINE);
			g2.fill(line);
			g2.draw(line);
		}
	}

}
