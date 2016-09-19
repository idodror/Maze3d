package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * This is the Model interface of the MVP
 * @author Gal Basre & Ido Dror
 */
public interface Model {
	
	/**
	 * Go one step left
	 * @param args, String[]
	 */
	public void goLeft(String[] args);

	/**
	 * Go one step right
	 * @param args, String[]
	 */
	public void goRight(String[] args);
	
	/**
	 * Go one step up
	 * @param args, String[]
	 */
	public void goUp(String[] args);
	
	/**
	 * Go one step down
	 * @param args, String[]
	 */
	public void goDown(String[] args);
	
	/**
	 * Go one step forward
	 * @param args, String[]
	 */
	public void goForward(String[] args);
	
	/**
	 * Go one step backward
	 * @param args, String[]
	 */
	public void goBackward(String[] args);
	
	/**
	 * Generate new maze
	 * @param args
	 */
	public void generateMaze(String[] args);
	
	/**
	 * Display exist maze
	 * @param args
	 */
	public void displayMaze(String[] args);
	
	/**
	 * Display all files and folders in path
	 * @param args
	 */
	public void displayFilesInPath(String[] args);
	
	/**
	 * Display cross section by {X/Y/Z} of a maze
	 * @param args
	 */
	public void displayCrossSection(String[] args);
	
	/**
	 * Save the maze
	 * @param args, maze name,file name
	 */
	public void saveMaze(String[] args);
	
	/**
	 * load the maze
	 * @param args, maze name,file name
	 */
	public void loadMaze(String[] args);
	
	/**
	 * solve the maze
	 * @param args, maze name,algorithm
	 */
	public void solve(String[] args);
	
	/**
	 * display the solution
	 * @param args, maze name
	 */
	public void displaySolution(String[] args);
	
	/**
	 * Exit command (close threads)
	 */
	public void exit();
	
	/**
	 * Print String to the output stream
	 * @param out, String
	 */
	public void printToOutputStream(String out);
	
	/**
	 * Save the mazeDatabase map to zip file
	 * @throws NullPointerException
	 */
	public void zipSave();
	
	/**
	 * Load the mazeDatabase map from zip file
	 * @throws NullPointerException
	 */
	public void zipLoad();
	
	/**
	 * getMaze
	 * @param String
	 * @return maze3d
	 */
	public Maze3d getMaze(String mazeName);
	/**
	 * getSolution
	 * @param String
	 * @return Solution
	 */
	public Solution<Position> getSolution(String mazeName);
	
	public Position getCurrPosition();

	public void whereAmI(String[] args);
}
