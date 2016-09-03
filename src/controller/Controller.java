package controller;

import algorithms.mazeGenerators.Position;
import model.Model;
import view.View;

/**
 * This is the controller interface of the MVC
 */
public interface Controller {
	public void executeCommand(Command cmd, String[] args);
	public void displayPosition(Position pos);
	public void setModelAndView(Model model, View view);
	public void printToOutputStream(String out);
}