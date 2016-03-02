package solver;

import java.util.HashMap;
import java.util.Stack;

import board.Board;
import board.BoardMovement;
import board.BoardState;
import board.BoardStateIDS;

public class IDS {

	public static final int MAX_DEPTH = Integer.MAX_VALUE;

	public static int solve(long correctBoard, long initialBoard) {
		System.out.println("1");
		BoardStateIDS initialState = new BoardStateIDS(initialBoard, -1, 1);
		BoardStateIDS parentState = new BoardStateIDS(-1, -1, 1);
		HashMap<Long, Long> pastStates = new HashMap<>();
		Stack<BoardStateIDS> s = new Stack<>();
		BoardStateIDS curStateBoard;
		long curBoard;
		int countAddsToStack = 1;
		for (int i = 0; i < MAX_DEPTH; i++) {
			s.clear();
			s.push(parentState);
			s.push(initialState);
			pastStates.clear();
			System.out.println("Depth: " + i);
			while (!s.isEmpty()) {
				curStateBoard = s.pop();
				curBoard = curStateBoard.getBoard();
				parentState = s.pop();
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
						int depth = curStateBoard.getDepth();
						if (!pastStates.containsKey(newBoard) && depth < MAX_DEPTH && newBoard != parentState.getBoard()) {
							s.push(curStateBoard);
							s.add(new BoardStateIDS(newBoard, curBoard, depth + 1));
							countAddsToStack++;
						}
					}
			}
		}
		return countAddsToStack;
	}
}
