package com.example.tictt.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	public Long id;

	@Column(name = "board", length = 2000)
	public Boolean[][] board = new Boolean[10][10]; // true-X, false-O, null-empty

	@Column(name = "moveX")
	public boolean moveX;

	@Column(name = "moveO")
	public boolean moveO;

	@ElementCollection
	@Column(name = "x_position")
	public List<String> x_position = new ArrayList<String>();

	@ElementCollection
	@Column(name = "o_position")
	public List<String> o_position = new ArrayList<String>();

	public BoardEntity(Long id, Boolean[][] board, boolean moveX, boolean moveO, List<String> x_position,
			List<String> o_position) {
		super();
		this.id = id;
		this.board = board;
		this.moveX = moveX;
		this.moveO = moveO;
		this.x_position = x_position;
		this.o_position = o_position;
	}

	public BoardEntity() {
	}

}
