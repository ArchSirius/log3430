package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListeChaineeTest {

	private ListeChaineeImpl listeChainee;
	private MyListImpl list;
	// First value 
	private ArrayList<Object> val1;
	// Second value
	private ArrayList<Object> val2;
	// Result
	private ArrayList<Object> result;
	
	@BeforeClass
	public void InitialSetUp(){
		listeChainee = new ListeChaineeImpl();
		list = new MyListImpl();
		val1 = new ArrayList<Object>();
		val2 = new ArrayList<Object>();
		result = new ArrayList<Object>();
	}
	
	@Before
	public void cleanUp(){
		list.reset();
		val1.clear();
		val2.clear();
		result.clear();
	}

	@Test
	public void testUnion() throws Exception{
		val1.add(15);
		val1.add(2);
		val1.add(-4);
		
		val2.add(-27);
		val2.add(2);
		val2.add(11);
		val2.add(325);
		
		result.add(15);
		result.add(2);
		result.add(-4);
		result.add(-27);
		result.add(11);
		result.add(325);
		
		list.add(val1);
		list.add(val2);
		list.add(result);
		
		assertEquals("The union of {15,2,-4} and {-27,2,11,325} must be {15,2,-4,-27,11,325}", list, listeChainee.build("union",val1,val2));
	}

}
