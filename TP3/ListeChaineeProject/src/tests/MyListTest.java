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
	private ArrayList<Object> setC;
	private ArrayList<Object> setD;

	@Before
	public void setUp() {
		list = new MyListImpl();

		// Create 4 sets
		setA = new ArrayList<Object>();
		setB = new ArrayList<Object>();
		setC = new ArrayList<Object>();
		setD = new ArrayList<Object>();
		setA.add(1);
		setB.add(2);
		setC.add(3);
		setD.add(4);

		// Start with a list of three sets
		list.add(setA);
		list.add(setB);
		list.add(setC);
	}

   /*
	* Tests MyList.add
	* A1: List is empty
	* A2: List is not empty
	* B1: Element is empty
	* B2: Element is not empty and is not in List
	* B3: Element is not empty and is in List
	*
	* A1B1 should add
	* A1B2 should add
	* A1B3 should add
	* A2B1 should add
	* A2B2 should add
	* A2B3 --
	*/
	@Test
	public void testAdd() {
		// Non empty list, add new set
		list.add(setD);
		assertEquals("Size should be 4",
				4, list.getSize());

		// Non empty list, add existing set
		list.add(setD);
		assertEquals("Size should be 5",
				5, list.getSize());

		// Non empty list, add empty set
		setD = new ArrayList<Object>();
		list.add(setD);
		assertEquals("Size should be 6",
				6, list.getSize());

		// Empty list, add empty set
		list = new MyListImpl();
		list.add(setD);
		assertEquals("Size should be 1",
				1, list.getSize());

		// Empty list, add non empty set
		list = new MyListImpl();
		list.add(setA);
		assertEquals("Size should be 1",
				1, list.getSize());
	}

   /*
	* Tests MyList.removeItem
	* A1: List is empty
	* A2: List is not empty and has no doubles
	* A3: List is not empty and has doubles
	* B1: Element is not in List
	* B2: Element is in List
	*
	* A1     B1 should not remove
	* A1     B2 --
	* A[2-3] B1 should remove 0
	* A2     B2 should remove 1
	* A3     B2 should remove N
	*/
	@Test
	public void testRemoveItemA1() {
		// A1: Non empty list without doubles, remove non existing set
		list.removeItem(setD);
		assertEquals("Size should be 3",
				3, list.getSize());
	}
	
	@Test
	public void testRemoveItemA2B1() {
		// A2B1: Non empty list without doubles, remove existing set
		list.removeItem(setA);
		assertEquals("Size should be 2",
				2, list.getSize());
		assertEquals("First element should be setB",
				setB, list.getAt(0));
	}
	
	@Test
	public void testRemoveItemA2B2() {
		list.removeItem(setA);
		// A2B2: Non empty list with doubles, remove non existing set
		list.add(setB);
		list.removeItem(setD);
		assertEquals("Size should be 3",
				3, list.getSize());
	}
	
	@Test
	public void testRemoveItemA3B2() {
		list.removeItem(setA);
		list.add(setB);
		// A3B2: Non empty list with doubles, remove existing set
		list.removeItem(setB);
		assertEquals("Size should be 1",
				1, list.getSize());
		assertEquals("First element should be setC",
				setC, list.getAt(0));
	}
	
	/*
	* Tests MyList.removeItem
	* Test en boite blanche pour couvrir toutes les branches
	* Retrait du tout dernier élément de la liste
	*/
	@Test
	public void testRemoveItemLast() {
		// Remove last item from the list
		list.removeItem(setC);
		assertEquals("Size should be 2",
				2, list.getSize());
	}

   /*
	* Tests MyList.removeAt
	* A1: Index < 0
	* A2: Index = 0
	* A3: 0 < Index < list.size
	* A4: Index = list.size
	* A5: Index > list.size
	*
	* A[1,4-5] should throw IndexOutOfBoundsException
	* A[2-4]   should remove 1
	*/
	@Test
	public void testRemoveAt() {
		// A3: 0 < Index < list.size
		list.removeAt(1);
		assertEquals("First element should be setA",
				setA, list.getAt(0));

		// A2: Index = 0
		list.removeAt(0);
		assertEquals("First element should be setC",
				setC, list.getAt(0));

		// A4: Index = list.size
		try {
			list.removeAt(2);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}

		// A5: Index > list.size
		try {
			list.removeAt(3);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}

		// A1: Index < 0
		try {
			list.removeAt(-1);
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
	* Tests MyList.setAt
	* A1: Index < 0
	* A2: Index = 0
	* A3: 0 < Index < list.size
	* A4: Index = list.size
	* A5: Index > list.size
	*
	* A[1,4-5] should throw IndexOutOfBoundsException
	* A[2-4]   should set
	*/
	@Test
	public void testSetAt() {
		// A2: Index = 0
		list.setAt(setD, 0);
		assertEquals("First element should be setD",
				setD, list.getAt(0));

		// A3: 0 < Index < list.size
		list.setAt(setD, 2);
		assertEquals("Last element should be setD",
				setD, list.getAt(2));

		// A4: Index = list.size
		try {
			list.setAt(setD, 3);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}

		// A5: Index > list.size
		try {
			list.setAt(setD, 4);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}

		// A1: Index < 0
		try {
			list.setAt(setD, -1);
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
	* Tests MyList.getAt
	* A1: Index < 0
	* A2: Index = 0
	* A3: 0 < Index < list.size
	* A4: Index = list.size
	* A5: Index > list.size
	*
	* A[1,4-5] should throw IndexOutOfBoundsException
	* A[2-4]   should set
	*/
	@Test
	public void testGetAt() {
		// A2: Index = 0
		assertEquals("First element should be setA",
				setA, list.getAt(0));

		// A3: 0 < Index < list.size
		assertEquals("Last element should be getC",
				setC, list.getAt(2));

		// A4: Index = list.size
		try {
			list.getAt(3);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}

		// A5: Index > list.size
		try {
			list.getAt(4);
			fail("Expected IndexOutOfBoundsException");
		}
		catch (final IndexOutOfBoundsException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IndexOutOfBoundsException");
		}

		// A1: Index < 0
		try {
			list.getAt(-1);
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
	* Tests MyList.getSize
	*/
	@Test
	public void testGetSize() {
		assertEquals("List size should be 3",
				3, list.getSize());
		list = new MyListImpl();
		assertEquals("List size should be 0",
				0, list.getSize());
	}
	
   /*
	* Tests MyList.getSize
	*/
	@Test
	public void testReset() {
		list.reset();
		assertEquals("List size should be 0",
				0, list.getSize());
		list.reset();
		assertEquals("List size should be 0",
				0, list.getSize());
	}
}
