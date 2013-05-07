package ca.ubc.cpsc.superbus.model;

import ca.ubc.cpsc.superbus.model.exception.DoorException;
import ca.ubc.cpsc.superbus.model.exception.MotionException;
import ca.ubc.cpsc.superbus.model.exception.RampException;

/*
 * Models a bus
 */
public class Bus {

	private boolean bStopped;
	private boolean bDoorOpen;
	private boolean bRampExtended;
	
	/*
	 * Constructor
	 * EFFECTS: bus is stopped, ramp is not lowered and door is closed.
	 */
	public Bus() {
		bStopped = true;
		bDoorOpen = false;
		bRampExtended = false;
	}
	
	/*
	 * REQUIRES: !isDoorOpen()
	 * MODIFIES: this
	 * EFFECTS: bus is moving
	 */
	public void go() throws DoorException{
		if (isDoorOpen()) {
			throw new DoorException("Door is still open!");
		}
		else {
			bStopped = false;
		}

	}
	
	/*
	 * MODIFIES: this
	 * EFFECTS: bus is stopped
	 */
	public void stop() {
		bStopped = true;
	}
	
	/*
	 * EFFECTS: returns true if bus is stopped, false otherwise
	 */
	public boolean isStopped() {
		return bStopped;
	}
	
	/*
	 * REQUIRES: isStopped()
	 * MODIFIES: this
	 * EFFECTS: door is open
	 */
	public void openDoor() throws MotionException{
		if (!isStopped()) {
			throw new MotionException("Bus Still moving!");
		} else {
			bDoorOpen = true;
		}

	}
	
	/*
	 * REQUIRES: !isRampLowered()
	 * MODIFIES: this
	 * EFFECTS: door is closed
	 */
	public void closeDoor() throws RampException {
		if (isRampLowered()) {
			throw new RampException("Ramp is lowered!!");

		} else {
			bDoorOpen = false;
		}

	}
	
	/*
	 * EFFECTS: returns true if door is open, false otherwise
	 */
	public boolean isDoorOpen() {
		return bDoorOpen;
	}
	
	/*
	 * REQUIRES: isDoorOpen() (which requires isStopped())
	 * MODIFIES: this
	 * EFFECTS: ramp is lowered
	 */
	public void lowerRamp() throws DoorException, MotionException {
		
		
		if (!isStopped()){
			throw new MotionException("Blahb alsdfh");
		}else if (!isDoorOpen()) {
			throw new DoorException("Door is not open, god your stupid!");
		}
		else 
			bRampExtended = true;
	}
	
	/*
	 * REQUIRES: isDoorOpen() 
	 * MODIFIES: this
	 * EFFECTS: ramp is raised
	 */
	public void raiseRamp() throws DoorException{
		if (!isDoorOpen()) {
			throw new DoorException("Door not open!");
		}
		else 
			bRampExtended = false;
	}
	
	/*
	 * EFFECTS: returns true if ramp is lowered, false otherwise
	 */
	public boolean isRampLowered() {
		return bRampExtended;
	}
}

