package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * This class Represent a maze, a position of the player on the maze and it solution
 */
public class MazeAndPlayer {
	
	private Maze3d maze;
	private Position currPosition;
	private Solution<Maze3d> solution;

	/**
	 * Constructor
	 */
	public MazeAndPlayer() {
		this.maze = null;
		this.currPosition = null;
		this.solution = null;
	}
	
	/**
	 * Constructor
	 * Get a Maze3d object and initial the maze of the object to it + the currPosition to maze's start
	 * @param maze, Maze3d
	 */
	public MazeAndPlayer(Maze3d maze) {
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
	 * Set the maze of the MazeAndPlayer object
	 * @param maze, Maze3d object
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}

	/**
	 * returns the solution of the maze
	 * @return solution
	 */
	public Solution<Maze3d> getSolution() {
		return solution;
	}

	/**
	 * Set the solution of the maze
	 * @param solution
	 */
	public void setSolution(Solution<Maze3d> solution) {
		this.solution = solution;
	}
}
