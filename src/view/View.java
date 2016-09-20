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
	 * 
	 */
	public void generatedMaze(Maze3d maze, String mazeName);
	
	public void move(Position pos);
	
	public void displaySolution(Solution<Position> solution);
	
	/**
	 * exit the program without anything open
	 */
	public void exit();

	public void winner();

	public void databaseValues(String databaseValues);
}
