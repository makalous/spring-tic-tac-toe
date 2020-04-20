package com.example.tictt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.NonNull;

@Entity
public class PlayerEntity {

	@NonNull
	@Id
	public Integer id;
	public String name;
	public Boolean figure;

	public PlayerEntity() {
	}

	public PlayerEntity(int id, String name, Boolean figure) {
		super();
		this.id = id;
		this.name = name;
		this.figure = figure;
	}

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
