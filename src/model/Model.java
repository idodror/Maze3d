package model;

/**
 * This is the Model interface of the MVC
 */
public interface Model {
	public void goLeft(String[] args);
	public void goRight(String[] args);
	public void goUp(String[] args);
	public void goDown(String[] args);
	public void goForward(String[] args);
	public void goBackward(String[] args);
	
	/**
	 * Generate new maze
	 * @param args
	 */
	public void generateMaze(String[] args);
	
	/**
	 * Display exist maze
	 * @param args
	 */
	public void displayMaze(String[] args);
	
	/**
	 * Display all files and folders in path
	 * @param args
	 */
	public void displayFilesInPath(String[] args);
	
	/**
	 * Display cross section by {X/Y/Z} of a maze
	 * @param args
	 */
	public void displayCrossSection(String[] args);
	
	/**
	 * Save the maze
	 * @param args, maze name,file name
	 */
	public void saveMaze(String[] args);
	
	/**
	 * load the maze
	 * @param args, maze name,file name
	 */
	public void loadMaze(String[] args);
	
	/**
	 * solve the maze
	 * @param args, maze name,algorithm
	 */
	public void solve(String[] args);
	
	/**
	 * display the solution
	 * @param args, maze name
	 */
	public void displaySolution(String[] args);
	
	public void printToOutputStream(String out);
}
