package model;

public interface Model {
	public void goLeft(String name);
	public void goRight(String name);
	public void goUp(String name);
	public void goDown(String name);
	public void goForward(String name);
	public void goBackward(String name);
	public void generateMaze(String[] args);
	public void displayMaze(String[] args);
}
