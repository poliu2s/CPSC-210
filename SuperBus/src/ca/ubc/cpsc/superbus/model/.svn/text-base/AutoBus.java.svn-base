package ca.ubc.cpsc.superbus.model;

import ca.ubc.cpsc.superbus.model.exception.BusException;
import ca.ubc.cpsc.superbus.model.exception.DoorException;
import ca.ubc.cpsc.superbus.model.exception.MotionException;

/* 
 * A new and improved bus that automates some
 * operations.
 */
public class AutoBus extends Bus {
	
	public AutoBus() {
		super();
	}
	
	/*
	 * REQUIRES: isStopped()
	 * MODIFIES: this
	 * EFFECTS: door is open and ramp is lowered 
	 */
	/*
	 * MODIFIES: this
	 * EFFECTS: if isMoving() throws MotionException
	 *          otherwise door is open and ramp is lowered
	 */
	@Override
	public void lowerRamp() throws MotionException {
		if (!super.isStopped()) {
			throw new MotionException("Bus is moving!");
		}
		try {
			openDoor();
			super.lowerRamp();
		} catch (BusException e) {
			
		}
		
	}
	
	/*
	 * REQUIRES: isDoorOpen()
	 * MODIFIES: this
	 * EFFECTS: ramp is raised and door is closed
	 */
	/*
	 * MODIFIES: this
	 * EFFECTS: ramp is raised and door is closed
	 */

	@Override
	public void raiseRamp() throws DoorException{
		if (!isDoorOpen()) {
			throw new DoorException("blskfj");
		}
		try {
			super.raiseRamp();
			closeDoor();
			
		} catch (BusException e) {
			
		}
	}
}
