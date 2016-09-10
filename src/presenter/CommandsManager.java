package presenter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import model.Model;
import view.View;

/**
 * CommandsManager
 * this is the CommandsManager that will control all the mvc
 * it have Model, View and HashMap<String, Command>
 * @author Gal Basre & Ido Dror
 */
public class CommandsManager {
	
	private Model model;
	private View view;
	private HashMap<String, Command> commandMap;
	
	/**
	 * Contractor
	 * @param Model, View
	 * at the end of the Contractor we will put all the command in the hashmap
	 */
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;
		this.commandMap = new HashMap<String, Command>();
		putAllCommandsInHashMap();
	}
	
	/**
	 * putAllCommandsInHashMap
	 * add all the command to the hash map
	 */
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
		this.commandMap.put("load_maze", new LoadMaze());
		this.commandMap.put("solve", new Solve());
		this.commandMap.put("display_solution", new DisplaySolution());
		this.commandMap.put("exit", new Exit());
		this.commandMap.put("MazeIsReady", new MazeIsReady());
		this.commandMap.put("SolutionIsReady", new SolutionIsReady());
	}
	
	/**
	 * class UpCommand
	 * the command that will be up in the cli
	 *implements Command
	 */
	class UpCommand implements Command {

		/**
		 * doCommand
		 * call the go up of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.goUp(args);
		}
	}
	
	/**
	 * class DownCommand
	 * the command that will be down in the cli
	 *implements Command
	 */
	class DownCommand implements Command {

		/**
		 * doCommand
		 * call the go down of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.goDown(args);
		}
	}
	
	/**
	 * class RightCommand
	 * the command that will be right in the cli
	 *implements Command
	 */
	class RightCommand implements Command {

		/**
		 * doCommand
		 * call the go Right of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.goRight(args);
		}
	}
	
	/**
	 * class LeftCommand
	 * the command that will be left in the cli
	 *implements Command
	 */
	class LeftCommand implements Command {

		/**
		 * doCommand
		 * call the go Left of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.goLeft(args);
		}
	}
	
	/**
	 * class ForwardCommand
	 * the command that will be forward in the cli
	 *implements Command
	 */
	class ForwardCommand implements Command {

		/**
		 * doCommand
		 * call the go Forward of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.goForward(args);
		}
	}
	
	/**
	 * class BackwardCommand 
	  * the command that will be backward in the cli
	 *implements Command
	 */
	class BackwardCommand implements Command {

		/**
		 * doCommand
		 * call the go Backward of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.goBackward(args);
		}
	}
	
	/**
	 * class GenerateMaze 
	 * the command that will be generate_maze in the cli
	 *implements Command
	 */
	class GenerateMaze implements Command {

		/**
		 * doCommand
		 * call the generateMaze of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.generateMaze(args);
		}
	}
	/**
	 * class DisplayMaze 
	 * the command that will be display in the cli
	 *implements Command
	 */
	
	class DisplayMaze implements Command {

		/**
		 * doCommand
		 * call the displayMaze of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.displayMaze(args);	
		}
	}
	
	/**
	 * class DisplayFilesInPath 
	 *  * the command that will be dir in the cli
	 *implements Command
	 */
	class DisplayFilesInPath implements Command {

		/**
		 * doCommand
		 * call the displayFilesInPath of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.displayFilesInPath(args);
		}
	}
	
	/**
	 * class DisplayCrossSection 
	 *  * the command that will be display_crocss_section in the cli
	 *implements Command
	 */
	class DisplayCrossSection implements Command {

		/**
		 * doCommand
		 * call the displayCrossSection of model
		 * @param String[]
		 */		
		@Override
		public void doCommand(String[] args) {
			model.displayCrossSection(args);
		}
	}
	
	/**
	 * class SaveMaze 
	 * the command that will be save_maze in the cli
	 *implements Command
	 */
	class SaveMaze implements Command {

		/**
		 * doCommand
		 * call the saveMaze of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.saveMaze(args);
		}
	}
	
	/**
	 * class LoadMaze 
	 * the command that will be load_maze in the cli
	 *implements Command
	 */
	class LoadMaze implements Command {

		/**
		 * doCommand
		 * call the LoadMaze of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.loadMaze(args);
		}
	}
	
	/**
	 * class Solve 
	 * the command that will be Solve-the maze in the cli
	 *implements Command
	 */
	class Solve implements Command{
		
		/**
		 * doCommand
		 * call the solve of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.solve(args);			
		}
		
	}
	
	/**
	 * class DisplaySolution 
	 * the command that will be display_solution in the cli
	 *implements Command
	 */
	class DisplaySolution implements Command{

		/**
		 * doCommand
		 * call the displaySolution of model
		 * @param String[]
		 */
		@Override
		public void doCommand(String[] args) {
			model.displaySolution(args);			
		}
		
	}
	
	/**
	 * This command will kiil Threads/Files open on the model
	 */
	class Exit implements Command {

		/**
		 * doCommand
		 * call the exit of model
		 */
		@Override
		public void doCommand(String[] args) {
			model.exit();
			view.exit();
		}
		
	}
	
	class MazeIsReady implements Command {

		@Override
		public void doCommand(String[] args) {
			Maze3d maze = model.getMaze(args[0]);
			view.printToOutputStream(maze.toString());
		}
		
	}
	
	class SolutionIsReady implements Command {

		@Override
		public void doCommand(String[] args) {
			Solution<Maze3d> solution = model.getSolution(args[0]);
			view.printToOutputStream(solution.toString());	
		}
		
	}

	/**
	 * getView
	 * @return View
	 */
	public View getView() {
		return this.view;
	}

	/**
	 * getCommandMap
	 * @return HashMap<String, Command>
	 */
	public HashMap<String, Command> getCommandMap() {
		return this.commandMap;
	}
	
	/**
	 * setModelAndView
	 * @param Model ,View
	 */
	public void setModelAndView(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Get a commandLine by string, if it exist in the commands map, asks the controller to execute it
	 * @param commandLine, String
	 * @throws IllegalArgumentException
	 */
	public void executeCommand(String commandLine){
		Command cmd;
		String[] args = commandLine.split(" ");
		cmd = this.commandMap.get(args[0]);
		if (cmd == null)
			throw new IllegalArgumentException("Invalid Command!");
		cmd.doCommand(Arrays.copyOfRange(args, 1, args.length));
	}
}
