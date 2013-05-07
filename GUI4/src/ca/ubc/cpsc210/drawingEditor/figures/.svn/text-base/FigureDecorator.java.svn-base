package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Represents a figure decorator
 */
public abstract class FigureDecorator extends Figure {
	protected static final int SIZE = 10;
	protected Figure decoratedFigure;
	
	public FigureDecorator(Figure f) {
		x = f.x;
		y = f.y;
		decoratedFigure = f;
	}
	
	@Override
	public void draw(Graphics g) {
		decoratedFigure.draw(g);
	}

	@Override
	public boolean contains(Point point) {
		return decoratedFigure.contains(point);
	}
	
	@Override
	public void translate(int dx, int dy) {
		super.translate(dx, dy);
		decoratedFigure.translate(dx, dy);
	}
	
	@Override
	public void select() {
		super.select();
		decoratedFigure.select();
	}
	
	@Override
	public void unselect() {
		super.unselect();
		decoratedFigure.unselect();
	}
}
