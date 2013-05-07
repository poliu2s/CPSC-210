package ca.ubc.cpsc.superbus.model.exception;

@SuppressWarnings("serial")
public class DoorException extends BusException {

	public DoorException() {
		
	}
	
	public DoorException(String e) {
		super(e);
	}
}
