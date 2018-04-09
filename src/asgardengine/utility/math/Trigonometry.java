package asgardengine.utility.math;

/**
 * The Trigonometry class provides basic trigonometrical functionality.
 * 
 * @author Planters
 *
 */
public class Trigonometry {
	
	public static final int TRIG_MAP_RESOLUTION = 100000; // the maximum number of steps between zero and a quarter of pi for trigonometry functions
	public static final double PI_DOUBLE = Math.PI * 2.0d; // the double of pi
	public static final double PI_HALF = Math.PI * 0.5d; // the half of pi
	private static double[] sinMap = new double[Trigonometry.TRIG_MAP_RESOLUTION + 1];
	private static boolean init = false; // has this class been initialised?
	private static double trigDiff = PI_DOUBLE / Trigonometry.TRIG_MAP_RESOLUTION;
	
	
	private static void populateMaps() {
		for (int i = 0; i < TRIG_MAP_RESOLUTION + 1; i++) {
			Trigonometry.sinMap[i] = Math.sin(Trigonometry.trigDiff*i);
		}
//		System.out.println(getSinIndex(PI_DOUBLE + 1));
	}
	
//	public static void initialise() {
//		if (!Trigonometry.init) {
//			Trigonometry.populateMaps();
//			Trigonometry.init = true;
//		}
//	}
//	
	static {
		if (!Trigonometry.init) {
			Trigonometry.populateMaps();
			Trigonometry.init = true;
		}
	}

	public static double sin(double radians) {
//		Trigonometry.initialise();
		double rad = radians;
		if (rad < 0.0d) {
			rad *= -1.0d;
		}
		if (rad >= PI_DOUBLE) {
			rad = rad%PI_DOUBLE;
		}
		int index = Trigonometry.getSinIndex(rad);			
		return Trigonometry.sinMap[index];
	}
	
	public static double cos(double radians) {
		return sin(radians + PI_HALF);
	}
	
	private static int getSinIndex(double radians) {
		int index = (int) (radians/Trigonometry.trigDiff);
		return index;
	}
	
	public static double sinSimpleCalc(double radians) {
		double x = radians;
		double sin = 0.0d;
		if (x < -3.14159265) {
		    x += 6.28318531;
		} else if (x >  3.14159265) {
		    x -= 6.28318531;
		}
		if (x < 0) {
		    sin = 1.27323954 * x + .405284735 * x * x;
		} else {
		    sin = 1.27323954 * x - 0.405284735 * x * x;
		}
		return sin;
	}
	
	public static double cosSimpleCalc(double radians) {
		double x = radians;
		double cos = 0.0d;
		if (x < -3.14159265) {
		    x += 6.28318531;
		} else if (x >  3.14159265) {
		    x -= 6.28318531;
		}
		x += 1.57079632;
		if (x >  3.14159265) {
		    x -= 6.28318531;
		}
		if (x < 0) {
		    cos = 1.27323954 * x + 0.405284735 * x * x;
		} else {
		    cos = 1.27323954 * x - 0.405284735 * x * x;
		}
		return cos;
	}
	
    private static double[] a = new double[65536];

    public static final double sinNotch(float f) {
        return a[(int) (f * 10430.378F) & '\uffff'];
    }

    public static final double cosNotch(float f) {
        return a[(int) (f * 10430.378F + 16384.0F) & '\uffff'];
    }

    static {
        for (int i = 0; i < 65536; ++i) {
            a[i] = Math.sin((double) i * 3.141592653589793D * 2.0D / 65536.0D);
        }
    }
	
//	public static double tan() {
//		
//	}

}
