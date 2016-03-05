package solver;

import board.Board;

public class Heuristic {

	//Heuristics implemented
	//h1: Manhattan
	//h2: Furious boy
	public static final String H[] = { "h1", "h2" };

	/**
	 * Manhattan's heuristic
	 * @param board Puzzle 4x4
	 * @return estimated cost for the board
	 */
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

	/**
	 * Furious boy's heuristic
	 * @param board Puzzle 4x4
	 * @return estimated cost for the board
	 */
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

}
