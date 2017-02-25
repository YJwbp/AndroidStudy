package com.bpwei.examples.bean;

/**
 * Created by new on 15/8/24.
 */
public class Character {
	private String name;
	private int imgId;

	public Character(String name, int imgId) {
		this.name = name;
		this.imgId = imgId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getName() {

		return name;
	}

	public int getImgId() {
		return imgId;
	}
}
