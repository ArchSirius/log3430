package main;

import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SetCalculatorTest {

	private SetCalculatorImpl calculator;
	private ArrayList<Object> leftSet;
	private ArrayList<Object> rightSet;
	private ArrayList<Object> resultSet;

	@Before
	public void setUp(){
		calculator = new SetCalculatorImpl();
		leftSet = new ArrayList<Object>();
		rightSet = new ArrayList<Object>();
		resultSet = new ArrayList<Object>();
	}

   /*
	* Tests SetCalculator.union
	* 1. Union of two empty sets     : A union B = {} for A = B = {}
	* 2. Union of two disjoint sets  : A union B = {} for all a in A not in B; A,B != {}
	* 3. Union of two identical sets : A union B = A  for A = B != {}
	* 4. Union of two joint sets     : A union B = C  where all c in C are in A or B for A != B; A,B != {}; A,B not disjoint
	*/
	@Test
	public void testUnion() {
		// A = B = {}
		assertEquals("The union of {} and {} should be {}",
				resultSet, calculator.union(leftSet, rightSet));

		// A and B are disjoint sets
		leftSet.add(2);
		leftSet.add(15);
		rightSet.add(-27);
		resultSet.add(2);
		resultSet.add(15);
		resultSet.add(-27);
		assertEquals("The union of {2, 15} and {-27} should be {-27, 2, 15}",
				resultSet, calculator.union(leftSet, rightSet));

		// A = B != {}
		rightSet.clear();
		rightSet.add(2);
		rightSet.add(15);
		resultSet.clear();
		resultSet.add(2);
		resultSet.add(15);
		assertEquals("The union of {2, 15} and {2, 15} should be {2, 15}",
				resultSet, calculator.union(leftSet, rightSet));

		// A != B joint sets
		leftSet.add(-4);
		rightSet.add(11);
		rightSet.add(325);
		resultSet.clear();
		resultSet.add(2);
		resultSet.add(15);
		resultSet.add(-4);
		resultSet.add(11);
		resultSet.add(325);
		assertEquals("The union of {-4, 2, 15} and {2, 11, 15, 325} should be {-4, 2, 11, 15, 325}",
				resultSet, calculator.union(leftSet, rightSet));
	}
	
   /*
	* Tests SetCalculator.intersection
	* 1. Intersection of two empty sets     : A intersection B = {} for A = B = {}
	* 2. Intersection of two disjoint sets  : A intersection B = {} for all a in A not in B; A,B != {}
	* 3. Intersection of two identical sets : A intersection B = A  for A = B != {}
	* 4. Intersection of two joint sets     : A intersection B = C  where all c in C are in A and B for A != B; A,B != {}; A,B not disjoint
	*/
	@Test
	public void testIntersection() {
		// A = B = {}
		assertEquals("The intersection of {} and {} should be {}",
				resultSet, calculator.intersection(leftSet, rightSet));

		// A and B are disjoint sets
		leftSet.add(15);
		rightSet.add(-27);
		rightSet.add(2);
		assertEquals("The intersection of {15} and {-27, 2} should be {}",
				resultSet, calculator.intersection(leftSet, rightSet));

		// A = B != {}
		leftSet.add(-27);
		leftSet.add(2);
		rightSet.add(15);
		resultSet.add(15);
		resultSet.add(-27);
		resultSet.add(2);
		assertEquals("The intersection of {-27, 2, 15} and {-27, 2, 15} should be {-27, 2, 15}",
				resultSet, calculator.intersection(leftSet, rightSet));

		// A != B joint sets
		leftSet.add(-4);
		rightSet.add(11);
		rightSet.add(325);
		assertEquals("The intersection of {-27, -4, 2, 15} and {-27, 2, 15, 11, 325} should be {-27, 2, 15}",
				resultSet, calculator.intersection(leftSet, rightSet));
	}

   /*
	* Tests SetCalculator.difference
	* 1. Difference of two empty sets     : A difference B = {} for A = B = {}
	* 2. Difference of two identical sets : A difference B = {} for A = B != {}
	* 3. Difference of two disjoint sets  : A difference B = A  for all a in A not in B; A,B != {}
	* 4. Difference of two joint sets     : A difference B = C  where all c in C are in A and B for A != B; A,B != {}; A,B not disjoint
	* 5. Difference of two joint sets     : B difference A = D  where all d in D are in A and B for A != B; A,B != {}; A,B not disjoint
	*/
	@Test
	public void testDifference() {
		// A = B = {}
		assertEquals("The difference of {} and {} should be {}",
				resultSet, calculator.difference(leftSet, rightSet));

		// A = B != {}
		leftSet.add(2);
		leftSet.add(15);
		rightSet.add(2);
		rightSet.add(15);
		assertEquals("The difference of {2, 15} and {2, 15} should be {}",
				resultSet, calculator.difference(leftSet, rightSet));

		// A and B are disjoint sets
		rightSet.clear();
		rightSet.add(325);
		resultSet.add(2);
		resultSet.add(15);
		assertEquals("The difference of {2, 15} and {325} should be {2, 15}",
				resultSet, calculator.difference(leftSet, rightSet));

		// A != B joint sets, LTR
		leftSet.add(-27);
		leftSet.add(11);
		rightSet.add(-27);
		resultSet.add(11);
		assertEquals("The difference of {-27, 2, 11, 15} and {11, 325} should be {-27, 2, 15}",
				resultSet, calculator.difference(leftSet, rightSet));

		// A != B joint sets, RTL
		resultSet.clear();
		resultSet.add(325);
		assertEquals("The difference of {11, 325} and {-27, 2, 11, 15} should be {325}",
				resultSet, calculator.difference(rightSet, leftSet));
	}

   /*
	* Tests SetCalculator.symDifference
	* 1. SymDifference of two empty sets     : A symDifference B = {}        for A = B = {}
	* 2. SymDifference of two disjoint sets  : A symDifference B = A union B for all a in A not in B; A,B != {}
	* 3. SymDifference of two identical sets : A symDifference B = {}        for A = B != {}
	* 4. SymDifference of two joint sets     : A symDifference B = C         where all c in C are in A and B for A != B; A,B != {}; A,B not disjoint
	* 5. SymDifference of two joint sets     : B symDifference A = C         where all c in C are in A and B for A != B; A,B != {}; A,B not disjoint
	*/
	@Test
	public void testSymDifference() {
		// A = B = {}
		assertEquals("The symetric difference of {} and {} should be {}",
				resultSet, calculator.symDifference(leftSet, rightSet));

		// A and B are disjoint sets
		leftSet.add(15);
		rightSet.add(-4);
		rightSet.add(2);
		resultSet.add(15);
		resultSet.add(-4);
		resultSet.add(2);
		assertEquals("The symetric difference of {15} and {-4, 2} should be {-4, 2, 15}",
				resultSet, calculator.symDifference(leftSet, rightSet));

		// A = B != {}
		leftSet.add(-4);
		leftSet.add(2);
		rightSet.add(15);
		resultSet.clear();
		assertEquals("The symetric difference of {-4, 2, 15} and {-4, 2, 15} should be {}",
				resultSet, calculator.symDifference(leftSet, rightSet));

		// A != B joint sets, LTR
		leftSet.add(325);
		rightSet.add(11);
		resultSet.add(325);
		resultSet.add(11);
		assertEquals("The symetric difference of {-4, 2, 15, 325} and {-4, 2, 11, 15} should be {11, 325}",
				resultSet, calculator.symDifference(leftSet, rightSet));

		// A != B joint sets, RTL
		resultSet.clear();
		resultSet.add(11);
		resultSet.add(325);
		assertEquals("The symetric difference of {-4, 2, 11, 15} and {-4, 2, 15, 325} should be {11, 325}",
				resultSet, calculator.symDifference(rightSet, leftSet));
	}

   /*
	* Tests SetCalculator.isSubset
	* 1. Two empty sets     : A isSubset B = true  for A = B = {}
	* 2. Two identical sets : A isSubset B = true  for A = B != {}
	* 3. Two disjoint sets  : A isSubset B = false for all a in A not in B; A,B != {}
	* 4. Two joint sets     : A isSubset B = true  for all a in A in B and exists b in B not in A; A,B != {}
	* 5. Two joint sets     : B isSubset A = false for all a in A in B and exists b in B not in A; A,B != {}
	*/
	@Test
	public void testIsSubset() {
		// A = B = {}
		resultSet.add(String.valueOf(true));
		assertEquals("{} should be a subset of {}",
				resultSet, calculator.isSubset(leftSet, rightSet));

		// A = B != {}
		leftSet.add(2);
		leftSet.add(15);
		rightSet.add(2);
		rightSet.add(15);
		assertEquals("{2, 15} should be a subset of {2, 15}",
				resultSet, calculator.isSubset(leftSet, rightSet));

		// A and B are disjoint sets
		rightSet.clear();
		rightSet.add(-27);
		rightSet.add(325);
		resultSet.clear();
		resultSet.add(String.valueOf(false));
		assertEquals("{2, 15} should not be a subset of {-27, 325}",
				resultSet, calculator.isSubset(leftSet, rightSet));

		// A != B joint sets, LTR
		rightSet.add(2);
		rightSet.add(15);
		resultSet.clear();
		resultSet.add(String.valueOf(true));
		assertEquals("{2, 15} should be a subset of {-27, 2, 15, 325}",
				resultSet, calculator.isSubset(leftSet, rightSet));

		// A != B joint sets, RTL
		resultSet.clear();
		resultSet.add(String.valueOf(false));
		assertEquals("{-27, 2, 15, 325} should not be a subset of {2, 15}",
				resultSet, calculator.isSubset(rightSet, leftSet));
	}

   /*
	* Tests SetCalculator.isSuperset
	* 1. Two empty sets     : A isSuperset B = true  for A = B = {}
	* 2. Two identical sets : A isSuperset B = true  for A = B != {}
	* 3. Two disjoint sets  : A isSuperset B = false for all a in A not in B; A,B != {}
	* 4. Two joint sets     : A isSuperset B = true  for exists a in A not in B and all b in B in A; A,B != {}
	* 5. Two joint sets     : B isSuperset A = false for exists a in A not in B and all b in B in A; A,B != {}
	*/
	@Test
	public void testIsSuperset() {
		// A = B = {}
		resultSet.add(String.valueOf(true));
		assertEquals("{} should be a superset of {}",
				resultSet, calculator.isSuperset(leftSet, rightSet));

		// A = B != {}
		leftSet.add(-27);
		leftSet.add(3);
		rightSet.add(-27);
		rightSet.add(3);
		assertEquals("{-27, 3} should be a superset of {-27, 3}",
				resultSet, calculator.isSuperset(leftSet, rightSet));

		// A and B are disjoint sets
		rightSet.clear();
		rightSet.add(15);
		resultSet.clear();
		resultSet.add(String.valueOf(false));
		assertEquals("{-27, 3} should not be a superset of {15}",
				resultSet, calculator.isSuperset(leftSet, rightSet));

		// A != B joint sets, LTR
		rightSet.add(3);
		leftSet.add(15);
		resultSet.clear();
		resultSet.add(String.valueOf(true));
		assertEquals("{-27, 3, 15} should be a superset of {3, 15}",
				resultSet, calculator.isSuperset(leftSet, rightSet));

		// A != B joint sets, RTL
		resultSet.clear();
		resultSet.add(String.valueOf(false));
		assertEquals("{3, 15} should not be a superset of {-27, 3, 15}",
				resultSet, calculator.isSuperset(rightSet, leftSet));
	}
}
