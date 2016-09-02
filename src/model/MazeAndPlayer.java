package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

/**
 * This class Represent a maze and a position of the player on the maze
 */
public class MazeAndPlayer {
	private Maze3d maze;
	private Position currPosition;
	
	/**
	 * Constructor
	 */
	public MazeAndPlayer() {
		this.maze = null;
		this.currPosition = null;
	}
	
	/**
	 * Set the new postion of the player on the maze
	 * @param currPosition Position
	 */
	public void setCurrPosition(Position currPosition) {
		this.currPosition = currPosition;
	}

	/**
	 * Get the current position of the player on the maze
	 * @return currPosition
	 */
	public Position getCurrPosition() {
		return this.currPosition;
	}

	/**
	 * @return maze, Maze3d object
	 */
	public Maze3d getMaze() {
		return maze;
	}

	/**
	 * Set the maze of the MazeAndPlayer object
	 * @param maze, Maze3d object
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
}
