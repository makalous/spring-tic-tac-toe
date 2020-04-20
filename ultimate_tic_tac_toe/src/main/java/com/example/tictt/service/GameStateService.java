package com.example.tictt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tictt.entity.BoardEntity;
import com.example.tictt.entity.GameHistEntity;
import com.example.tictt.entity.GameStateEntity;
import com.example.tictt.entity.PlayerEntity;
import com.example.tictt.service.BoardService;

@Service
public class GameStateService {

	@Autowired
	public GameStateEntity gState = new GameStateEntity();

	@Autowired
	public BoardService bService = new BoardService();

	public GameStateService() {
	}

	public void addToGameHistWin(Integer index) {
		Boolean added = false;
		for (int i = 0; i < gState.gameHist.size(); i++) {
			if (gState.gameHist.get(i).getId() == index) {
				gState.gameHist.get(i).setWins(gState.gameHist.get(i).getWins() + 1);
				added = true;
			}
		}
		if (added == false) {
			gState.gameHist.add(new GameHistEntity(index, 1, 0, 0));
		}

	}

	public void addToGameHistDraw(Integer index) {
		Boolean added = false;
		for (int i = 0; i < gState.gameHist.size(); i++) {
			if (gState.gameHist.get(i).getId() == index) {
				gState.gameHist.get(i).setDraws(gState.gameHist.get(i).getDraws() + 1);
				added = true;
			}
		}
		if (added == false) {
			gState.gameHist.add(new GameHistEntity(index, 0, 0, 1));
		}

	}

	public GameHistEntity returnPlayerHist(Integer index) {
		Integer found = null;
		for (int i = 0; i < gState.gameHist.size(); i++) {
			if (gState.gameHist.get(i).getId() == index) {
				found = i;
			}
		}
		if (found != null)
			return gState.gameHist.get(found);
		else {
			GameHistEntity temp_null = new GameHistEntity(null, 0, 0, 0);
			return temp_null;
		}

	}

	public void addToGameHistLose(Integer index) {
		Boolean added = false;
		for (int i = 0; i < gState.gameHist.size(); i++) {
			if (gState.gameHist.get(i).getId() == index) {
				gState.gameHist.get(i).setLoses(gState.gameHist.get(i).getLoses() + 1);
				added = true;
			}
		}
		if (added == false) {
			gState.gameHist.add(new GameHistEntity(index, 0, 1, 0));
		}

	}

	public void constructState() {
		bService.setMoveO(false);
		bService.setMoveX(false);
		gState.board = bService.getBoard1();
	}

	public String startGame() {
		bService.start_game();
		bService.board.o_position.clear();
		bService.board.x_position.clear();
		bService.map_board();

		gState.setPlayer1(new PlayerEntity(0, "Player 1", true));
		gState.setPlayer2(new PlayerEntity(1, "Player 2", false));

		if (bService.who_starts() == true) {
			bService.setMoveX(true);
			gState.board = bService.getBoard1();
			return "Starting player: X";

		} else {
			bService.setMoveO(true);
			gState.board = bService.getBoard1();
			return "Starting player: O";
		}

	}

	public PlayerEntity checkWinner() {
		if (bService.check_winner(true)) {
			addToGameHistWin(gState.getPlayer1().getId());
			addToGameHistLose(gState.getPlayer2().getId());
			return gState.getPlayer1();
		} else if (bService.check_winner(false)) {
			addToGameHistWin(gState.getPlayer2().getId());
			addToGameHistLose(gState.getPlayer1().getId());
			return gState.getPlayer2();
		} else if (bService.check_if_fulfilled()) {
			addToGameHistDraw(gState.getPlayer2().getId());
			addToGameHistDraw(gState.getPlayer1().getId());
			PlayerEntity temp_pe = new PlayerEntity(0, "", null);
			return temp_pe;
		} else {
			PlayerEntity temp_pe = new PlayerEntity(0, "", null);
			return temp_pe;
		}
	}

	public BoardEntity nextMove(int row, int col) {
		bService.map_board();
		if (bService.isMoveX() == true) {
			if (bService.check_if_field_taken(row, col)) {
				return null;
			} else {
				gState.board.moveO = true;
				gState.board.moveX = false;
				bService.add_pair_list(true, row, col);
				bService.figure_move(true, row, col);
				gState.board = bService.getBoard1();

				return bService.getBoard1();
			}

		} else {
			if (bService.check_if_field_taken(row, col)) {
				return null;
			} else {
				gState.board.moveO = false;
				gState.board.moveX = true;
				bService.add_pair_list(false, row, col);
				bService.figure_move(false, row, col);
				gState.board = bService.getBoard1();

				return bService.getBoard1();
			}

		}
	}

//getting player by ID at current game
	public PlayerEntity getPlayerById(Integer index) {
		switch (index) {
		case 0:
			return gState.getPlayer1();
		case 1:
			return gState.getPlayer2();
		default: {
			PlayerEntity temp_pe = new PlayerEntity(0, "", null);
			return temp_pe;
		}

		}

	}

}
