package snake;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class Snake {

    private Deque<Tile> snake = new ArrayDeque<Tile>();

    public Snake(Tile start) {
        addToSnake(start);
    }

    public void move(Game g, Tile tile) {
        if (tile.hasCoin()) {
            tile.removeCoin();
            addToSnake(tile);
            g.addCoin();
        } else {
            removeFromSnake();
            addToSnake(tile);
        }
    }

    public void addToSnake(Tile tile) {
        tile.addSnake();
        snake.add(tile);
    }

    public void removeFromSnake() {
        Tile tail = snake.poll();
        if (tail != null) { tail.removeSnake(); }
    }

    public Tile getHead() {
        return snake.peekLast();
    }

    public Tile getTail() {
        return snake.peekFirst();
    }

    public Deque<Tile> getSnake() {
        return this.snake;
    }

    public int getLength() {
        return this.snake.size();
    }

    public void takeTurn(Game g, List<Tile> path) {
        Tile head = getHead();
        Tile tail = getTail();

        List<Tile> orederedPath = setHeadToStart(path, head);

        Tile target = orederedPath.get(0);

        int highestSkip = 0;

        for (Tile tile : orederedPath) {
            if (tile.hasSnake() && (tile != tail)) { break; }

            if (head.getNeighbours().contains(tile)) {
                int skip = orederedPath.indexOf(tile);
                if (highestSkip < skip) {
                    highestSkip = skip;
                    target = tile;
                }
            }

            if (tile.hasCoin()) { break; }
            if (tile == tail) { break; }
        }

        if (target == null) { throw new IllegalStateException("No tile was a valid target to move to"); }

        move(g, target);
    }

    public List<Tile> setHeadToStart(List<Tile> givenList, Tile start) {
        if (!givenList.contains(start)) {
            throw new IllegalArgumentException("given tile is not in the list of tiles");
        }

        Queue<Tile> queue = new ArrayDeque<Tile>(givenList);

        while (!queue.peek().equals(start)) { queue.add(queue.poll()); }

        queue.poll(); // remove tile the head is currently on

        return new ArrayList<Tile>(queue);
    }
}
