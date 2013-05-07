package ca.ubc.cpsc210.alarm.model;

public class ConsoleWriter implements Observer {
	private Alarm alarm;

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("The alarm is " + (alarm.isSounding() ? "ringing" : "off"));
	}

	@Override
	public void setObservable(Observable o) {
		alarm = (Alarm) o;
	}
}
