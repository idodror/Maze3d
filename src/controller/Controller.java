package controller;

import algorithms.mazeGenerators.Position;
import model.Model;
import view.View;

public interface Controller {
	public void executeCommand(String commandLine);
	public void displayPosition(Position pos);
	public void setModelAndView(Model model, View view);
	public CommandsManager getCommandsManager();
}