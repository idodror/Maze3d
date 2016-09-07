package presenter;

import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

public class Presenter implements Observer {
	private MyView view;
	private MyModel model;
	private CommandsManager commandsManager;
	
	public Presenter(MyView view, MyModel model) {
		this.view = view;
		this.model = model;
		this.commandsManager = new CommandsManager(model, view); 
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o == view){
			String commandLine = (String)arg;
			commandsManager.executeCommand(commandLine);
		}
		else {
			String str = (String)arg;
			this.view.getOut().println(str);
		}
	}
	
}
