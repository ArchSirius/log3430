package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MyListTest {

	private MyListImpl list;
	private ArrayList<Object> arrayA;
	private ArrayList<Object> arrayB;
	private ArrayList<Object> arrayC;
	private ArrayList<Object> arrayD;
	
	@Before
	public void setUp(){
		list = new MyListImpl();
		
		// Create 4 arrays 
		arrayA = new ArrayList<Object>();
		arrayB = new ArrayList<Object>();
		arrayC = new ArrayList<Object>();
		arrayD = new ArrayList<Object>();
		arrayA.add(1);
		arrayB.add(2);
		arrayC.add(3);
		arrayD.add(4);
		
		// Start with a list of three arrays
		list.add(arrayA);
		list.add(arrayB);
		list.add(arrayC);
		
	}
	
	@Test
	public void testAdd() {
		assertEquals("Initial size must be 3", 3, list.getSize());
		list.add(arrayD);
		assertEquals("Size must be 4", 4, list.getSize());
	}
	
	@Test
	public void testRemoveItem() {
		list.removeItem(arrayA);
		assertEquals("First element must be arrayB", arrayB, list.getAt(0));
		// Size should now be one less
		assertEquals("Size must be 2", 2, list.getSize());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveAtException() {
		list.removeAt(list.getSize()+1);
	}
	
	// On devrait nous occuper du negatif??
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveAtNegativ() {
		list.removeAt(-1);
	}
	
	@Test
	public void testRemoveAt() {
		list.removeAt(0);
		assertEquals("First element must be arrayB", arrayB, list.getAt(0));
		// Size should now be one less
		assertEquals("Size must be 2", 2, list.getSize());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetAtException() {
		list.setAt(arrayD,list.getSize()+1);
	}
	
	// On devrait nous occuper du negatif??
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetAtNegativ() {
		list.setAt(arrayD,-1);
	}
	
	@Test
	public void testSetAt() {
		list.setAt(arrayD,0);
		assertEquals("First element must be arrayD", arrayD, list.getAt(0));
		// Size shouldn't change
		assertEquals("Size must be 3", 3, list.getSize());
		
		list.setAt(arrayA,list.getSize()-1);
		assertEquals("Last element must be arrayA", arrayA, list.getAt(list.getSize()-1));
		// Size shouldn't change
		assertEquals("Size must be 3", 3, list.getSize());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetAtException() {
		list.getAt(list.getSize()+1);
	}
	
	// On devrait nous occuper du negatif?? et on fait quoi avec pos = size?
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetAtNegativ() {
		list.getAt(-1);
	}
	
	@Test
	public void testGetAt() {
		assertEquals("First element must be arrayA", arrayA, list.getAt(0));
		assertEquals("Last element must be arrayC", arrayC, list.getAt(list.getSize()));
	}

}
