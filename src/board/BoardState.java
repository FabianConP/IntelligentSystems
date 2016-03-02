package board;

public class BoardState implements Comparable<BoardState> {
	private long board;
	private int cost;
	private long prev;

	public BoardState(long board, int cost, long prev) {
		super();
		this.board = board;
		this.cost = cost;
		this.prev = prev;
	}

	@Override
	public int compareTo(BoardState o) {
		return cost - o.cost;
	}

	@Override
	public String toString() {
		return "BoardState [board=" + Board.toString(board) + ", cost=" + cost + ", prev=" + Board.toString(prev) + "]";
	}

	public long getBoard() {
		return board;
	}

	public void setBoard(long board) {
		this.board = board;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public long getPrev() {
		return prev;
	}

	public void setPrev(long prev) {
		this.prev = prev;
	}

}