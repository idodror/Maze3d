package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Position;
import controller.Command;
import controller.Controller;

public class MyView implements View {
	private Controller controller;
	private CLI cli;

	public MyView(Controller controller, BufferedReader in, PrintWriter out) {
		this.controller = controller;
		this.cli = new CLI(this, in, out);
	}

	@Override
	public void start() {
		this.cli.start();
	}

	@Override
	public void displayPosition(Position pos) {
		this.cli.displayPosition(pos);
	}

	@Override
	public void setCommandsMap(HashMap<String, Command> commandMap) {
		this.cli.setCommandsMap(commandMap);
		
	}

	public void executeCommand(Command cmd, String[] args) {
		this.controller.executeCommand(cmd, args);		
	}
	
	@Override
	public void printToScreen(String out) {
		System.out.println(out);
	}
}
