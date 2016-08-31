package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import algorithms.mazeGenerators.Position;
import controller.Controller;

public class MyView implements View {
	private Controller controller;
	private CLI cli;

	public MyView(Controller controller, BufferedReader in, PrintWriter out) {
		this.controller = controller;
		this.cli = new CLI(this, in, out, this.controller.getCommandsManager().getCommandMap());
	}

	@Override
	public void start() {
		this.cli.start();
	}

	@Override
	public void displayPosition(Position pos) {
	
	}
	
	public void executeCommand(String commandLine) {
		this.controller.executeCommand(commandLine);
	}
}
