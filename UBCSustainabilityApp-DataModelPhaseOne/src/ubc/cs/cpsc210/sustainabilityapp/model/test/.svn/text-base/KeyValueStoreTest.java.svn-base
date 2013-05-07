package ubc.cs.cpsc210.sustainabilityapp.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ubc.cs.cpsc210.sustainabilityapp.model.KeyValueStore;
import ubc.cs.cpsc210.sustainabilityapp.model.MemoryKeyValueStore;

/**
 * Test the Memory-based KeyValueStore
 * 
 * @author CPSC 210 Instructor
 */
public class KeyValueStoreTest {

	private KeyValueStore store;

	private static String SAMPLE_KEY = "aKey";
	private static String RESERVED_CHAR_STRING = new String(";");

	// We indicate to JUnit that we expect no exceptions in the general case.
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
		store = new MemoryKeyValueStore();
	}

	/**
	 * Test storing one item for a key.
	 */
	@Test
	public void testPutStringListOneItem() {
		// Form input
		List<String> aList = new ArrayList<String>();
		aList.add("one");
		// Perform operations on store
		store.putStringList(SAMPLE_KEY, aList);
		List<String> resultList = store.getStringList(SAMPLE_KEY);
		// Check results
		assertEquals(1, resultList.size());
		assertTrue(resultList.contains("one"));
	}

	/**
	 * Test storing a slightly longer list (i.e., two) items for a key
	 */
	@Test
	public void testPutStringListTwoItems() {
		// Form input
		List<String> aList = new ArrayList<String>();
		aList.add("one");
		aList.add("two");
		// Operate on store
		store.putStringList(SAMPLE_KEY, aList);
		List<String> resultList = store.getStringList(SAMPLE_KEY);
		// Check result, including list is in the correct order
		assertEquals(2, resultList.size());
		String resultString = resultList.get(0);
		assertTrue(resultString.equals("one"));	
		resultString = resultList.get(1);
		assertTrue(resultString.equals("two"));
	}

	/**
	 * For good measure, test a slightly longer list (i.e., three) items for a key
	 */
	@Test
	public void testPutStringListThreeItems() {
		// Form input
		List<String> aList = new ArrayList<String>();
		aList.add("one");
		aList.add("two");
		aList.add("three");
		// Operate on store
		store.putStringList(SAMPLE_KEY, aList);
		List<String> resultList = store.getStringList(SAMPLE_KEY);
		// Check result
		assertEquals(3, resultList.size());
		String resultString = resultList.get(0);
		assertTrue(resultString.equals("one"));	
		resultString = resultList.get(1);
		assertTrue(resultString.equals("two"));
		resultString = resultList.get(2);
		assertTrue(resultString.equals("three"));
	}

	/**
	 * Ensure an exception is thrown if the key is null
	 */
	@Test
	public void testPutStringListNullKey() {
		exception.expect(IllegalArgumentException.class);
		store.putStringList(null, new ArrayList<String>());
	}

	/**
	 * Test that is is ok to store an empty list for a key
	 */
	@Test
	public void testPutStringListNullItemsList() {
		store.putStringList(SAMPLE_KEY, null);
		assertEquals(null, store.getStringList(SAMPLE_KEY));
	}

	/**
	 * Ensure an exception is thrown if the only item in a list is null
	 */
	@Test
	public void testPutStringListNullItem() {
		List<String> aList = new ArrayList<String>();
		aList.add(null);
		exception.expect(IllegalArgumentException.class);
		store.putStringList(SAMPLE_KEY, aList);
	}
	
	/** 
	 * Ensure an exception is thrown if there is a null item in the list
	 */
	@Test
	public void testPutStringListWithNullItem() {
		List<String> aList = new ArrayList<String>();
		aList.add("one");
		aList.add(null);
		aList.add("three");
		exception.expect(IllegalArgumentException.class);
		store.putStringList(SAMPLE_KEY, aList);
	}

	/** 
	 * Ensure an exception is thrown if there is a reserved character used in the list
	 */
	@Test
	public void testPutStringListReservedCharacterUse() {
		List<String> aList = new ArrayList<String>();
		aList.add("one");
		aList.add(RESERVED_CHAR_STRING);
		aList.add("three");
		exception.expect(IllegalArgumentException.class);
		store.putStringList(SAMPLE_KEY, aList);
	}
	
	/**
	 * Ensure an exception is thrown if we ask for the list for a null key
	 */
	@Test
	public void testGetStringListNull() {
		exception.expect(IllegalArgumentException.class);
		List<String> resultList = store.getStringList(null);
	}

}
