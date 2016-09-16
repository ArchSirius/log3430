import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class represents a linked list of sets of integers.
 *
 * @author Samuel Rondeau, Jennifer Khoury
 * @version 1.1
 */
public class ListeChainee extends LinkedList<Ensemble> implements Operable {
	
   /**
	* Constructs a new ListeChainee containing two input sets and a resulting set based on a specific operation.
	* @param operation the operation to execute
	* @param left      the left set
	* @param right     the right set
	*/
	public ListeChainee(final Operable.operation operation, final Ensemble left, final Ensemble right) {
		super(Arrays.asList(left, right));
		switch(operation) {
			case UNION:
				add(union(left, right));
				break;
			case INTERSECTION:
				add(intersection(left, right));
				break;
			case DIFFERENCE:
				add(difference(left, right));
				break;
			case SYMMETRIC_DIFFERENCE:
				add(symmetricDifference(left, right));
				break;
			case IS_SUBSET:
				add(isSubset(left, right));
				break;
			case IS_SUPERSET:
				add(isSuperset(left, right));
				break;
			default:
				break;
		}
	}
	
	/**
	* Constructs a new ListeChainee containing two input sets and a resulting set based on a specific operation from String input.
	* This constructor has been deprecated in v1.1
	* @deprecated 1.1
	* @throws UnsupportedOperationException
	* @param operation the operation to execute
	* @param left      the left set
	* @param right     the right set
	*/
	public ListeChainee(final String operation, final Ensemble left, final Ensemble right) throws UnsupportedOperationException {
		super(Arrays.asList(left, right));
		switch(operation) {
			case UNION:
				add(union(left, right));
				break;
			case INTERSECTION:
				add(intersection(left, right));
				break;
			case DIFFERENCE:
				add(difference(left, right));
				break;
			case SYMMETRIC_DIFFERENCE:
				add(symmetricDifference(left, right));
				break;
			case IS_SUBSET:
				add(isSubset(left, right));
				break;
			case IS_SUPERSET:
				add(isSuperset(left, right));
				break;
			default:
				throw new UnsupportedOperationException("Operation \"" + operation + "\" is not supported");
		}
	}
	
   /**
	* Returns the union of two sets.
	* A ∪ B = { x : x ∈ A ∨ x ∈ B }
	* @param left  the left set (A)
	* @param right the right set (B)
	* @return the resulting set
	*/
	final public Ensemble union(final Ensemble left, final Ensemble right) {
		final Ensemble result = new Ensemble();
		result.addAll(left);
		result.addAll(right);
		return result.unique().sort();
	}
	
   /**
	* Returns the intersection of two sets.
	* A ∩ B = { x : x ∈ A ^ x ∈ B }
	* @param left  the left set (A)
	* @param right the right set (B)
	* @return the resulting set
	*/
	final public Ensemble intersection(final Ensemble left, final Ensemble right) {
		final Ensemble result = new Ensemble();
		for (final Integer element : left) {
			if (right.contains(element)) {
				result.add(element);
			}
		}
		return result.unique().sort();
	}
	
	/**
	* Returns the difference of two sets.
	* A \ B = { x : x ∈ A ^ x ∉ B }
	* @param left  the left set (A)
	* @param right the right set (B)
	* @return the resulting set
	*/
	final public Ensemble difference(final Ensemble left, final Ensemble right) {
		final Ensemble result = new Ensemble();
		for (final Integer element : left) {
			if (!right.contains(element)) {
				result.add(element);
			}
		}
		return result.unique().sort();
	}
	
	/**
	* Returns the symmetric difference of two sets.
	* A ∆ B = { x : (x ∈ A) ⊕ (x ∈ B) }
	* @param left  the left set (A)
	* @param right the right set (B)
	* @return the resulting set
	*/
	final public Ensemble symmetricDifference(final Ensemble left, final Ensemble right) {
		final Ensemble result = new Ensemble();
		for (final Integer element : left) {
			if (!right.contains(element)) {
				result.add(element);
			}
		}
		for (final Integer element : right) {
			if (!left.contains(element)) {
				result.add(element);
			}
		}
		return result.unique().sort();
	}
	
	/**
	* Returns {1} if the left set is a subset of the right set; {0} otherwise.
	* A ⊆ B
	* @param left  the left set (A)
	* @param right the right set (B)
	* @return the resulting set
	*/
	final public Ensemble isSubset(final Ensemble left, final Ensemble right) {
		return new Ensemble(right.containsAll(left) ? 1 : 0);
	}
	
	/**
	* Returns {1} if the left set is a superset of the right set; {0} otherwise.
	* A ⊇ B
	* @param left  the left set (A)
	* @param right the right set (B)
	* @return the resulting set
	*/
	final public Ensemble isSuperset(final Ensemble left, final Ensemble right) {
		return new Ensemble(left.containsAll(right) ? 1 : 0);
	}
	
   /**
	* Appends the specified Ensemble to the end of this ListeChainee.
	* @param ensemble Ensemble to be appended to this ListeChainee
	* @return true (as specified by {@link Collection.#add(E)})
	*/
	public boolean add(final Ensemble ensemble) {
		return super.add(ensemble);
	}
	
	/**
	* Removes the element at the specified position in this ListeChainee.
	* Shifts any subsequent elements to the left (subtracts one from their indices).
	* Returns the element that was removed from the ListeChainee.
	* @param position the index of the element to be removed
	* @return the element previously at the specified position
	*/
	public Ensemble removeAt(final int position) {
		return super.remove(position);
	}
	
	/**
	* Removes the first occurrence of the specified Ensemble in this ListeChainee (when traversing the ListeChainee from head to tail).
	* If the ListeChainee does not contain the Ensemble, it is unchanged.
	* @param ensemble Ensemble to be removed from this ListeChainee, if present
	* @return true if the ListeChainee contained the specified Ensemble
	*/
	public boolean removeItem(final Ensemble ensemble) {
		return super.removeFirstOccurrence(ensemble);
	}
	
	/**
	* Appends the specified Ensemble to the end of this ListeChainee.
	* This method is equivalent to {@#linkaddLast(E)}.
	* @param ensemble Ensemble to be inserted
	* @param position position at which the specified Ensemble is to be inserted
	*/
	public void setAt(final Ensemble ensemble, final int position) {
		super.add(position, ensemble);
	}
	
	/**
	* Returns the Ensemble at the specified position in this ListeChainee.
	* @param position position of the element to return
	* @return the Ensemble at the specified position in this ListeChainee
	*/
	final public Ensemble getAt(final int position) {
		return super.get(position);
	}
	
	/**
	* Returns the number of Ensemble in this ListeChainee.
	* @return the number of elements in this list
	*/
	final public int getSize() {
		return super.size();
	}
	
	/**
	* Removes all of the Ensemble from this ListeChainee.
	* The ListeChainee will be empty after this call returns.
	*/
	public void reset() {
		super.clear();
	}
	
	/**
	* Returns a string representation of this ListeChainee.
	* The string representation consists of a list of the ListeChainee's Ensembles in the order they are returned by its iterator.
	* Adjacent Ensembles are separated by the characters ", " (comma and space).
	* Ensembles are converted to strings as by {@link Ensemble.#toString()}.
	* @return a string representation of this ListeChainee
	*/
	@Override
	final public String toString() {
		String s = "";
		int i = 0;
		for (final Ensemble ensemble : this) {
			if (i++ > 0) {
				s += ", ";
			}
			s += ensemble.toString();
		}
		return s;
	}
}
