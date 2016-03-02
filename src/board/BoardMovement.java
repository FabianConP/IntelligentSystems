package board;

import java.util.Random;

public class BoardMovement {

	public static final int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static long rand(long b) {
		Random rand = new Random();
		int randDir = 0;
		do {
			randDir = rand.nextInt(dir.length);
		} while (!isValidMovement(b, dir[randDir]));
		b = BoardMovement.doMovement(b, dir[randDir][0], dir[randDir][1]);
		return b;
	}

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

	public static boolean isValidMovement(long b, int r, int c, int[] d) {
		int size = Board.SIZE;
		return r + d[0] >= 0 && r + d[0] < size && c + d[1] >= 0 && c + d[1] < size;
	}

	public static boolean isValidMovement(long b, int[] d) {
		int[] rc = BoardMovement.findSpace(b);
		int r = rc[0], c = rc[1];
		return isValidMovement(b, r, c, d);
	}

	public static long doMovement(long b, int dr, int dc) {
		int[] rc = BoardMovement.findSpace(b);
		int r = rc[0], c = rc[1];
		b = doMovement(b, r, c, dr, dc);
		return b;
	}

	public static long doMovement(long b, int r, int c, int dr, int dc) {
		int size = Board.SIZE;
		b = Board.swap(b, r * size + c, (r + dr) * size + (c + dc));
		return b;
	}

}
