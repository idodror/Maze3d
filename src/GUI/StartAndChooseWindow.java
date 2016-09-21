package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import utils.MyJaxbUtil;

public class StartAndChooseWindow implements Runnable {

	private Display display;
	private Shell shell;

	private void initWidgets() {
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		this.shell.setText("Start");
		
		this.shell.setLayout(new GridLayout(2, false));
		this.shell.setSize(250, 200);
		
		// Open in center of screen
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		// handle with the RED X
		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				System.exit(0);
			}
		});
		
		Label lblHead = new Label(shell, SWT.BOLD);
		FontData fontData = lblHead.getFont().getFontData()[0];
		Font font = new Font(display, new FontData(fontData.getName(), fontData.getHeight()+3, SWT.BOLD));
		lblHead.setFont(font);
		lblHead.setText("Choose your favorite UI");
		lblHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		Button btnCLI = new Button(shell, SWT.PUSH);
		btnCLI.setText("CLI");
		btnCLI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnCLI.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				MyJaxbUtil.writeXml(btnCLI.getText(), "false");
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Button btnGUI = new Button(shell, SWT.PUSH);
		btnGUI.setText("GUI");
		btnGUI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblYesNo = new Label(shell, SWT.NONE);
		lblYesNo.setText("Get up/down hints [GUI ONLY]?");
		lblYesNo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		Composite radioChoose = new Composite(shell, SWT.NONE);
		radioChoose.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		
		Button[] radios = new Button[2];
		radios[0] = new Button(radioChoose, SWT.RADIO);
		radios[0].setText("YES");
		radios[0].setBounds(10, 30, 40, 30);
		radios[0].setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		radios[0].setEnabled(false);
		radios[1] = new Button(radioChoose, SWT.RADIO);
		radios[1].setText("NO");
		radios[1].setBounds(100, 30, 40, 30);
		radios[1].setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		radios[1].setEnabled(false);
		
		btnGUI.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				radios[0].setEnabled(true);
				radios[1].setEnabled(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		radios[0].addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MyJaxbUtil.writeXml(btnGUI.getText(), "true");
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		radios[1].addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MyJaxbUtil.writeXml(btnGUI.getText(), "false");
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
	}

	@Override
	public void run() {
		start();
	}

	private void start() {
		this.display = new Display();
		this.shell = new Shell(this.display);
		
		initWidgets();
		shell.open();
		
		// main event loop
		while (!shell.isDisposed()) // windows isn't closed
			if (!this.display.readAndDispatch())
				this.display.sleep();
		this.display.close();
	}
	
}
