package controller;

/**
 * This interface represent Command to do in the model
 */
public interface Command {
	/**
	 * Execute the command and send with it arguments in String[]
	 * @param args, String[]
	 */
	void doCommand(String[] args);
}