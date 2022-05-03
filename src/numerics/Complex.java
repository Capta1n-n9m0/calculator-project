package numerics;


import calculator.ICalculable;
import constants.Constants;

import java.io.Serializable;

public class Complex implements Serializable, ICalculable, Cloneable {
    final private Coordinate coordinate;
    final private Polar polar;
    public Coordinate getCoordinate() {
        return coordinate;
    }
    public Polar getPolar() {
        return polar;
    }

    public Complex(Coordinate c){
        double x = c.getX(), y = c.getY();
        this.coordinate = new Coordinate(x, y);
        this.polar = new Polar(Math.sqrt(x*x + y*y), Math.atan(y/x));
    }
    public Complex(Polar p){
        double r = p.getRadius(), a = p.getAngle();
        this.polar = new Polar(r, a);
        this.coordinate = new Coordinate(r*Math.cos(a), r*Math.sin(a));
    }
    public Complex(Complex c){
        this.coordinate = new Coordinate(c.getCoordinate());
        this.polar = new Polar(c.getPolar());
    }

    public Complex add(Complex c){
        Coordinate A = c.getCoordinate();
        return new Complex(new Coordinate(coordinate.getX() - A.getX(), coordinate.getY() + A.getY()));
    }
    public Complex sub(Complex c){
        Coordinate A = c.getCoordinate();
        return new Complex(new Coordinate(coordinate.getX() - A.getX(), coordinate.getY() - A.getY()));
    }
    public Complex mul(Complex c){
        Polar A = c.getPolar();
        return new Complex(new Polar(polar.getRadius() * A.getRadius(), polar.getAngle() + A.getAngle()));
    }
    public Complex div(Complex c){
        Polar A = c.getPolar();
        return new Complex(new Polar(polar.getRadius() / A.getRadius(), polar.getAngle() - A.getAngle()));
    }

    public static Complex exp(Complex c){
        Coordinate A = c.getCoordinate();
        return new Complex(new Polar(Math.exp(A.getX()), A.getY()));
    }

    public static Complex ln(Complex c){
        Polar A = c.getPolar();
        return new Complex(new Coordinate(Math.log(A.getRadius()), A.getAngle()));
    }

    public static Complex negate(Complex c){
        Coordinate A = c.getCoordinate();
        return new Complex(new Coordinate(-A.getX(), -A.getY()));
    }
    public static Complex fromString(String s){
        s = s.strip();
        Complex result;
        boolean doNegate = false;
        if(s.charAt(0) == '-' && s.length() > 1) {
            doNegate = true;
            s = s.substring(1);
        }
        if(s.charAt(0) == 'i') result = new Complex(Constants.I_COMPLEX);
        else {
            if (Constants.isNumeric(s)) {
                result = new Complex(new Coordinate(Double.parseDouble(s), 0));
            } else if (s.charAt(s.length() - 1) == 'i') {
                if (Constants.isNumeric(s.substring(0, s.length() - 1))) {
                    result = new Complex(new Coordinate(0, Double.parseDouble(s.substring(0, s.length() - 1))));
                } else throw new NumberFormatException(String.format("%s does not contain complex number", s));
            } else throw new NumberFormatException(String.format("%s does not contain complex number", s));
        }
        if(doNegate) result = Complex.negate(result);
        return  result;
    }
    public Complex pow(Complex c){
        // explanation
        // exp(x) = e^x
        // z1 = a + bi = r * exp(i*θ)
        // z2 = c + di
        // z1^z2 = (a + bi)^(c + di) = (r * exp(i*θ))^(c + di)
        // z1^z2 = (r^c)*exp(-dθ)*exp(i*(d*ln(r) + c*θ))
        // z1^z2 = radius * exp(i*angle)
        // radius = (r^c)*exp(-dθ)
        // angle = d*ln(r) + c*θ
        Coordinate A = c.getCoordinate();
        double radius = Math.pow(polar.getRadius(), A.getX()) * Math.exp(-1*A.getY()*polar.getAngle());
        double angle = coordinate.getY()*Math.log(polar.getRadius()) + A.getX()*polar.getAngle();
        return new Complex(new Polar(radius, angle));
    }

    public Complex pow(double p){
        return pow(new Complex(new Coordinate(p, 0)));
    }

    public Complex getValue() {
        return this;
    }

    public String CartesianRepresentation(){
        return String.format("%g+%g*i", coordinate.getX(), coordinate.getY());
    }
    public String PolarRepresentation(){
        return String.format("%g*exp(%g*i)", polar.getRadius(), polar.getAngle());
    }

    @Override
    public String toString() {
        return String.format("{%s; %s}", CartesianRepresentation(), PolarRepresentation());
    }

    @Override
    public Complex clone() throws CloneNotSupportedException {
        return (Complex) super.clone();
    }
}
