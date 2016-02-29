package board;

public class Board {

	public static final int SPACE = 15;
	private long board;

	public Board() {
		this.board = 0;
	}

	public Board(long board) {
		this.board = board;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(board).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (board != other.board)
			return false;
		return true;
	}

	public long getBoard() {
		return board;
	}

	public void setBoard(long board) {
		this.board = board;
	}

	public int getPos(int pos) {
		int mask = 0, num = 0;
		long b = board;
		for (int i = 0; i < 15; i++) {
			num = (int) (b % 16L);
			if (i == pos)
				return num;
			mask |= (1 << num);
			b /= 16L;
		}
		for (int i = 0, j = 1; i < 16; i++, j <<= 1)
			if ((mask & j) == 0)
				return i;
		return 0;
	}

	public void setPos(int pos, int value) {
		if (pos < 15) {
			long rp = (1L << (pos * 4L)) - 1L, rigth = rp & board, v = (long) value;
			// System.out.println(Long.toBinaryString(board));
			board >>= (4L * (pos + 1));
			// System.out.println(Long.toBinaryString(board));
			board <<= (4L * (pos + 1));
			// System.out.println(Long.toBinaryString(board));
			board |= (v << (pos * 4L));
			// System.out.println(Long.toBinaryString(board));
			board |= rigth;
			// System.out.println(Long.toBinaryString(board));
		}
	}

	public void swap(int posA, int posB) {
		int aux = getPos(posA);
		setPos(posA, getPos(posB));
		setPos(posB, aux);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Board(this.board);
	}

	@Override
	public String toString() {
		int size = getSize(), id = 0;
		StringBuilder r = new StringBuilder("[");
		for (int i = 0; i < size; i++) {
			r.append("[");
			for (int j = 0; j < size; j++)
				r.append(getPos(id++) + ",");
			r.setLength(r.length() - 1);
			r.append("],");
		}
		r.setLength(r.length() - 1);
		r.append("]");
		return "Board [board=" + r.toString() + "]";
	}

	public void randMovement() {
		BoardMovement.rand(this);
	}

	public static Board getFirstBoard() {
		return new Board(1070935975390360080L);
	}

	public int getSize() {
		return 4;
	}
}
