package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Position;
import controller.Command;

public class CLI implements View {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commandMap;
	private MyView view;
	
	public CLI(MyView view, BufferedReader in, PrintWriter out, HashMap<String, Command> commandMap) {
		this.in = in;
		this.out = out;
		this.commandMap = commandMap;
		this.view = view;
	}

	@Override
	public void displayPosition(Position pos) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void start() { 
		String commandLine = null;
		do {
			System.out.println("Choose: u(up), d(down), l(left), r(right), f(forward), b(backward), e(exit)");
			try {
				commandLine = in.readLine();
			} catch (IOException e) {
				System.out.println("IO Exception");
			}
			this.view.executeCommand(commandLine);
		} while (!(commandLine.equals("e")));
	}
}
