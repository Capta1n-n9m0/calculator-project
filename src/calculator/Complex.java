package calculator;


import java.io.Serializable;
import java.text.Format;
import java.util.Formatter;

public class Complex implements Serializable {
    private Coordinate coordinate;
    private Polar polar;
    public Coordinate getCoordinate() {
        return coordinate;
    }
    public Polar getPolar() {
        return polar;
    }

    public Complex(Coordinate c){
        double x = c.getX(), y = c.getY();
        this.coordinate = new Coordinate(x, y);
        this.polar = new Polar(Math.atan(y/x),Math.sqrt(x*x + y*y));
    }
    public Complex(Polar p){
        double r = p.getRadius(), a = p.getAngle();
        this.polar = new Polar(a, r);
        this.coordinate = new Coordinate(r*Math.cos(a), r*Math.sin(a));
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
        return new Complex(new Polar(polar.getAngle() + A.getAngle(), polar.getRadius() * A.getRadius()));
    }
    public Complex div(Complex c){
        Polar A = c.getPolar();
        return new Complex(new Polar(polar.getAngle() - A.getAngle(), polar.getRadius() / A.getRadius()));
    }

    public String CartesianRepresentation(){
        return String.format("%g + %g*j", coordinate.getX(), coordinate.getY());
    }
    public String PolarRepresentation(){
        return String.format("%g*exp(%g*i)", polar.getRadius(), polar.getAngle());
    }

    @Override
    public String toString() {
        return String.format("{%s; %s}", CartesianRepresentation(), PolarRepresentation());
    }
}
