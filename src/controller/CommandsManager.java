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
		this.commandMap.put("dir", new DisplayFilesInPath());
		this.commandMap.put("display_cross_section", new DisplayCrossSection());
		this.commandMap.put("save_maze", new SaveMaze());
		this.commandMap.put("load_maze", new loadMaze());
		this.commandMap.put("solve",new solve());
	}
	
	class UpCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goUp(args);
		}
	}
	
	class DownCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goDown(args);
		}
	}
	
	class RightCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goRight(args);
		}
	}
	
	class LeftCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goLeft(args);
		}
	}
	
	class ForwardCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goForward(args);
		}
	}
	
	class BackwardCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goBackward(args);
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
	
	class DisplayFilesInPath implements Command {

		@Override
		public void doCommand(String[] args) {
			model.displayFilesInPath(args);
		}
	}
	
	class DisplayCrossSection implements Command {

		@Override
		public void doCommand(String[] args) {
			model.DisplayCrossSection(args);
		}
	}
	
	class SaveMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			model.saveMaze(args);
		}
	}
	class loadMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			model.loadMaze(args);
		}
	}
	class solve implements Command{

		@Override
		public void doCommand(String[] args) {
			model.solve(args);			
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
