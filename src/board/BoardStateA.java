package board;

/**
 * 
 * @author Fabian Conejo
 *
 */
public class BoardStateA extends BoardState implements Comparable<BoardStateA> {
	private int cost;

	public BoardStateA(long board, long prev, int cost) {
		super(board, prev);
		this.cost = cost;
	}

	@Override
	public int compareTo(BoardStateA o) {
		return cost - o.cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "BoardStateA [cost=" + cost + ", toString()=" + super.toString() + "]";
	}

}