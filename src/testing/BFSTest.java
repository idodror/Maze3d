package testing;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.Maze3dDomain;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;

public class BFSTest {

	int[][][] maze = { { { 1, 1, 1, 1, 1 },
						{ 1, 0, 1, 0, 1 },
						{ 1, 0, 1, 0, 1 },
						{ 0, 0, 0, 0, 1 },
						{ 1, 1, 1, 1, 1 } }	};
	Position startPosition = new Position(0, 3, 1);
	Position goalPosition = new Position(0, 1, 3);
	Searchable<Position> mazeSearchable = new Maze3dDomain<>(new Maze3d(maze, startPosition, goalPosition));
	Searcher<Position> bfs = new BFS<Position>();
	Solution<Position> sol = bfs.search(mazeSearchable);
	
	@Test
	public void shouldReturnCountOfPathFromStartToFinishPosition() {
		assertEquals(5, sol.getStates().size());
	}
	
	@Test
	public void shouldReturnTheGoalStateOfTheMaze() {
		assertEquals(goalPosition, sol.getStates().get(sol.getStates().size()-1).getValue());
	}
	
	@Test
	public void shouldReturnTheStartStateOfTheMaze() {
		assertEquals(startPosition, sol.getStates().get(0).getValue());
	}

	@Test
	public void checkIfReturnsZeroNeighborsOfInvalidPosition() {
		assertEquals(0, mazeSearchable.getAllPossibleStates(new State<Position>(new Position(-1, 0, 0), null)).size());
	}
	
	@Test
	public void checkIfTheEvaluatedNumberOfNodesOfTheSolutionIsValid() {
		assertEquals(true, bfs.getNumberOfNodesEvaluated() >= sol.getStates().size());
	}
	
	@Test
	public void shouldReturnAllThePossibleMovesOfState() {
		Position posToBeChecked = new Position(0, 3, 2);
		List<State<Position>> desiredNeigborsList = new ArrayList<State<Position>>();
		List<State<Position>> possibleNeiborsFromTheAlgo = mazeSearchable.getAllPossibleStates(new State<Position>(posToBeChecked, null));
		desiredNeigborsList.add(new State<Position>(new Position(0, 3, 1), new State<Position>(posToBeChecked, null)));	// neighbor 1
		desiredNeigborsList.add(new State<Position>(new Position(0, 3, 3), new State<Position>(posToBeChecked, null)));	// neighbor 2

		// if and only if the lists are contains each other fully
		assertEquals(true, desiredNeigborsList.containsAll(possibleNeiborsFromTheAlgo));
		assertEquals(true, possibleNeiborsFromTheAlgo.containsAll(desiredNeigborsList));
	}
}
