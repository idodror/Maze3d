package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import view.MyView;
/**
 * class GenerateMazeWindows
 * extends DialogWindow
 * @author Gal Basre & Ido Dror
 *
 */
public class GenerateMazeWindows extends DialogWindow {
/**
 * Constructor
 * @param MyView
 */
	public GenerateMazeWindows(MyView view) {
		this.view = view;
	}
	
	/**
	 * initWidgets
	 * @Override
	 * this it the window of the generate maze.
	 * we will enter the floors, cols, rows and the name of the maze.
	 * then we will push the button to generate the maze.
	 */
	@Override
	protected void initWidgets() {
		this.shell.setText("Generate maze windows");
		this.shell.setLayout(new GridLayout(2, false));
		
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
			 * widgetSelected
			 * @Override
			 * @param SelectionEvent
			 *  take all the floors, cols, rows and make them from string to int with the parsint.
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
				} catch (NullPointerException e) {
					throw new IllegalArgumentException("Invalid Arguments!");
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Invalid Arguments!");
				}
				view.executeCommand("generate_maze " + mazeName + " " + floors + " " + rows + " " + cols);
				shell.dispose();
			}
			/**
			 * widgetDefaultSelected
			 * @param SelectionEvent
			 * @Override
			 */
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}

}
