package main;

import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
	public void testRemoveItem() {
		// A1: Non empty list without doubles, remove non existing set
		list.removeItem(setD);
		assertEquals("Size should be 3",
				3, list.getSize());

		// A2B1: Non empty list without doubles, remove existing set
		list.removeItem(setA);
		assertEquals("Size should be 2",
				2, list.getSize());
		assertEquals("First element should be setB",
				setB, list.getAt(0));

		// A2B2: Non empty list with doubles, remove non existing set
		list.add(setB);
		list.removeItem(setD);
		assertEquals("Size should be 3",
				3, list.getSize());

		// A3B2: Non empty list with doubles, remove existing set
		list.removeItem(setB);
		assertEquals("Size should be 1",
				1, list.getSize());
		assertEquals("First element should be setC",
				setC, list.getAt(0));
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
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetAtException() {
		// Liste non vide, index superieur � la taille
		list.setAt(setD,list.getSize()+1);
	}
	
	// On devrait nous occuper du negatif??
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetAtNegativ() {
		// Liste non vide, index negatif
		list.setAt(setD,-1);
	}
	
	@Test
	public void testSetAt() {
		// Liste non vide, element non vide, premier de la liste
		list.setAt(setD,0);
		assertEquals("First element must be arrayD", setD, list.getAt(0));
		// Size shouldn't change
		assertEquals("Size must be 3", 3, list.getSize());
		
		// Liste non vide, element vide, dernier de la liste
		setA.clear();
		list.setAt(setA,list.getSize()-1);
		assertEquals("Last element must be arrayA", setA, list.getAt(list.getSize()-1));
		
		// Size shouldn't change
		assertEquals("Size must be 3", 3, list.getSize());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetAtException() {
		// Liste non vide, index > taille
		list.getAt(list.getSize()+1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetAtExceptionAtSize() {
		// Liste non vide, index taille
		list.getAt(list.getSize());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetAtNegativ() {
		// Liste non vide, index n�gatif
		list.getAt(-1);
	}
	
	@Test
	public void testGetAt() {
		// Liste non vide, index nul
		assertEquals("First element must be arrayA", setA, list.getAt(0));
		// Liste non vide, index ok
		assertEquals("Second element must be arrayB", setB, list.getAt(1));
		// Liste non vide, index taille - 1
		assertEquals("Last element must be arrayC", setC, list.getAt(list.getSize()-1));
	}
	
	@Test
	public void testGetSize() {
		// Liste non vide
		assertEquals("List size must be 3", 3, list.getSize());
		// Liste vide
		list = new MyListImpl();
		assertEquals("List must be empty", 0, list.getSize());
	}
	
	@Test
	public void testReset() {
		// Liste non vide
		list.reset();
		assertEquals("List must be empty", 0, list.getSize());
		// Liste est deja vide
		list.reset();
		assertEquals("List must be empty", 0, list.getSize());
	}
}
