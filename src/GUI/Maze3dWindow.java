package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
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
	private Position myCurrPos;
	private List<Point> canMoveUp;
	private List<Point> canMoveDown;
	private int[][] crossSection;
	private int[][] upperCrossSection;
	private int[][] lowerCrossSection;
	
	/**
	 * Constructor
	 * @param view
	 */
	public Maze3dWindow(MyView view) {
		this.view = view;
		this.myMaze = null;
		this.mazeName = null;
		this.myCurrPos = null;
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
		btnHint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				executeCommand("WhereAmI " + mazeName);
				Maze3d newMaze = new Maze3d(myMaze.getMaze3d(), myCurrPos, myMaze.getGoalPosition());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		@SuppressWarnings("unused")
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
		
		@SuppressWarnings("unused")
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
		this.printMessage("You are the winner!");
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
	public void setPosition(Position myPos) {
		this.myCurrPos = myPos;
	}
	
}
