package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * This is the CLI which presents the Viewer of the MVP
 * @author Gal Basre & Ido Dror
 */
public class CLI extends UI {
	
	private MyView view;
	private Thread ioThread;
	
	/**
	 * Constructor
	 * @param view, MyView
	 */
	public CLI(MyView view) {
		this.view = view;
		this.ioThread = null;
	}

	/**
	 * Start the CLI
	 * Menu with command line to get commands from the user
	 */
	public void start() { 
		run();
	}
	
	@Override
	public void run() {
		this.ioThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				String commandLine = null;
				do {
					menu();
					commandLine = getLine();
					try {
						executeCommand(commandLine);
					} catch (IllegalArgumentException e) {
						printMessage(e.getMessage());
					} catch (NullPointerException e) {
						printMessage(e.getMessage());
					}
				} while (!(commandLine.equals("exit")));
			}	
		});
		this.ioThread.start();
	}
	
	/**
	 * Execute the commandLine to the View
	 * @param commandLine, String
	 */
	@Override
	public void executeCommand(String commandLine) {
		this.view.executeCommand(commandLine);
	}
	
	/**
	 * Exit from the program and close threads and files
	 */
	public void exit() {
		this.ioThread.interrupt();
	}
	
	/**
	 * Menu
	 */
	private void menu() {
		this.view.printMessage("*****MENU*****");
		this.view.printMessage("(0) <u/d/f/b/r/l> <maze_name>");
		this.view.printMessage("(1) dir <path>");
		this.view.printMessage("(2) generate_maze <name> <other params>");
		this.view.printMessage("(3) display <name>");
		this.view.printMessage("(4) display_cross_section <index> <{X,Y,Z}> <name>");
		this.view.printMessage("(5) save_maze <name> <file name>");
		this.view.printMessage("(6) load_maze <file name> <name>");
		this.view.printMessage("(7) solve <name> <algorithm>");
		this.view.printMessage("(8) display_solution <name>");
		this.view.printMessage("(9) exit");
	}
	
	/**
	 * Print to the output stream
	 * @param out, String
	 */
	public void printMessage(String msg) {
		this.view.printToOutputStream(msg);
	}
	
	/**
	 * Get line input from the view
	 * @return String, the input
	 */
	public String getLine() {
		return this.view.getLine();
	}

	@Override
	public void mazeReady(Maze3d maze, String mazeName) {
		this.printMessage(maze.toString());
	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		this.printMessage(solution.toString());
	}

	@Override
	public void move(Position pos) {
		this.printMessage(pos.toString());
	}

	@Override
	public void winner() {
		this.printMessage("You are the winner!");
	}

	@Override
	public void databaseValues(String databaseValues) {
		this.printMessage(databaseValues);
	}
}
