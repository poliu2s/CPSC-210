package ca.ubc.cpsc210.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemResource {
	List<FileSystemResource> children;

	Folder(String name) {
		this.name = name;
		children = new ArrayList<FileSystemResource>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		return result;
	}
	
	void addChild(FileSystemResource child) {
		children.add(child);
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("Directory: " + getName());
		for (FileSystemResource f : children) {
			f.print();
		}
	}
	
	public FileSystemResource getChildNamed(String name) {
		for (FileSystemResource f : children) {
			if (f.getName().equals(name))
				return f;
		}
		return null;
	}

	public FileSystemResource find(String name) {
		if (super.find(name) != null)
			return super.find(name);
		for (FileSystemResource f : children) {
			if (f.find(name) != null)
				return f.find(name);
		}
		return null;
	}

}
