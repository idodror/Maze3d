package view;

import algorithms.mazeGenerators.Maze3d;

public abstract class UI implements Runnable {	
	protected MyView view;
	public abstract void start();
	public abstract void exit();
	public abstract void mazeReady(Maze3d maze);
}
