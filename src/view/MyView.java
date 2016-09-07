package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;


/**
 * This is the view layer of the MVC
 * get all the IO from the CLI
 */
public class MyView extends Observable implements View {
	
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;

	/**
	 * Constructor
	 * @param controller,BufferedReader ,PrintWriter
	 */
	public MyView( BufferedReader in, PrintWriter out) {
		this.cli = new CLI(this);
		this.in = in;
		this.out = out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public PrintWriter getOut() {
		return out;
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
		setChanged();
		notifyObservers(out);
	}

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
}
