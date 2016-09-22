package presenter;

import java.util.Arrays;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

	/**
	 * CommandsManager
	 * his is the CommandsManager that will control all the MVP
	 * It have Model, View and HashMap<String, Command>
	 * @author Gal Basre & Ido Dror
	 */
public class CommandsManager {
	
	private Model model;
	private View view;
	private HashMap<String, Command> commandMap;
	
	/**
	 * Contractor
	 * @param model, Model
	 * @param view, View
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
		this.commandMap.put("DirListReady", new DirListReady());
		this.commandMap.put("display_cross_section", new DisplayCrossSection());
		this.commandMap.put("save_maze", new SaveMaze());
		this.commandMap.put("load_maze", new LoadMaze());
		this.commandMap.put("hint", new Hint());
		this.commandMap.put("solve", new Solve());
		this.commandMap.put("display_solution", new DisplaySolution());
		this.commandMap.put("exit", new Exit());
		this.commandMap.put("GetDatabaseValues", new GetDatabaseValues());
		this.commandMap.put("DatabaseValues", new DatabaseValues());
		this.commandMap.put("MazeIsReady", new MazeIsReady());
		this.commandMap.put("SolutionIsReady", new SolutionIsReady());
		this.commandMap.put("DisplayMessage", new DisplayMessage());
		this.commandMap.put("CharacterMoved", new CharacterMoved());
		this.commandMap.put("WIN", new Winner());
	}
	
	/**
	 * class UpCommand - call the go up of model
	 * the command that will be up in the UI
	 * implements Command
	 */
	class UpCommand implements Command {
		
		@Override
		public void doCommand(String[] args) {
			model.goUp(args);
		}
	}
	
	/**
	 * class DownCommand - call the go down of model
	 * the command that will be down in the UI
	 * implements Command
	 */
	class DownCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goDown(args);
		}
	}
	
	/**
	 * class RightCommand - call the go Right of model
	 * the command that will be right in the UI
	 * implements Command
	 */
	class RightCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goRight(args);
		}
	}
	
	/**
	 * class LeftCommand - call the go Left of model
	 * the command that will be left in the UI
	 * implements Command
	 */
	class LeftCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goLeft(args);
		}
	}
	
	/**
	 * class ForwardCommand - call the go Forward of model
	 * the command that will be forward in the UI
	 * implements Command
	 */
	class ForwardCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goForward(args);
		}
	}
	
	/**
	 * class BackwardCommand - call the go Backward of model
	 * the command that will be backward in the UI
	 * implements Command
	 */
	class BackwardCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.goBackward(args);
		}
	}
	
	/**
	 * class GenerateMaze - call the generateMaze of model
	 * the command that will be generate_maze in the UI
	 * implements Command
	 */
	class GenerateMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			model.generateMaze(args);
		}
	}
	/**
	 * class DisplayMaze - call the displayMaze of model 
	 * the command that will be display in the UI
	 * implements Command
	 */
	
	class DisplayMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			model.displayMaze(args);	
		}
	}
	
	/**
	 * class DisplayFilesInPath - call the displayFilesInPath of model
	 * the command that will be dir in the UI
	 * implements Command
	 */
	class DisplayFilesInPath implements Command {

		@Override
		public void doCommand(String[] args) {
			model.displayFilesInPath(args);
		}
	}
	
	/**
	 * class DisplayCrossSection - call the displayCrossSection of model
	 * the command that will be display_crocss_section in the UI
	 * implements Command
	 */
	class DisplayCrossSection implements Command {

		@Override
		public void doCommand(String[] args) {
			model.displayCrossSection(args);
		}
	}
	
	/**
	 * class SaveMaze - call the saveMaze of model 
	 * the command that will be save_maze in the UI
	 * implements Command
	 */
	class SaveMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			model.saveMaze(args);
		}
	}
	
	/**
	 * class LoadMaze - call the LoadMaze of model
	 * the command that will be load_maze in the UI
	 * implements Command
	 */
	class LoadMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			model.loadMaze(args);
		}
	}
	
	/**
	 * class Hint-call the hint in the model
	 * the command that will be hint in the UI
	 * implements Command
	 */
	class Hint implements Command {

		@Override
		public void doCommand(String[] args) {
			model.hint(args);
		}
	}
	
	/**
	 * class Solve - call the solve of model
	 * the command that will be Solve-the maze in the UI
	 * implements Command
	 */
	class Solve implements Command{
		
		@Override
		public void doCommand(String[] args) {
			model.solve(args);			
		}	
	}
	
	/**
	 * class DisplaySolution - call the displaySolution of model 
	 * the command that will be display_solution in the cli
	 * implements Command
	 */
	class DisplaySolution implements Command{

		@Override
		public void doCommand(String[] args) {
			model.displaySolution(args);			
		}
	}
	
	/**
	 * class Exit - call the exit of model, call the exit of view  
	 * This command will kiil Threads/Files open on the model
	 * implements Command
	 */
	class Exit implements Command {

		@Override
		public void doCommand(String[] args) {
			model.exit();
			view.exit();
		}
		
	}
	
	/**
	 * class GetDatabaseValues - call the getDatabaseValues of the model
	 * This command get all the name of maze
	 * implements Command
	 */
	class GetDatabaseValues implements Command {

		@Override
		public void doCommand(String[] args) {
			model.getDatabaseValues(args);
		}
	}
	
	/**
	 * class DatabaseValues - call the databaseValues of the view
	 * display the DatabaseValues 
	 * implements Command
	 */
	class DatabaseValues implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args.length > 0)
				view.databaseValues(args[0]);
			else view.databaseValues("");
		}
	}
	
	/**
	 * class DirListReady - call the DirListReady of the view
	 * display the DirListReady
	 * implements Command
	 */
	class DirListReady implements Command{

		@Override
		public void doCommand(String[] args) {
			view.dirListReady(args);
		}
	}
	
	/**
	 * class MazeIsReady - the command will get the maze from the model and print it to the view
	 * this command will be used after we will generate_maze and return  maze is ready
	 * we have a command that called mazeIsReady
	 * implements Command
	 */
	class MazeIsReady implements Command {

		@Override
		public void doCommand(String[] args) {
			Maze3d maze = model.getMaze(args[0]);
			view.generatedMaze(maze, args[0]);
		}
	}
	
	/**
	 * class SolutionIsReady - the command will get the solution from the model and print it to the view
	 * this command will be used after we will solve_maze and return  solution is ready
	 * we have a command that called SolutionIsReady
	 *implements Command
	 */
	class SolutionIsReady implements Command {
		
		@Override
		public void doCommand(String[] args) {
			Solution<Position> solution = model.getSolution(args[0]);
			view.displaySolution(solution);
		}	
	}
	/**
	 * DisplayMessage - call the printMessage of the view
	 * use string builder for strings 
	 * implements Command
	 */
	class DisplayMessage implements Command {

		@Override
		public void doCommand(String[] args) {
			StringBuilder sb = new StringBuilder();
			for (String s : args) {
				sb.append(s + " ");
			}
			view.printMessage(sb.toString());
		}
	}
	
	/**
	 * class CharacterMoved -  call the move of the view with the current position
	 * get the current position 
	 * implements Command
	 */
	public class CharacterMoved implements Command {

		@Override
		public void doCommand(String[] args) {
			Position currPos = model.getCurrPosition();
			view.move(currPos);
		}
		
	}
	
	/**
	 * class Winner - call the winner of the view 
	 * use when we will have a winner in the maze
	 *  implements Command
	 */
	public class Winner implements Command {

		@Override
		public void doCommand(String[] args) {
			view.winner();
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
