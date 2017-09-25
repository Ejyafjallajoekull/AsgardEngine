package asgardengine.utility.binary;

public class ByteUtilities {

	// wrap bytes in a Byte array
	public static Byte[] wrap(byte[] bytes) {
		if (bytes != null) {
			Byte[] b = new Byte[bytes.length];
			for (int i = 0; i < bytes.length; i++) {
				b[i] = bytes[i];
			}
			return b;
		} else {
			return null;
		}
	}
	
	public static byte[] join(byte[] extend, byte[] with) {
		if (extend != null || with != null) {
			if(extend == null) {
				return with.clone();
			} else if (with == null) {
				return extend.clone();
			}
			byte[] joined = new byte[extend.length + with.length];
			for (int i = 0; i < extend.length; i++) {
				joined[i] = extend[i];
			}
			for (int i = extend.length; i < joined.length; i++) {
				joined[i] = with[i - extend.length];
			}
			return joined;
		} else {
			return null;
		}
	}

}
