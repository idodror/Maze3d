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
	
	private int floors;
	private int cols;
	private int rows;
	private int threadPoolNumber;
	private String generateAlgorithm;
	private String solveAlgorithm;
	private String userInterface;
	private String zipFilePath;
	
	/**
	 * Constructor Properties
	 */
	public Properties() {
		this.floors = 0;
		this.cols = 0;
		this.rows = 0;
		this.threadPoolNumber = 0;
		this.generateAlgorithm = null;
		this.solveAlgorithm = null;
		this.userInterface = null;
		this.zipFilePath = null;
	}
	
	/**
	 * getFloors 
	 * @return int 
	 */
	public int getFloors() {
		return floors;
	}
	
	/**
	 * setFloors 
	 * @param floors, int
	 */
	@XmlElement
	public void setFloors(int floors) {
		this.floors = floors;
	}
	
	/**
	 * getCols 
	 * @return int 
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * setCols
	 * @param cols, int
	 */
	@XmlElement
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * getRows 
	 * @return int 
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * setRows 
	 * @param rows, int
	 */
	@XmlElement
	public void setRows(int rows) {
		this.rows = rows;
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

}
