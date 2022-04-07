package calculator;


public class Complex {
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
        return new Complex(new Coordinate(A.getX() + coordinate.getX(), A.getY() + coordinate.getY()));
    }
    public Complex sub(Complex c){
        Coordinate A = c.getCoordinate();
        return new Complex(new Coordinate(A.getX() - coordinate.getX(), A.getY() - coordinate.getY()));
    }
    public Complex mul(Complex c){
        Polar A = c.getPolar();
        return new Complex(new Polar(A.getAngle() + polar.getAngle(), A.getRadius() * polar.getRadius()));
    }
    public Complex div(Complex c){
        Polar A = c.getPolar();
        return new Complex(new Polar(A.getAngle() - polar.getAngle(), A.getRadius() / polar.getRadius()));
    }
}
