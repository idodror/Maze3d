package controller;

import algorithms.mazeGenerators.Position;
import model.Model;
import view.View;

public class MyController implements Controller {
	private Model model;
	private View view;
	private CommandsManager commandsManager;
	
	public MyController() {
		commandsManager = new CommandsManager(this.model, this.view);
	}

	@Override
	public void executeCommand (Command cmd, String[] args) {
		cmd.doCommand(args);
	}

	@Override
	public void displayPosition(Position pos) {
		this.view.displayPosition(pos);
	}

	@Override
	public void setModelAndView(Model model, View view) {
		this.model = model;
		this.view = view;
		this.view.setCommandsMap(this.commandsManager.getCommandMap());
		this.commandsManager.setModelAndView(model, view);
	}

	@Override
	public void printToScreen(String out) {
		this.view.printToScreen(out);
		
	}

}
