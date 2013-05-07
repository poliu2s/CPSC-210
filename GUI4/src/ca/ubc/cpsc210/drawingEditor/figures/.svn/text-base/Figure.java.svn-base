package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Represents figures in the drawing application.
 */
public abstract class Figure {
	/* offsets for drawing shadow when figure is selected */
	protected static final int DX = 1;
	protected static final int DY = 1;
	/* colour of shadow */
	protected static final Color SHADOW_COLOR = new Color(130, 130, 230);
	/* x and y coordinate of Figure */
	protected int x;
	protected int y;
	/* true if figure is selected, false otherwise */
	protected boolean selected = false;

	/**
	 * Draws the figure on a given graphics object
	 * @param g  the graphics object on which the figure will be drawn.
	 */
	public abstract void draw(Graphics g);

	/**
	 * Determines if the smallest rectangle that bounds this figure contains a 
	 * given point.
	 * @param point  the point to be checked
	 * @return  true if the point to be checked is contained within the rectangle
	 * that bounds this figure, false otherwise. 
	 */
	public abstract boolean contains(Point point);

	/**
	 * Translates this figure by an amount dx in the x direction
	 * and dy in the y direction
	 * @param dx  translation in x direction
	 * @param dy  translation in y direction
	 */
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	/**
	 * Marks this figure as selected.
	 */
	public void select() {
		selected = true;
	}
	
	/**
	 * Marks this figure as not selected.  
	 */
	public void unselect() {
		selected = false;
	}
}

