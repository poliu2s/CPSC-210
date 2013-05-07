package ca.ubc.cpsc210.filesystem;


public abstract class FileSystemResource {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileSystemResource other = (FileSystemResource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	protected String name;
	
	public String getName() {
		return name;
	}
	
	public abstract void print();
	
	public FileSystemResource find(String name) {
		if (getName().equals(name))
			return this;
		else
			return null;
	}

}
