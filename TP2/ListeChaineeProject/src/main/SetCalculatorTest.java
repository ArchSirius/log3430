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
	public void InitialSetUp(){
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
		
		// A = B = {} (ensemble vide)
		assertEquals("The union of {} and {} must be {}", setR, calculator.union(setA, setB));
		
		// A et B sont disjoints
		setA.add(15);
		setA.add(2);
		setB.add(-27);	
		setR.add(15);
		setR.add(2);
		setR.add(-27);	
		assertEquals("The union of {15,2} and {-27} must be {15,2,-27}", setR, calculator.union(setA, setB));
		
		// A = B = non-null
		setB.clear();
		setB.add(15);
		setB.add(2);
		setR.clear();
		setR.add(15);
		setR.add(2);
		assertEquals("The union of {15,2} and {15,2} must be {15,2}", setR, calculator.union(setA, setB));
		
		// A != B
		setA.add(-4);
		setB.add(11);
		setB.add(325);
		setR.clear();
		setR.add(15);
		setR.add(2);
		setR.add(-4);
		setR.add(11);
		setR.add(325);
		assertEquals("The union of {15,2,-4} and {15,2,11,325} must be {15,2,-4,11,325}", setR, calculator.union(setA, setB));

		}
	
	@Test
	public void testIntersection() {
		
		// A = B = {} (ensemble vide)
		assertEquals("The intersection of {} and {} must be {}", setR, calculator.intersection(setA, setB));
		
		// A et B sont disjoints (A intersect B = {})
		setA.add(15);
		setB.add(-27);
		setB.add(2);
		assertEquals("The intersection of {15} and {-27,2} must be {}", setR, calculator.intersection(setA, setB));		
		
		// A = B != {}
		setA.add(2);
		setA.add(-27);
		setB.add(15);
		setR.add(15);
		setR.add(-27);
		setR.add(2);
		assertEquals("The intersection of {15,-27,2} and {-27,2,15} must be {15,-27,2}", setR, calculator.intersection(setA, setB));
		
		// A != B et A intersect B != {}
		setA.add(-4);
		setB.add(11);
		setB.add(325);
		setR.add(2);
		assertEquals("The intersection of {15,-27,2,-4} and {-27,2,15,11,325} must be {15,-27,2}", setR, calculator.intersection(setA, setB));
	}
	
	@Test
	public void testDifference() {
		
		// A = B = {} (ensemble vide)
		assertEquals("The difference of {} and {} must be {}", setR, calculator.difference(setA, setB));
		
		// A = B != {}
		setA.add(15);
		setA.add(2);
		setB.add(15);
		setB.add(2);
		assertEquals("The difference of {15,2} and {15,2} must be {}", setR, calculator.difference(setA, setB));
		
		// A et B sont disjoints (A diff B = A )
		setB.clear();
		setB.add(325);
		setR.add(15);
		setR.add(2);
		assertEquals("The difference of {15,2} and {325} must be {15,2}", setR, calculator.difference(setA, setB));
		
		// A != B et A diff B != {}
		setA.add(-27);
		setA.add(11);
		setB.add(11);
		setR.add(-27);
		assertEquals("The difference of {15,2,-27,11} and {325,11} must be {15,2,-27}", setR, calculator.difference(setA, setB));
	}
	
	@Test
	public void testSymDifference() {
		
		// A = B = {} (ensemble vide)
		assertEquals("The symetric difference of {} and {} must be {}", setR, calculator.symDifference(setA, setB));
		
		// A et B sont disjoints (A diff B = {})
		setA.add(15);
		setB.add(2);
		setB.add(-4);
		setR.add(15);
		setR.add(2);
		setR.add(-4);
		assertEquals("The symetric difference of {15} and {2,-4} must be {15,2,-4}", setR, calculator.symDifference(setA, setB));
		
		// A = B != {}
		setA.add(2);
		setA.add(-4);
		setB.add(15);
		setR.clear();
		assertEquals("The symetric difference of {15,2,-4} and {2,-4,15} must be {}", setR, calculator.symDifference(setA, setB));
		
		// A != B et A symdiff B = C != {}
		setA.add(325);
		setB.add(11);
		setR.add(325);
		setR.add(11);
		assertEquals("The symetric difference of {15,2,-4,325} and {2,-4,15,11} must be {325,11}", setR, calculator.symDifference(setA, setB));	
		
		// A != B et B symdiff A = C != {} 
		setR.clear();
		setR.add(11);
		setR.add(325);
		assertEquals("The symetric difference of {15,2,-4,11} and {2,-4,15,325} must be {11,325}", setR, calculator.symDifference(setA, setB));	
		
	}
	
	@Test
	public void testIsSubset() {
		
		// A = B = {} (ensemble vide)
		setR.add(String.valueOf(true));
		assertEquals("{} must be a subset of {}", setR, calculator.isSubset(setA, setB));
		
		// A = B != {}
		setA.add(15);
		setA.add(2);
		setB.add(15);
		setB.add(2);
		assertEquals("{15,2} must be a subset of {15,2}", setR, calculator.isSubset(setA, setB));
		
		// A et B sont disjoints (A issub B = {})
		setB.clear();
		setB.add(325);
		setB.add(-27);
		setR.clear();
		setR.add(String.valueOf(false));
		assertEquals("{15,2} must not be a subset of {325,-27}", setR, calculator.isSubset(setA, setB));
		
		// A != B et A issub B = 0
		setB.add(15);
		assertEquals("{15,2} must not be a subset of {325,-27,15}", setR, calculator.isSubset(setA, setB));
		
		// A != B et B issub A = 1
		setB.add(2);
		setR.clear();
		setR.add(String.valueOf(true));
		assertEquals("{15,2} must not be a subset of {325,-27,15,2}", setR, calculator.isSubset(setA, setB));
	}
	
	@Test
	public void testIsSuperset() {
		
		// A = B = {} (ensemble vide)
		setR.add(String.valueOf(true));
		assertEquals("{} must be a superset of {}", setR, calculator.isSuperset(setA, setB));
		// A = B != {}
		setA.add(-27);
		setA.add(3);
		setB.add(3);
		setB.add(-27);
		assertEquals("{-27,3} must be a superset of {3,-27}", setR, calculator.isSuperset(setA, setB));
		// A et B sont disjoints (A issuper B = {})
		setB.clear();
		setB.add(15);
		setR.clear();
		setR.add(String.valueOf(false));
		assertEquals("{-27,3} must not be a superset of {15}", setR, calculator.isSuperset(setA, setB));
		// A != B et A issuper B = 0
		setB.add(3);
		assertEquals("{-27,3} must not be a superset of {15,3}", setR, calculator.isSuperset(setA, setB));
		// A != B et B issuper A = 1
		setA.add(15);
		setR.clear();
		setR.add(String.valueOf(true));
		assertEquals("{-27,3,15} must be a superset of {15,3}", setR, calculator.isSuperset(setA, setB));
	}
}