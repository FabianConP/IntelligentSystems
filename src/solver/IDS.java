package solver;

import java.util.HashMap;
import java.util.Stack;

import board.BoardMovement;
import board.BoardStateIDS;

public class IDS {

	public static final int MAX_DEPTH = Integer.MAX_VALUE;

	/**
	 * IDS implementation for Puzzle 4x4
	 * @param correctBoard Correct board
	 * @param initialBoard Initial board
	 * @return Nodes added to the search tree
	 */
	public static int solve(long correctBoard, long initialBoard) {
		BoardStateIDS initialState = new BoardStateIDS(initialBoard, -1, 1);
		BoardStateIDS parentState = new BoardStateIDS(-1, -1, 1);
		HashMap<Long, Long> pastStates = new HashMap<>();
		Stack<BoardStateIDS> s = new Stack<>();
		BoardStateIDS curStateBoard;
		long curBoard;
		int countAddsToStack = 1;
		boolean solFound = false;
		for (int i = 1; i < MAX_DEPTH && !solFound; i++) {
			//Initial parent's node
			s.push(new BoardStateIDS(-1, -1, 1));
			//Initial state
			s.push(initialState);
			//Clear predecessor's map
			pastStates.clear();
			while (!s.isEmpty()) {
				//Current state
				curStateBoard = s.pop();
				curBoard = curStateBoard.getBoard();
				//Parent state
				parentState = s.pop();
//				pastStates.put(curBoard, curStateBoard.getPrev());
				//Found the solution
				if (curBoard == correctBoard) {
					//Print path
					// System.out.println(Board.printPath(curBoard,pastStates));
					solFound = true;
					break;
				}
				//Checks depth's limit
				if (curStateBoard.getDepth() == i)
					continue;
				//Checks each direction
				for (int[] d : BoardMovement.dir)
					if (BoardMovement.isValidMovement(curBoard, d)) {
						//Moves the space
						long newBoard = BoardMovement.doMovement(curBoard, d[0], d[1]);
						int depth = curStateBoard.getDepth() + 1;
						if (depth <= i && newBoard != parentState.getBoard() && !pastStates.containsKey(newBoard)) {
							//Add current state's parent
							s.push(curStateBoard);
							//Add new state
							s.push(new BoardStateIDS(newBoard, curBoard, depth));
							//Save new board's predecessor
							pastStates.put(newBoard, curBoard);
							countAddsToStack++;
						}
					}
			}
		}
		return countAddsToStack;
	}
}
