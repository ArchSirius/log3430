import java.util.Scanner;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		do {
			boolean terminateFlow = false;
			// Ask user for operation
			System.out.println("Opération valides: ");
			System.out.println("1) " + Operable.UNION);
			System.out.println("2) " + Operable.INTERSECTION);
			System.out.println("3) " + Operable.DIFFERENCE);
			System.out.println("4) " + Operable.SYMMETRIC_DIFFERENCE);
			System.out.println("5) " + Operable.IS_SUBSET);
			System.out.println("6) " + Operable.IS_SUPERSET);
			System.out.print("Entrez une opération: ");
			Operable.operation operation = null;
			try {
				operation = getOperation(scanner.nextLine());
			}
			catch (final UnsupportedOperationException e) {
				System.out.println(e.getMessage());
				terminateFlow = true;
			}

			Ensemble ensemble1 = null;
			if (!terminateFlow) {
				// Ask user for left Ensemble
				System.out.println("Entrez le premier ensemble constitué d'entiers séparés par une virgule: ");
				try {
					ensemble1 = str2Ensemble(scanner.nextLine());
				}
				catch (final NumberFormatException e) {
					System.out.println("Format invalide");
					terminateFlow = true;
				}
			}

			Ensemble ensemble2 = null;
			if (!terminateFlow) {
				// Ask user for right Ensemble
				System.out.println("Entrez le second ensemble constitué d'entiers séparés par une virgule: ");
				try {
					ensemble2 = str2Ensemble(scanner.nextLine());
				}
				catch (final NumberFormatException e) {
					System.out.println("Format invalide");
					terminateFlow = true;
				}
			}

			if (!terminateFlow) {
				// Show results
				System.out.print("Résultat: ");
				final ListeChainee resultat = new ListeChainee(operation, ensemble1, ensemble2);
				System.out.println(resultat);
			}
		}
		while (askContinue(scanner));
		scanner.close();
		System.exit(0);
	}

	/**
	* Returns the input operation in enum format if input is valid
	* @throws UnsupportedOperationException
	* @param input the user input
	* @return the resulting enum
	*/
	final static private Operable.operation getOperation(final String input) throws UnsupportedOperationException {
		if (input.equals("1") || input.toLowerCase().equals(Operable.UNION.toLowerCase())) {
			return Operable.operation.UNION;
		}
		if (input.equals("2") || input.toLowerCase().equals(Operable.INTERSECTION.toLowerCase())) {
			return Operable.operation.INTERSECTION;
		}
		if (input.equals("3") || input.toLowerCase().equals(Operable.DIFFERENCE.toLowerCase())) {
			return Operable.operation.DIFFERENCE;
		}
		if (input.equals("4") || input.toLowerCase().equals(Operable.SYMMETRIC_DIFFERENCE.toLowerCase())) {
			return Operable.operation.SYMMETRIC_DIFFERENCE;
		}
		if (input.equals("5") || input.toLowerCase().equals(Operable.IS_SUBSET.toLowerCase())) {
			return Operable.operation.IS_SUBSET;
		}
		if (input.equals("6") || input.toLowerCase().equals(Operable.IS_SUPERSET.toLowerCase())) {
			return Operable.operation.IS_SUPERSET;
		}
		throw new UnsupportedOperationException("Operation \"" + input + "\" is not supported");
	}

	/**
	* Returns a constructed Ensemble from user input
	* @throws NumberFormatException
	* @param input the user input
	* @return the constructed Ensemble
	*/
	final static private Ensemble str2Ensemble(final String input) throws NumberFormatException {
		final String[] array = input.split(",");
		Integer[] values = new Integer[array.length];
		for (int i = 0; i < array.length; ++i) {
			values[i] = Integer.parseInt(array[i].replaceAll(" ", ""));
		}
		return new Ensemble(values);
	}

	final static private boolean askContinue(final Scanner scanner) {
		System.out.print("Continuer? [Y/n] ");
		final String input = scanner.nextLine();
		if (input.toLowerCase().equals("y") || input.equals("")) {
			System.out.println();
			return true;
		}
		return false;
	}
}
