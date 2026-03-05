package snake;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HamiltonianCycle {
	private boolean found = false;
	protected Deque<Tile> path = new ArrayDeque<Tile>();
	private Set<Tile> visited = new HashSet<Tile>();
	protected final Board board;
	private Tile startTile;

	public HamiltonianCycle(Board givenBoard) {
		this.board = givenBoard;
	}

	public void calcPath() {
		long start = System.currentTimeMillis();
		this.startTile = board.getTileAt(0, 0);
		path.add(startTile);
		visited.add(startTile);
		found = explorePath(path);
		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + ((end - start) / 1000.0) + " seconds");
	}

	public boolean explorePath(Deque<Tile> currentPath) {
		System.out.println(currentPath.size());
		if (currentPath.size() == board.getTiles().size()) {
			if (currentPath.peekFirst().isNeighbour(currentPath.peekLast())) { return true; }
			return false;
		}

		Tile last = currentPath.peekLast();
		List<Tile> sorted = new ArrayList<>(last.getNeighbours());
		sorted.sort(Comparator
				.comparingInt(n -> (int) n.getNeighbours().stream().filter(t -> !visited.contains(t)).count()));
		for (Tile neighbour : sorted) {
			if (!visited.contains(neighbour)) {
				currentPath.addLast(neighbour);
				visited.add(neighbour);
				if (explorePath(currentPath)) return true;
				currentPath.removeLast(); // backtrack
				visited.remove(neighbour); // backtrack visited
			}
		}
		return false;
	}

	public boolean getFound() {
		return found;
	}

	public List<Tile> getPath() {
		return Collections.unmodifiableList(new ArrayList<>(path));
	}
}
