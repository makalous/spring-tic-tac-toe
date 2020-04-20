package com.example.tictt;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.example.tictt.service.BoardService;

public class BoardTest {

	@Test
	public void testCheck_winner_diagonal() {
		BoardService test_board = new BoardService();
		test_board.start_game();
		test_board.board.board[0][5] = false;
		test_board.board.board[1][6] = false;
		test_board.board.board[2][7] = false;
		test_board.board.board[3][8] = false;
		test_board.board.board[4][9] = false;

		test_board.board.board[4][7] = true;
		test_board.board.board[5][6] = true;
		test_board.board.board[6][5] = true;
		test_board.board.board[7][4] = true;
		test_board.board.board[8][3] = true;

		assertEquals(true, test_board.check_winner(true));
		assertEquals(true, test_board.check_winner(false));
	}

	@Test
	public void testCheck_winner_horiz_vert() {
		BoardService test_board = new BoardService();
		test_board.start_game();
		test_board.board.board[5][2] = false;
		test_board.board.board[5][3] = false;
		test_board.board.board[5][4] = false;
		test_board.board.board[5][5] = false;
		test_board.board.board[5][6] = false;

		test_board.board.board[3][9] = true;
		test_board.board.board[4][9] = true;
		test_board.board.board[5][9] = true;
		test_board.board.board[6][9] = true;
		test_board.board.board[7][9] = true;

		assertEquals(true, test_board.check_winner(true));
		assertEquals(true, test_board.check_winner(false));
	}

	@Test
	public void testCheck_draw() {
		BoardService test_board = new BoardService();
		test_board.start_game();
		for (int c = 0; c <= 8; c = c + 2) {

			for (int r = 0; r <= 3; r++)
				test_board.board.board[r][c] = true;
			for (int r = 4; r <= 7; r++)
				test_board.board.board[r][c] = false;
			for (int r = 8; r <= 9; r++)
				test_board.board.board[r][c] = true;

		}

		for (int c = 1; c <= 9; c = c + 2) {

			for (int r = 0; r <= 3; r++)
				test_board.board.board[r][c] = false;
			for (int r = 4; r <= 7; r++)
				test_board.board.board[r][c] = true;
			for (int r = 8; r <= 9; r++)
				test_board.board.board[r][c] = false;

		}

		// We have draw when 1)there is no winner and 2)all squares are filled up
		assertEquals(false, test_board.check_winner(true));
		assertEquals(false, test_board.check_winner(false));
		assertEquals(true, test_board.check_if_fulfilled());
	}

	@Test
	public void testFigure_move() {
		BoardService test_board = new BoardService();
		test_board.start_game();
		test_board.board.board[9][9] = false;
		test_board.figure_move(true, 4, 5);
		test_board.figure_move(false, 0, 0);
		test_board.figure_move(true, 9, 9);

		assertEquals(true, test_board.board.board[4][5]);
		assertEquals(false, test_board.board.board[0][0]);
		assertEquals(false, test_board.board.board[9][9]);
		assertEquals(false, test_board.figure_move(true, 9, 9));

	}

	@Test
	public void testStart_game() {
		BoardService test_board = new BoardService();
		test_board.start_game();
		boolean check = true;
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				if (test_board.board.board[i][j] != null)
					check = false;
			}
		}
		assertEquals(true, check);
	}

	@Test
	public void testWho_starts() {
		BoardService test_board = new BoardService();
		assertThat(test_board.who_starts(), anyOf(is(true), is(false)));
	}

}
