package board;

import java.util.HashMap;
import java.util.Stack;

public class Board {

	public static final int SPACE = 15;
	public static final int SIZE = 4;
	public static final long FIRST_BOARD = 1070935975390360080L;

	/*
	 * private long board;
	 * 
	 * public Board() { this.board = 0; }
	 * 
	 * public Board(long board) { this.board = board; }
	 * 
	 * @Override public int hashCode() { return Long.valueOf(board).hashCode();
	 * }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (getClass() != obj.getClass())
	 * return false; Board other = (Board) obj; if (board != other.board) return
	 * false; return true; }
	 * 
	 * public long getBoard() { return board; }
	 * 
	 * public void setBoard(long board) { this.board = board; }
	 */

	public static int getPos(long board, int pos) {
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

	public static long setPos(long board, int pos, int value) {
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
		return board;
	}

	public static long swap(long board, int posA, int posB) {
		int aux = Board.getPos(board, posA);
		board = Board.setPos(board, posA, Board.getPos(board, posB));
		board = setPos(board, posB, aux);
		return board;
	}
	//
	// @Override
	// public Object clone() throws CloneNotSupportedException {
	// return new Board(this.board);
	// }

	public static String toString2(long board) {
		int size = Board.SIZE, id = 0;
		StringBuilder r = new StringBuilder("[");
		for (int i = 0; i < size; i++) {
			r.append("[");
			for (int j = 0; j < size; j++)
				r.append(Board.getPos(board, id++) + ",");
			r.setLength(r.length() - 1);
			r.append("],");
		}
		r.setLength(r.length() - 1);
		r.append("]");
		return "Board [board=" + r.toString() + "]";
	}

	public static String toString(long board) {
		int size = Board.SIZE, id = 0, num = 0;
		StringBuilder r = new StringBuilder("");
		for (int i = 0; i < size; i++) {
			r.append("[");
			for (int j = 0; j < size; j++) {
				num = Board.getPos(board, id++);
				if (num == Board.SPACE)
					r.append("  ,");
				else
					r.append(String.format("%2d", num) + ",");
			}
			r.setLength(r.length() - 1);
			r.append("]\n");
		}
		// return "Board [board=" + r.toString() + "]";
		return r.toString();
	}

	public static long randMovement(long board) {
		board = BoardMovement.rand(board);
		return board;
	}

	public static String printPath(long initialBoard, HashMap<Long, Long> pastStates) {
		StringBuilder r = new StringBuilder("Path\n");
		long board = initialBoard;
		Stack<Long> s = new Stack<>();
		while (board != -1) {
			s.push(board);
			board = pastStates.get(board);
		}
		while (!s.isEmpty())
			r.append(Board.toString(s.pop()) + "\n");
		return r.toString();
	}
}
