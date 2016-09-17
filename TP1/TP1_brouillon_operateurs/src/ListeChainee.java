/**
 * This class represents a linked list of Ensembles.
 *
 * @author Samuel Rondeau, Jennifer Khoury
 * @version 1.2
 */
public class ListeChainee implements Operable {
	
	private Node mHead;
	
   /**
	* Constructs a new ListeChainee containing two input sets and a resulting set based on a specific operation.
	* @param operation the operation to execute
	* @param left      the left set
	* @param right     the right set
	*/
	public ListeChainee(final Operable.operation operation, final Ensemble left, final Ensemble right) {
		mHead = new Node(left, new Node(right, null));
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
		mHead = new Node(left, new Node(right, null));
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
	*/
	public void add(final Ensemble ensemble) {
		if (mHead == null) {
			mHead = new Node(ensemble, null);
		}
		else {
			Node node = mHead;
			while (node.hasNext()) {
				node = node.getNext();
			}
			node.setNext(new Node(ensemble, null));
		}
	}
	
	/**
	* Removes the element at the specified position in this ListeChainee.
	* Shifts any subsequent elements to the left (subtracts one from their indices).
	* Returns the element that was removed from the ListeChainee.
	* @param position the index of the element to be removed
	* @return the element previously at the specified position
	*/
	public Ensemble removeAt(final int position) {
		if (mHead == null) {
			return null;
		}
		if (position == 0) {
			final Ensemble data = mHead.getData();
			mHead = mHead.getNext();
			return data;
		}
		Node previous = mHead;
		Node current = mHead;
		int i = 0;
		while (current != null) {
			if (i++ == position) {
				previous.setNext(current.getNext());
				return current.getData();
			}
			previous = current;
			current = current.getNext();
		}
		return null;
	}
	
	/**
	* Removes the first occurrence of the specified Ensemble in this ListeChainee (when traversing the ListeChainee from head to tail).
	* If the ListeChainee does not contain the Ensemble, it is unchanged.
	* @param ensemble Ensemble to be removed from this ListeChainee, if present
	* @return true if the ListeChainee contained the specified Ensemble
	*/
	public boolean removeItem(final Ensemble ensemble) {
		if (mHead == null) {
			return false;
		}
		if (mHead.getData().equals(ensemble)) {
			mHead = mHead.getNext();
			return true;
		}
		Node previous = mHead;
		Node current = mHead;
		while (current != null) {
			if (current.getData().equals(ensemble)) {
				previous.setNext(current.getNext());
				return true;
			}
			previous = current;
			current = current.getNext();
		}
		return false;
	}
	
	/**
	* Changes the Ensemble at the specified position in this ListeChainee.
	* @param ensemble Ensemble to replace the previous one
	* @param position position at which the change is to be done
	* @return true if the ListeChainee contained an Ensemble at the specified position and change was successful
	*/
	public boolean setAt(final Ensemble ensemble, final int position) {
		if (mHead == null) {
			return false;
		}
		Node node = mHead;
		int i = 0;
		while (node != null) {
			if (i++ == position) {
				node.setData(ensemble);
				return true;
			}
			node = node.getNext();
		}
		return false;
	}
	
	/**
	* Returns the Ensemble at the specified position in this ListeChainee.
	* @param position position of the element to return
	* @return the Ensemble at the specified position in this ListeChainee
	*/
	final public Ensemble getAt(final int position) {
		if (mHead == null) {
			return null;
		}
		Node node = mHead;
		int i = 0;
		while (node != null) {
			if (i++ == position) {
				return node.getData();
			}
			node = node.getNext();
		}
		return null;
	}
	
	/**
	* Returns the number of Ensembles in this ListeChainee.
	* @return the number of Ensembles in this ListeChainee
	*/
	final public int getSize() {
		if (mHead == null) {
			return 0;
		}
		int size = 0;
		Node node = mHead;
		while (node != null) {
			node = node.getNext();
			++size;
		}
		return size;
	}
	
	/**
	* Removes all of the Ensembles from this ListeChainee.
	* The ListeChainee will be empty after this call returns.
	*/
	public void reset() {
		mHead = null;
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
		Node node = mHead;
		while (node != null) {
			final Ensemble ensemble = node.getData();
			if (i++ > 0) {
				s += ", ";
			}
			s += ensemble.toString();
			node = node.getNext();
		}
		return s;
	}
	
	
	/**
	 * This class represents a node in the ListeChainee wrapping an Ensemble.
	 *
	 * @author Samuel Rondeau, Jennifer Khoury
	 * @version 1.0
	 */
	protected static class Node {
		
		private Ensemble mData;
		private Node mNext;

	   /**
		* Constructs a new Node containing an Ensemble and referencing its next node in the ListeChainee, if any.
		* @param data the Ensemble contained in this Node
		* @param next the next node
		*/
		public Node(final Ensemble data, final Node next) {
			mData = data;
			mNext = next;
		}
		
	   /**
		* Returns the Ensemble contained in this Node.
		* @return the Ensemble contained in this Node
		*/
		final public Ensemble getData() {
			return mData;
		}
		
	   /**
		* Changes the Ensemble contained in this node.
		* @param ensemble the new Ensemble to replace the previous one
		*/
		public void setData(final Ensemble ensemble) {
			mData = ensemble;
		}
		
	   /**
		* Returns true if a next Node following this one in this ListeChainee exists.
		* @return true if a next Node following this one in this ListeChainee exists
		*/
		final public boolean hasNext() {
			return mNext != null;
		}
		
	   /**
		* Returns the next Node following this one in this ListeChainee.
		* @return the next Node
		*/
		final public Node getNext() {
			return mNext;
		}
		
	   /**
		* Changes the next Node following this one in this ListeChainee.
		* @param node the next Node
		*/
		public void setNext(final Node node) {
			mNext = node;
		}
	}
}
