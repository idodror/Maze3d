package presenter;

/**
 * This interface represent Command to do in the model
 * @author Gal Basre & Ido Dror
 */
public interface Command {
	
	/**
	 * Execute the command and send with it arguments in String[] 
	 * @param args, String[]
	 */
	void doCommand(String[] args);
}