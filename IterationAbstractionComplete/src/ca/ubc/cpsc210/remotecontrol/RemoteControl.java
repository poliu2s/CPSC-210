package ca.ubc.cpsc210.remotecontrol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * A remote control.
 */
public class RemoteControl implements Iterable<Channel>{
	
	// Add private variables to provide an implementation
	// of the remote here.
	private List<Channel> channels;
	
	
	public RemoteControl() {
		// Add initialization of private variables here.
		channels = new ArrayList<Channel>();		
	}
	
	public void addChannel(int numberOfChannel) {
		// Add a new channel to the remote here.
		Channel aChannel = new Channel(numberOfChannel);
		channels.add(aChannel);
	}

	@Override
	public Iterator<Channel> iterator() {
		return channels.iterator();
	}
	

}

