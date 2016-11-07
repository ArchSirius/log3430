package tests;

import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.MyListImpl;

public class MyListTest {

	private MyListImpl list;
	private ArrayList<Object> setA;
	private ArrayList<Object> setB;

	@Before
	public void setUp() {
		// Create 4 sets
		setA = new ArrayList<Object>();
		setB = new ArrayList<Object>();
		setA.add(1);
		setB.add(2);
	}
	
	/*
	* Cas de test 1
	* Sequence
	* 	setAt
	* Oracle : S0,S2
	*/
	@Test
	public void test1(){
		// Empty list
		list = new MyListImpl();
		try {
			list.setAt(setA, 0);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}
	}
	
	/*
	* Cas de test 2
	* Sequence
	* 	removeAt
	* Oracle : S0,S2
	*/
	@Test
	public void test2(){
		// Empty list
		list = new MyListImpl();
		try {
			list.removeAt(0);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}
	}
	
	/*
	* Cas de test 3
	* Sequence
	* 	removeItem
	* Oracle : S0,S0
	*/
	@Test
	public void test3(){
		// Empty list
		list = new MyListImpl();
		// Remove random item
		try {
			list.removeItem(setA);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException, found " + t.getClass());
		}
	}
	
	/*
	* Cas de test 4
	* Sequence
	* 	reset
	* Oracle : S0,S0
	*/
	@Test
	public void test4(){
		// Empty list
		list = new MyListImpl();
		// Reset list
		list.reset();
		assertEquals("List should be empty, size should be 0",
				0, list.getSize());
	}
	
	/*
	* Cas de test 5
	* Sequence
	* 	add
	* Oracle : S0,S1
	*/
	@Test
	public void test5(){
		// Empty list
		list = new MyListImpl();
		// Add item
		list.add(setA);
		assertEquals("List should not be empty, size should be 1",
				1, list.getSize());
	}
	
	/*
	* Cas de test 6
	* Sequence [avec conditions]
	* 	add
	* 	add
	* 	removeAt [pos<=size && pos >=0 && size>0]
	* Oracle : S0,S1,S1,S1
	*/
	@Test
	public void test6(){
		// Empty list
		list = new MyListImpl();
		// Add 2 items
		list.add(setA);
		list.add(setB);
		// Remove an item
		list.removeAt(0);
		assertEquals("List should not be empty, size should be 1",
				1, list.getSize());
	}
	
	/*
	* Cas de test 7
	* Sequence
	* 	add
	* 	add
	* 	removeItem [(in && size>0) || not in]
	* Oracle : S0,S1,S1,S1
	*/
	@Test
	public void test7(){
		// Empty list
		list = new MyListImpl();
		// Add 2 items
		list.add(setA);
		list.add(setB);
		// Remove an item
		list.removeItem(setB);
		assertEquals("List should not be empty, size should be 1",
				1, list.getSize());
	}
	
	/*
	* Cas de test 8
	* Sequence [avec conditions]
	* 	add
	* 	setAt [pos<= size && pos>=0] 
	* Oracle : S0,S1,S1
	*/
	@Test
	public void test8(){
		// Empty list
		list = new MyListImpl();
		// Add an item
		list.add(setA);
		// Replace an item 
		list.setAt(setB, 0);
		assertEquals("List should not be empty, size should be 1",
				1, list.getSize());
		assertEquals("Element 0 should be setB",
				setB, list.getAt(0));
	}
	
	/*
	* Cas de test 9
	* Sequence
	* 	add
	* 	reset
	* Oracle : S0,S1,S0
	*/
	@Test
	public void test9(){
		// Empty list
		list = new MyListImpl();
		// Add an item
		list.add(setA);
		// Reset list
		list.reset();
		assertEquals("List should be empty, size should be 0",
				0, list.getSize());
	}
	
	/*
	* Cas de test 10
	* Sequence [avec conditions]
	* 	add
	* 	removeAt [pos==0 && size==0]
	* Oracle : S0,S1,S0
	*/
	@Test
	public void test10(){
		// Empty list
		list = new MyListImpl();
		// Add an item
		list.add(setA);
		// Remove the item
		list.removeAt(0);
		assertEquals("List should be empty, size should be 0",
				0, list.getSize());
	}
	
	/*
	* Cas de test 11
	* Sequence [avec conditions]
	* 	add
	* 	removeItem [in && size==0]
	* Oracle : S0,S1,S0
	*/
	@Test
	public void test11(){
		// Empty list
		list = new MyListImpl();
		// Add an item
		list.add(setA);
		// Remove the item
		list.removeItem(setA);
		assertEquals("List should be empty, size should be 0",
				0, list.getSize());
	}
	
	/*
	* Cas de test 12
	* Sequence [avec conditions]
	* 	add
	* 	setAt [pos>size || pos<0]
	* Oracle : S0,S1,S2
	*/
	@Test
	public void test12(){
		// Empty list
		list = new MyListImpl();
		// Add an item
		list.add(setA);
		try {
			list.setAt(setB, 2);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}
	}
	
	/*
	* Cas de test 13
	* Sequence [avec conditions]
	* 	add
	* 	removeAt [pos>size || pos<0]
	* Oracle : S0,S1,S2
	*/
	@Test
	public void test13(){
		// Empty list
		list = new MyListImpl();
		// Add an item
		list.add(setA);
		try {
			list.removeAt(-2);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException, found " + t.getClass());
		}
	}
}
