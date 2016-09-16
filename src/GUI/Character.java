package GUI;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

public class Character {
	private Point pos;
	private Image img;
	
	public Character() {
		img = new Image(null, "images/android.png");
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}
	
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, cellWidth * pos.x, cellHeight * pos.y, cellWidth, cellHeight);
	}
	
	public void moveRight() {
		pos.x++;
	}
	
}
