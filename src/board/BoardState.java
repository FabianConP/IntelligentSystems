package board;

/**
 * 
 * @author Fabian Conejo
 *
 */
public class BoardState {
	private long board;
	private long prev;
	
	public BoardState(long board, long prev) {
		this.board = board;
		this.prev = prev;
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

	@Override
	public String toString() {
		return "BoardState [board=\n" + Board.toString(board) + "\n, prev=" + prev + "]";
	}

	
}
