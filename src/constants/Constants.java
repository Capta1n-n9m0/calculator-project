package constants;

import numerics.Complex;
import numerics.Coordinate;
import numerics.Polar;

public class Constants {
    final public static int PORT = 5900;
    final public static String TAG = "Calculator";
    final public static String SERVER = "127.0.0.1";
    final public static Coordinate ORIGIN_C = new Coordinate(0 ,0);
    final public static Polar ORIGIN_P = new Polar(0, 0);
    final public static Complex ZERO_COMPLEX = new Complex(ORIGIN_C);
    final public static Complex ONE_COMPLEX = new Complex(new Coordinate(1, 0));
    final public static Complex NEGATIVE_ONE_COMPLEX = new Complex(new Coordinate(-1, 0));
    final public static Complex I_COMPLEX = new Complex(new Coordinate(0, 1));
    final public static Complex E_COMPLEX = new Complex(new Coordinate(Math.E, 0));
    final public static Complex PI_COMPLEX = new Complex(new Coordinate(Math.PI, 0));

}
