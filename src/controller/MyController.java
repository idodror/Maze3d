package controller;

import java.util.HashMap;

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
	public void executeCommand(String commandLine) {
		this.commandsManager.executeCommand(commandLine);
	}

	@Override
	public void displayPosition(Position pos) {

		
	}

	@Override
	public void setModelAndView(Model model, View view) {
		this.model = model;
		this.view = view;
		
		this.commandsManager.setModelAndView(model, view);
	}
	
	public CommandsManager getCommandsManager() {
		return this.commandsManager;
	}

}
