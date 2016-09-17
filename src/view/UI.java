package view;

import algorithms.mazeGenerators.Maze3d;
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
	 * @param Maze3 dmaze
	 * @param String mazeName
	 */
	public abstract void mazeReady(Maze3d maze, String mazeName);
}
