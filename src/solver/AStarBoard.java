package solver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.PriorityQueue;

import board.Board;
import board.BoardMovement;
import board.BoardStateA;

/**
 * 
 * @author Fabian Conejo
 *
 */
public class AStarBoard {

	/**
	 * A* implementation for Puzzle 4x4
	 * @param correctBoard Correct board
	 * @param initialBoard Initial board
	 * @param heuristic	Kind of heuristic
	 * @return Nodes added to the search tree
	 * @throws Exception
	 */
	public static int solve(long initialBoard, String heuristic) throws Exception {
		Method method = Heuristic.class.getMethod(heuristic, Long.class);
		//Initial state
		BoardStateA initialState = new BoardStateA(initialBoard, 0, -1);
		//Predecessor's map
		HashMap<Long, Long> pastStates = new HashMap<>();
		PriorityQueue<BoardStateA> pq = new PriorityQueue<>();
		//Adds the initial state
		pq.add(initialState);
		BoardStateA curStateBoard;
		long curBoard;
		int countAddsToQueue = 1;
		while (!pq.isEmpty()) {
			//Get the best choice
			curStateBoard = pq.poll();
			curBoard = curStateBoard.getBoard();
			//Check if current board was solved
			if (pastStates.containsKey(curBoard))
				continue;
			//Save current board's predecessor
			pastStates.put(curBoard, curStateBoard.getPrev());
			//Found the solution
			if (curBoard == Board.FIRST_BOARD) {
				//Print path
				// System.out.println(Board.printPath(curBoard, pastStates));
				break;
			}
			//Checks each direction
			for (int[] d : BoardMovement.dir)
				if (BoardMovement.isValidMovement(curBoard, d)) {
					//Moves the space
					long newBoard = BoardMovement.doMovement(curBoard, d[0], d[1]);
					//Check if new board was solved 
					if (!pastStates.containsKey(newBoard)) {
						//Previous cost
						int prevCost = curStateBoard.getCost();
						//Real cost
						int real = 1;
						//Estimated cost by the heuristic
						int estimated = (int) method.invoke(Heuristic.class, newBoard);
						//Total estimated cost
						int totalEstimated = real + estimated;
						//Add the new board
						pq.add(new BoardStateA(newBoard, curBoard, totalEstimated + prevCost));
						countAddsToQueue++;
					}
				}
		}
		return countAddsToQueue;
	}
}
