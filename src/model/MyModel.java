package model;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import controller.Controller;

public class MyModel implements Model {
	private Controller controller;
	private Position currPosition;
	private Maze3d maze;
	private Position wantedPosition;

	public MyModel(Controller controller) {
		this.controller = controller;
		currPosition = null;
		Maze3dGenerator mg = new GrowingTreeGenerator();
		maze = mg.generate(1, 5, 5);
		this.currPosition = this.maze.getStartPosition();
	}

	@Override
	public void goLeft() {
		wantedPosition = new Position(this.currPosition.z, this.currPosition.y, this.currPosition.x-1);
		if (this.maze.validPos(wantedPosition))
			this.currPosition = wantedPosition;
		else System.out.println("sorry, you can't go left");
	}

	@Override
	public void goRight() {
		wantedPosition = new Position(this.currPosition.z, this.currPosition.y, this.currPosition.x+1);
		if (this.maze.validPos(wantedPosition))
			this.currPosition = wantedPosition;
		else System.out.println("sorry, you can't go right");
	}

	@Override
	public void goUp() {
		wantedPosition = new Position(this.currPosition.z+1, this.currPosition.y, this.currPosition.x);
		if (this.maze.validPos(wantedPosition))
			this.currPosition = wantedPosition;
		else System.out.println("sorry, you can't go up");
	}

	@Override
	public void goDown() {
		wantedPosition = new Position(this.currPosition.z-1, this.currPosition.y, this.currPosition.x);
		if (this.maze.validPos(wantedPosition))
			this.currPosition = wantedPosition;
		else System.out.println("sorry, you can't go down");
	}

	@Override
	public void goForward() {
		wantedPosition = new Position(this.currPosition.z, this.currPosition.y+1, this.currPosition.x);
		if (this.maze.validPos(wantedPosition))
			this.currPosition = wantedPosition;
		else System.out.println("sorry, you can't go forward");
	}

	@Override
	public void goBackward() {
		wantedPosition = new Position(this.currPosition.z, this.currPosition.y-1, this.currPosition.x);
		if (this.maze.validPos(wantedPosition))
			this.currPosition = wantedPosition;
		else System.out.println("sorry, you can't go backward");
	}

}
