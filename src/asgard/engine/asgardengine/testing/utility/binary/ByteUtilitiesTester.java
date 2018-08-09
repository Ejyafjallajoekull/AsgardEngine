package asgard.engine.asgardengine.testing.utility.binary;

import java.util.Arrays;

import asgardengine.testing.run.TestFailureException;
import asgardengine.testing.run.TestSubject;
import asgardengine.utility.binary.ByteUtilities;

public class ByteUtilitiesTester implements TestSubject {

	public static void runTests() {
		byte[] a = {0, 1, 2, 122, -12};
		byte[] b = {-56, 45, 3, 33};
		ByteUtilities.join(a, b);
	}
	
	public void testIntegerToByte() throws TestFailureException {
		if (!Arrays.equals(ByteUtilities.integerToByte(0), new byte[Integer.BYTES])) {
			throw new TestFailureException("Arrays.equals(ByteUtilities.integerToByte(1), new byte[Integer.BYTES])");
		}
				
				
		if (!Arrays.equals(ByteUtilities.integerToByte(0), new byte[Integer.BYTES])) {
			
		}
	}

	@Override
	public void runAllTests() throws TestFailureException {
		this.testIntegerToByte();
		
	}

}
