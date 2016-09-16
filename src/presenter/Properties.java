package presenter;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Properties implements Serializable {
	
	private int floors;
	private int cols;
	private int rows;
	private int threadPoolNumber;
	private String generateAlgorithm;
	private String solveAlgorithm;
	private String userInterface;
	
	public Properties() {
		this.floors = 0;
		this.cols = 0;
		this.rows = 0;
		this.threadPoolNumber = 0;
		this.generateAlgorithm = null;
		this.solveAlgorithm = null;
		this.userInterface = null;
	}
	
	public int getFloors() {
		return floors;
	}

	@XmlElement
	public void setFloors(int floors) {
		this.floors = floors;
	}

	public int getCols() {
		return cols;
	}

	@XmlElement
	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	@XmlElement
	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getThreadPoolNumber() {
		return threadPoolNumber;
	}

	@XmlElement
	public void setThreadPoolNumber(int threadPoolNumber) {
		this.threadPoolNumber = threadPoolNumber;
	}

	public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}

	@XmlElement
	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}

	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}

	@XmlElement
	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}

	public String getUserInterface() {
		return userInterface;
	}

	@XmlElement
	public void setUserInterface(String userInterface) {
		this.userInterface = userInterface;
	}

}
