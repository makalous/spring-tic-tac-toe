package com.example.tictt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.NonNull;

//import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class GameHistEntity {
	// private static final AtomicInteger id = new AtomicInteger(0);
	@NonNull
	@Id
	public Integer id;
	public Integer wins;
	public Integer loses;
	public Integer draws;

	public GameHistEntity() {
	}

	public GameHistEntity(Integer id, Integer wins, Integer loses, Integer draws) {
		super();
		this.id = id;
		this.wins = wins;
		this.loses = loses;
		this.draws = draws;
	}

	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoses() {
		return loses;
	}

	public void setLoses(Integer loses) {
		this.loses = loses;
	}

	public Integer getDraws() {
		return draws;
	}

	public void setDraws(Integer draws) {
		this.draws = draws;
	}

}
