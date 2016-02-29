package board;

import java.util.HashSet;
import java.util.Random;

public class BoardGenerator {
	int size;
	int[][] board;

	public BoardGenerator(int size) throws Exception {
		if (size < 0)
			throw new Exception("Size should be greater than 0");
		this.size = size;
		board = new int[size][size];
		int lim = size * size, number;
		HashSet<Integer> set = new HashSet<>(lim);
		Random r = new Random();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				do {
					number = r.nextInt(lim);
				} while (set.contains(number));
				set.add(number);
				board[i][j] = number + 1;
			}
	}

	public int[][] getBoard() {
		return board;
	}

}
