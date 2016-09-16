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

public class Maze3dWindow extends BaseWindow {
	
	private Maze3d myMaze;
	private String mazeName;
	private MazeDisplay mazeDisplay;
	
	public Maze3dWindow(MyView view) {
		this.view = view;
		this.myMaze = null;
		this.mazeName = null;
	}

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
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateMazeWindows genWin = new GenerateMazeWindows(view);
				genWin.start(display);
				int[][] cross = myMaze.getCrossSectionByZ(0);
				mazeDisplay = new MazeDisplay(shell, SWT.BORDER, view, cross);
				mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

	}
	
	@Override
	public void mazeReady(Maze3d maze, String mazeName) {
		this.mazeName = mazeName;
		this.myMaze = maze;
	}
	
}
