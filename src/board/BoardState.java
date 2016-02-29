package board;

public class BoardState implements Comparable<BoardState> {
	private Board board;
	private int cost;

	public BoardState(Board board, int cost) {
		super();
		this.board = board;
		this.cost = cost;
	}

	@Override
	public int compareTo(BoardState o) {
		return cost - o.cost;
	}

	@Override
	public String toString() {
		return "BoardState [board=" + board + ", cost=" + cost + "]";
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	

}