package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class MazeCurrent {
	private Maze3d maze;
	private Position currPosition;
	
	public void setCurrPosition(Position currPosition) {
		this.currPosition = currPosition;
	}

	public MazeCurrent() {
		this.maze = null;
		this.currPosition = null;
	}

	public Position getCurrPosition() {
		return this.currPosition;
	}

	public Maze3d getMaze() {
		return maze;
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
}
