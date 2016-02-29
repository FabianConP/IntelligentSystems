package solver;

import board.Board;

public class Heuristic {

	public static final String H[] = { "h1", "h2", "h3" };

	public static int h1(Board board) {
		int est = 0, row = 0, col = 0, id = 0, size = board.getSize();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				row = board.getPos(id) / size;
				col = board.getPos(id) % size;
				est += Math.abs(i - row) + Math.abs(j - col);
				id++;
			}
		return est;
	}

	public static int h2(Board board) {
		int est = 0, correct = 0, id = 0, size = board.getSize();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				correct = (i * size + j);
				if (board.getPos(id) != correct)
					est++;
				id++;
			}
		return est;
	}

	public static int h3(Board board) {
		int h1 = h1(board);
		int h2 = h2(board);
		return Math.max(h1, h2);
	}
}
