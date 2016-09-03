package view;

import java.util.HashMap;
import algorithms.mazeGenerators.Position;
import controller.Command;

/**
 * This is the view interface of the MVC
 */
public interface View {
	
	/**
	 * Display the position of the player
	 * @param pos, Position
	 */
	public void displayPosition(Position pos);
	
	/**
	 * Start the ui
	 */
	public void start();
	
	/**
	 * Set the commands map (HashMap)
	 * @param commandMap, HashMap
	 */
	public void setCommandsMap(HashMap<String, Command> commandMap);
	
	/**
	 * Print the string to output stream
	 * @param out, String
	 */
	public void printToOutputStream(String out);
	
}
