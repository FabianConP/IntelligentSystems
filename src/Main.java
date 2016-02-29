import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

import board.Board;
import solver.AStarBoard;
import solver.Heuristic;

public class Main {

	static final int MIN_AMOUNT_RAND_MOV = 10;
	static final int MAX_AMOUNT_RAND_MOV = 25;
	static final int TEST_PER_BOARD = 25;
	static final int NUM_HEURISTICS = 3;
	static final int NUM_IDS = 1;

	public static void main(String[] args) throws Exception {
		 System.setOut(new PrintStream("Log.txt"));
		Board correctBoard = Board.getFirstBoard();
		for (int i = MIN_AMOUNT_RAND_MOV; i <= MAX_AMOUNT_RAND_MOV; i += 5) {
			ArrayList<Integer>[] h = new ArrayList[4];
			for (int j = 0; j < NUM_HEURISTICS; j++)
				h[j] = new ArrayList<>();
			for (int j = 0; j < TEST_PER_BOARD; j++) {
				Board board = Board.getFirstBoard();
				for (int r = 0; r < i; r++)
					board.randMovement();
				for (int k = 0; k < NUM_HEURISTICS; k++)
					h[k].add(AStarBoard.solve(correctBoard, (Board) board.clone(), Heuristic.H[k]));
			}
			double[] mean = new double[NUM_HEURISTICS + NUM_IDS];
			double[] median = new double[NUM_HEURISTICS + NUM_IDS];
			double[] devMean = new double[NUM_HEURISTICS + NUM_IDS];
			double[] devMedian = new double[NUM_HEURISTICS + NUM_IDS];
			int a = TEST_PER_BOARD / 2;
			for (int j = 0; j < NUM_HEURISTICS; j++)
				Collections.sort(h[j]);
			for (int k = 0; k < NUM_HEURISTICS; k++) {
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
			System.out.println("Random movements: " + i + "\n");
			for (int j = 0; j < NUM_HEURISTICS; j++)
				System.out.println("Mean h" + j + ": " + mean[j]);
			for (int j = 0; j < NUM_HEURISTICS; j++)
				System.out.println("Median h" + j + ": " + median[j]);
			for (int j = 0; j < NUM_HEURISTICS; j++)
				System.out.println("Standard deviation mean h" + j + ": " + devMean[j]);
			for (int j = 0; j < NUM_HEURISTICS; j++)
				System.out.println("Standard deviation median h" + j + ": " + devMedian[j]);
			System.out.println();
		}
	}
}
