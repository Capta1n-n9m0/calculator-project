package numerics;

import java.io.Serializable;

public class Coordinate implements Serializable, Cloneable {
    final private double x, y;
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public Coordinate(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Coordinate(Coordinate c){
        this.x = c.getX();
        this.y = c.getY();
    }
    @Override
    public Coordinate clone() throws CloneNotSupportedException {
        return (Coordinate) super.clone();
    }
}
