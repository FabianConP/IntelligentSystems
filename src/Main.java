import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

import board.Board;
import solver.AStarBoard;
import solver.Heuristic;
import solver.IDS;

public class Main {

	static final int MIN_AMOUNT_RAND_MOV = 10;
	static final int MAX_AMOUNT_RAND_MOV = 25;
	static final int TEST_PER_BOARD = 25;
	static final int NUM_HEURISTICS = 3;
	static final int NUM_IDS = 1;

	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream("Log.txt"));
		StringBuilder r = new StringBuilder();
		long correctBoard = Board.FIRST_BOARD;
		for (int i = MIN_AMOUNT_RAND_MOV; i <= MAX_AMOUNT_RAND_MOV; i += 5) {
			System.out.println(i);
			ArrayList<Integer>[] h = new ArrayList[4];
			for (int j = 0; j < NUM_HEURISTICS + NUM_IDS; j++)
				h[j] = new ArrayList<>();
			for (int j = 0; j < TEST_PER_BOARD; j++) {
				long board = Board.FIRST_BOARD;
				for (int rr = 0; rr < i; rr++)
					board = Board.randMovement(board);
				for (int k = 0; k < NUM_HEURISTICS; k++)
					h[k].add(AStarBoard.solve(correctBoard, board, Heuristic.H[k]));
				for (int k = NUM_HEURISTICS; k < NUM_HEURISTICS + NUM_IDS; k++)
					h[k].add(IDS.solve(correctBoard, board));
			}
			double[] mean = new double[NUM_HEURISTICS + NUM_IDS];
			double[] median = new double[NUM_HEURISTICS + NUM_IDS];
			double[] devMean = new double[NUM_HEURISTICS + NUM_IDS];
			double[] devMedian = new double[NUM_HEURISTICS + NUM_IDS];
			int a = TEST_PER_BOARD / 2;
			for (int j = 0; j < NUM_HEURISTICS + NUM_IDS; j++)
				Collections.sort(h[j]);
			for (int k = 0; k < NUM_HEURISTICS + NUM_IDS; k++) {
				for (int j = 0; j < TEST_PER_BOARD; j++)
					mean[k] += h[k].get(j);
				mean[k] /= TEST_PER_BOARD;
				median[k] = (h[k].get(a) + h[k].get(a - 1)) / 2.0;
				for (int j = 0; j < TEST_PER_BOARD; j++) {
					devMean[k] += Math.pow(h[k].get(j) - mean[k], 2);
					devMedian[k] += Math.pow(h[k].get(j) - median[k], 2);
				}
				devMean[k] = Math.sqrt(devMean[k] / TEST_PER_BOARD);
				devMedian[k] = Math.sqrt(devMedian[k] / TEST_PER_BOARD);
			}
			// r.append("Random movements: " + i + "\n\n");
			// for (int j = 0; j < NUM_HEURISTICS + NUM_IDS; j++)
			// r.append("Mean " + (j < NUM_HEURISTICS ? ('h' + j) : "IDS") + ":
			// " + mean[j]+"\n");
			// for (int j = 0; j < NUM_HEURISTICS + NUM_IDS; j++)
			// r.append("Median " + (j < NUM_HEURISTICS ? ('h' + j) : "IDS") +
			// ": " + median[j]+"\n");
			// for (int j = 0; j < NUM_HEURISTICS + NUM_IDS; j++)
			// r.append("Standard deviation mean " + (j < NUM_HEURISTICS ? ('h'
			// + j) : "IDS") + devMean[j]+"\n");
			// for (int j = 0; j < NUM_HEURISTICS + NUM_IDS; j++)
			// r.append(
			// "Standard deviation median " + (j < NUM_HEURISTICS ? ('h' + j) :
			// "IDS") + devMedian[j]+"\n");
		}
		System.out.println(r);
	}
}
