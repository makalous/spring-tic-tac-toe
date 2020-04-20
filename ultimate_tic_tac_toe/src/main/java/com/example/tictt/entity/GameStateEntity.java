package com.example.tictt.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.NonNull;

@Entity
public class GameStateEntity {

	@NonNull
	@Id
	public Long id;
	@Transient
	public BoardEntity board;
	@Transient
	public PlayerEntity player1, player2;
	@Transient
	public List<GameHistEntity> gameHist = new ArrayList<GameHistEntity>();

	public GameStateEntity() {
	}

	public GameStateEntity(BoardEntity board, PlayerEntity player1, PlayerEntity player2,
			List<GameHistEntity> gameHist) {
		super();
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.gameHist = gameHist;
	}

	public List<GameHistEntity> getGameHist() {
		return gameHist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BoardEntity getBoard() {
		return board;
	}

	public void setBoard(BoardEntity board) {
		this.board = board;
	}

	public PlayerEntity getPlayer1() {
		return player1;
	}

	public void setPlayer1(PlayerEntity player1) {
		this.player1 = player1;
	}

	public PlayerEntity getPlayer2() {
		return player2;
	}

	public void setPlayer2(PlayerEntity player2) {
		this.player2 = player2;
	}

	public void setGameHist(List<GameHistEntity> gameHist) {
		this.gameHist = gameHist;
	}

}
