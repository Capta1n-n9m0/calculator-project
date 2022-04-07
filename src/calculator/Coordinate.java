package calculator;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private double x, y;
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

}
