import java.util.LinkedList;
import javax.swing.JFrame;
/**
 * Created by Matheos Asfaw  on 10/18/2016.
 */
public class MazeTester {

    public static void main(String []args ){



        boolean[][] mazeData = new boolean[][]{
                {false,false,true ,true ,false,false,false,false},
                {true ,false,true ,true ,false,true ,true ,false },
                {true ,false,true ,true ,false,true ,true ,false },
                {true ,false,true ,true ,true ,true ,true ,false },
                {true ,false,false,false,false,false,false,false },
        };

        MazeCoord startLoc = new MazeCoord(2,4);
        MazeCoord endLoc = new MazeCoord(0,0);
/*
        System.out.println("Wall = " + maze.hasWallAt(wall));
        System.out.println("free = " + maze.hasWallAt(free ));
        MazeCoord wall = new MazeCoord(3,3);
        MazeCoord free = new MazeCoord(4,4);
        boolean[][] mazeData = new boolean[][]{
                {false,false},
                {false,false}
        };
        MazeCoord startLoc = new MazeCoord(1,1);
        MazeCoord endLoc = new MazeCoord(0,0);
*/
        LinkedList<MazeCoord> path, path2;
       Maze maze = new Maze(mazeData,startLoc,endLoc);


        System.out.println("Entry location = " + maze.getEntryLoc());
        System.out.println("Exit Loc = "+ maze.getExitLoc());
        System.out.println("Path before search = " + maze.getPath());


        maze.search();
        path= maze.getPath();
        path2 = maze.getPath();
        System.out.println("length of path = " + path.size());

        for (int i =0;i < path.size(); i++){
            System.out.println(path.get(i).toString());
        }
        System.out.println();
        System.out.println();
        System.out.println("length of path2 = " + path2.size());

        for (int i =0;i < path.size(); i++){
            System.out.println(path2.get(i).toString());
        }
        System.out.println();

/*
      //  MazeComponent mazeComponent = new MazeComponent(maze);
        MazeFrame frame = new MazeFrame(mazeData,startLoc,endLoc);
        frame.setTitle("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.add(mazeComponent);
        frame.setVisible(true);
*/
    }
}