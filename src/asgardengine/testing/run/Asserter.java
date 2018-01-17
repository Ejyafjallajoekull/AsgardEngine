package asgardengine.testing.run;

public class Asserter {


	/**
	 * Tests whether a testing subject performs without error.
	 * 
	 * @param subject - the testing subject to test
	 * @return true if all tests returned correctly
	 */
	public static boolean assertTestSubject(TestSubject subject) {
		if (subject != null) {
			try {
				subject.runAllTests();
				return true;
			} catch (TestFailureException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
