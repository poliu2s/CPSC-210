package ca.ubc.cpsc210.alarm.ui;

import java.awt.Component;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ca.ubc.cpsc210.alarm.model.Event;
import ca.ubc.cpsc210.alarm.model.EventLog;
import ca.ubc.cpsc210.alarm.model.Observable;
import ca.ubc.cpsc210.alarm.model.Observer;

/**
 * Represents a screen printer for printing event log to screen.
 */
public class ScreenPrinter extends JInternalFrame implements LogPrinter, Observer {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	private JTextArea logArea;
	private EventLog eventlog;

	/**
	 * Constructor sets up window in which log will be printed on screen
	 * @param parent  the parent component
	 */
	public ScreenPrinter(Component parent) {
		super("Event log", false, true, false, false);
		logArea = new JTextArea();
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea);
		add(scrollPane);
		setSize(WIDTH, HEIGHT);
		setPosition(parent);
		setVisible(true);
	}

	@Override
	public void printLog(EventLog el) {
		for (Event next : el)
			logArea.setText(logArea.getText() + next.toString() + "\n\n");
		repaint();
	}

	/**
	 * Sets the position of window in which log will be printed relative to 
	 * parent
	 * @param parent  the parent component
	 */
	private void setPosition(Component parent) {
		setLocation(parent.getWidth() - getWidth() - 20,
				parent.getHeight() - getHeight() - 20);
	}

	public void update() {
		logArea.setText("");
		printLog(eventlog);
	}

	@Override
	public void setObservable(Observable o) {
		this.eventlog = (EventLog) o;		
	}
}
