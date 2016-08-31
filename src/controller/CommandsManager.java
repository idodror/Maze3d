package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public class CommandsManager {
	
	private Model model;
	private View view;
	private HashMap<String, Command> commandMap;

	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;
		this.commandMap = new HashMap<String, Command>();
		putAllCommandsInHashMap();
	}

	private void putAllCommandsInHashMap() {
		this.commandMap.put("u", new UpCommand());
		this.commandMap.put("d", new DownCommand());
		this.commandMap.put("r", new RightCommand());
		this.commandMap.put("l", new LeftCommand());
		this.commandMap.put("f", new ForwardCommand());
		this.commandMap.put("b", new BackwardCommand());
	}
	
	class UpCommand implements Command {

		@Override
		public void doCommand() {
			model.goUp();
		}
	}
	
	class DownCommand implements Command {

		@Override
		public void doCommand() {
			model.goDown();
		}
	}
	
	class RightCommand implements Command {

		@Override
		public void doCommand() {
			model.goRight();
		}
	}
	
	class LeftCommand implements Command {

		@Override
		public void doCommand() {
			model.goLeft();
		}
	}
	
	class ForwardCommand implements Command {

		@Override
		public void doCommand() {
			model.goForward();
		}
	}
	
	class BackwardCommand implements Command {

		@Override
		public void doCommand() {
			model.goBackward();
		}
	}

	public View getView() {
		return this.view;
	}

	public HashMap<String, Command> getCommandMap() {
		return this.commandMap;
	}
	
	public void executeCommand(String commandLine) {
		Command cmd = this.commandMap.get(commandLine);
		if (cmd == null)
			throw new IllegalArgumentException("Invalid Command!");
		cmd.doCommand();
	}

	public void setModelAndView(Model model, View view) {
		this.model = model;
		this.view = view;
	}
}
