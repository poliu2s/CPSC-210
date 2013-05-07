package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents composite figures (groups of figures) in the drawing application
 */
public class CompositeFigure extends Figure implements Iterable<Figure> {
	private Set<Figure> group;
	
	/**
	 * Constructor creates an empty group
	 */
	public CompositeFigure() {
		group = new HashSet<Figure>();
	}

	@Override
	public void draw(Graphics g) {
		for( Figure f : group )
			f.draw( g );
	}

	@Override
	public boolean contains(Point point) {
		Iterator<Figure> itr = group.iterator();
		boolean bContains = false;
		
		while( itr.hasNext() && !bContains )
			if( itr.next().contains(point) )
				bContains = true;
		
		return bContains;
	}

	@Override
	public void translate(int dx, int dy) {
		super.translate(dx, dy);
		for( Figure f : group )
			f.translate(dx, dy);
	}
	
	/**
	 * Adds a figure to this group
	 * @param f  the figure to add to this group
	 */
	public void add( Figure f ) {
		group.add( f );
		
		// make coordinates of group the coordinates of the most
		// recent figure added (could do something fancier than this
		// like make it the average location of the figures in
		// the group
		x = f.x;
		y = f.y;
	}
	
	/**
	 * Adds a set of figures to this group
	 * @param figs  the set of figures to add to this group
	 */
	public void add( Set<Figure> figs ) {
		for (Figure f : figs)
			add(f);
	}
	
	/**
	 * Gets number of figures in group
	 * @return number of figures in group
	 */
	public int size() {
		return group.size();
	}
	
	@Override
	public void select() {
		super.select();
		for (Figure f : group)
			f.select();
	}
	
	@Override
	public void unselect() {
		super.unselect();
		for (Figure f : group )
			f.unselect();
	}

	@Override
	public Iterator<Figure> iterator() {
		return group.iterator();
	}
}
