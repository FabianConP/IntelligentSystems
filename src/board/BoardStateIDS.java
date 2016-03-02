package board;

public class BoardStateIDS {
	private long board;
	private long prev;
	private int depth;

	public BoardStateIDS(long board, long prev, int depth) {
		super();
		this.board = board;
		this.prev = prev;
		this.depth = depth;
	}

	public long getBoard() {
		return board;
	}

	public void setBoard(long board) {
		this.board = board;
	}

	public long getPrev() {
		return prev;
	}

	public void setPrev(long prev) {
		this.prev = prev;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "BoardStateIDS [board=" + board + ", prev=" + prev + ", depth=" + depth + "]";
	}

}
