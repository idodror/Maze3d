package view;

/**
 * This is the view interface of the MVC
 */
public interface View {
	
	public String getLine();
	
	/**
	 * Start the ui
	 */
	public void start();
	
	/**
	 * Print the string to output stream
	 * @param out, String
	 */
	public void printToOutputStream(String out);	
}
