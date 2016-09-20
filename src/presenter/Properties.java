package presenter;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * class Properties implements Serializable
 * This class is for the xml file
 * All the data member  are the decision  of the user
 * Data Member int floor, int cols, int rows, int threadPoolNumber
 *  Data Member String solveAlgorithm, String userInterface
 * @author Gal Basre & Ido Dror
 */
@SuppressWarnings("serial")
@XmlRootElement
public class Properties implements Serializable {
	
	private int threadPoolNumber;
	private String generateAlgorithm;
	private String solveAlgorithm;
	private String userInterface;
	private String zipFilePath;
	private String GUIUpDownHints;
	
	/**
	 * Constructor Properties
	 */
	public Properties() {
		this.threadPoolNumber = 0;
		this.generateAlgorithm = null;
		this.solveAlgorithm = null;
		this.userInterface = null;
		this.zipFilePath = null;
		this.GUIUpDownHints = null;
	}

	/**
	 * getThreadPoolNumber 
	 * @return int 
	 */
	public int getThreadPoolNumber() {
		return threadPoolNumber;
	}

	/**
	 * setThreadPoolNumber
	 * @param threadPoolNumber, int 
	 */
	@XmlElement
	public void setThreadPoolNumber(int threadPoolNumber) {
		this.threadPoolNumber = threadPoolNumber;
	}

	/**
	 * getGenerateAlgorithm
	 * @return String
	 */
	public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}

	/**
	 * setGenerateAlgorithm 
	 * @param generateAlgorithm, String 
	 */
	@XmlElement
	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}
	
	/**
	 * getSolveAlgorithm 
	 * @return String
	 */
	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}

	/**
	 * setSolveAlgorithm 
	 * @param solveAlgorithm, String  
	 */
	@XmlElement
	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}
	
	/**
	 * getUserInterface
	 * @return String
	 */
	public String getUserInterface() {
		return userInterface;
	}

	/**
	 * setUserInterface
	 * @param userInterface, String
	 */
	@XmlElement
	public void setUserInterface(String userInterface) {
		this.userInterface = userInterface;
	}

	/**
	 * getZipFilePath
	 * @return String
	 */
	public String getZipFilePath() {
		return zipFilePath;
	}

	/**
	 * 
	 * @param zipFilePath, String
	 */
	@XmlElement
	public void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
	}
	
	public String getGUIUpDownHints() {
		return GUIUpDownHints;
	}

	@XmlElement
	public void setGUIUpDownHints(String gUIUpDownHints) {
		GUIUpDownHints = gUIUpDownHints;
	}

}
