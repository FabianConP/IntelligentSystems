package test;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Board;

public class TestBoard {

	@Test
	public void test() {
		Board b = new Board();
		for (int i = 0; i < 16; i++)
			b.setPos(i, i);
		for (int i = 0; i < 16; i++)
			assertTrue(b.getPos(i) == i);
	}

	@Test
	public void test1() {
		Board b = Board.getFirstBoard();
		for (int i = 0; i < 16; i++)
			assertTrue(b.getPos(i) == i);
	}

	@Test
	public void test2() {
		Board b = Board.getFirstBoard();
		for (int i = 0; i < 8; i++)
			b.swap(i, 15 - i);
		for (int i = 0; i < 16; i++)
			assertTrue(b.getPos(i) == 15 - i);
	}

	@Test
	public void test3() {
		int[] v = { 1, 4, 3, 13, 0, 2, 12, 15, 14, 8, 11, 9, 10, 6, 7, 5 };
		Board b = new Board();
		for (int i = 0; i < 16; i++)
			b.setPos(i, v[i]);
		b.swap(0, 1);
		assertTrue(b.getPos(0) == 4);
		assertTrue(b.getPos(1) == 1);
		b.swap(14, 15);
		assertTrue(b.getPos(14) == 5);
		assertTrue(b.getPos(15) == 7);
		b.swap(0, 15);
		assertTrue(b.getPos(0) == 7);
		assertTrue(b.getPos(15) == 4);
		b.swap(7, 8);
		assertTrue(b.getPos(7) == 14);
		assertTrue(b.getPos(8) == 15);
		v = new int[]{7,1,3, 13, 0, 2, 12, 14,15, 8, 11, 9, 10, 6,5,4};
		for (int i = 0; i < 16; i++) 
			assertTrue(v[i] == b.getPos(i));
	}

}
