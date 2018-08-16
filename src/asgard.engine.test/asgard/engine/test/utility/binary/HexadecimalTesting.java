package asgard.engine.test.utility.binary;

import java.util.Arrays;
import java.util.Random;

import asgard.engine.asgardengine.utility.binary.Hexadecimal;
import koro.sensei.tester.TestFailureException;
import koro.sensei.tester.TestSubject;

/**
 * The HexadecimalTesting class test the Hexadecimal class for correct functionality.
 * 
 * @author Planters
 *
 */
public class HexadecimalTesting implements TestSubject {

	private static Random RANDOM = new Random();
	
	@Override
	public void runAllTests() throws TestFailureException {
		HexadecimalTesting.testConstructors();
		HexadecimalTesting.testHexRepresentation();
		HexadecimalTesting.testHexRepresentationStatic();
		HexadecimalTesting.testByteRepresentation();
		HexadecimalTesting.testEquals();
	}
	
	/**
	 * Test the constructors and some basic equality.
	 * 
	 * @throws TestFailureException the test did fail
	 */
	private static void testConstructors() throws TestFailureException {
		for (int i = 0; i < 10000; i++) {
			// test null
			Hexadecimal h1 = new Hexadecimal((byte[]) null);
			Hexadecimal h2 = new Hexadecimal((byte[]) null);
			TestSubject.assertTestCondition(h1.equals(h2), String.format("%s should equal %s.",
					h1, h2));
			h1 = new Hexadecimal((Byte) null);
			h2 = new Hexadecimal((Byte) null);
			TestSubject.assertTestCondition(h1.equals(h2), String.format("%s should equal %s.",
					h1, h2));
			// test empty byte arrays
			byte[] zeroBytes = new byte[0];
			h1 = new Hexadecimal(zeroBytes);
			h2 = new Hexadecimal(zeroBytes);
			TestSubject.assertTestCondition(h1.equals(h2), String.format("%s should equal %s.",
					h1, h2));
			// test random byte arrays
			byte[] randomBytes = new byte[HexadecimalTesting.RANDOM.nextInt(400)];
			HexadecimalTesting.RANDOM.nextBytes(randomBytes);
			h1 = new Hexadecimal(randomBytes);
			h2 = new Hexadecimal((byte[]) null);
			TestSubject.assertTestCondition(!h1.equals(h2), String.format("%s should not equal %s.",
					h1, h2));
			h2 = new Hexadecimal(randomBytes);
			TestSubject.assertTestCondition(h1.equals(h2), String.format("%s should equal %s.",
					h1, h2));
			// test random byte
			byte[] randomByte = new byte[1];
			HexadecimalTesting.RANDOM.nextBytes(randomBytes);
			h1 = new Hexadecimal(randomByte[0]);
			h2 = new Hexadecimal((Byte) null);
			TestSubject.assertTestCondition(!h1.equals(h2), String.format("%s should not equal %s.",
					h1, h2));
			h2 = new Hexadecimal(randomByte[0]);
			TestSubject.assertTestCondition(h1.equals(h2), String.format("%s should equal %s.",
					h1, h2));
			HexadecimalTesting.RANDOM.nextBytes(randomBytes);
			h1 = new Hexadecimal(Byte.valueOf(randomByte[0]));
			h2 = new Hexadecimal((Byte) null);
			TestSubject.assertTestCondition(!h1.equals(h2), String.format("%s should not equal %s.",
					h1, h2));
			h2 = new Hexadecimal(Byte.valueOf(randomByte[0]));
			TestSubject.assertTestCondition(h1.equals(h2), String.format("%s should equal %s.",
					h1, h2));
		}
	}
	
	/**
	 * Tests if bytes get displayed as hexadecimal numbers correctly.
	 * 
	 * @throws TestFailureException the test did fail
	 */
	private static void testHexRepresentation() throws TestFailureException {
		for (int i = 0; i < 10000; i++) {
			// test null
			Hexadecimal nullHex = new Hexadecimal((byte[]) null);
			TestSubject.assertTestCondition(nullHex.toString() == null, String.format("%s should equal %s.",
					nullHex, null));
			// test random values
			byte[] randomBytes = new byte[HexadecimalTesting.RANDOM.nextInt(400)];
			HexadecimalTesting.RANDOM.nextBytes(randomBytes);
			String hexString = "";
			for (byte b : randomBytes) {
				String intString = Integer.toHexString((int) b).toUpperCase();
				while (intString.length() < 2) {
					intString = "0" + intString;
				}
				hexString += intString.substring(intString.length()-2, intString.length());
			}
			Hexadecimal randomHex = new Hexadecimal(randomBytes);
			TestSubject.assertTestCondition(randomHex.toString().equals(hexString), String.format("The byte "
					+ "sequence %s should have the hexadecimal representation %s, but has %s.",
					Arrays.toString(randomBytes), hexString, randomHex));
			if (randomBytes.length == 1) {
				Hexadecimal randomHexByte = new Hexadecimal(randomBytes[0]);
				TestSubject.assertTestCondition(randomHexByte.toString().equals(hexString), String.format("The byte "
						+ "sequence %s should have the hexadecimal representation %s, but has %s.",
						Arrays.toString(randomBytes), hexString, randomHex));
				randomHexByte = new Hexadecimal(Byte.valueOf(randomBytes[0]));
				TestSubject.assertTestCondition(randomHexByte.toString().equals(hexString), String.format("The byte "
						+ "sequence %s should have the hexadecimal representation %s, but has %s.",
						Arrays.toString(randomBytes), hexString, randomHex));
			}
		}
	}
	
	/**
	 * Tests if bytes get displayed as hexadecimal numbers correctly when using the static method.
	 * 
	 * @throws TestFailureException the test did fail
	 */
	private static void testHexRepresentationStatic() throws TestFailureException {
		for (int i = 0; i < 10000; i++) {
			// test null
			String nullHex = Hexadecimal.toHex((byte[]) null);
			TestSubject.assertTestCondition(nullHex == null, String.format("%s should equal %s.",
					nullHex, null));
			// test random values
			byte[] randomBytes = new byte[HexadecimalTesting.RANDOM.nextInt(400)];
			HexadecimalTesting.RANDOM.nextBytes(randomBytes);
			String hexString = "";
			for (byte b : randomBytes) {
				String intString = Integer.toHexString((int) b).toUpperCase();
				while (intString.length() < 2) {
					intString = "0" + intString;
				}
				hexString += intString.substring(intString.length()-2, intString.length());
			}
			String randomHex = Hexadecimal.toHex(randomBytes);
			TestSubject.assertTestCondition(randomHex.equals(hexString), String.format("The byte "
					+ "sequence %s should have the hexadecimal representation %s, but has %s.",
					Arrays.toString(randomBytes), hexString, randomHex));
			if (randomBytes.length == 1) {
				String randomHexByte = Hexadecimal.toHex(randomBytes[0]);
				TestSubject.assertTestCondition(randomHexByte.equals(hexString), String.format("The byte "
						+ "sequence %s should have the hexadecimal representation %s, but has %s.",
						Arrays.toString(randomBytes), hexString, randomHex));
				randomHexByte = Hexadecimal.toHex(Byte.valueOf(randomBytes[0]));
				TestSubject.assertTestCondition(randomHexByte.equals(hexString), String.format("The byte "
						+ "sequence %s should have the hexadecimal representation %s, but has %s.",
						Arrays.toString(randomBytes), hexString, randomHex));
			}
		}
	}
	
	/**
	 * Tests if the conversion back to a byte array works correctly.
	 * 
	 * @throws TestFailureException the test did fail
	 */
	private static void testByteRepresentation() throws TestFailureException {
		for (int i = 0; i < 10000; i++) {
			// test null
			Hexadecimal nullHex = new Hexadecimal((byte[]) null);
			TestSubject.assertTestCondition(nullHex.toBytes() == null, String.format("%s should equal %s.",
					Arrays.toString(nullHex.toBytes()), null));
			// test random values
			byte[] randomBytes = new byte[HexadecimalTesting.RANDOM.nextInt(400)];
			HexadecimalTesting.RANDOM.nextBytes(randomBytes);
			Hexadecimal randomHex = new Hexadecimal(randomBytes);
			TestSubject.assertTestCondition(Arrays.equals(randomHex.toBytes(), randomBytes), String.format(
					"%s should have the byte representation %s, but has %s.",
					randomHex, Arrays.toString(randomBytes),  Arrays.toString(randomHex.toBytes())));
		}
	}
	
	/**
	 * Test equality to different byte representations.
	 * 
	 * @throws TestFailureException the test did fail
	 */
	@SuppressWarnings("unlikely-arg-type")
	private static void testEquals() throws TestFailureException {
		for (int i = 0; i < 10000; i++) {
			// test null
			Hexadecimal nullHex = new Hexadecimal((byte[]) null);
			TestSubject.assertTestCondition(!nullHex.equals(null), String.format("%s should not equal %s.",
					nullHex, null));
			Hexadecimal someHex = new Hexadecimal((byte) 39);
			TestSubject.assertTestCondition(!nullHex.equals(someHex), String.format("%s should not equal %s.",
					nullHex, someHex));
			Hexadecimal secondNullHex = new Hexadecimal((byte[]) null);
			TestSubject.assertTestCondition(nullHex.equals(secondNullHex), String.format("%s should equal %s.",
					nullHex, secondNullHex));
			secondNullHex = new Hexadecimal((Byte) null);
			TestSubject.assertTestCondition(nullHex.equals(secondNullHex), String.format("%s should equal %s.",
					nullHex, secondNullHex));
			// test random values
			byte[] randomBytes = new byte[HexadecimalTesting.RANDOM.nextInt(400)];
			HexadecimalTesting.RANDOM.nextBytes(randomBytes);
			Hexadecimal randomHex = new Hexadecimal(randomBytes);
			Hexadecimal secondRandomHex = new Hexadecimal(randomBytes);
			TestSubject.assertTestCondition(randomHex.equals(secondRandomHex), String.format("%s should "
					+ "equal %s.",
					randomHex, secondRandomHex));
			TestSubject.assertTestCondition(randomHex.equals(randomBytes), String.format("The "
					+ "hexadecimal sequence %s should equal the byte sequence %s.",
					randomHex, Arrays.toString(randomBytes)));
			if (randomBytes.length == 1) {
				TestSubject.assertTestCondition(randomHex.equals(randomBytes[0]), String.format("The "
						+ "hexadecimal sequence %s should equal the byte sequence %s.",
						randomHex, Arrays.toString(randomBytes)));
				TestSubject.assertTestCondition(randomHex.equals(Byte.valueOf(randomBytes[0])), String.format("The "
						+ "hexadecimal sequence %s should equal the byte sequence %s.",
						randomHex, Arrays.toString(randomBytes)));
			}
		}
	}

}
