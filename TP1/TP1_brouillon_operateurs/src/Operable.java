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
	
	public static final String UNION = "union";
	public static final String INTERSECTION = "intersection";
	public static final String DIFFERENCE = "difference";
	public static final String SYMMETRIC_DIFFERENCE = "symmetricDifference";
	public static final String IS_SUBSET = "isSubset";
	public static final String IS_SUPERSET = "isSuperset";

	public Ensemble union(final Ensemble left, final Ensemble right);
	public Ensemble intersection(final Ensemble left, final Ensemble right);
	public Ensemble difference(final Ensemble left, final Ensemble right);
	public Ensemble symmetricDifference(final Ensemble left, final Ensemble right);
	public Ensemble isSubset(final Ensemble left, final Ensemble right);
	public Ensemble isSuperset(final Ensemble left, final Ensemble right);
}
