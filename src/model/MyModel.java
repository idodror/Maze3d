package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Maze3dDomain;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * This is the Model layer of the MVP
 * Makes all the calculations of the game
 * Holds a HashMap of the maze's created (database)
 * @author Gal Basre & Ido Dror
 */
public class MyModel extends Observable implements Model {
	
	private Position wantedPosition;
	private Maze3d currMaze;		// current maze play on from the database
	private Position currPosition;	// current maze's position on the maze
	private Solution<Maze3d> currSolution;
	private Map<String, MazeRecord> mazeDatabase;
	private ExecutorService threadPool;

	/**
	 * Constructor
	 */
	public MyModel() {
		this.currPosition = null;
		this.wantedPosition = null;
		this.mazeDatabase = Collections.synchronizedMap(new HashMap<String, MazeRecord>());
		this.threadPool = Executors.newFixedThreadPool(10);
	}
	
	/**
	 * Return the mazeDatabase HashMap
	 * @return HashMap
	 */
	public Map<String, MazeRecord> getMazeDatabase() {
		return mazeDatabase;
	}

	/**
	 * Gets a name of maze and get it from the mazeDatabase map
	 * If there is no such maze, throws exception
	 * if there is, update the data members currMaze to the maze and currPosition to the player's position on this maze
	 * @param name, String
	 * @throws IllegalArgumentException if there is no such maze in the database
	 */
	private void getMazeFromDatabase(String name) {
		MazeRecord maze = this.mazeDatabase.get(name);
		if (maze == null)
			throw new IllegalArgumentException("There is no maze called " + name);
		this.currMaze = maze.getMaze();
		this.currPosition = maze.getCurrPosition();
		this.currSolution = maze.getSolution();
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
		Future<Maze3d> myFutureMaze = threadPool.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				int[] mazeDimensions = argsToMazeDimension(Arrays.copyOfRange(args, 1, args.length));
				Maze3dGenerator mg = new GrowingTreeGenerator();
				MazeRecord maze = new MazeRecord();
				maze.setMaze(mg.generate(mazeDimensions[0], mazeDimensions[1], mazeDimensions[2]));
				maze.setCurrPosition(maze.getMaze().getStartPosition());
				putInDatabase(args[0], maze);
				setChanged();
				notifyObservers("MazeIsReady " + args[0]);
				return maze.getMaze();
			}
		});
	}
	
	/**
	 * Put a MazeRecord + maze's name in the HashMap
	 * @param key, String - maze's name
	 * @param value, MazeRecord Object (the maze and current player's position)
	 */
	private void putInDatabase(String key, MazeRecord value) {
		this.mazeDatabase.put(key, value);
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
	
	/**
	 * Go one step left
	 * @param args, String[]
	 * @throws IllegalArgumentException if the arguments illegal
	 */
	@Override
	public void goLeft(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y, this.currPosition.x-1);
		goSomewhere();
	}

	/**
	 * Go one step right
	 * @param args, String[]
	 * @throws IllegalArgumentException if the arguments illegal
	 */
	@Override
	public void goRight(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y, this.currPosition.x+1);
		goSomewhere();
	}

	/**
	 * Go one step up
	 * @param args, String[]
	 * @throws IllegalArgumentException if the arguments illegal
	 */
	@Override
	public void goUp(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z+1, this.currPosition.y, this.currPosition.x);
		goSomewhere();
	}

	/**
	 * Go one step down
	 * @param args, String[]
	 * @throws IllegalArgumentException if the arguments illegal
	 */
	@Override
	public void goDown(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z-1, this.currPosition.y, this.currPosition.x);
		goSomewhere();
	}

	/**
	 * Go one step forward
	 * @param args, String[]
	 * @throws IllegalArgumentException if the arguments illegal
	 */
	@Override
	public void goForward(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		this.wantedPosition = new Position(this.currPosition.z, this.currPosition.y+1, this.currPosition.x);
		goSomewhere();
	}

	/**
	 * Go one step backward
	 * @param args, String[]
	 * @throws IllegalArgumentException if the arguments illegal
	 */
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
		String out;
		if (this.currMaze.validPos(this.wantedPosition) && !this.currMaze.isWall(this.wantedPosition))
			setCurrentToWanted();
		else out = "sorry, you can't go there";
		out = this.currPosition.toString();
		setChanged();
		notifyObservers(out);
	}

	/**
	 * Gets a maze name and asks the controller to display it
	 * @param args, String[] - maze name
	 */
	@Override
	public void displayMaze(String[] args) {
		getMazeFromDatabase(args[0]);
		setChanged();
		notifyObservers(this.currMaze.toString());
	}

	/**
	 * Display all the files and folders in the path
	 * Command input: dir [path]
	 * @param args, String[] - file path
	 */
	@Override
	public void displayFilesInPath(String[] args) {
		StringBuilder sb = new StringBuilder();
		if (args.length != 1)
			throw new IllegalArgumentException("Invalid Arguments!");
		File folder = new File(args[0]);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null)
			throw new NullPointerException("There is no such path or path is empty");
		for (int i = 0; i < listOfFiles.length; i++)
			sb.append(listOfFiles[i].getName() + "\n");
		setChanged();
		notifyObservers(sb);
	}

	/**
	 * Display the cross section {X / Y / Z} of a maze
	 * Command input: display_cross_section [index] [{X/Y/Z}] [name]
	 * If the arguments are valid, asks from the controller to print the cross section to the stream
	 * @param args, String[] - index, X/Y/Z, maze name
	 */
	@Override
	public void displayCrossSection(String[] args) {
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
		setChanged();
		notifyObservers(Array2dtoString(crossSection));
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
	 * Calls to the private method save to start the MyCompressorOutputStream
	 * Command input: save_maze [name] [file_name]
	 * @throws IllegalArgumentException, NullPointerException if something damaged with the file
	 * @param args, String[] - maze name, file name
	 */
	@Override
	public void saveMaze(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		File myFile = null;
		FileOutputStream fileOutput = null;
		try {
			myFile = new File(args[1]);
			fileOutput = new FileOutputStream(myFile);
			save(fileOutput);
		} catch (FileNotFoundException e) {
			 new IllegalArgumentException("File not found");
		}
	}
	
	/**
	 * Save the maze using MyCompressorOutputStream into the OutputStream object sent to it
	 * @param output, OutputStream
	 * @throws IllegalArgumentException
	 */
	private void save(OutputStream output) {
		MyCompressorOutputStream save = null;
		try {
			save = new MyCompressorOutputStream(output);
			save.write(this.currMaze.toByteArray());
			save.close();
		} catch (IOException e) {
			throw new NullPointerException("Can't open/close or create this file");
		}
	}
	
	/**
	 * This method get a maze name and file name and load the maze from the file (deCompressed)
	 *  Command input: load_maze [file_name] [name]
	 * @throws NullPointerException if something damaged with the file
	 * @param args, String[] - maze name, file name
	 */
	@Override
	public void loadMaze(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Illegal Arguments!");
		Maze3d loadedMaze;
		File myFile = new File(args[0]);
		MyDecompressorInputStream in = null;
		byte[] readedData = null;
		try {
			in = new MyDecompressorInputStream(new FileInputStream(myFile));
			readedData = new byte[in.read()];	// size of readedData needed
			in.read(readedData);
			in.close();
		} catch (FileNotFoundException e){
			throw new NullPointerException("File not found");
		} catch (IOException e) {
			throw new NullPointerException("File not found");
		}
		loadedMaze = new Maze3d(readedData);
		if (loadedMaze != null)
			mazeDatabase.put(args[1], new MazeRecord(loadedMaze));
	}

	/**
 	* This method get a maze name and algorithm and return the solution is ready
 	* Command input: solve [name] [algorithm]
	* @throws IllegalArgumentException, if something damaged with the maze
	* @param args, String[] - maze name, algorithm
	*/
	@Override
	public void solve(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		if (this.mazeDatabase.get(args[0]).getSolution() != null)
			displaySolution(Arrays.copyOfRange(args, 0, 1));
		else { 
			Future<Solution<Maze3d>> myFutureSolution = threadPool.submit(new Callable<Solution<Maze3d>>() {
	
				@Override
				public Solution<Maze3d> call() throws Exception {
					getMazeFromDatabase(args[0]);
					MazeRecord mazeRecord = new MazeRecord();
					Solution<Maze3d> solution = null;
					Searcher<Maze3d> searchAlgorithm = null;
					mazeRecord = getMazeDatabase().get(args[0]);
					Searchable<Maze3d> searchInMaze = new Maze3dDomain<Maze3d>(mazeRecord.getMaze());
					switch (args[1]) {
					case "bfs":
					case "BFS":
						searchAlgorithm = new BFS<Maze3d>();
						solution = searchAlgorithm.search(searchInMaze);
						break;
					case "dfs":
					case "DFS":
						searchAlgorithm = new DFS<Maze3d>();
						solution = searchAlgorithm.search(searchInMaze);
						break;
					default: throw new IllegalArgumentException("Invalid Arguments!");
					}
					mazeRecord.setSolution(solution);
					setChanged();
					notifyObservers("SolutionIsReady " + args[0]);
					return solution;
				}
			});
		}
	}

	/**
	 * This method get a maze name  and return all the states until the solution
	 * Command input: display_solution [name]
	 * @throws IllegalArgumentException if something damaged with the file
	 * @param args, String[] - maze name
	 */
	@Override
	public void displaySolution(String[] args) {
		String out;
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal Arguments!");
		getMazeFromDatabase(args[0]);
		if (this.mazeDatabase.get(args[0]).getSolution() != null)
			out = this.mazeDatabase.get(args[0]).getSolution().toString();
		else out = "There is no solution available for this maze";
		setChanged();
		notifyObservers(out);
	}
	
	/**
	 * Exit command (close threads)
	 */
	@Override
	public void exit() {
		try {
			this.threadPool.shutdownNow();
		} catch (Exception e) {
		}
	}

	/**
	 * Print String to the output stream
	 * @param out, String
	 */
	@Override
	public void printToOutputStream(String out) {
		setChanged();
		notifyObservers(out);
	}
	
	// save_maze [name] [file_name]
	@Override
	public void GZip() throws FileNotFoundException, IOException {
		/*GZIPOutputStream gz = null;
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		String[] args = new String[2];
		File file = null;
		FileOutputStream out = null;
		for (Map.Entry<String, MazeRecord> entry : this.mazeDatabase.entrySet()) {
			getMazeFromDatabase(entry.getKey());
			save(bytes);
			file = new File(entry.getKey() + ".maz");
			out = new FileOutputStream(file);
			out.write(bytes);
			out.close();
		}*/
	}

	@Override
	public Maze3d getMaze(String mazeName) {
		getMazeFromDatabase(mazeName);
		return this.currMaze;
	}

	@Override
	public Solution<Maze3d> getSolution(String mazeName) {
		getMazeFromDatabase(mazeName);
		return this.currSolution;
	}

}