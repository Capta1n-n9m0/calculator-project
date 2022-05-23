package constants;

import numerics.Complex;
import numerics.Coordinate;
import numerics.Polar;

public class Constants {
    final public static int PORT = 5900;
    final public static String TAG = "Calculator";
    final public static String SERVER = "192.168.154.149";
    final public static Coordinate ORIGIN_C = new Coordinate(0 ,0);
    final public static Polar ORIGIN_P = new Polar(0, 0);
    final public static Complex ZERO_COMPLEX = new Complex(ORIGIN_C);
    final public static Complex ONE_COMPLEX = new Complex(new Coordinate(1, 0));
    final public static Complex NEGATIVE_ONE_COMPLEX = new Complex(new Coordinate(-1, 0));
    final public static Complex I_COMPLEX = new Complex(new Coordinate(0, 1));
    final public static Complex E_COMPLEX = new Complex(new Coordinate(Math.E, 0));
    final public static Complex PI_COMPLEX = new Complex(new Coordinate(Math.PI, 0));
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isDigit(char c){
        switch (c){
            case '1': case '2': case '3': case '4': case '5':
            case '6': case '7': case '8': case '9': case '0':
                return true;
            default:
                return false;
        }
    }
    public static boolean isDigit(String s){
        if(s.isBlank()) return false;
        return isDigit(s.charAt(0));
    }
    public static boolean isOperator(char c){
        switch (c){
            case '+': case '-':
            case '*': case 'x': case '/': case '÷':
                return true;
            default:
                return false;
        }
    }
    public static boolean isOperator(String s){
        if(s.isBlank()) return false;
        return isOperator(s.charAt(0));
    }

}
