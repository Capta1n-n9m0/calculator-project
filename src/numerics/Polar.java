package numerics;

import java.io.Serializable;

public class Polar implements Serializable {
    private double angle, radius;
    public double getAngle() {
        return angle;
    }
    public double getRadius() {
        return radius;
    }

    public Polar(double radius, double angle){
        this.angle = angle % (Math.PI*2);
        this.radius = Math.abs(radius);
    }
    public Polar(Polar p){
        this.angle = p.getAngle();
        this.radius = p.getRadius();
    }
}
