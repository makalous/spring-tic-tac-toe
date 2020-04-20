package com.example.tictt.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDto {
	public Integer id;
	public String name;
	public Boolean figure;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getFigure() {
		return figure;
	}
	public void setFigure(Boolean figure) {
		this.figure = figure;
	}
	
	

}
