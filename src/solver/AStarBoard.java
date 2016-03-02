package solver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.PriorityQueue;

import board.Board;
import board.BoardMovement;
import board.BoardState;

public class AStarBoard {

	public static int solve(long correctBoard, long initialBoard, String heuristic) throws Exception {
		Method method = Heuristic.class.getMethod(heuristic, Long.class);
		BoardState initialState = new BoardState(initialBoard, 0, -1);
		HashMap<Long, Long> pastStates = new HashMap<>();
		PriorityQueue<BoardState> pq = new PriorityQueue<>();
		pq.add(initialState);
		BoardState curStateBoard;
		long curBoard;
		int countAddsToQueue = 1;
		while (!pq.isEmpty()) {
			curStateBoard = pq.poll();
			curBoard = curStateBoard.getBoard();
			if (pastStates.containsKey(curBoard))
				continue;
			pastStates.put(curBoard, curStateBoard.getPrev());
			if (curBoard == correctBoard) {
				System.out.println(Board.printPath(curBoard, pastStates));
				break;
			}
			for (int[] d : BoardMovement.dir)
				if (BoardMovement.isValidMovement(curBoard, d)) {
					long newBoard = BoardMovement.doMovement(curBoard, d[0], d[1]);
					if (!pastStates.containsKey(newBoard)) {
						int prevCost = curStateBoard.getCost();
						int real = 1;
						int estimated = (int) method.invoke(Heuristic.class, newBoard);
						int totalEstimated = real + estimated;
						pq.add(new BoardState(newBoard, totalEstimated + prevCost, curBoard));
						countAddsToQueue++;
					}
				}
		}
		return countAddsToQueue;
	}
}
