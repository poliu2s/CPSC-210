package ca.ubc.cpsc210.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemResource {
	List<FileSystemResource> children = new ArrayList<FileSystemResource>();
	
	Folder(String name) {
		this.name = name;
	}
	
	@Override
	public void print() {
		System.out.println("Directory: " + getName());
		for (FileSystemResource f : children)
			f.print();
	}
	
	void addChild(FileSystemResource c) {
		children.add(c);
	}
}
