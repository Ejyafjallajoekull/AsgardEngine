package asgardengine.testing.run;

/**
 * The TestSubject interface streamlines the access of testing functions.
 * 
 * @author Planters
 *
 */
public interface TestSubject {

	/**
	 * Run all predefined tests on this test subject.
	 * 
	 * @throws TestFailureException this will be thrown, if a test did not meet the
	 * specified requirements
	 */
	public void runAllTests() throws TestFailureException;
	
}
