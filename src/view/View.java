package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * This is the view interface of the MVP
 * @author Gal Basre & Ido Dror
 */
public interface View {
	
	/**
	 * Get line from the input
	 * @return String
	 */
	public String getLine();
	
	/**
	 * Start the ui
	 */
	public void start();
	
	/**
	 * Print the string to output stream
	 * @param out, String
	 */
	public void printMessage(String msg);
	
	/**
	 * Generate the maze
	 * @param maze, Maze3d
	 * @param mazeName, String
	 */
	public void generatedMaze(Maze3d maze, String mazeName);
	
	/**
	 * Move the position
	 * @param pos, Position
	 */
	public void move(Position pos);
	
	/**
	 * Display the solution
	 * @param solution, Solution<Position>
	 */
	public void displaySolution(Solution<Position> solution);
	
	/**
	 * exit the program without anything open
	 */
	public void exit();

	/**
	 * winner
	 */
	public void winner();

	/**
	 * databaseValues
	 * @param databaseValues, String
	 */
	public void databaseValues(String databaseValues);

	/**
	 * dirListReady
	 * @param dirList, String[]
	 */
	public void dirListReady(String[] dirList);
}
