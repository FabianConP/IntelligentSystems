package board;

/**
 * 
 * @author Fabian Conejo
 *
 */
public class BoardStateIDS extends BoardState {
	private int depth;

	public BoardStateIDS(long board, long prev, int depth) {
		super(board, prev);
		this.depth = depth;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "BoardStateIDS [depth=" + depth + ", toString()=" + super.toString() + "]";
	}

}
