package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

import algorithms.mazeGenerators.Position;
import controller.Command;

/**
 * This is the CLI which presents the Viewer of the MVC
 */
public class CLI implements View {
	
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commandMap;
	private MyView view;
	
	/**
	 * Constructor
	 * @param view, MyView
	 * @param in, BufferedReader
	 * @param out, PrintWriter
	 */
	public CLI(MyView view, BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		this.view = view;
	}

	/**
	 * Print the position pos to the output stream
	 * @param pos, Position
	 */
	@Override
	public void displayPosition(Position pos) {
		this.out.println("Current Position: " + pos);
	}
	
	/**
	 * Start the CLI
	 * Menu with command line to get commands from the user
	 */
	@Override
	public void start() { 
		String commandLine = null;
		do {
			menu();
			try {
				commandLine = in.readLine();
				executeCommand(commandLine);
			} catch (IOException e) {
				this.out.println("IO Exception");
			} catch (IllegalArgumentException e) {
				if (!commandLine.equals("exit"))
					this.out.println(e.getMessage());
			} catch (NullPointerException e) {
				this.out.println(e.getMessage());
			}
		} while (!(commandLine.equals("exit")));
		this.out.println("Goodbye!");
	}

	/**
	 * Set the command map of the CLI
	 * @param commandMap, HashMap
	 */
	@Override
	public void setCommandsMap(HashMap<String, Command> commandMap) {
		this.commandMap = commandMap;
	}
	
	/**
	 * Get a commandLine by string, if it exist in the commands map, asks the controller to execute it
	 * @param commandLine, String
	 * @throws IllegalArgumentException
	 */
	public void executeCommand(String commandLine) {
		Command cmd;
		String[] args = commandLine.split(" ");
		cmd = this.commandMap.get(args[0]);
		if (cmd == null)
			throw new IllegalArgumentException("Invalid Command!");
		this.view.executeCommand(cmd, Arrays.copyOfRange(args, 1, args.length));
	}

	/**
	 * Prints the string to the output stream
	 * @param out, String to print
	 */
	@Override
	public void printToOutputStream(String out) {
		this.out.println(out);
	}
	
	/**
	 * Menu
	 */
	private void menu() {
		this.out.println("*****MENU*****");
		this.out.println("(0) <u/d/f/b/r/l> <maze_name>");
		this.out.println("(1) dir <path>");
		this.out.println("(2) generate_maze <name> <other params>");
		this.out.println("(3) display <name>");
		this.out.println("(4) display_cross_section <index> <{X,Y,Z}> <name>");
		this.out.println("(5) save_maze <name> <file name>");
		this.out.println("(6) load_maze <file name> <name>");
		this.out.println("(7) solve <name> <algorithm>");
		this.out.println("(8) display_solution <name>");
		this.out.println("(9) exit");
	}
}
