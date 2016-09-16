package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.MyView;
import view.View;

public abstract class DialogWindow {

	protected Shell shell;
	protected MyView view;
	
	protected abstract void initWidgets();
	
	public void start(Display display) {
		this.shell = new Shell(display);
		initWidgets();
		this.shell.open();
	}
	
}
