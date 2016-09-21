package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
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
	
	private boolean giveMeAHint;
	private Maze3d myMaze;
	private String mazeName;
	private String[] itemsFromDatabase;
	private MazeDisplay mazeDisplay;
	private List<Point> canMoveUp;
	private List<Point> canMoveDown;
	private int[][] crossSection;
	private int[][] upperCrossSection;
	private int[][] lowerCrossSection;
	
	final String WINNER = "You are the winner!";
	
	/**
	 * Constructor
	 * @param view
	 */
	public Maze3dWindow(MyView view) {
		this.view = view;
		this.giveMeAHint = false;
		this.myMaze = null;
		this.mazeName = null;
		this.itemsFromDatabase = null;
		this.canMoveUp = null;
		this.canMoveDown = null;
		this.crossSection = null;
		this.upperCrossSection = null;
		this.lowerCrossSection = null;
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
		
		// Open in center of screen
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		// handle with the RED X
		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				executeCommand("exit");
			}
		});
		
		Composite buttons = new Composite(shell, SWT.NONE);
		//RowLayout rowLayout = new RowLayout(SWT.VERTICAL | SWT.FILL | SWT.CENTER);
		buttons.setLayout(new GridLayout(1, false));
		
		Button btnGenerateMaze = new Button(buttons, SWT.PUSH);
		this.shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setText("Generate new maze");
		btnGenerateMaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		Button btnSolve = null;
		Button btnHint = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnHint);
		btnHint.setText("Hint");
		btnHint.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnHint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				createHint();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		GridData gd = new GridData(GridData.GRAB_VERTICAL); 
		gd.verticalSpan = 5; 
		
		Label lblSpace1 = new Label(buttons, SWT.NONE);
		lblSpace1.setLayoutData(gd);
		
		Label lblSolve = new Label(buttons, SWT.NONE);
		lblSolve.setText("Choose Algorithm");
		lblSolve.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		Combo cmbSolveAlgo = new Combo(buttons, SWT.READ_ONLY | SWT.FILL);
		String items[] = {"BFS", "DFS"};
		cmbSolveAlgo.setItems(items);
		cmbSolveAlgo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	
		btnSolve = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnSolve);
		btnSolve.setText("Solve");
		btnSolve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSolve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (mazeName == null)
					view.printMessage("Generate/Load a maze first!");
				else executeCommand("solve " + mazeName + " " + cmbSolveAlgo.getText());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Label lblSpace2 = new Label(buttons, SWT.NONE);
		lblSpace2.setLayoutData(gd);
		
		Label lblLoadFromDatabase = new Label(buttons, SWT.NONE);
		lblLoadFromDatabase.setText("Load maze from database");
		lblLoadFromDatabase.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		Combo cmbLoadFromDatabase = new Combo(buttons, SWT.READ_ONLY);
		executeCommand("GetDatabaseValues");
		cmbLoadFromDatabase.setItems(this.itemsFromDatabase);
		cmbLoadFromDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnLoadFromDatabase = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnLoadFromDatabase);
		btnLoadFromDatabase.setText("Load Maze");
		btnLoadFromDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLoadFromDatabase.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (cmbLoadFromDatabase.getText() != "")
					executeCommand("display " + cmbLoadFromDatabase.getText());
				else view.printMessage("Pick a maze from the list first!");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Label lblSpace3 = new Label(buttons, SWT.NONE);
		lblSpace3.setLayoutData(gd);
		
		Button btnSave = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnSave);
		btnSave.setText("Save to file");
		btnSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		btnLoad.setText("Load from file");
		btnLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLoad.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				executeCommand("dir .");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Label lblSpace4 = new Label(buttons, SWT.NONE);
		lblSpace4.setLayoutData(gd);
		
		Button btnExit = new Button(buttons, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnExit);
		btnExit.setText("Exit");
		btnExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
	
	protected void createHint() {
		if (mazeName == null)
			view.printMessage("Generate/Load a maze first!");
		else {
			giveMeAHint = true;
			executeCommand("hint " + mazeName + " BFS");
		}
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
		this.mazeDisplay.setCharacterPosition(this.myMaze.getStartPosition());
		this.crossSection = this.myMaze.getCrossSectionByZ(0);
		setIfCanGoUpOrDown(0);
		this.mazeDisplay.setCrossSection(this.crossSection, this.canMoveUp, this.canMoveDown);
		this.mazeDisplay.setGoalPosition(this.myMaze.getGoalPosition());
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
		if (msg.equals(this.WINNER))	// new game
			this.mazeDisplay.setWinner(false);
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
	public void displaySolution(Solution<Position> solution) {
		if (this.giveMeAHint) { // the user asked for only one step from the solution (a hint)
			this.giveMeAHint = false;
			this.mazeDisplay.drawHint(solution.getStates().get(1).getValue());
		} else {
			TimerTask animationSolutionTask = new TimerTask() {
				
				int i = 0;
				
				@Override
				public void run() {
					if (i < solution.getStates().size())
						move(solution.getStates().get(i++).getValue());
					else {
						display.syncExec(new Runnable() {
	
							@Override
							public void run() {
								winner();
							}
							
						});
						cancel();
					}
				}
			};
			Timer showSolutionByAnimation = new Timer();
			showSolutionByAnimation.scheduleAtFixedRate(animationSolutionTask, 0, 500);
		}
	}

	@Override
	public void move(Position pos) {
		this.crossSection = this.myMaze.getCrossSectionByZ(pos.z);
		setIfCanGoUpOrDown(pos.z);
		this.mazeDisplay.setCrossSection(this.crossSection, this.canMoveUp, this.canMoveDown);
		this.mazeDisplay.setWhichFloorAmI(pos.z);
		this.mazeDisplay.moveTheCharacter(pos);
	}

	@Override
	public void winner() {
		this.crossSection = this.myMaze.getCrossSectionByZ(this.myMaze.getGoalPosition().z);
		this.mazeDisplay.setWhichFloorAmI(this.myMaze.getGoalPosition().z);
		setIfCanGoUpOrDown(this.myMaze.getGoalPosition().z);
		this.mazeDisplay.setCrossSection(this.crossSection, this.canMoveUp, this.canMoveDown);
		this.mazeDisplay.setWinner(true);
		this.printMessage(this.WINNER);
		this.mazeDisplay.setEnabled(false);
		this.mazeDisplay.setWinner(false);
	}
	
	private void setIfCanGoUpOrDown(int floor) {
		boolean upPossible = false;
		boolean downPossible = false;
		this.canMoveUp = new ArrayList<Point>();
		this.canMoveDown = new ArrayList<Point>();
		
		if (floor < this.myMaze.getMaze3d().length - 1) {
			this.upperCrossSection = this.myMaze.getCrossSectionByZ(floor + 1);
			upPossible = true;
		}
		if (floor > 0) {
			this.lowerCrossSection = this.myMaze.getCrossSectionByZ(floor - 1);
			downPossible = true;
		}
		
		for (int i = 0; i < this.crossSection.length; i++) {
			for (int j = 0; j < this.crossSection[0].length; j++) {
				if (upPossible)
					checkForUp(i, j);
				if (downPossible)
					checkForDown(i, j);
			}
		}
	}

	private void checkForDown(int y, int x) {
		if (this.lowerCrossSection[y][x] == this.crossSection[y][x] && this.crossSection[y][x] == Maze3d.FREE)
			this.canMoveDown.add(new Point(y, x));
	}

	private void checkForUp(int y, int x) {
		if (this.upperCrossSection[y][x] == this.crossSection[y][x] && this.crossSection[y][x] == Maze3d.FREE)
			this.canMoveUp.add(new Point(y, x));
	}

	@Override
	public void databaseValues(String databaseValues) {
		this.itemsFromDatabase = databaseValues.split(",");
	}

	@Override
	public void dirListReady(String[] dirList) {
		LoadWindow winLoad = new LoadWindow(view, dirList[0]);
		winLoad.start(display);

	}
	
	
}
