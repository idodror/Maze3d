package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/**
 *  abstract class UI
 *  implements Runnable
 *  Data Member MyView view
 * @author Gal Basre & Ido Dror
 *
 */
public abstract class UI implements Runnable {	
	protected MyView view;
	
	/**
	 * start
	 * abstract
	 */
	public abstract void start();
	
	/**
	 * exit
	 * abstract
	 */
	public abstract void exit();
	
	/**
	 * mazeReady
	 * abstract
	 * @param Maze3d maze
	 * @param String mazeName
	 */
	public abstract void mazeReady(Maze3d maze, String mazeName);
	
	public abstract void printMessage(String msg);
	
	public abstract void displaySolution(Solution<Position> solution);
	
	public abstract void move(Position pos);
	
	public abstract void winner();
	
	public abstract void executeCommand(String commandLine);
	
}
