package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import main.MyListImpl;

public class MyListImpl_testsMadum {
	
	private ArrayList<Object> objList;
	
	private MyListImpl mli;
	
	/*****************************************
	 * Generalisation des tests
	 ****************************************/
	
	private void testReset() {
		mli.reset();
		assertNull("La propriete start devrait etre egale a null", mli.getStart());
		assertNull("La propriete current devrait etre egale a start, et donc a null", mli.getCurrent());
		assertTrue("La propriete size devrait etre egale a zero.",mli.getSize() == 0);
	}
	
	
	private void testStartAdd() {
		mli.add(objList);
		assertNotNull("La propriete start devrait pas etre egale a null", mli.getStart());
	}
	
	private void testSizeAdd() {
		mli.add(objList);
		assertTrue("La propriete size devrait etre egale a un.",mli.getSize() == 1);
	}
	
	private void testCurrentAdd() {
		mli.add(objList);
		assertNotNull("La propriete current devrait pas etre egale a null", mli.getCurrent());
	}
	
	private void testStartRemoveAtSuccess() {
		mli.removeAt(0);
		assertNull("La propriete start devrait etre egale a null", mli.getStart());
	}
	
	private void testSizeRemoveAtSuccess() {
		mli.removeAt(0);
		assertTrue("La propriete size devrait etre egale a zero.",mli.getSize() == 0);
	}
	
	private void testStartRemoveAtFailure() {
		try
		{
			mli.removeAt(0);
			fail("Une exception devrait etre lancee.");
		}
		catch (ArrayIndexOutOfBoundsException e) 
		{
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une ArrayIndexOutOfBoundsException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	private void testSizeRemoveAtFailure() {
		try
		{
			mli.removeAt(0);
			fail("Une exception devrait etre lancee.");
		}
		catch (ArrayIndexOutOfBoundsException e) 
		{
			assertTrue("La propriete size devrait etre egale a zero.",mli.getSize() == 0);
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une ArrayIndexOutOfBoundsException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	private void testStartRemoveItemSuccess() {
		mli.removeItem(objList);
		assertNull("La propriete start devrait etre egale a null", mli.getStart());
	}
	
	private void testSizeRemoveItemSuccess() {
		mli.removeItem(objList);
		assertTrue("La propriete size devrait etre egale a zero.",mli.getSize() == 0);
	}
	
	/*****************************************
	 * Methode qui teste la suppression d'une entree donnee pour l'attribut Start, dans les cas ou
	 * il devrait y avoir erreur. 
	 ****************************************/
	private void testStartRemoveItemFailure() {
		try
		{
			mli.removeItem(objList);
			fail("Une exception devrait etre lancee.");
		}
		catch (NullPointerException e) 
		{
			assertNull("La propriete start devrait etre egale a null", mli.getStart());
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une NullPointerException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	/*****************************************
	 * Methode qui teste la suppression d'une entree donnee pour l'attribut Size, dans les cas ou
	 * il devrait y avoir erreur. 
	 ****************************************/
	private void testSizeRemoveItemFailure() {
		try
		{
			mli.removeItem(objList);
			fail("Une exception devrait etre lancee.");
		}
		catch (NullPointerException e) 
		{
			assertTrue("La propriete size devrait etre egale a zero.",mli.getSize() == 0);
		}
		catch(Exception e) 
		{
			fail("Ceci devrait lancer une NullPointerException, mais lance plutot une "+e.getClass().getName());
		}
	}
	
	
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
	
	/****************************************
	 * Tests sur l'attribut Start
	 ****************************************/
	
	@Test
	/****************************************
	 * Methode : testStart1()
	 * Attribut: Start
	 * Sequence: add, removeAt, removeItem, reset
	 ****************************************/
	public void testStart1()
	{
		testStartAdd();
		testStartRemoveAtSuccess();
		testStartRemoveItemFailure();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testStart2()
	 * Attribut: Start
	 * Sequence: add, removeAt, reset, removeItem
	 ****************************************/
	public void testStart2() 
	{		
		testStartAdd();
		testStartRemoveAtSuccess();
		testReset();
		testStartRemoveItemFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testStart3()
	 * Attribut: Start
	 * Sequence: add, removeItem, removeAt, reset
	 ****************************************/
	public void testStart3()
	{
		testStartAdd();
		testStartRemoveItemSuccess();
		testStartRemoveAtFailure();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testStart4()
	 * Attribut: Start
	 * Sequence: add, removeItem, reset, removeAt
	 ****************************************/
	public void testStart4()
	{
		testStartAdd();
		testStartRemoveItemSuccess();
		testReset();
		testStartRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testStart5()
	 * Attribut: Start
	 * Sequence: add, reset, removeAt, removeItem
	 ****************************************/
	public void testStart5()
	{
		testStartAdd();
		testStartRemoveItemSuccess();
		testReset();
		testStartRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testStart6()
	 * Attribut: Start
	 * Sequence: add, reset, removeItem, removeAt
	 ****************************************/
	public void testStart6()
	{
		testStartAdd();
		testReset();
		testStartRemoveItemFailure();
		testStartRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testStart7()
	 * Attribut: Start
	 * Sequence: removeAt, add, removeItem, reset
	 ****************************************/
	public void testStart7()
	{
		testStartRemoveAtFailure();
		testStartAdd();
		testStartRemoveItemSuccess();
		testReset();
		
		
	}
	
	@Test
	/****************************************
	 * Methode : testStart8()
	 * Attribut: Start
	 * Sequence: removeAt, add, reset, removeItem
	 ****************************************/
	public void testStart8()
	{
		testStartRemoveAtFailure();
		testStartAdd();
		testReset();
		testStartRemoveItemFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testStart9()
	 * Attribut: Start
	 * Sequence: removeAt, removeItem, add, reset
	 ****************************************/
	public void testStart9()
	{
		testStartRemoveAtFailure();
		testStartRemoveItemFailure();
		testStartAdd();
		testReset();	
	}
	
	@Test
	/****************************************
	 * Methode : testStart10()
	 * Attribut: Start
	 * Sequence: removeAt, removeItem, reset, add
	 ****************************************/
	public void testStart10()
	{
		testStartRemoveAtFailure();
		testStartRemoveItemFailure();
		testReset();
		testStartAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testStart11()
	 * Attribut: Start
	 * Sequence: removeAt, reset, add, removeItem
	 ****************************************/
	public void testStart11()
	{
		testStartRemoveAtFailure();
		testReset();
		testStartAdd();
		testStartRemoveItemSuccess();
	}
	
	@Test
	/****************************************
	 * Methode : testStart12()
	 * Attribut: Start
	 * Sequence: removeAt, reset, removeItem, add
	 ****************************************/
	public void testStart12()
	{
		testStartRemoveAtFailure();
		testReset();
		testStartRemoveItemFailure();
		testStartAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testStart13()
	 * Attribut: Start
	 * Sequence: removeItem, add, removeAt, reset
	 ****************************************/
	public void testStart13()
	{
		testStartRemoveItemFailure();
		testStartAdd();
		testStartRemoveAtSuccess();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testStart14()
	 * Attribut: Start
	 * Sequence: removeItem, add, reset, removeAt
	 ****************************************/
	public void testStart14()
	{
		testStartRemoveItemFailure();
		testStartAdd();
		testReset();
		testStartRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testStart15()
	 * Attribut: Start
	 * Sequence: removeItem,  removeAt,  add, reset
	 ****************************************/
	public void testStart15()
	{
		testStartRemoveItemFailure();
		testStartRemoveAtFailure();
		testStartAdd();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testStart16()
	 * Attribut: Start
	 * Sequence: removeItem, removeAt, reset, add
	 ****************************************/
	public void testStart16()
	{
		testStartRemoveItemFailure();
		testStartRemoveAtFailure();
		testReset();
		testStartAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testStart17()
	 * Attribut: Start
	 * Sequence: removeItem, reset, add, removeAt
	 ****************************************/
	public void testStart17()
	{
		testStartRemoveItemFailure();
		testReset();
		testStartAdd();
		testStartRemoveAtSuccess();
	}
	
	@Test
	/****************************************
	 * Methode : testStart18()
	 * Attribut: Start
	 * Sequence: removeItem, reset, removeAt, add
	 ****************************************/
	public void testStart18()
	{
		testStartRemoveItemFailure();
		testReset();
		testStartRemoveAtFailure();
		testStartAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testStart19()
	 * Attribut: Start
	 * Sequence: reset, add, removeAt, removeItem
	 ****************************************/
	public void testStart19()
	{		
		testReset();
		testStartAdd();
		testStartRemoveAtSuccess();
		testStartRemoveItemFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testStart20()
	 * Attribut: Start
	 * Sequence: reset, add, removeItem, removeAt
	 ****************************************/
	public void testStart20()
	{		
		testReset();
		testStartAdd();
		testStartRemoveItemSuccess();
		testStartRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testStart21()
	 * Attribut: Start
	 * Sequence: reset, removeAt, add, removeItem
	 ****************************************/
	public void testStart21()
	{		
		testReset();
		testStartRemoveAtFailure();
		testStartAdd();
		testStartRemoveItemSuccess();
	}
	
	@Test
	/****************************************
	 * Methode : testStart22()
	 * Attribut: Start
	 * Sequence: reset, removeAt, removeItem, add
	 ****************************************/
	public void testStart22()
	{		
		testReset();
		testStartRemoveAtFailure();
		testStartRemoveItemFailure();
		testStartAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testStart23()
	 * Attribut: Start
	 * Sequence: reset, removeItem, add, removeAt
	 ****************************************/
	public void testStart23()
	{		
		testReset();
		testStartRemoveItemFailure();
		testStartAdd();
		testStartRemoveAtSuccess();
	}
	
	@Test
	/****************************************
	 * Methode : testStart24()
	 * Attribut: Start
	 * Sequence: reset, removeItem, removeAt, add
	 ****************************************/
	public void testStart24()
	{		
		testReset();
		testStartRemoveItemFailure();
		testStartRemoveAtFailure();
		testStartAdd();
	}
	
	/****************************************
	 * Tests sur l'attribut Size
	 ****************************************/
	
	@Test
	/****************************************
	 * Methode : testSize1()
	 * Attribut: Size
	 * Sequence: add, removeAt, removeItem, reset
	 ****************************************/
	public void testSize1()
	{
		testSizeAdd();
		testSizeRemoveAtSuccess();
		testSizeRemoveItemFailure();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testSize2()
	 * Attribut: Size
	 * Sequence: add, removeAt, reset, removeItem
	 ****************************************/
	public void testSize2() 
	{		
		testSizeAdd();
		testSizeRemoveAtSuccess();
		testReset();
		testSizeRemoveItemFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testSize3()
	 * Attribut: Size
	 * Sequence: add, removeItem, removeAt, reset
	 ****************************************/
	public void testSize3()
	{
		testSizeAdd();
		testSizeRemoveItemSuccess();
		testSizeRemoveAtFailure();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testSize4()
	 * Attribut: Size
	 * Sequence: add, removeItem, reset, removeAt
	 ****************************************/
	public void testSize4()
	{
		testSizeAdd();
		testSizeRemoveItemSuccess();
		testReset();
		testSizeRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testSize5()
	 * Attribut: Size
	 * Sequence: add, reset, removeAt, removeItem
	 ****************************************/
	public void testSize5()
	{
		testSizeAdd();
		testSizeRemoveItemSuccess();
		testReset();
		testSizeRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testSize6()
	 * Attribut: Size
	 * Sequence: add, reset, removeItem, removeAt
	 ****************************************/
	public void testSize6()
	{
		testSizeAdd();
		testReset();
		testSizeRemoveItemFailure();
		testSizeRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testSize7()
	 * Attribut: Size
	 * Sequence: removeAt, add, removeItem, reset
	 ****************************************/
	public void testSize7()
	{
		testSizeRemoveAtFailure();
		testSizeAdd();
		testSizeRemoveItemSuccess();
		testReset();
		
		
	}
	
	@Test
	/****************************************
	 * Methode : testSize8()
	 * Attribut: Size
	 * Sequence: removeAt, add, reset, removeItem
	 ****************************************/
	public void testSize8()
	{
		testSizeRemoveAtFailure();
		testSizeAdd();
		testReset();
		testSizeRemoveItemFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testSize9()
	 * Attribut: Size
	 * Sequence: removeAt, removeItem, add, reset
	 ****************************************/
	public void testSize9()
	{
		testSizeRemoveAtFailure();
		testSizeRemoveItemFailure();
		testSizeAdd();
		testReset();	
	}
	
	@Test
	/****************************************
	 * Methode : testSize10()
	 * Attribut: Size
	 * Sequence: removeAt, removeItem, reset, add
	 ****************************************/
	public void testSize10()
	{
		testSizeRemoveAtFailure();
		testSizeRemoveItemFailure();
		testReset();
		testSizeAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testSize11()
	 * Attribut: Size
	 * Sequence: removeAt, reset, add, removeItem
	 ****************************************/
	public void testSize11()
	{
		testSizeRemoveAtFailure();
		testReset();
		testSizeAdd();
		testSizeRemoveItemSuccess();
	}
	
	@Test
	/****************************************
	 * Methode : testSize12()
	 * Attribut: Size
	 * Sequence: removeAt, reset, removeItem, add
	 ****************************************/
	public void testSize12()
	{
		testSizeRemoveAtFailure();
		testReset();
		testSizeRemoveItemFailure();
		testSizeAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testSize13()
	 * Attribut: Size
	 * Sequence: removeItem, add, removeAt, reset
	 ****************************************/
	public void testSize13()
	{
		testSizeRemoveItemFailure();
		testSizeAdd();
		testSizeRemoveAtSuccess();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testSize14()
	 * Attribut: Size
	 * Sequence: removeItem, add, reset, removeAt
	 ****************************************/
	public void testSize14()
	{
		testSizeRemoveItemFailure();
		testSizeAdd();
		testReset();
		testSizeRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testSize15()
	 * Attribut: Size
	 * Sequence: removeItem,  removeAt,  add, reset
	 ****************************************/
	public void testSize15()
	{
		testSizeRemoveItemFailure();
		testSizeRemoveAtFailure();
		testSizeAdd();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testSize16()
	 * Attribut: Size
	 * Sequence: removeItem, removeAt, reset, add
	 ****************************************/
	public void testSize16()
	{
		testSizeRemoveItemFailure();
		testSizeRemoveAtFailure();
		testReset();
		testSizeAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testSize17()
	 * Attribut: Size
	 * Sequence: removeItem, reset, add, removeAt
	 ****************************************/
	public void testSize17()
	{
		testSizeRemoveItemFailure();
		testReset();
		testSizeAdd();
		testSizeRemoveAtSuccess();
	}
	
	@Test
	/****************************************
	 * Methode : testSize18()
	 * Attribut: Size
	 * Sequence: removeItem, reset, removeAt, add
	 ****************************************/
	public void testSize18()
	{
		testSizeRemoveItemFailure();
		testReset();
		testSizeRemoveAtFailure();
		testSizeAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testSize19()
	 * Attribut: Size
	 * Sequence: reset, add, removeAt, removeItem
	 ****************************************/
	public void testSize19()
	{		
		testReset();
		testSizeAdd();
		testSizeRemoveAtSuccess();
		testSizeRemoveItemFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testSize20()
	 * Attribut: Size
	 * Sequence: reset, add, removeItem, removeAt
	 ****************************************/
	public void testSize20()
	{		
		testReset();
		testSizeAdd();
		testSizeRemoveItemSuccess();
		testSizeRemoveAtFailure();
	}
	
	@Test
	/****************************************
	 * Methode : testSize21()
	 * Attribut: Size
	 * Sequence: reset, removeAt, add, removeItem
	 ****************************************/
	public void testSize21()
	{		
		testReset();
		testSizeRemoveAtFailure();
		testSizeAdd();
		testSizeRemoveItemSuccess();
	}
	
	@Test
	/****************************************
	 * Methode : testSize22()
	 * Attribut: Size
	 * Sequence: reset, removeAt, removeItem, add
	 ****************************************/
	public void testSize22()
	{		
		testReset();
		testSizeRemoveAtFailure();
		testSizeRemoveItemFailure();
		testSizeAdd();
	}
	
	@Test
	/****************************************
	 * Methode : testSize23()
	 * Attribut: Size
	 * Sequence: reset, removeItem, add, removeAt
	 ****************************************/
	public void testSize23()
	{		
		testReset();
		testSizeRemoveItemFailure();
		testSizeAdd();
		testSizeRemoveAtSuccess();
	}
	
	@Test
	/****************************************
	 * Methode : testSize24()
	 * Attribut: Size
	 * Sequence: reset, removeItem, removeAt, add
	 ****************************************/
	public void testSize24()
	{		
		testReset();
		testSizeRemoveItemFailure();
		testSizeRemoveAtFailure();
		testSizeAdd();
	}
	
	/****************************************
	 * Tests sur l'attribut Current
	 ****************************************/
	
	@Test
	/****************************************
	 * Methode : testCurrent1()
	 * Attribut: Current
	 * Sequence: add, reset
	 ****************************************/
	public void testCurrent1()
	{
		testCurrentAdd();
		testReset();
	}
	
	@Test
	/****************************************
	 * Methode : testCurrent2()
	 * Attribut: Current
	 * Sequence: reset, add
	 ****************************************/
	public void testCurrent2() 
	{		
		testReset();
		testCurrentAdd();
	}
}
