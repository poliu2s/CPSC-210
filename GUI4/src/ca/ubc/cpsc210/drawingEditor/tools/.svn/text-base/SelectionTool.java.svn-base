package ca.ubc.cpsc210.drawingEditor.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ca.ubc.cpsc210.drawingEditor.editor.DrawingEditor;
import ca.ubc.cpsc210.drawingEditor.figures.CompositeFigure;
import ca.ubc.cpsc210.drawingEditor.figures.Figure;

/**
 * Represents selection tools in the drawing application.
 */
public class SelectionTool extends Tool {

	private CompositeFigure currentComposite;
	private JPopupMenu popup;

	/**
	 * Constructor
	 * @param editor   the drawing editor
	 * @param parent   the parent component to which this tool will be added
	 */
	public SelectionTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		currentComposite = null;
		createPopup();
	}

	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Select");
		addToParent(parent);
	}

	/**
	 * Adds currently selected group of figures to drawing
	 * and removes the individual figures within the group 
	 * from the drawing.
	 */
	public void group() {
		editor.addToDrawing(currentComposite);
		for (Figure f : currentComposite)
			editor.removeFromDrawing(f);
		currentComposite.unselect();
		currentComposite = null;
	}

	@Override
	protected void addListener() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setActiveTool(SelectionTool.this);
			}
		});
	}

	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		if (currentComposite != null && e.isPopupTrigger())
			popup.show(e.getComponent(), e.getX(), e.getY());
		else {
			Figure figure = editor.getFigureInDrawing(e.getPoint());
			if (figure != null) {
				figure.select();
				if (currentComposite == null) {
					currentComposite = new CompositeFigure();
				}
				currentComposite.add(figure);
			} else {
				if (currentComposite != null)
					currentComposite.unselect();
				currentComposite = null;
			}
		}
	}

	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		if (currentComposite != null && e.isPopupTrigger())
			popup.show(e.getComponent(), e.getX(), e.getY());
	}

	@Override
	public void mouseClickedInDrawingArea(MouseEvent e) {

	}

	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Helper to create a pop-up menu when pop-up trigger is fired.
	 * Note that the pop-up trigger is system dependent.
	 */
	private void createPopup() {
		JMenuItem menuItem;
		popup = new JPopupMenu();
		menuItem = new JMenuItem("Group");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				group();
			}
		});
		popup.add(menuItem);
	}
}
