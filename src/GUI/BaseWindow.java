package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import view.UI;
/**
 * abstract class BaseWindow
 * extends UI
 * Data member Display, Shell
 * @author Gal Basre & Ido Dror
 */
public abstract class BaseWindow extends UI {
	
	protected Display display;
	protected Shell shell;
	/**
	 * initWidgets
	 * abstract
	 */
	protected abstract void initWidgets();
	
	/**
	 * start
	 * call run
	 * @override 
	 */
	@Override
	public void start() {
		run();
	}
	
	/**
	 *run
	 *this is the main loop of the gui
	 *@override
	 */
	@Override
	public void run() {
		this.display = new Display();
		this.shell = new Shell(this.display);
		
		initWidgets();
		shell.open();
		
		// main event loop
		while (!shell.isDisposed()) // windows isn't closed
			if (!this.display.readAndDispatch())
				this.display.sleep();
		exit();
	}
	
	/**
	 * exit	- dispose the display
	 */
	@Override
	public void exit() {
		this.display.dispose();
	}

}
