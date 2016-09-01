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
		this.commandMap.put("generate_maze", new GenerateMaze());
		this.commandMap.put("display", new DisplayMaze());
	}
	
	class UpCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goUp(args[0]);
		}
	}
	
	class DownCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goDown(args[0]);
		}
	}
	
	class RightCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goRight(args[0]);
		}
	}
	
	class LeftCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goLeft(args[0]);
		}
	}
	
	class ForwardCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goForward(args[0]);
		}
	}
	
	class BackwardCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goBackward(args[0]);
		}
	}
	
	class GenerateMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			model.generateMaze(args);
		}
	}
	
	class DisplayMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			model.displayMaze(args);	
		}
	}

	public View getView() {
		return this.view;
	}

	public HashMap<String, Command> getCommandMap() {
		return this.commandMap;
	}

	public void setModelAndView(Model model, View view) {
		this.model = model;
		this.view = view;
	}
}
