package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import main.MyListImpl;

public class MyListImpl_testsExtra {
	private MyListImpl mli;
	
	@Before
	/*****************************************
	 *	Methode qui initialise MyListImpl via son constructeur et
	 *	valide les paramètres initiaux
	 ****************************************/
	public void setUp()
	{
		
		mli  = new MyListImpl();
		assertNull("La propriete start devrait etre egale a null", mli.getStart());
		assertNull("La propriete current devrait etre egale a start, et donc a null", mli.getCurrent());
		assertTrue("La propriete size devrait etre egale a zero.",mli.getSize() == 0);
	}
	
	@Test
	/*****************************************
	 * Methode qui verifie si une liste vide est geree pour SetAt
	 ****************************************/
	public void testEmptySet() {
		ArrayList<Object> objList1 = new ArrayList<Object>();
		objList1.add(new Integer(1));
		try
		{
			mli.setAt(objList1, 0);
			fail("Une exception devrait etre lancee.");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une ArrayIndexOutOfBoundsException, mais lance plutot une "+e.getClass().getName());
		}
		
	}
	
	@Test
	/*****************************************
	 * Methode qui verifie si une liste vide est geree pour GetAt
	 ****************************************/
	public void testEmptyGet() {
		try
		{
			mli.getAt(0);
			fail("Une exception devrait etre lancee.");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une ArrayIndexOutOfBoundsException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	@Test
	/*****************************************
	 * Methode qui verifie si la une position trop elevee est geree pour SetAt
	 ****************************************/
	public void testOverflowSet() {
		ArrayList<Object> objList1 = new ArrayList<Object>();
		objList1.add(new Integer(1));
		mli.add(objList1);
		try
		{
			mli.setAt(objList1, 1);
			fail("Une exception devrait etre lancee.");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une ArrayIndexOutOfBoundsException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	@Test
	/*****************************************
	 * Methode qui verifie si une position trop elevee est geree pour GetAt
	 ****************************************/
	public void testOverflowGet() {
		ArrayList<Object> objList1 = new ArrayList<Object>();
		objList1.add(new Integer(1));
		mli.add(objList1);
		try
		{
			mli.getAt(1);
			fail("Une exception devrait etre lancee.");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une ArrayIndexOutOfBoundsException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	@Test
	/*****************************************
	 * Methode qui verifie si la position negative est geree pour SetAt
	 ****************************************/
	public void testNegativeSet() {
		ArrayList<Object> objList1 = new ArrayList<Object>();
		objList1.add(new Integer(1));
		try
		{
			mli.setAt(objList1,-1);
			fail("Une exception devrait etre lancee.");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une ArrayIndexOutOfBoundsException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	@Test
	/*****************************************
	 * Methode qui verifie si la position negative est geree pour GetAt
	 ****************************************/
	public void testNegativeGet() {
		try
		{
			mli.getAt(-1);
			fail("Une exception devrait etre lancee.");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une ArrayIndexOutOfBoundsException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	@Test
	/*****************************************
	 *	Methode qui teste les autres methodes qui accedent les attributs
	 *  Start et Size.
	 *  Les deux sont testes ensemble puisqu'ils sont un "Getter" et un "Setter" pour la meme information. 
	 ****************************************/
	public void testSetAndGetAt() {
		ArrayList<Object> objList1 = new ArrayList<Object>();
		ArrayList<Object> objList2 = new ArrayList<Object>();
		ArrayList<Object> objList3 = new ArrayList<Object>();
		objList1.add(new Integer(1));
		objList1.add(new Integer(2));
		objList2.add(new Integer(3));
		objList2.add(new Integer(4));
		objList2.add(new Integer(5));
		objList3.add(new Integer(10));
		objList3.add(new Integer(11));
		objList3.add(new Integer(12));
		objList3.add(new Integer(13));
		mli.add(objList1);
		assertTrue("Le getAt a la position 0 devrait etre egal a objList1",mli.getAt(0).equals(objList1));
		mli.setAt(objList2,0);
		assertTrue("Le getAt a la position 0 devrait etre egal a objList2",mli.getAt(0).equals(objList2));
		mli.add(objList1);
		assertTrue("Le getAt a la position 1 devrait etre egal a objList1",mli.getAt(1).equals(objList1));
		mli.setAt(objList3,1);
		assertTrue("Le getAt a la position 1 devrait etre egal a objList3",mli.getAt(1).equals(objList3));
		mli.add(objList1);
		assertTrue("Le getAt a la position 2 devrait etre egal a objList1",mli.getAt(2).equals(objList1));
	}
}
