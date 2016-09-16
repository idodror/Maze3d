package view;

import algorithms.mazeGenerators.Maze3d;

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
	public void printToOutputStream(String out);
	
	/**
	 * 
	 */
	public void generatedMaze(Maze3d maze);
	
	/**
	 * exit the program without anything open
	 */
	public void exit();
}
