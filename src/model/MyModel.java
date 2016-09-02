package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import controller.Controller;
import io.MyCompressorOutputStream;

/**
 * This is the Model layer of the MVC
 * Makes all the calculations of the game
 * Holds a HashMap of the maze's created (database)
 */
public class MyModel implements Model {
	private Controller controller;
	private Position wantedPosition;
	private Maze3d currMaze;		// current maze play on from the database
	private Position currPosition;	// current maze's position on the maze
	private HashMap<String, MazeAndPlayer> mazeDatabase;

	/**
	 * Constructor
	 * @param controller
	 */
	public MyModel(Controller controller) {
		this.controller = controller;
		this.currPosition = null;
		this.wantedPosition = null;
		this.mazeDatabase = new HashMap<String, MazeAndPlayer>();
	}
	
	/**
	 * Gets a name of maze and get it from the mazeDatabase map
	 * If there is no such maze, throws exception
	 * if there is, update the data members currMaze to the maze and currPosition to the player's position on this maze
	 * @param name, String
	 * @throws IllegalArgumentException if there is no such maze in the database
	 */
	private void getMazeFromDatabase(String name) {
		MazeAndPlayer maze = this.mazeDatabase.get(name);
		if (maze == null)
			throw new IllegalArgumentException("There is no maze called " + name);
		this.currMaze = maze.getMaze();
		this.currPosition = maze.getCurrPosition();
	}

	/**
	 * Generate new maze by GrowingTree algorithm
	 * gets the dimensions of the maze from the args
	 * after create it, put it in the HashMap mazeDatabase
	 * @param args String[], the dimensions of the maze
	 */
	@Override
	public void generateMaze(String[] args) {
		if (args.length != 4) 
			throw new IllegalArgumentException("Illegal Arguments!");
		int[] mazeDimensions = argsToMazeDimension(Arrays.copyOfRange(args, 1, args.length));
		Maze3dGenerator mg = new GrowingTreeGenerator();
		MazeAndPlayer maze = new MazeAndPlayer();
		maze.setMaze(mg.generate(mazeDimensions[0], mazeDimensions[1], mazeDimensions[2]));
		maze.setCurrPosition(maze.getMaze().getStartPosition());
		this.mazeDatabase.put(args[0], maze);
		this.controller.printToOutputStream("maze " + args[0] + " is ready");
		
	}
	
	/**
	 * Gets a String[] and return the dimensions sent by the cli
	 * @param args, String[] - the dimensions of the maze
	 * @return int[] of the dimensions ([0]-floors, [1]-rows, [2]-cols)
	 * @throws IllegalArgumentException if the arguments illegal (not numbers, not enough / too many arguments)
	 */
	private int[] argsToMazeDimension(String[] args) {
		int floors, rows, cols;
		try {
			floors = Integer.parseInt(args[0]);
			rows = Integer.parseInt(args[1]);
			cols = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Illegal Arguments! (Not all numbers to the maze dimensions)");
		}
		int[] mazeDimensions = {floors, rows, cols};
		return mazeDimensions;
	}

	/**
	 * Replace the values of the current position in a specific maze to the wanted position
	 */
	private void setCurrentToWanted() {
		this.currPosition.z = this.wantedPosition.z;
		this.currPosition.y = this.wantedPosition.y;
		this.currPosition.x = this.wantedPosition.x;
	}
	
	@Override
	public void goLeft(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y, this.currPosition.x-1);
		goSomewhere();
	}

	@Override
	public void goRight(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y, this.currPosition.x+1);
		goSomewhere();
	}

	@Override
	public void goUp(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z+1, this.currPosition.y, this.currPosition.x);
		goSomewhere();
	}

	@Override
	public void goDown(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z-1, this.currPosition.y, this.currPosition.x);
		goSomewhere();
	}

	@Override
	public void goForward(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y+1, this.currPosition.x);
		goSomewhere();
	}

	@Override
	public void goBackward(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y-1, this.currPosition.x);
		goSomewhere();
	}
	
	/**
	 * If the move from currPosition to wantedPosition is legal,
	 * make it and change the value of currPosition
	 * asks the controller to display the current position now
	 */
	private void goSomewhere() {
		if (this.currMaze.validPos(this.wantedPosition) && !this.currMaze.isWall(this.wantedPosition))
			setCurrentToWanted();
		else this.controller.printToOutputStream("sorry, you can't go there");
		this.controller.displayPosition(this.currPosition);
	}

	/**
	 * Gets a maze name and asks the controller to display it
	 * @param args, String[] - maze name
	 */
	@Override
	public void displayMaze(String[] args) {
		getMazeFromDatabase(args[0]);
		this.controller.printToOutputStream(this.currMaze.toString());
	}

	/**
	 * Display all the files and folders in the path
	 * Command input: dir [path]
	 * @param args, String[] - file path
	 */
	@Override
	public void displayFilesInPath(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Invalid Arguments!");
		File folder = new File(args[0]);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null)
			throw new NullPointerException("There is no such path or path is empty");
		for (int i = 0; i < listOfFiles.length; i++)
			this.controller.printToOutputStream(listOfFiles[i].getName());
	}

	/**
	 * Display the cross section {X / Y / Z} of a maze
	 * Command input: display_cross_section [index] [{X/Y/Z}] [name]
	 * If the arguments are valid, asks from the controller to print the cross section to the stream
	 * @param args, String[] - index, X/Y/Z, maze name
	 */
	@Override
	public void DisplayCrossSection(String[] args) {
		int[][] crossSection = null;
		int index;
		if (args.length != 3)
			throw new IllegalArgumentException("Invalid Arguments!");
		try {
			index = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Illegal Arguments! (index is not a number)");
		}
		getMazeFromDatabase(args[2]);
		try {
			switch (args[1]) {
			case "X":
			case "x":
				crossSection = this.currMaze.getCrossSectionByX(index);
				break;
			case "Y":
			case "y":
				crossSection = this.currMaze.getCrossSectionByY(index);
				break;
			case "Z":
			case "z":
				crossSection = this.currMaze.getCrossSectionByZ(index);
				break;
			default: throw new IllegalArgumentException("Invalid Arguments!");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid Arguments!");
		}
		this.controller.printToOutputStream(Array2dtoString(crossSection));
	}

	/**
	 * This methods gets a 2d array of integers and turn it to string
	 * @param crossSection, int[][] 2d array
	 * @return String (like toString to 2d array of integers)
	 */
	private String Array2dtoString(int[][] crossSection) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < crossSection.length; i++)
		{
			for (int j = 0; j < crossSection[0].length; j++)
				sb.append(crossSection[i][j]);
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * This method get a maze name and file name and save the maze to this file (Compressed)
	 * Command input: save_maze [name] [file_name]
	 * @throws IllegalArgumentException, NullPointerException if something damaged with the file
	 * @param args, String[] - maze name, file name
	 */
	@Override
	public void saveMaze(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		File myFile = new File(args[1]);
		MyCompressorOutputStream save = null;
		try {
			save = new MyCompressorOutputStream(new FileOutputStream(myFile));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found");
		}
		try {
			save.write(this.currMaze.toByteArray());
			save.close();
		} catch (IOException e) {
			throw new NullPointerException("Can't open/close or create this file");
		}
	}
}
