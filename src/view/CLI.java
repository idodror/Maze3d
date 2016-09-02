package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

import algorithms.mazeGenerators.Position;
import controller.Command;

public class CLI implements View {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commandMap;
	private MyView view;
	
	public CLI(MyView view, BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		this.view = view;
	}

	@Override
	public void displayPosition(Position pos) {
		this.out.println("Current Position: " + pos);
	}
	
	@Override
	public void start() { 
		String commandLine = null;
		do {
			this.out.println("Choose: u(up), d(down), l(left), r(right), f(forward), b(backward), e(exit)");
			try {
				commandLine = in.readLine();
			} catch (IOException e) {
				this.out.println("IO Exception");
			}
			try {
				executeCommand(commandLine);
			} catch (IllegalArgumentException e) {
				if (!commandLine.equals("e"))
					this.out.println(e.getMessage());
				else this.out.println("Goodbye!");
			} catch (NullPointerException e) {
				this.out.println(e.getMessage());
			}
		} while (!(commandLine.equals("e")));
	}

	@Override
	public void setCommandsMap(HashMap<String, Command> commandMap) {
		this.commandMap = commandMap;
	}
	
	public void executeCommand(String commandLine) {
		Command cmd;
		String[] args = commandLine.split(" ");
		cmd = this.commandMap.get(args[0]);
		if (cmd == null)
			throw new IllegalArgumentException("Invalid Command!");
		this.view.executeCommand(cmd, Arrays.copyOfRange(args, 1, args.length));
	}

	@Override
	public void printToOutputStream(String out) {
		this.out.println(out);
	}
}
