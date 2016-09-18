package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import view.MyView;

/**
 * class Maze3dWindow
 * extends BaseWindow
 * Data member Maze3d  myMaze, String mazeName, MazeDisplay mazeDisplay
 * @author Gal Basre & Ido Dror
 */
public class Maze3dWindow extends BaseWindow {
	
	private Maze3d myMaze;
	private String mazeName;
	private MazeDisplay mazeDisplay;
	
	/**
	 * Constructor
	 * @param view
	 */
	public Maze3dWindow(MyView view) {
		this.view = view;
		this.myMaze = null;
		this.mazeName = null;
	}

	/**
	 * initWidgets
	 * New grid layout, create a new composite and a new button to push.
	 */
	@Override
	protected void initWidgets() {
		GridLayout grid = new GridLayout(2, false);
		this.shell.setLayout(grid);
		this.shell.setText("MyMaze3d");
		
		Composite buttons = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL | SWT.FILL);
		buttons.setLayout(rowLayout);
		
		Button btnGenerateMaze = new Button(buttons, SWT.PUSH);
		this.shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setText("Generate new maze");
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateMazeWindows genWin = new GenerateMazeWindows(view);
				genWin.start(display);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	
		Button btnHint = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnHint);
		btnHint.setText("Hint");
		
		Label lblSpace1 = new Label(buttons, SWT.NONE);
		
		Label lblSolve = new Label(buttons, SWT.NONE);
		lblSolve.setText("Choose Algorithm");

		Combo cmbSolveAlgo = new Combo(buttons, SWT.READ_ONLY);
		String items[] = {"BFS", "DFS"};
		cmbSolveAlgo.setItems(items);
	
		Button btnSolve = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnSolve);
		btnSolve.setText("Solve");
		btnSolve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String chosen = cmbSolveAlgo.getText();
				executeCommand("solve " + mazeName + " " + chosen);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Label lblSpace2 = new Label(buttons, SWT.NONE);
		
		Button btnSave = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnSave);
		btnSave.setText("Save Maze");
		btnSave.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				executeCommand("save_maze " + mazeName + " " + mazeName + ".maz");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Button btnLoad = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnLoad);
		btnLoad.setText("Load Maze");
		btnLoad.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//view.executeCommand("load_maze");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Button btnExit = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnExit);
		btnExit.setText("Exit");
		btnExit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				executeCommand("exit");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		mazeDisplay = new MazeDisplay(shell, SWT.BORDER, this.view);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		mazeDisplay.setFocus();
	}
	
	/**
	 * mazeReady
	 * @param Maze3d, the maze
	 * @param String, maze name  
	 */
	@Override
	public void mazeReady(Maze3d maze, String mazeName) {
		this.mazeName = mazeName;
		this.myMaze = maze;
		this.mazeDisplay.setCharacterPosition(this.myMaze.getGoalPosition().z, this.myMaze.getStartPosition().y, this.myMaze.getStartPosition().x);
		this.mazeDisplay.setCrossSection(this.myMaze.getCrossSectionByZ(0));
		this.mazeDisplay.setMazeName(this.mazeName);
	}

	/**
	 * Get the maze name
	 * @return String, mazeName
	 */
	public String getMazeName() {
		return mazeName;
	}

	@Override
	public void printMessage(String msg) {
		MessageBox msgBox = new MessageBox(shell, SWT.ICON_INFORMATION);
		msgBox.setMessage(msg);
		msgBox.open();
	}

	@Override
	public void executeCommand(String commandLine) {
		try {
			this.view.executeCommand(commandLine);
		} catch (IllegalArgumentException e) {
			printMessage(e.getMessage());
		}
	}

	@Override
	public void displaySolution(Solution<Maze3d> solution) {
		this.mazeDisplay.displaySolutionOnCanvas(solution);
	}

	@Override
	public void move(Position pos) {
		this.mazeDisplay.setCrossSection(this.myMaze.getCrossSectionByZ(pos.z));
		this.mazeDisplay.moveTheCharacter(pos);
	}

	@Override
	public void winner() {
		this.printMessage("You are the winner!");
	}
	
}
