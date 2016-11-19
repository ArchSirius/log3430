package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import main.MyListImpl;

/****************************************
 * Tests des "Getter". Il faut s'assurer de leur exactitude afin 
 * de valider les tests MaDUM
 ***************************************/
public class MyListImpl_testsGetter {
	private ArrayList<Object> objList;
	private MyListImpl mli;
	
	@Before
	/*****************************************
	 *	Methode qui initialise MyListImpl via son constructeur et
	 *	valide les paramètres initiaux
	 ****************************************/
	public void setUp()
	{
		objList = new ArrayList<Object>();
		objList.add(new Integer(1));
		mli  = new MyListImpl();
		assertNull("La propriete start devrait etre egale a null", mli.getStart());
		assertNull("La propriete current devrait etre egale a start, et donc a null", mli.getCurrent());
		assertTrue("La propriete size devrait etre egale a zero.",mli.getSize() == 0);
	}
	
	
	
	
	
	@Test
	/****************************************
	 * Methode de test pour le Getter de start.
	 * Sequence : add, removeItem, add, removeAt, add, add, add, removeItem, removeAt, add, add, removeItem, removeAt; 
	 ***************************************/
	public void testGetStart() {
		try {
			ArrayList<Object> objList1 = new ArrayList<Object>();
			ArrayList<Object> objList2 = new ArrayList<Object>();
			ArrayList<Object> objList3 = new ArrayList<Object>();
			objList1.add(new Integer(5));
			objList1.add(new Integer(3));
			objList2.add(new Integer(4));
			objList2.add(new Integer(12));
			objList3.add(new Integer(1));
			mli.add(objList1);

			assertTrue("La propritete Start devrait etre egale a objList1",mli.getStart().getContent().equals(objList1));
			assertNull("La propriete Next de Start devrait etre egale a null",mli.getStart().getNext());
			mli.removeItem(objList1);
			assertNull("La propriete Start devrait etre egale a null", mli.getStart());
			mli.add(objList2);
			assertTrue("La propriete Start devrait etre egale a objList2",mli.getStart().getContent().equals(objList2));
			assertNull("La propriete Next de Start devrait etre egale a null",mli.getStart().getNext());
			mli.removeAt(0);
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
			mli.add(objList3);
			assertTrue("La propriete Start devrait etre egale a objList3",mli.getStart().getContent().equals(objList3));
			assertNull("La propriete Next de Start devrait etre egale a null",mli.getStart().getNext());
			mli.add(objList2);
			assertTrue("La propriete Start devrait etre egale a objList3",mli.getStart().getContent().equals(objList3));
			assertNotNull("La propriete Next de Start devrait ne etre pas egale a null",mli.getStart().getNext());
			assertTrue("La propriete Next de Start devrait etre egale a objList2",mli.getStart().getNext().getContent().equals(objList2));
			mli.add(objList1);
			assertTrue("La propriete Start devrait etre egale a objList3",mli.getStart().getContent().equals(objList3));
			assertNotNull("La propriete Next de Start ne devrait pas etre egale a null",mli.getStart().getNext());
			assertTrue("La propriete Next de Start devrait etre egale a objList2",mli.getStart().getNext().getContent().equals(objList2));
			mli.removeAt(1);
			assertTrue("La propriete Start devrait etre egale a objList3",mli.getStart().getContent().equals(objList3));
			assertNotNull("La propriete Next de Start ne devrait pas etre egale a null",mli.getStart().getNext());
			assertTrue("La propriete Next de Start devrait etre egale a objList1",mli.getStart().getNext().getContent().equals(objList1));
			mli.removeItem(objList1);
			assertTrue("La propriete Start devrait etre egale a objList3",mli.getStart().getContent().equals(objList3));
			assertNull("La propriete Next de Start devrait etre egale a null",mli.getStart().getNext());
			mli.add(objList2);
			assertTrue("La propriete Start devrait etre egale a objList3",mli.getStart().getContent().equals(objList3));
			assertNotNull("La propriete Next de Start ne devrait pas etre egale a null",mli.getStart().getNext());
			assertTrue("La propriete Next de Start devrait etre egale a objList2",mli.getStart().getNext().getContent().equals(objList2));
			mli.add(objList1);
			assertTrue("La propriete Start devrait etre egale a objList3",mli.getStart().getContent().equals(objList3));
			assertNotNull("La propriete Next de Start ne devrait pas etre egale a null",mli.getStart().getNext());
			assertTrue("La propriete Next de Start devrait etre egale a objList2",mli.getStart().getNext().getContent().equals(objList2));
			mli.removeAt(0);
			assertTrue("La propriete Start devrait etre egale a objList2",mli.getStart().getContent().equals(objList2));
			assertNotNull("La propriete Next de Start ne devrait pas etre egale a null",mli.getStart().getNext());
			assertTrue("La propriete Next de Start devrait etre egale a objList1",mli.getStart().getNext().getContent().equals(objList1));
			mli.removeItem(objList2);
			assertTrue("La propriete Start devrait etre egale a objList1",mli.getStart().getContent().equals(objList1));
			assertNull("La propriete Next de Start devrait etre egale a null",mli.getStart().getNext());
		} catch (Exception e) {
			fail("Il ne devrait pas y avoir d'exception.");
		}
	}
	
	@Test
	/****************************************
	 * Methode de test pour le Getter de size.
	 * On reutilise la sequence de testGetStart
	 * Sequence : add, removeItem, add, removeAt, add, add, add, removeItem, removeAt, add, add, removeItem, removeAt; 
	 ***************************************/
	public void testGetSize() {
		try {
			ArrayList<Object> objList1 = new ArrayList<Object>();
			ArrayList<Object> objList2 = new ArrayList<Object>();
			ArrayList<Object> objList3 = new ArrayList<Object>();
			objList1.add(new Integer(5));
			objList1.add(new Integer(3));
			objList2.add(new Integer(4));
			objList2.add(new Integer(12));
			objList3.add(new Integer(1));
			assertTrue("La propriete Size devrait etre egale a 0",mli.getSize() == 0);
			mli.add(objList1);
			assertTrue("La propriete Size devrait etre egale a 1",mli.getSize() == 1);
			mli.removeItem(objList1);
			assertTrue("La propriete Size devrait etre egale a 0",mli.getSize() == 0);
			mli.add(objList2);
			assertTrue("La propriete Size devrait etre egale a 1",mli.getSize() == 1);
			mli.removeAt(0);
			assertTrue("La propriete Size devrait etre egale a 0",mli.getSize() == 0);
			mli.add(objList3);
			assertTrue("La propriete Size devrait etre egale a 1",mli.getSize() == 1);
			mli.add(objList2);
			assertTrue("La propriete Size devrait etre egale a 2",mli.getSize() == 2);
			mli.add(objList1);
			assertTrue("La propriete Size devrait etre egale a 3",mli.getSize() == 3);
			mli.removeAt(1);
			assertTrue("La propriete Size devrait etre egale a 2",mli.getSize() == 2);
			mli.removeItem(objList1);
			assertTrue("La propriete Size devrait etre egale a 1",mli.getSize() == 1);
			mli.add(objList2);
			assertTrue("La propriete Size devrait etre egale a 2",mli.getSize() == 2);
			mli.add(objList1);
			assertTrue("La propriete Size devrait etre egale a 3",mli.getSize() == 3);
			mli.removeAt(0);
			assertTrue("La propriete Size devrait etre egale a 2",mli.getSize() == 2);
			mli.removeItem(objList2);
			assertTrue("La propriete Size devrait etre egale a 1",mli.getSize() == 1);
		} catch (Exception e) {
			fail("Il ne devrait pas y avoir d'exception.");
		}
	}
	
	@Test
	/****************************************
	 * Methode de test pour le Getter de Current.
	 * On reutilise la sequence de testGetStart
	 * Sequence : add, removeItem, add, removeAt, add, add, add, removeItem, removeAt, add, add, removeItem, removeAt; 
	 ***************************************/
	public void testGetCurrent() {
		try {
			ArrayList<Object> objList1 = new ArrayList<Object>();
			ArrayList<Object> objList2 = new ArrayList<Object>();
			ArrayList<Object> objList3 = new ArrayList<Object>();
			objList1.add(new Integer(5));
			objList1.add(new Integer(3));
			objList2.add(new Integer(4));
			objList2.add(new Integer(12));
			objList3.add(new Integer(1));
			mli.add(objList1);
			//Les Assert Suivant requiert de mettre Elem comme classe publique. Sans quoi, on ne peux pas en voir le contenu, ou le Next.
			assertTrue("La propritete Current devrait etre egale a objList1",mli.getCurrent().getContent().equals(objList1));
			mli.removeItem(objList1);
			assertNull("La propriete Current devrait etre egale a null", mli.getCurrent());
			mli.add(objList2);
			assertTrue("La propriete Current devrait etre egale a objList2",mli.getCurrent().getContent().equals(objList2));
			mli.removeAt(0);
			assertNull("La propriete Current devrait etre egale a null", mli.getCurrent());
			mli.add(objList3);
			assertTrue("La propriete Current devrait etre egale a objList3",mli.getCurrent().getContent().equals(objList3));
			mli.add(objList2);
			assertTrue("La propriete Current devrait etre egale a objList2",mli.getCurrent().getContent().equals(objList2));
			mli.add(objList1);
			assertTrue("La propriete Current devrait etre egale a objList1",mli.getCurrent().getContent().equals(objList1));
			mli.removeAt(1);
			assertTrue("La propriete Current devrait etre egale a objList1",mli.getCurrent().getContent().equals(objList1));
			mli.removeItem(objList1);
			assertTrue("La propriete Current devrait etre egale a objList3",mli.getCurrent().getContent().equals(objList3));
			mli.add(objList2);
			assertTrue("La propriete Current devrait etre egale a objList2",mli.getCurrent().getContent().equals(objList2));
			mli.add(objList1);
			assertTrue("La propriete Current devrait etre egale a objList1",mli.getCurrent().getContent().equals(objList1));
			mli.removeAt(0);
			assertTrue("La propriete Current devrait etre egale a objList1",mli.getCurrent().getContent().equals(objList1));
			mli.removeItem(objList2);
			assertTrue("La propriete Current devrait etre egale a objList1",mli.getCurrent().getContent().equals(objList1));
		} catch (Exception e) {
			fail("Il ne devrait pas y avoir d'exception.");
		}
	}
}
