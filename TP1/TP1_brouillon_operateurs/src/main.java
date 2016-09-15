public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Ensemble e1 = new Ensemble(2, 5, 15, 23);
		final Ensemble e2 = new Ensemble(2, 3, 5, 12, 56);
		
		final ListeChainee listeUnion = new ListeChainee(Operable.operation.UNION, e1, e2);
		System.out.println("union:\t" + listeUnion.toString());
		
		final ListeChainee listeIntersection = new ListeChainee(Operable.operation.INTERSECTION, e1, e2);
		System.out.println("intersection:\t" + listeIntersection.toString());
		
		final ListeChainee listeDifference = new ListeChainee(Operable.operation.DIFFERENCE, e1, e2);
		System.out.println("difference:\t" + listeDifference.toString());
		
		final ListeChainee listeSymmetricDifference = new ListeChainee(Operable.operation.SYMMETRIC_DIFFERENCE, e1, e2);
		System.out.println("symmetric difference:\t" + listeSymmetricDifference.toString());
		
		final ListeChainee listeSubset1 = new ListeChainee(Operable.operation.IS_SUBSET, e1, e2);
		System.out.println("subset:\t" + listeSubset1.toString());
		
		final ListeChainee listeSubset2 = new ListeChainee(Operable.operation.IS_SUBSET, e1, new Ensemble(2, 23, 15, 5, 56, 12));
		System.out.println("subset:\t" + listeSubset2.toString());
		
		final ListeChainee listeSuperset1 = new ListeChainee(Operable.operation.IS_SUPERSET, e1, e2);
		System.out.println("superset:\t" + listeSuperset1.toString());
		
		final ListeChainee listeSuperset2 = new ListeChainee(Operable.operation.IS_SUPERSET, e2, new Ensemble(2, 3, 5));
		System.out.println("superset:\t" + listeSuperset2.toString());
	}
}
