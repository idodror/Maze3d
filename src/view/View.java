package view;

/**
 * This is the view interface of the MVC
 * @author Gal Basre & Ido Dror
 */
public interface View {
	
	/**
	 * Get line from the input
	 * @return String
	 */
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
	
	public void exit();
}
