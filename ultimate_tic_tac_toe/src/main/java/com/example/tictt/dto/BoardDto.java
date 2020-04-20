package com.example.tictt.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {
	public Long id;
	public Boolean[][] board = new Boolean[10][10];
	public boolean moveX;
	public boolean moveO;
	public List<String> x_position = new ArrayList<String>();
	public List<String> o_position = new ArrayList<String>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean[][] getBoard() {
		return board;
	}
	public void setBoard(Boolean[][] board) {
		this.board = board;
	}
	public boolean isMoveX() {
		return moveX;
	}
	public void setMoveX(boolean moveX) {
		this.moveX = moveX;
	}
	public boolean isMoveO() {
		return moveO;
	}
	public void setMoveO(boolean moveO) {
		this.moveO = moveO;
	}
	public List<String> getX_position() {
		return x_position;
	}
	public void setX_position(List<String> x_position) {
		this.x_position = x_position;
	}
	public List<String> getO_position() {
		return o_position;
	}
	public void setO_position(List<String> o_position) {
		this.o_position = o_position;
	}
	
	
}
