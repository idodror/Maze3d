package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import view.MyView;

/**
 * class GenerateMazeWindows
 * extends DialogWindow
 * @author Gal Basre & Ido Dror
 */
public class GenerateMazeWindows extends DialogWindow {
	
	/**
	 * Constructor
	 * @param view, MyView
	 */
	public GenerateMazeWindows(MyView view) {
		this.view = view;
	}
	
	/**
	 * this it the window of the generate maze.
	 * we will enter the floors, cols, rows and the name of the maze.
	 * then we will push the button to generate the maze.
	 */
	@Override
	protected void initWidgets() {
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		this.shell.setText("Generate maze window");
		this.shell.setLayout(new GridLayout(2, false));
		this.shell.setSize(200, 200);
		this.shell.setBackgroundImage(new Image(null, "resources/images/backgroundSmall.png"));
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		// Open in center of screen
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		Label lblHead = new Label(shell, SWT.BOLD);
		FontData fontData = lblHead.getFont().getFontData()[0];
		Font font = new Font(display, new FontData(fontData.getName(), fontData.getHeight()+3, SWT.BOLD));
		lblHead.setFont(font);
		lblHead.setText("Enter maze dimensions");
		lblHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		Label lblFloors = new Label(this.shell, SWT.NONE);
		lblFloors.setText("Floors: ");
		Text txtFloors = new Text(this.shell, SWT.BORDER);
		txtFloors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblRows = new Label(this.shell, SWT.NONE);
		lblRows.setText("Rows: ");
		Text txtRows = new Text(this.shell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblCols = new Label(this.shell, SWT.NONE);
		lblCols.setText("Cols: ");
		Text txtCols = new Text(this.shell, SWT.BORDER);
		txtCols.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblName = new Label(this.shell, SWT.NONE);
		lblName.setText("Name: ");
		Text txtName = new Text(this.shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button btnStartGame = new Button(shell, SWT.PUSH);
		btnStartGame.setText("Start Play!");
		btnStartGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		btnStartGame.addSelectionListener(new SelectionListener() {
			
			/**
			 * take all the floors, cols, rows and make them from string to int with the parsint.
			 * @param SelectionEvent
			 */
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int floors = 0;
				int rows = 0;
				int cols = 0;
				String mazeName = null;
				try {
					floors = Integer.parseInt(txtFloors.getText());
					rows = Integer.parseInt(txtRows.getText());
					cols = Integer.parseInt(txtCols.getText());
					mazeName = txtName.getText();
				} catch (NullPointerException | NumberFormatException e) {
					view.printMessage("Invalid Arguments!");
				}
				view.executeCommand("generate_maze " + mazeName + " " + floors + " " + rows + " " + cols);
				shell.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
	}

}
