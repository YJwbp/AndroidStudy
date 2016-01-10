package com.bpwei.examples.entites;

/**
 * 极坐标 0-25
 * 角度为 0-360
 * Created by new on 16/1/9.
 */
public class PolarPoint {
	private float radius;
	private float angle;
	private boolean isSelected;

	public boolean isSelected() {
		return isSelected;
	}

	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	@Override
	public String toString() {
		return "PolarPoint{" +
				"radius=" + radius +
				", angle=" + angle +
				", isSelected=" + isSelected +
				'}';
	}
}
