package com.example.tictt.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameHistDto {
	public Integer id;
	public Integer wins;
	public Integer loses;
	public Integer draws;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWins() {
		return wins;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
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
