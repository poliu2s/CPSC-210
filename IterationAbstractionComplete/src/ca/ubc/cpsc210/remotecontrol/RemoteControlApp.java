package ca.ubc.cpsc210.remotecontrol;

public class RemoteControlApp {
	/*
	 * Sample app to demonstrate use of remote control
	 */
	public static void main(String[] args) {
		RemoteControl rc = new RemoteControl();
		
		// Add channels
		for ( int i = 0; i < 100; i++ )
			rc.addChannel(i);
		
		// Channel surf, playing each channel
		for (Channel c: rc)
			c.play();
	}
}
