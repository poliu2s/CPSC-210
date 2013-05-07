package ca.ubc.cpsc210.alarm.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JPanel;

import ca.ubc.cpsc210.alarm.model.Alarm;
import ca.ubc.cpsc210.alarm.model.Observable;
import ca.ubc.cpsc210.alarm.model.Observer;

/**
 * Represents visual user interface for alarm.
 */
public class AlarmUI extends JPanel implements Observer {
	private static final int WIDTH = 100;
	private static final int HEIGHT = 30;
	private static final int TEXT_INDENT = 30;
	private static final String data[] = {"S i l e n t...", "R i n g i n g..."};
	private int displayString;
	private Color fillColor;
	private Alarm alarm;
	
	/**
	 * Constructor creates interface to display status of alarm
	 */
	public AlarmUI() {
		displayString = 0;
		fillColor = Color.green;
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(fillColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawChars(data[displayString].toCharArray(), 
				0, 
				data[displayString].length(), 
				TEXT_INDENT, 
				2 * getHeight() / 3);
	}
	
	@Override
	public void update() {
		displayString = (alarm.isSounding() ? 1 : 0);
		fillColor = (alarm.isSounding() ? Color.RED : Color.GREEN);
		repaint();
	}

	@Override
	public void setObservable(Observable o) {
		alarm = (Alarm) o;		
	}
}
