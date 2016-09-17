package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import view.MyView;

/**
 * abstract class DialogWindow
 * Data member Shell, MyView
 * @author Gal Basre & Ido Dror
 */
public abstract class DialogWindow {

	protected Shell shell;
	protected MyView view;
	
	/**
	 * initWidgets
	 * abstract
	 */
	protected abstract void initWidgets();
	
	/**
	 * start the window
	 * @param display
	 */
	public void start(Display display) {
		this.shell = new Shell(display);
		initWidgets();
		this.shell.open();
	}
	
}
