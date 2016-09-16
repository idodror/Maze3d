package GUI;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import view.MyView;
import view.View;

public class MazeDisplay extends Canvas {
	
	private int[][] crossSection = null;

	public MazeDisplay(Composite parent, int style, MyView view, int[][] crossSection) {
		super(parent, style);
		if (crossSection != null) {
			this.addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					e.gc.setForeground(new Color(null,1,255,0));
					   e.gc.setBackground(new Color(null,255,255,255));
	
					   int width=getSize().x;
					   int height=getSize().y;
	
					   int w=width/crossSection[0].length;
					   int h=height/crossSection.length;
	
					   for(int i=0;i<crossSection.length;i++)
					      for(int j=0;j<crossSection[i].length;j++){
					          int x=j*w;
					          int y=i*h;
					          if(crossSection[i][j]!=0)
					              e.gc.fillRectangle(x,y,w,h);
					      }
				}
			});
		}
	}

	public int[][] getCrossSection() {
		return crossSection;
	}

	public void setCrossSection(int[][] crossSection) {
		this.crossSection = crossSection;
	}
	
}
