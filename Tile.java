package snake;

import java.util.HashSet;
import java.util.Set;

public class Tile {
	public final int x;
	public final int y;
	
	private boolean coin = false;
	private boolean snake = false;
	
	private static Tile head = null;

	private Set<Tile> neighbours = new HashSet<Tile>();

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void addNeighbour(Tile neighbour) {
		if (neighbours.contains(neighbour)) {
			throw new IllegalArgumentException("Trying to add a neighbour that is already a neighbour");
		}
		neighbours.add(neighbour);
	}

	public boolean isNeighbour(Tile tile) {
		if (tile.equals(this)) { return false; }
		return (Math.abs(tile.x - this.x) <= 1 && tile.y == this.y)
				|| (Math.abs(tile.y - this.y) <= 1 && tile.x == this.x);
	}
	
	public int getNeighbourCount() {
		return neighbours.size();
	}
	
	public Set<Tile> getNeighbours() {
		return neighbours;
	}
	
	public Tile isHead() {
		return head;
	}
	
	public boolean hasSnake() {
		return snake;
	}
	
	public boolean hasCoin() {
		return coin;
	}
	
	public void addSnake() {
		if(coin) {throw new IllegalStateException("Trying to add snake to tile that already has coin");}
		if(snake) {throw new IllegalStateException("Trying to add snake to tile that already has a snake");}
		snake = true;
		head = this;
	}
	
	public void removeSnake() {
		if(!snake) {throw new IllegalStateException("Trying to remove snake to tile that doesn't have snake");}
		snake = false;
	}
	
	public void addCoin() {
		if(snake) {throw new IllegalStateException("Trying to add coin to tile that already has a snake");}
		if(coin) {throw new IllegalStateException("Trying to add coin to tile that already has coin");}
		coin = true;
	}
	
	public void removeCoin() {
		if(!coin) {throw new IllegalStateException("Trying to remove coin to tile that doesn't have a coin");}
		coin = false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tile)) return false;
		Tile other = (Tile) o;
		return this.x == other.x && this.y == other.y;
	}

	@Override
	public int hashCode() {
		return 31 * x + y;
	}
	
	@Override
	public String toString() {
		return "Tile: x:" + x + " y:" + y;
	}
}
