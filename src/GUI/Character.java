package GUI;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import algorithms.mazeGenerators.Position;
/**
 * class Character
 * @author Gal Basre & Ido Dror
 */
public class Character {
	private Position pos;
	private Image img;
	
	public Character() {
		this.img = new Image(null, "images/android.png");
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, cellWidth * pos.x, cellHeight * pos.y, cellWidth, cellHeight);
	}
	
}
