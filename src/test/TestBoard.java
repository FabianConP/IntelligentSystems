package test;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Board;

public class TestBoard {

	@Test
	public void test() {
		long b = 0;
		for (int i = 0; i < 16; i++)
			b = Board.setPos(b, i, i);
		for (int i = 0; i < 16; i++)
			assertTrue(Board.getPos(b, i) == i);
	}

	@Test
	public void test1() {
		long b = Board.FIRST_BOARD;
		for (int i = 0; i < 16; i++)
			assertTrue(Board.getPos(b, i) == i);
	}

	@Test
	public void test2() {
		long b = Board.FIRST_BOARD;
		for (int i = 0; i < 8; i++)
			b = Board.swap(b, i, 15 - i);
		for (int i = 0; i < 16; i++)
			assertTrue(Board.getPos(b, i) == 15 - i);
	}

	@Test
	public void test3() {
		int[] v = { 1, 4, 3, 13, 0, 2, 12, 15, 14, 8, 11, 9, 10, 6, 7, 5 };
		long b = 0;
		for (int i = 0; i < 16; i++)
			b = Board.setPos(b, i, v[i]);
		b = Board.swap(b, 0, 1);
		assertTrue(Board.getPos(b, 0) == 4);
		assertTrue(Board.getPos(b, 1) == 1);
		b = Board.swap(b, 14, 15);
		assertTrue(Board.getPos(b, 14) == 5);
		assertTrue(Board.getPos(b, 15) == 7);
		b = Board.swap(b, 0, 15);
		assertTrue(Board.getPos(b, 0) == 7);
		assertTrue(Board.getPos(b, 15) == 4);
		b = Board.swap(b, 7, 8);
		assertTrue(Board.getPos(b, 7) == 14);
		assertTrue(Board.getPos(b, 8) == 15);
		v = new int[] { 7, 1, 3, 13, 0, 2, 12, 14, 15, 8, 11, 9, 10, 6, 5, 4 };
		for (int i = 0; i < 16; i++)
			assertTrue(v[i] == Board.getPos(b, i));
	}

}
