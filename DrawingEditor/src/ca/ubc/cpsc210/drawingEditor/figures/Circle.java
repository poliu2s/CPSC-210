package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Represents circle figures
 */
public class Circle extends Ellipse {
	
	/**
	 * Constructs a circle whose bounding rectangle is at topLeft
	 * @param topLeft  top-left corner of circle's bounding rectangle
	 */
	public Circle(Point topLeft) {
		super(topLeft);
		x = (int) topLeft.getX();
		y = (int) topLeft.getY();
	}
	
	/**
	 * Sets diameter of circle to be difference in x coordinates 
	 * between top-left corner of this circle's bounding rectangle
	 * and x coordinate of bottomRight
	 * @param bottomRight  point used to determine diameter of circle
	 */
	public void setDiameter(Point bottomRight) {
		int diam = (int) bottomRight.getX() - x;
		super.setWidth(diam);
		super.setHeight(diam);
	}
	
	@Override 
	public void setWidth(int width) {
		super.setWidth(width);
		super.setHeight(width);
	}
	
	@Override
	public void setHeight(int height) {
		super.setWidth(height);
		super.setHeight(height);
	}
	
	public static void main(String[] args) {
		Circle c = new Circle();
	}
}


