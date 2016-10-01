package main;

import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

public class SetCalculatorTest {
	
	private SetCalculatorImpl calculator;
	// First set 
	private ArrayList<Object> setA;
	// Second set
	private ArrayList<Object> setB;
	// Result set
	private ArrayList<Object> setR;
	
	@BeforeClass
	public void setUp(){
		calculator = new SetCalculatorImpl();
		setA = new ArrayList<Object>();
		setB = new ArrayList<Object>();
		setR = new ArrayList<Object>();
	}
	
	@Before
	public void cleanUp(){
		calculator = new SetCalculatorImpl();
		setA.clear();
		setB.clear();
		setR.clear();
	}

	@Test
	public void testUnion() {
		setA.add(15);
		setA.add(2);
		setA.add(-4);
		
		setB.add(-27);
		setB.add(2);
		setB.add(11);
		setB.add(325);
		
		setR.add(15);
		setR.add(2);
		setR.add(-4);
		setR.add(-27);
		setR.add(11);
		setR.add(325);
		
		assertEquals("The union of {15,2,-4} and {-27,2,11,325} must be {15,2,-4,-27,11,325}", setR, calculator.union(setA, setB));
	}
	
	@Test
	public void testIntersection() {
		setA.add(15);
		setA.add(2);
		setA.add(-4);
		
		setB.add(-27);
		setB.add(2);
		setB.add(11);
		setB.add(325);
		
		setR.add(2);
		assertEquals("The intersection of {15,2,-4} and {-27,2,11,325} must be {2}", setR, calculator.intersection(setA, setB));
	}
	
	@Test
	public void testDifference() {
		setA.add(15);
		setA.add(2);
		setA.add(-4);
		
		setB.add(-27);
		setB.add(2);
		setB.add(11);
		setB.add(325);
		
		setR.add(15);
		setR.add(-4);
		assertEquals("The difference of {15,2,-4} and {-27,2,11,325} must be {15,-4}", setR, calculator.difference(setA, setB));
	}
	
	@Test
	public void testSymDifference() {
		setA.add(15);
		setA.add(2);
		setA.add(-4);
		
		setB.add(-27);
		setB.add(2);
		setB.add(11);
		setB.add(325);
		
		setR.add(15);
		setR.add(-4);
		setR.add(-27);
		setR.add(11);
		setR.add(325);
		assertEquals("The symetric difference of {15,2,-4} and {-27,2,11,325} must be {15,-4,-27,11,325}", setR, calculator.symDifference(setA, setB));
	}
	
	@Test
	public void testIsSubset() {
		setA.add(15);
		setA.add(2);
		setA.add(-4);
		
		setB.add(-27);
		setB.add(2);
		setB.add(-4);
		setB.add(15);
		
		setR.add(String.valueOf(true));
		assertEquals("{15,2,-4} must be a subset of {-27,2,-4,15}", setR, calculator.isSubset(setA, setB));
	}
	
	@Test
	public void testIsSuperset() {
		setA.add(-27);
		setA.add(2);
		setA.add(-4);
		setA.add(15);
		
		setB.add(15);
		setB.add(2);
		setB.add(-4);
		
		setR.add(String.valueOf(true));
		assertEquals("{-27,2,-4,15} must be a superset of {15,2,-4}", setR, calculator.isSuperset(setA, setB));
	}
	

}