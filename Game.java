package snake;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private final Board board = new Board(10, 10);
    private final HamiltonianCycle path = new RectangleHamiltonianCycle(board);
    private Snake snake;
    private boolean gameOver = false;

    private BoardVisualizer visualizer;

    public Game() {
        path.calcPath();
        snake = new Snake(path.getPath().get(0));
        visualizer = BoardVisualizer.show(board, path.getPath());
        addCoin();
        runGame();
    }

    public void runGame() {
        int turnNum = 0;
        while (!gameOver) {
            turn();
            visualizer.repaint();
            try {
                Thread.sleep(0); // Add delay so you can see updates
            } catch (InterruptedException e) {}
            turnNum++;
        }
        System.out.println("Game Over");
    }

    public void turn() {
        snake.takeTurn(this, path.getPath());
    }

    public void addCoin() {
    	ArrayList<Tile> tiles = new ArrayList<Tile>(board.getTiles());
    	Collections.shuffle(tiles);
    	for(Tile tile : tiles) {
    		if(!tile.hasSnake() && !tile.hasCoin()) {
    			tile.addCoin();
    			return;
    		}
    	}
    	if(snake.getLength() == board.getTiles().size()) {
        	gameOver = true;
        	return;
        }
    	throw new IllegalArgumentException("Couldn't add a new coin");
    }
}
