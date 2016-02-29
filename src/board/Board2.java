package board;

import java.util.Arrays;

public class Board2 {
	
	public static final int SPACE = 9;
	private int[][] board;

	public Board2(int[][] board) {
		super();
		this.board = board;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board2 other = (Board2) obj;
		for (int i = 0; i < board.length; i++) 
			for (int j = 0; j < board[i].length; j++) 
				if(board[i][j] != other.board[i][j])
					return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder r = new StringBuilder("[");
		for (int i = 0; i < board.length; i++)
			r.append(Arrays.toString(board[i]) + ",");
		r.setLength(r.length() - 1);
		r.append("]");
		return "Board [board=" + r.toString() + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		int[][] b = new int[this.board.length][this.board.length];
		for (int i = 0; i < b.length; i++)
			for (int j = 0; j < b[i].length; j++)
				b[i][j] = this.board[i][j];
		return new Board2(b);
	}

	public void randMovement() {
//		BoardMovement.rand(this);
	}

	public static Board2 getFirstBoard(int size){
		int[][] first = new int[size][size];
		int id = 1;
		for (int i = 0; i < size; i++) 
			for (int j = 0; j < size; j++) 
				first[i][j] = id++;
		return new Board2(first);
	}

}
