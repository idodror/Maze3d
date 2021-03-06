package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import view.MyView;

/**
 * abstract class DialogWindow
 * Data member Display, Shell, MyView
 * @author Gal Basre & Ido Dror
 */
public abstract class DialogWindow {

	protected Display display;
	protected Shell shell;
	protected MyView view;
	
	/**
	 * initWidgets
	 * abstract
	 */
	protected abstract void initWidgets();
	
	/**
	 * start the window
	 * @param display, Display
	 */
	public void start(Display display) {
		this.display = display;
		this.shell = new Shell(this.display);
		initWidgets();
		this.shell.open();
	}
	
}
