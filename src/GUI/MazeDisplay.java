package GUI;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import view.MyView;
import view.View;

public class MazeDisplay extends Canvas {
	
	private Maze3d maze3d;
	private MyView view;

	public MazeDisplay(Composite parent, int style, MyView view, int floors, int rows, int cols, String mazeName) {
		super(parent, style);
		this.view = view;
		
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	
	
}
