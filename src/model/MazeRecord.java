package model;

import java.io.Serializable;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * This class Represent a maze, a position of the player on the maze and it solution
 * @author Gal Basre & Ido Dror
 */
@SuppressWarnings("serial")
public class MazeRecord implements Serializable{
	
	private Maze3d maze;
	private Position currPosition;
	private Solution<Position> solution;

	/**
	 * Constructor
	 */
	public MazeRecord() {
		this.maze = null;
		this.currPosition = null;
		this.solution = null;
	}
	
	/**
	 * Constructor
	 * Get a Maze3d object and initial the maze of the object to it + the currPosition to maze's start
	 * @param maze, Maze3d
	 */
	public MazeRecord(Maze3d maze) {
		this.maze = maze;
		this.currPosition = maze.getStartPosition();
		this.solution = null;
	}
	
	/**
	 * Set the new position of the player on the maze
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
	 * Set the maze of the MazeRecord object
	 * @param maze, Maze3d object
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}

	/**
	 * returns the solution of the maze
	 * @return solution
	 */
	public Solution<Position> getSolution() {
		return solution;
	}

	/**
	 * Set the solution of the maze
	 * @param solution
	 */
	public void setSolution(Solution<Position> solution) {
		this.solution = solution;
	}
}
