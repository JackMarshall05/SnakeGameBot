package snake;

public class RectangleHamiltonianCycle extends HamiltonianCycle {

	public RectangleHamiltonianCycle(Board givenBoard) {
		super(givenBoard);
		if(givenBoard.boardHeight % 2 != 0) {
			throw new IllegalArgumentException("Rectangel must have even height");
		}
	}

	public void calcPath() {
	    int mRX = (int)(board.boardWidth / 2);
	    int mLX = mRX - 1;
	    int mTY = (int)(board.boardHeight / 2);
	    int mBY = mTY - 1;
        
        for(int y = 0; y < mTY; y++) {
            path.add(board.getTileAt(mLX, y));
        }
        
        for(int y = mTY - 1; y >= 0; y--) {
            path.add(board.getTileAt(mRX, y));
        }
		
		for (int y = 0; y < board.boardHeight; y++) {
			for (int x = mRX + 1; x < board.boardWidth; x++) { 
				if(y % 2 == 0) {
					path.add(board.getTileAt(x, y)); 
				}else {
					path.add(board.getTileAt(board.boardHeight - x + mRX, y)); 
				}
			}
		}
		
		for(int y = board.boardHeight - 1; y > mBY; y--) {
            path.add(board.getTileAt(mRX, y));
        }
		
		for(int y = mBY + 1; y < board.boardHeight; y++) {
            path.add(board.getTileAt(mLX, y));
        }
		
		for (int y = board.boardHeight - 1; y >= 0; y--) {
            for (int x = mLX; x > 0; x--) { 
                if(y % 2 == 0) {
                    path.add(board.getTileAt(mLX - x, y)); 
                }else {
                    path.add(board.getTileAt(x - 1, y)); 
                }
            }
        }
	}
}
