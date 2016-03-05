import java.io.PrintStream;

import board.Board;
import solver.AStarBoard;
import solver.Heuristic;
import solver.IDS;

public class Main {

	static final int MIN_AMOUNT_RAND_MOV = 10;
	static final int MAX_AMOUNT_RAND_MOV = 25;
	static final int TEST_PER_BOARD = 30;
	static final int NUM_HEURISTICS = 2;
	static final int NUM_IDS = 1;

	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream("Log.txt"));
		StringBuilder r = new StringBuilder();
		for (int i = MIN_AMOUNT_RAND_MOV; i <= MAX_AMOUNT_RAND_MOV; i += 5) {
			r.append("Rand" + i + "\n");
			for (int j = 0; j < TEST_PER_BOARD; j++) {
				long board = Board.FIRST_BOARD;
				for (int rr = 0; rr < i; rr++)
					board = Board.randMovement(board);
				for (int k = 0; k < NUM_HEURISTICS; k++)
					r.append(AStarBoard.solve(board, Heuristic.H[k]) + " ");
				for (int k = NUM_HEURISTICS; k < NUM_HEURISTICS + NUM_IDS; k++)
					r.append(IDS.solve(Board.FIRST_BOARD, board) + " ");
				r.setLength(r.length() - 1);
				r.append("\n");
			}
		}
		System.out.println(r);
	}
}
