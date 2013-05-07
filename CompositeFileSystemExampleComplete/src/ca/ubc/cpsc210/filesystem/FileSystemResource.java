package ca.ubc.cpsc210.filesystem;


public abstract class FileSystemResource {
	
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public abstract void print();
}
