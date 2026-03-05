package snake;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Board {
	public final int boardWidth;
	public final int boardHeight;

	private Set<Tile> tiles = new HashSet<Tile>();

	public Board(int width, int height) {
		this.boardWidth = width;
		this.boardHeight = height;
		initTiles();
		iniTileNeighbours();
	}

	public void initTiles() {
		for (int i = 0; i < boardWidth; i++) { for (int j = 0; j < boardHeight; j++) { tiles.add(new Tile(i, j)); } }
	}

	public void iniTileNeighbours() {
		for (Tile tile : tiles) {
			for (Tile possibleNeighbour : tiles) {
				if (tile.isNeighbour(possibleNeighbour)) { tile.addNeighbour(possibleNeighbour); }
			}
		}
	}

	public Tile getTileAt(int x, int y) {
	    if(x >= boardWidth || x < 0) {
	        throw new IllegalArgumentException("X is either below 0 or larger that boardWidth: " + boardWidth + " x: " + x);
	    }
	    if(y >= boardHeight || y < 0) {
            throw new IllegalArgumentException("Y is either below 0 or larger that boardHeight: " + boardHeight + " y: " + y);
        }
		for (Tile tile : tiles) { if (tile.x == x && tile.y == y) { return tile; } }
		throw new IllegalStateException("Couldn't find tile at x: " + x + " y: " + y);
	}

	public Set<Tile> getTiles() {
		return Collections.unmodifiableSet(tiles);
	}
}
