package board;

import java.util.HashMap;
import java.util.Stack;

/**
 * 
 * @author Fabian Conejo
 *
 */
public class Board {

	public static final int SPACE = 15;
	public static final int SIZE = 4;
	public static final long FIRST_BOARD = 1070935975390360080L;

	/**
	 * Returns the value at the position on the 
	 * board
	 * @param board Puzzle 4x4
	 * @param pos 	Position to return
	 * @return value at the position 
	 */
	public static int getPos(long board, int pos) {
		int mask = 0, num = 0;
		long b = board;
		for (int i = 0; i < 15; i++) {
			num = (int) (b % 16L);
			if (i == pos)
				return num;
			mask |= (1 << num);
			b /= 16L;
		}
		for (int i = 0, j = 1; i < 16; i++, j <<= 1)
			if ((mask & j) == 0)
				return i;
		return 0;
	}

	
	/**
	 * Returns the new board with the
	 * change at the position on the board
	 * @param board Puzzle 4x4
	 * @param pos	Position to change
	 * @param value New value at the position
	 * @return new board with the change
	 */
	public static long setPos(long board, int pos, int value) {
		if (pos < 15) {
			long rp = (1L << (pos * 4L)) - 1L, rigth = rp & board, v = (long) value;
			board >>= (4L * (pos + 1));
			board <<= (4L * (pos + 1));
			board |= (v << (pos * 4L));
			board |= rigth;
		}
		return board;
	}

	/**
	 * Swaps the values from two positions
	 * on the board
	 * @param board Puzzle 4x4
	 * @param posA	First position
	 * @param posB	Second position
	 * @return	new boards with the swaps
	 */
	public static long swap(long board, int posA, int posB) {
		int aux = Board.getPos(board, posA);
		board = Board.setPos(board, posA, Board.getPos(board, posB));
		board = setPos(board, posB, aux);
		return board;
	}

	/**
	 * Returns the puzzle with format
	 * @param board	Puzzle 4x4
	 * @return puzzle formatted
	 */
	public static String toString(long board) {
		int size = Board.SIZE, id = 0, num = 0;
		StringBuilder r = new StringBuilder("");
		for (int i = 0; i < size; i++) {
			r.append("[");
			for (int j = 0; j < size; j++) {
				num = Board.getPos(board, id++);
				if (num == Board.SPACE)
					r.append("  ,");
				else
					r.append(String.format("%2d", num) + ",");
			}
			r.setLength(r.length() - 1);
			r.append("]\n");
		}
		return r.toString();
	}

	/**
	 * Returns the board with a
	 * random movement in the space
	 * @param board Puzzle 4x4
	 * @return	new board with the random movement
	 */
	public static long randMovement(long board) {
		board = BoardMovement.rand(board);
		return board;
	}

	/**
	 * Returns the path from  unsolved 
	 * to correct board
	 * @param initialBoard	Correct board
	 * @param pastStates	Predecessors 
	 * @return	path from unsolved to correct board
	 */
	public static String printPath(long initialBoard, HashMap<Long, Long> pastStates) {
		StringBuilder r = new StringBuilder("Path\n");
		long board = initialBoard;
		Stack<Long> s = new Stack<>();
		while (board != -1) {
			s.push(board);
			board = pastStates.get(board);
		}
		while (!s.isEmpty())
			r.append(Board.toString(s.pop()) + "\n");
		return r.toString();
	}
}
