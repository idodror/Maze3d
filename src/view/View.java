package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Position;
import controller.Command;

public interface View {
	public void displayPosition(Position pos);
	public void start();
	public void setCommandsMap(HashMap<String, Command> commandMap);
	public void printToOutputStream(String out);
}
