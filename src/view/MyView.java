package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

import GUI.Maze3dWindow;
import algorithms.mazeGenerators.Maze3d;
import utils.MyJaxbUtil;

/**
 * This is the view layer of the MVP
 * get all the IO from the CLI
 * @author Gal Basre & Ido Dror
 */
public class MyView extends Observable implements View {
	
	private BufferedReader in;
	private PrintWriter out;
	private UI ui;

	/**
	 * Constructor
	 * @param controller,BufferedReader ,PrintWriter
	 */
	public MyView (BufferedReader in, PrintWriter out) {
		this.ui = chooseUIFromProperties();
		this.in = in;
		this.out = out;
	}

	private UI chooseUIFromProperties() {
		switch (MyJaxbUtil.getProperties().getUserInterface()) {
		case "CLI":
		case "cli":
			return new CLI(this);
		case "GUI":
		case "gui":
			return new Maze3dWindow(this);
		}
		return null;
	}

	/**
	 * Get the input stream
	 * @return BufferedReader 
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * Get the output stream
	 * @return PrintWriter 
	 */
	public PrintWriter getOut() {
		return out;
	}
	
	/**
	 * start
	 * call the start of cli
	 */
	@Override
	public void start() {
		this.ui.start();
	}
	
	/**
	 * executeCommand
	 * call the executeCommand of CLI
	 * @param  String
	 */
	public void executeCommand(String commandLine) {
		setChanged();
		notifyObservers(commandLine);		
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
	 * Get line from the input stream
	 * @return String, the input
	 */
	@Override
	public String getLine() {
		String line = null;
		try {
			line = this.in.readLine();
		} catch (IOException e) {
			printToOutputStream("IO Exception");
		}
		return line;
	}
	
	/**
	 * exit
	 * close all the input and output
	 */
	@Override
	public void exit() {
		try {
			in.close();
		} catch (IOException e) {
		}
		out.close();
		this.ui.exit();
	}

	@Override
	public void generatedMaze(Maze3d maze) {
		this.ui.mazeReady(maze);
	}
}
