package presenter;

import java.util.Observable;
import java.util.Observer;
import model.MyModel;
import view.MyView;

/**
 * This is the Presenter interface of the MVP
 * @author Gal Basre & Ido Dror
 */
public class Presenter implements Observer {
	private MyView view;
	private MyModel model;
	private CommandsManager commandsManager;
	
	/**
	 * Constructor
	 * @param view, model
	 */
	public Presenter(MyView view, MyModel model) {
		this.view = view;
		this.model = model;
		this.commandsManager = new CommandsManager(model, view); 
	}
	
	/**
	 * update 
	 * check if the update from the model or from the view
	 * @param Observable, Object
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o == view){
			String commandLine = (String)arg;
			commandsManager.executeCommand(commandLine);  
		}
		if (o == model) {
			String commandLine = (String)arg;
			commandsManager.executeCommand(commandLine);
		}
	}
	
}
