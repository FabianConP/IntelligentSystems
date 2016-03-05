package board;

import java.util.Random;

/**
 * 
 * @author Fabian Conejo
 *
 */
public class BoardMovement {

	// 4 possible directions
	public static final int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	/**
	 * Returns the board with a
	 * random movement in the space
	 * @param board Puzzle 4x4
	 * @return	new board with the random movement
	 */
	public static long rand(long b) {
		Random rand = new Random();
		int randDir = 0;
		do {
			randDir = rand.nextInt(dir.length);
		} while (!isValidMovement(b, dir[randDir]));
		b = BoardMovement.doMovement(b, dir[randDir][0], dir[randDir][1]);
		return b;
	}

	/**
	 * Finds the space on the board
	 * row and column
	 * @param b
	 * @return return space's row and column
	 */
	public static int[] findSpace(long b) {
		int r = 0, c = 0;
		int id = 0, size = Board.SIZE;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (Board.getPos(b, id++) == Board.SPACE) {
					r = i;
					c = j;
					break;
				}
		return new int[] { r, c };
	}

	/**
	 * Check if the movement is valid
	 * @param b Puzzle 4x4
	 * @param r	Actual row 
	 * @param c	Actual column
	 * @param d Delta
	 * @return if the movement is valid
	 */
	public static boolean isValidMovement(long b, int r, int c, int[] d) {
		int size = Board.SIZE;
		return r + d[0] >= 0 && r + d[0] < size && c + d[1] >= 0 && c + d[1] < size;
	}

	/**
	 * Find the space and check 
	 * if the movement is valid
	 * @param b Puzzle 4x4
	 * @param d Delta
	 * @return if the movement is valid
	 */
	public static boolean isValidMovement(long b, int[] d) {
		int[] rc = BoardMovement.findSpace(b);
		int r = rc[0], c = rc[1];
		return isValidMovement(b, r, c, d);
	}

	/**
	 * Finds and moves the space
	 * @param b	Puzzle 4x4
	 * @param dr Delta row
	 * @param dc Delta column
	 * @return new board with the movement
	 */
	public static long doMovement(long b, int dr, int dc) {
		int[] rc = BoardMovement.findSpace(b);
		int r = rc[0], c = rc[1];
		b = doMovement(b, r, c, dr, dc);
		return b;
	}

	/**
	 * Moves the space
	 * @param b	Puzzle 4x4
	 * @param dr Delta row
	 * @param dc Delta column
	 * @return new board with the movement
	 */
	public static long doMovement(long b, int r, int c, int dr, int dc) {
		int size = Board.SIZE;
		b = Board.swap(b, r * size + c, (r + dr) * size + (c + dc));
		return b;
	}

}
