/**
 * This interface defines operations on Ensembles
 *
 * @author Samuel Rondeau, Jennifer Khoury
 * @version 1.0
 */
public interface Operable {
	
	public enum operation {
		UNION,
		INTERSECTION,
		DIFFERENCE,
		SYMMETRIC_DIFFERENCE,
		IS_SUBSET,
		IS_SUPERSET
	};

	public Ensemble union(final Ensemble left, final Ensemble right);
	public Ensemble intersection(final Ensemble left, final Ensemble right);
	public Ensemble difference(final Ensemble left, final Ensemble right);
	public Ensemble symmetricDifference(final Ensemble left, final Ensemble right);
	public Ensemble isSubset(final Ensemble left, final Ensemble right);
	public Ensemble isSuperset(final Ensemble left, final Ensemble right);
}
