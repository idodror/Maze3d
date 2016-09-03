package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Position;
import controller.Command;
import controller.Controller;

/**
 * This is the view layer of the MVC
 * get all the io from the cli
 */
public class MyView implements View {
	private Controller controller;
	private CLI cli;

	/**
	 * Constructor
	 * @param controller,BufferedReader ,PrintWriter
	 */
	public MyView(Controller controller, BufferedReader in, PrintWriter out) {
		this.controller = controller;
		this.cli = new CLI(this, in, out);
	}

	/**
	 * start
	 * call the start of cli
	 */
	@Override
	public void start() {
		this.cli.start();
	}

	/**
	 * displayPosition
	 * call the displayPosition of cli
	 * @param position
	 */
	@Override
	public void displayPosition(Position pos) {
		this.cli.displayPosition(pos);
	}
	
	/**
	 * setCommandsMap
	 * call the setCommandsMap of cli
	 * @param HashMap<String, Command>
	 */
	@Override
	public void setCommandsMap(HashMap<String, Command> commandMap) {
		this.cli.setCommandsMap(commandMap);
		
	}
	
	/**
	 * executeCommand
	 * call the executeCommand of cli
	 * @param Command, String[]
	 */
	public void executeCommand(Command cmd, String[] args) {
		this.controller.executeCommand(cmd, args);		
	}
	
	/**
	 * printToOutputStream
	 * call the printToOutputStream of cli
	 * @param String
	 */
	@Override
	public void printToOutputStream(String out) {
		this.cli.printToOutputStream(out);
	}
}
