package ca.ubc.cpsc210.arrayset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Implements a generic set class using arrays
 * 
 * @author CPSC 210 instructor
 */
public class ArraySet<E> implements Iterable<E> {

	private Object[] items;
	private int numberOfItems;
	private static int DEFAULT_CAPACITY = 10;
	private static int SIZE_MULTIPLER = 2;

	/*
	 * Constructs an empty set.
	 * 
	 * EFFECTS: set is empty
	 */
	public ArraySet() {
		items = new Object[DEFAULT_CAPACITY];
		numberOfItems = 0;
	}

	/*
	 * Adds a given item into the set.
	 * 
	 * MODIFIES: this
	 * EFFECTS: if ( newItem != null AND NOT contains(newItem) )
	 *          then item is added to set
	 *          otherwise the set is not changed;
	 *          returns true if item added to set, false otherwise.
	 */
	public boolean add(E newItem) {

		if (newItem == null)
			return false;

		if (!contains(newItem)) {
			if (numberOfItems == items.length) {
				// array is full so we should expand it
				expand();
			}
			items[numberOfItems] = newItem;
			numberOfItems++;
			return true;
		} else
			return false; // already contains the item, item will not be added
	}

	/*
	 * Check if a given object is in the set.
	 * 
	 * EFFECTS: returns true if item is in the set, and false otherwise.
	 */
	public boolean contains(E item) {

		if (item == null)
			return false;

		for (int i = 0; i < numberOfItems; i++)
			if (items[i].equals(item))
				return true;
		return false;
	}

	/*
	 * Removes a given object from the set.
	 * 
	 * MODIFIES: this
	 * EFFECTS: if contains(item) THEN item is removed from set
	 *          otherwise the set is not changed;
	 *          returns true if item was removed from the set, and false otherwise.
	 */
	public boolean remove(E item) {

		if (item == null)
			return false;

		for (int i = 0; i < numberOfItems; i++)
			if (items[i].equals(item)) {

				// replace the found object with the last one in array
				// and decrease the count of the size of the set
				items[i] = items[numberOfItems - 1];
				numberOfItems--;

				return true;
			}
		return false;
	}

	/*
	 * Removes all elements from the set.
	 * 
	 * MODIFIES: this
	 * EFFECTS: set is empty
	 */
	public void clear() {
		items = new Object[DEFAULT_CAPACITY];
		numberOfItems = 0;
	}

	/*
	 * Returns the number of items in the set.
	 * 
	 * EFFECTS: returns number of objects contained in the set.
	 */
	public int size() {
		return numberOfItems;
	}

	/*
	 * Expands capacity of array.
	 * 
	 * MODIFIES: this
	 * EFFECTS: increases the size of the array holding the set by a factor of
	 *          SIZE_MULTIPLIER. 
	 */
	private void expand() {
		Object[] newArray = new Object[items.length * SIZE_MULTIPLER];
		for (int i = 0; i < items.length; i++)
			newArray[i] = items[i];
		items = newArray;

	}

	@Override
	public Iterator<E> iterator() {
		return new ArraySetIterator<E>();
	}
	
	private class ArraySetIterator<E> implements Iterator<E> {
		
		int currentLocation;
		
		ArraySetIterator() {
			currentLocation = 0;
		}

		@Override
		public boolean hasNext() {
			return currentLocation < size();
		}

		@Override
		public E next() {
			if (hasNext()) {
				E returnVal = (E) items[currentLocation];
				currentLocation++;
				return returnVal;
			} else
				throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		
	}

}
