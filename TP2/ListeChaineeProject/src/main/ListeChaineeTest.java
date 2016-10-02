package main;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ListeChaineeTest {

	private ListeChaineeImpl listeChainee;
	private ArrayList<Object> leftSet;
	private ArrayList<Object> rightSet;

	@Before
	public void setUp(){
		listeChainee = new ListeChaineeImpl();
		leftSet = new ArrayList<Object>();
		leftSet.add(-4);
		leftSet.add(2);
		leftSet.add(15);
		rightSet = new ArrayList<Object>();
		rightSet.add(-27);
		rightSet.add(2);
		rightSet.add(11);
		rightSet.add(325);
	}

   /*
	* Tests ListeChainee.build
	* A1: union
	* A2: intersection
	* A3: difference
	* A4: symDifference
	* A5: isSubset
	* A6: isSuperset
	* A7: invalid value
	*/
	@Test
	public void testBuild() throws Exception {
		// A1: union
		assertEquals(3, listeChainee.build("union", leftSet, rightSet).getSize());

		// A2: intersection
		assertEquals(3, listeChainee.build("intersection", leftSet, rightSet).getSize());

		// A3: difference
		assertEquals(3, listeChainee.build("difference", leftSet, rightSet).getSize());

		// A4: symDifference
		assertEquals(3, listeChainee.build("symDifference", leftSet, rightSet).getSize());

		// A5: isSubset
		assertEquals(3, listeChainee.build("isSubset", leftSet, rightSet).getSize());

		// A6: isSuperset
		assertEquals(3, listeChainee.build("isSuperset", leftSet, rightSet).getSize());

		// A7: invalid value
		try {
			listeChainee.build("", leftSet, rightSet);
			fail("Expected IOException");
		}
		catch (final IOException e) {
			assert true;
		}
		catch (final Throwable t) {
			fail("Expected IOException, found " + t.getClass().getName());
		}
	}
}
