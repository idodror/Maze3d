package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import view.MyView;

/**
 * MazeDisplay
 * extends Canvas
 * this class will paint the maze
 * Data Member int [][] crossSection, Character character
 * @author Gal Basre & Ido Dror
 */
public class MazeDisplay extends Canvas {
	
	private MyView view;
	private String mazeName;

	private int[][] crossSection = { {0}, {0} };
	private Character character;

	/**
	 * Constructor 
	 * @param Composite parent
	 * @param int style
	 */
	public MazeDisplay(Composite parent, int style, MyView view) {
		super(parent, style);
		
		this.view = view;
		this.mazeName = null;
		
		character = new Character();
		character.setPos(new Position(-1, -1, -1));
		
		// draw the maze
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				int x, y;
				int canvasWidth = getSize().x;
				int canvasHeight = getSize().y;
				int cellWidth = canvasWidth / crossSection[0].length;
				int cellHeight = canvasHeight / crossSection.length;
				
				e.gc.setForeground(new Color(null, 1, 255, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));

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
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				String command = null;
				switch (e.keyCode) {
				case SWT.ARROW_RIGHT:
					command = "r " + mazeName;
					break;
				case SWT.ARROW_LEFT:
					command = "l " + mazeName;
					break;
				case SWT.ARROW_UP:
					command = "b " + mazeName;
					break;
				case SWT.ARROW_DOWN:
					command = "f " + mazeName;
					break;
				case SWT.PAGE_DOWN:
					command = "d " + mazeName;
					break;
				case SWT.PAGE_UP:
					command = "u " + mazeName;
					break;
				default: break;
				}
				if (command != null) {
					view.executeCommand(command);
					redraw();
				}
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
		redrawMe();
	}

	/**
	 * setCharacterPosition
	 * @param int x
	 * @param int y
	 */
	public void setCharacterPosition(int z, int y, int x) {
		this.character.setPos(new Position(z, y, x));
		redrawMe();
	}

	public void displaySolutionOnCanvas(Solution<Maze3d> solution) {
		redrawMe();
	}

	public void moveTheCharacter(Position pos) {
		this.character.setPos(pos);
		redrawMe();
	}
	
	public void setMazeName(String mazeName) {
		this.mazeName = mazeName;
	}
	
	/**
	 * 
	 */
	private void redrawMe() {
		getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				redraw();
			}
			
		});
	}
}
