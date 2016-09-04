package controller;

import algorithms.mazeGenerators.Position;
import model.Model;
import view.View;

/**
 * This is the controller layer of the MVC
 * Makes all the management  of the game
 * Holds the model,view and commandsManager
 */
public class MyController implements Controller {
	
	private Model model;
	private View view;
	private CommandsManager commandsManager;
	
	/**
	 * Constructor
	 */
	public MyController() {
		commandsManager = new CommandsManager(this.model, this.view);
	}
	
	/**
	 * executeCommand
	 * call the do command of command
	 * @param Command, String[]
	 */
	@Override
	public void executeCommand (Command cmd, String[] args) {
		cmd.doCommand(args);
	}
	
	/**
	 * displayPosition
	 * call the displayPosition of view 
	 * @param Position
	 */
	@Override
	public void displayPosition(Position pos) {
		this.view.displayPosition(pos);
	}
	/**
	 * setModelAndView
	 * set the model ,view and the commend manager
	 * @param Model ,View
	 */

	@Override
	public void setModelAndView(Model model, View view) {
		this.model = model;
		this.view = view;
		this.view.setCommandsMap(this.commandsManager.getCommandMap());
		this.commandsManager.setModelAndView(model, view);
	}

	/**
	 * printToOutputStream
	 * call printToOutputStream of view
	 * @param String
	 */
	@Override
	public void printToOutputStream(String out) {
		this.view.printToOutputStream(out);
	}

}
