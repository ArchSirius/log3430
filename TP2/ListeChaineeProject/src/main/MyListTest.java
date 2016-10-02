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
	public void recurentSetUp(){
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
		
		// Liste non vide, ajout d’un nouvel ensemble
		list.add(arrayD);
		assertEquals("Size must be 4", 4, list.getSize());
		
		// Liste non vide, ajout d’un ensemble déjà présent
		list.add(arrayD);
		assertEquals("Size must be 5", 5, list.getSize());
		
		// Liste non vide, ajout d’un ensemble vide
		arrayD.clear();
		list.add(arrayD);
		assertEquals("Size must be 6", 6, list.getSize());
		
		// Liste vide, ajout d’un ensemble vide
		list = new MyListImpl();
		list.add(arrayD);
		assertEquals("Size must be 1", 1, list.getSize());
		
		// Lise vide, ajout d’un ensemble non vide
		list = new MyListImpl();
		list.add(arrayA);
		assertEquals("Size must be 1", 1, list.getSize());
	}
	
	@Test
	public void testRemoveItem() {
		// Liste non vide sans doublon, item non vide et non présent
		list.removeItem(arrayD);
		// Size should be unchanged
		assertEquals("Size must be 3", 3, list.getSize());
		
		// Liste non vide sans doublons, item vide
		arrayD.clear();
		list.removeItem(arrayD);
		// Size should be unchanged
		assertEquals("Size must be 3", 3, list.getSize());
		
		// Liste non vide sans doublon, item non vide et présent
		list.removeItem(arrayA);
		assertEquals("First element must be arrayB", arrayB, list.getAt(0));
		// Size should now be one less
		assertEquals("Size must be 2", 2, list.getSize());
		
		// Liste non vide avec doublons, item vide
		list.add(arrayD);
		list.add(arrayD);
		list.removeItem(arrayD);
		// Size should now be one more
		assertEquals("Size must be 3", 3, list.getSize());
		
		// Liste non vide avec doublon, item non vide et non présent
		list.add(arrayB);
		list.removeItem(arrayC);
		// Size should be unchanged
		assertEquals("Size must be 3", 3, list.getSize());
		
		// Liste non vide avec doublon, item non vide, doublon et présent
		list.removeItem(arrayB);
		// Size should be one less
		assertEquals("Size must be 2", 2, list.getSize());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveAtException() {
		// Liste non vide, index > taille
		list.removeAt(list.getSize()+1);
	}
	
	// On devrait nous occuper du negatif??
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveAtNegativ() {
		// Liste non vide, index négatif
		list.removeAt(-1);
	}
	
	@Test
	public void testRemoveAt() {
		// Liste non vide, index nul 
		list.removeAt(0);
		assertEquals("First element must be arrayB", arrayB, list.getAt(0));
		// Size should now be one less
		assertEquals("Size must be 2", 2, list.getSize());
		
		// Liste non vide, index taille - 1
		list.removeAt(list.getSize()-1);
		assertEquals("Last element must be arrayB", arrayB, list.getAt(list.getSize()-1));
		// Size should now be one less
		assertEquals("Size must be 1", 1, list.getSize());
		
		// Liste non vide, index ok
		list.add(arrayA);
		list.add(arrayD);
		list.add(arrayC);
		list.removeAt(2);
		// Size should now be 2 more
		assertEquals("Size must be 3", 3, list.getSize());
		
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetAtException() {
		// Liste non vide, index superieur à la taille
		list.setAt(arrayD,list.getSize()+1);
	}
	
	// On devrait nous occuper du negatif??
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetAtNegativ() {
		// Liste non vide, index negatif
		list.setAt(arrayD,-1);
	}
	
	@Test
	public void testSetAt() {
		// Liste non vide, element non vide, premier de la liste
		list.setAt(arrayD,0);
		assertEquals("First element must be arrayD", arrayD, list.getAt(0));
		// Size shouldn't change
		assertEquals("Size must be 3", 3, list.getSize());
		
		// Liste non vide, element vide, dernier de la liste
		arrayA.clear();
		list.setAt(arrayA,list.getSize()-1);
		assertEquals("Last element must be arrayA", arrayA, list.getAt(list.getSize()-1));
		
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
		// Liste non vide, index négatif
		list.getAt(-1);
	}
	
	@Test
	public void testGetAt() {
		// Liste non vide, index nul
		assertEquals("First element must be arrayA", arrayA, list.getAt(0));
		// Liste non vide, index ok
		assertEquals("Second element must be arrayB", arrayB, list.getAt(1));
		// Liste non vide, index taille - 1
		assertEquals("Last element must be arrayC", arrayC, list.getAt(list.getSize()-1));
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
