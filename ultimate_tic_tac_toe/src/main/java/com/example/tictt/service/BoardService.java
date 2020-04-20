package com.example.tictt.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tictt.entity.BoardEntity;

@Service
public class BoardService {
	@Autowired
	public BoardEntity board = new BoardEntity();

	public BoardService() {
	}

	public boolean check_winner(Boolean expo) {
		// check rows
		boolean win_r = false;
		for (int i = 0; i <= 9; i++) {

			for (int j = 0; j <= 5; j++) {

				if (board.board[i][j] == expo && board.board[i][j + 1] == expo && board.board[i][j + 2] == expo
						&& board.board[i][j + 3] == expo && board.board[i][j + 4] == expo)
					win_r = true;
			}
		}
		if (win_r)
			return true;

		// check columns
		boolean win_c = false;
		for (int j = 0; j <= 9; j++) {

			for (int i = 0; i <= 5; i++) {

				if (board.board[i][j] == expo && board.board[i + 1][j] == expo && board.board[i + 2][j] == expo
						&& board.board[i + 3][j] == expo && board.board[i + 4][j] == expo)
					win_c = true;
			}
		}
		if (win_c)
			return true;

		// check left diagonal
		boolean win_ld = false;
		for (int i = 4; i <= 9; i++) {
			for (int j = 0; j <= 5; j++) {
				if (board.board[i][j] == expo && board.board[i - 1][j + 1] == expo && board.board[i - 2][j + 2] == expo
						&& board.board[i - 3][j + 3] == expo && board.board[i - 4][j + 4] == expo)
					win_ld = true;
			}

		}
		if (win_ld)
			return true;

		// check right diagonal
		boolean win_rd = false;
		for (int i = 4; i <= 9; i++) {
			for (int j = 9; j >= 4; j--) {
				if (board.board[i][j] == expo && board.board[i - 1][j - 1] == expo && board.board[i - 2][j - 2] == expo
						&& board.board[i - 3][j - 3] == expo && board.board[i - 4][j - 4] == expo)
					win_rd = true;
			}

		}
		if (win_rd)
			return true;

		return false;
	}

	public void start_game() {
		// clear the board.board
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				board.board[i][j] = null;
			}
		}

	}

	public Boolean who_starts() {
		Random rand = new Random();
		if (rand.nextBoolean() == true)
			return true;
		else
			return false;
	}

	public void random_board() {
		// randomly fulfilled board.board
		Random rand = new Random();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				if (rand.nextBoolean() == true)
					board.board[i][j] = true;
				else
					board.board[i][j] = false;
			}
		}
	}

	public boolean check_if_fulfilled() {

		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				if (board.board[i][j] == null)
					return false;
			}
		}
		return true;
	}

	public void add_pair_list(Boolean figure, int row, int col) {
		if (figure == true)
			board.x_position.add(row + ", " + col);
		else if (figure == false)
			board.o_position.add(row + ", " + col);
	}

	public void map_board() {
		board.x_position.clear();
		board.o_position.clear();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				if (board.board[i][j] != null) {
					if (board.board[i][j] == true)
						add_pair_list(true, i, j);
					else if (board.board[i][j] == false)
						add_pair_list(false, i, j);
				}

			}
		}

	}

	public boolean check_if_field_taken(int i, int j) {
		if (board.board[i][j] != null)
			return true;
		else
			return false;

	}

	public boolean figure_move(Boolean expo, int i, int j) {
		if (check_if_field_taken(i, j))
			return false;
		else {
			board.board[i][j] = expo;
			return true;
		}
	}

	public List<String> getX_position() {
		return board.x_position;
	}

	public void setX_position(List<String> x_position) {
		board.x_position = x_position;
	}

	public List<String> getO_position() {
		return board.o_position;
	}

	public BoardEntity getBoard1() {
		return this.board;
	}

	public void setBoard1(BoardEntity boarde) {
		this.board = boarde;
	}

	public void setO_position(List<String> o_position) {
		board.o_position = o_position;
	}

	public Boolean[][] getBoard() {
		return board.board;
	}

	public Long getId() {
		return board.id;
	}

	public void setId(Long id) {
		board.id = id;
	}

	public void setBoard(Boolean[][] boarde) {
		board.board = boarde;
	}

	public boolean isMoveX() {
		return board.moveX;
	}

	public void setMoveX(boolean moveX) {
		board.moveX = moveX;
	}

	public boolean isMoveO() {
		return board.moveO;
	}

	public void setMoveO(boolean moveO) {
		board.moveO = moveO;
	}

}
