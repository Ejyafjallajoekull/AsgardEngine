package asgardengine.testing.run;

/**
 * The TestFailureException will be thrown each time a specific test 
 * fails to meet the specified requirements.
 * 
 * @author Planters
 *
 */
public class TestFailureException extends Exception {

	/**
	 * Standard serialisation.
	 */
	private static final long serialVersionUID = 1L;

	public TestFailureException() {
		// TODO Auto-generated constructor stub
	}

	public TestFailureException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TestFailureException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TestFailureException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public TestFailureException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
