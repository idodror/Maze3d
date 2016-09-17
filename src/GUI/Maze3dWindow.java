package GUI;

import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
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
	 * @Override
	 * new grid layout,create a new composite and a new button to push.
	 */
	@Override
	protected void initWidgets() {
		GridLayout grid = new GridLayout(2, false);
		this.shell.setLayout(grid);
		this.shell.setText("MyMaze3d");
		
		Composite buttons = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		buttons.setLayout(rowLayout);
		
		Button btnGenerateMaze = new Button(buttons, SWT.PUSH);
		this.shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setText("Generate new maze");
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			/**
			 * widgetSelected
			 *@Override 
			 *@param SelectionEvent
			 */
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateMazeWindows genWin = new GenerateMazeWindows(view);
				genWin.start(display);
			}
			/**
			 * widgetDefaultSelected
			 * @Override 
			 *@param SelectionEvent 
			 */
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		mazeDisplay = new MazeDisplay(shell, SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	
	/**
	 * mazeReady
	 *@Override
	 *@param Maze3d, String  
	 */
	@Override
	public void mazeReady(Maze3d maze, String mazeName) {
		this.mazeName = mazeName;
		this.myMaze = maze;
		this.mazeDisplay.setCharacterPosition(this.myMaze.getStartPosition().x, this.myMaze.getStartPosition().y);
		this.mazeDisplay.setCrossSection(this.myMaze.getCrossSectionByZ(0));
	}
	
}
