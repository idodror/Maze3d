package GUI;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/**
 * MazeDisplay
 * extends Canvas
 * this class will paint the maze
 * Data Member int [][] crossSection, Character character
 * @author Gal Basre & Ido Dror
 */
public class MazeDisplay extends Canvas {
	
	private int[][] crossSection = { {0}, {0} };
	private Character character;

	/**
	 * Constructor 
	 * @param Composite parent
	 * @param int style
	 */
	public MazeDisplay(Composite parent, int style) {
		super(parent, style);
		
		character = new Character();
		character.setPos(new Point(0, 0));
		
		// draw the maze
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				int x, y;
				int canvasWidth = getSize().x;
				int canvasHeight = getSize().y;
				int cellWidth = canvasWidth / crossSection[0].length;
				int cellHeight = canvasHeight / crossSection.length;
				
				e.gc.setForeground(new Color(null,1,255,0));
				e.gc.setBackground(new Color(null,0, 0, 0));

				for (int i = 0; i < crossSection.length; i++) {
					for (int j = 0; j < crossSection[i].length; j++) {
						x = j * cellWidth;
						y = i * cellHeight;
						if (crossSection[i][j] != 0)
							e.gc.fillRectangle(x, y, cellWidth, cellHeight);
					}
				}
				character.draw(cellWidth, cellHeight, e.gc);
			}
		});

	}

	/**
	 * setCrossSection
	 * paint the maze in crossSection [][]
	 * @param int[][] crossSection
	 */
	public void setCrossSection(int[][] crossSection) {
		this.crossSection = crossSection;
		getDisplay().syncExec(new Runnable() {
			/**
			 * run
			 * @Override
			 * redraw -paint again
			 */
			@Override
			public void run() {
				redraw();
			}
		});
	}

	/**
	 * setCharacterPosition
	 * @param int x
	 * @param int y
	 */
	public void setCharacterPosition(int x, int y) {
		this.character.setPos(new Point(x, y));
		
		getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				redraw();
			}
			
		});	
	}
	
}
