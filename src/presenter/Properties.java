package presenter;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Properties implements Serializable {
	
	private int floors = 0;
	private int cols = 0;
	private int rows = 0;
	private int threadPoolNumber = 0;
	private String generateAlgorithm = null;
	private String solveAlgorithm = null;
	
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

}
