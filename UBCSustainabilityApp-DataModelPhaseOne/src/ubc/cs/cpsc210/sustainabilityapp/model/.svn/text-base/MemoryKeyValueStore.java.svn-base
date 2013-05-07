package ubc.cs.cpsc210.sustainabilityapp.model;

import java.util.ArrayList;
import java.util.List;

public class MemoryKeyValueStore implements KeyValueStore {
	
	//Class fields that store the designed string lists
	List<List<String>> tempStore;
	List<String> tempStoreKey;
	ArrayList<String> tempStoreNullList;
	
	/**
	 * Constructor that assigns memory allocation duties
	 */
	public MemoryKeyValueStore() {
		
		tempStore = new ArrayList<List<String>>();
	}
	
	@Override // see interface for detailed specification
	public void putStringList(String key, List<String> value) throws IllegalArgumentException  {
		tempStoreKey = new ArrayList<String>();
		
		
		if (key == null) {
			throw new IllegalArgumentException("The key doesn't contain anything!");
		}
		
		// doesn't do anything
		if (value == null) {
			
			String a = "null string";
			value = new ArrayList<String>();
			value.add(a);
		}
		
		
		for (String a : value) {
			if (a == null) {
				throw new IllegalArgumentException("A string in value doesn't contain anything!");
			}
			
			for (int b=0; b<a.length(); b++ ) {
				if (a.charAt(b) == ';') {
					throw new IllegalArgumentException("Reserved character!");
				}
			}
		}
		
		
		
		//Make the key it it's own array
		tempStoreKey.add(key);
		tempStore.add(tempStoreKey);
		
		tempStore.add(value);

		
		
	}
	
	@Override // see interface for detailed specification
	public List<String> getStringList(String key) throws IllegalArgumentException  {
		List<String> returnList = new ArrayList<String>();
		
		if (key == null) {
			throw new IllegalArgumentException("Searching for null key?"); 
		}
		
		for (int i = 0; i<tempStore.size(); i +=2) {
			if (tempStore.get(i).get(0).equals(key)) {
				
				if (tempStore.get(i+1).size() == 1 && tempStore.get(i+1).get(0).equals("null string")) {
					
					return null;
				}
				
				for (String j : tempStore.get(i+1)) {
					returnList.add(j);
				}
			
				
			}
		}
		return returnList;
	}
	
}
