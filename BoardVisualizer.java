package snake;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class BoardVisualizer extends JPanel {
	private final Board board;
	private final List<Tile> hamiltonianPath;
	private final int TILE_SIZE;

	public BoardVisualizer(Board board, List<Tile> list) {
		this.board = board;
		TILE_SIZE = 500 / board.boardHeight;
		this.hamiltonianPath = list;
		setPreferredSize(new Dimension(board.boardWidth * TILE_SIZE, board.boardHeight * TILE_SIZE));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int y = 0; y < board.boardHeight; y++) {
			for (int x = 0; x < board.boardWidth; x++) {
				Tile tile = board.getTileAt(x, y);
				int neighbours = tile != null ? tile.getNeighbourCount() : 0;

				if (tile != null && tile.isHead().equals(tile)) {
					g.setColor(Color.BLUE);
				} else if (tile != null && tile.hasCoin()) {
					g.setColor(Color.ORANGE);
				} else if (tile != null && tile.hasSnake()) {
					g.setColor(Color.GREEN);
				} else if (neighbours > 0) {
					g.setColor(new Color(200 - neighbours * 30, 255 - neighbours * 20, 255));
				} else {
					g.setColor(Color.WHITE);
				}

				g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				g.setColor(Color.BLACK);
				g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}

		/*if (hamiltonianPath != null && hamiltonianPath.size() > 1) {
			g.setColor(Color.RED);
			Iterator<Tile> it = hamiltonianPath.iterator();
			Tile from = it.next();
			Tile first = from;
			Tile last = null;

			while (it.hasNext()) {
				Tile to = it.next();
				int x1 = from.x * TILE_SIZE + TILE_SIZE / 2;
				int y1 = from.y * TILE_SIZE + TILE_SIZE / 2;
				int x2 = to.x * TILE_SIZE + TILE_SIZE / 2;
				int y2 = to.y * TILE_SIZE + TILE_SIZE / 2;
				g.drawLine(x1, y1, x2, y2);
				from = to;
			}

			last = from;
			if (last.getNeighbours().contains(first)) {
				g.drawLine(last.x * TILE_SIZE + TILE_SIZE / 2, last.y * TILE_SIZE + TILE_SIZE / 2,
						first.x * TILE_SIZE + TILE_SIZE / 2, first.y * TILE_SIZE + TILE_SIZE / 2);
			}
		}*/
	}

	public static BoardVisualizer show(Board board, List<Tile> list) {
		JFrame frame = new JFrame("Snake Board Visualizer");
		BoardVisualizer visualizer = new BoardVisualizer(board, list);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(visualizer);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return visualizer;
	}
}
