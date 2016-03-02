package solver;

import board.Board;

public class Heuristic {

	public static final String H[] = { "h1", "h2", "h3" };

	public static int h1(Long board) {
		int est = 0, row = 0, col = 0, id = 0, size = Board.SIZE;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				row = Board.getPos(board, id) / size;
				col = Board.getPos(board, id) % size;
				est += Math.abs(i - row) + Math.abs(j - col);
				id++;
			}
		return est;
	}

	public static int h2(Long board) {
		int est = 0, correct = 0, id = 0, size = Board.SIZE;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				correct = (i * size + j);
				if (Board.getPos(board, id) != correct)
					est++;
				id++;
			}
		return est;
	}

	public static int h3(Long board) {
		int h1 = h1(board);
		int h2 = h2(board);
		return Math.max(h1, h2);
	}
}
