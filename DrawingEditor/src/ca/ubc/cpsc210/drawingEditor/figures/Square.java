package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Represents a square figure
 */
public class Square extends Rectangle {

	/**
	 * Constructor creates square with top left corner at given point
	 * and zero side length
	 * @param topLeft  the top left corner of square
	 */
	public Square(Point topLeft) {
		super(topLeft);
		x = (int) topLeft.getX();
		y = (int) topLeft.getY();
	}

	/**
	 * Set the length of the side of this square
	 * @param bottomRight  length of side determined using x coordinate of this point
	 */
	public void setSide(Point bottomRight) {
		int width = (int) (bottomRight.getX() - x);
		super.setHeight(width);
		super.setWidth(width);
	}

	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}
	@Override
	public void setWidth(int width) {
		super.setHeight(width);
		super.setWidth(width);
	}
}
