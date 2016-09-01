package model;

import java.util.Arrays;
import java.util.HashMap;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import controller.Controller;

public class MyModel implements Model {
	private Controller controller;
	private Position wantedPosition;
	private Position currPosition;
	private Maze3d currMaze;
	private HashMap<String, MazeCurrent> mazeDatabase;

	public MyModel(Controller controller) {
		this.controller = controller;
		this.currPosition = null;
		this.wantedPosition = null;
		this.mazeDatabase = new HashMap<String, MazeCurrent>();
	}
	
	private void getMazeFromDatabase(String name) {
		MazeCurrent maze = this.mazeDatabase.get(name);
		if (maze == null)
			throw new IllegalArgumentException("There is no maze called " + name);
		this.currMaze = maze.getMaze();
		this.currPosition = maze.getCurrPosition();
	}

	@Override
	public void goLeft(String name) {
		getMazeFromDatabase(name);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y, this.currPosition.x-1);
		goSomewhere();
	}

	@Override
	public void goRight(String name) {
		getMazeFromDatabase(name);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y, this.currPosition.x+1);
		goSomewhere();
	}

	@Override
	public void goUp(String name) {
		getMazeFromDatabase(name);
		this.wantedPosition = new Position(this.currPosition.z+1, this.currPosition.y, this.currPosition.x);
		goSomewhere();
	}

	@Override
	public void goDown(String name) {
		getMazeFromDatabase(name);
		this.wantedPosition = new Position(this.currPosition.z-1, this.currPosition.y, this.currPosition.x);
		goSomewhere();
	}

	@Override
	public void goForward(String name) {
		getMazeFromDatabase(name);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y+1, this.currPosition.x);
		goSomewhere();
	}

	@Override
	public void goBackward(String name) {
		getMazeFromDatabase(name);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y-1, this.currPosition.x);
		goSomewhere();
	}

	@Override
	public void generateMaze(String[] args) {
		if (args.length != 4) 
			throw new IllegalArgumentException("Illegal Arguments!");
		int[] mazeDimensions = argsToMazeDimension(Arrays.copyOfRange(args, 1, args.length));
		Maze3dGenerator mg = new GrowingTreeGenerator();
		MazeCurrent maze = new MazeCurrent();
		maze.setMaze(mg.generate(mazeDimensions[0], mazeDimensions[1], mazeDimensions[2]));
		maze.setCurrPosition(maze.getMaze().getStartPosition());
		this.mazeDatabase.put(args[0], maze);
		this.controller.printToScreen("maze " + args[0] + " is ready");
		
	}
	
	private int[] argsToMazeDimension(String[] args) {
		Integer floors, rows, cols;
		floors = Integer.parseInt(args[0]);
		rows = Integer.parseInt(args[1]);
		cols = Integer.parseInt(args[2]);
		if (floors == null || rows == null || cols == null)
			throw new IllegalArgumentException("Illegal Arguments!");
		int[] mazeDimensions = {floors, rows, cols};
		 return mazeDimensions;
	}

	private void setCurrentToWanted() {
		this.currPosition.z = this.wantedPosition.z;
		this.currPosition.y = this.wantedPosition.y;
		this.currPosition.x = this.wantedPosition.x;
	}
	
	private void goSomewhere() {
		if (this.currMaze.validPos(this.wantedPosition) && !this.currMaze.isWall(this.wantedPosition))
			setCurrentToWanted();
		else System.out.println("sorry, you can't go there");
		this.controller.displayPosition(this.currPosition);
	}

	@Override
	public void displayMaze(String[] args) {
		getMazeFromDatabase(args[0]);
		this.controller.printToScreen(this.currMaze.toString());
	}

}
