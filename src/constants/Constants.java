package constants;

import numerics.Complex;
import numerics.Coordinate;
import numerics.Polar;

public class Constants {
    public static int PORT = 5900;
    public static String TAG = "Calculator";
    public static String SERVER = "127.0.0.1";
    public static Coordinate ORIGIN_C = new Coordinate(0 ,0);
    public static Polar ORIGIN_P = new Polar(0, 0);
    public static Complex ZERO_COMPLEX = new Complex(ORIGIN_C);
}
