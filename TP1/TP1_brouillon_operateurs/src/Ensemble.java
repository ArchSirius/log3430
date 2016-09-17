import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This immutable class represents a set of integers.
 *
 * @author Samuel Rondeau, Jennifer Khoury
 * @version 1.0
 */
public class Ensemble extends ArrayList<Integer> {

   /**
	* Constructs a new Ensemble from an array of integers.
	* @param entiers an array of integers
	*/
	public Ensemble(Integer... entiers) {
		super(Arrays.asList(entiers));
	}

   /**
	* Sorts this Ensemble in ascending numerical order.
	* @return this Ensemble
	*/
	public Ensemble sort() {
		Collections.sort(this);
		return this;
	}

   /**
	* Removes duplicates from this Ensemble.
	* @return this Ensemble
	*/
	public Ensemble unique() {
		final Ensemble unique = new Ensemble();
		for (final Integer element : this) {
			if (!unique.contains(element)) {
				unique.add(element);
			}
		}
		clear();
		addAll(unique);
		return this;
	}

	/**
	* Returns a string representation of this Ensemble.
	* The string representation consists of a list of the Ensemble's integers in the order they are returned by its iterator, enclosed in brackets ("{}").
	* Adjacent integers are separated by the characters ", " (comma and space).
	* Integers are converted to strings as by {@link Integer.#toString()}.
	* @return a string representation of this Ensemble
	*/
	@Override
	final public String toString() {
		String s = "{";
		int i = 0;
		for (final Integer element : this) {
			if (i++ > 0) {
				s += ", ";
			}
			s += element.toString();
		}
		s += "}";
		return s;
	}
}
