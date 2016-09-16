package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import view.UI;

public abstract class BaseWindow extends UI {
	
	protected Display display;
	protected Shell shell;
	
	protected abstract void initWidgets();
	
	@Override
	public void start() {
		run();
	}
	
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
	
	@Override
	public void exit() {
		this.display.dispose();
	}

}
