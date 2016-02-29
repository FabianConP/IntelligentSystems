package solver;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.PriorityQueue;

import board.Board;
import board.BoardMovement;
import board.BoardState;

public class AStarBoard {

	public static int solve(Board correctBoard, Board initialBoard, String heuristic) throws Exception {
		Method method = Heuristic.class.getMethod(heuristic, Board.class);
		BoardState initialState = new BoardState(initialBoard, 0);
		HashSet<Board> pastStates = new HashSet<>();
		PriorityQueue<BoardState> pq = new PriorityQueue<>();
		pq.add(initialState);
		BoardState curStateBoard;
		Board curBoard;
		int countAddsToQueue = 1;
		while (!pq.isEmpty()) {
			curStateBoard = pq.poll();
			curBoard = curStateBoard.getBoard();
			if (pastStates.contains(curBoard))
				continue;
			pastStates.add(curBoard);
			if (curBoard.equals(correctBoard)) {
				initialBoard = curBoard;
				break;
			}
			for (int[] d : BoardMovement.dir) {
				if (BoardMovement.isValidMovement(curBoard, d)) {
					Board newBoard = (Board) curBoard.clone();
					BoardMovement.doMovement(newBoard, d[0], d[1]);
					if (!pastStates.contains(newBoard)) {
						int prevCost = curStateBoard.getCost();
						int real = 1;
						int estimated = (int) method.invoke(Heuristic.class, newBoard);
						int totalEstimated = real + estimated;
						pq.add(new BoardState(newBoard, totalEstimated + prevCost));
						countAddsToQueue++;
					}
				}
			}
		}
		return countAddsToQueue;
	}
}
