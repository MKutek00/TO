package Lab02;

public class V2DPolarInheritance extends Vector2D{

    public V2DPolarInheritance(double x, double y) {
        super(x, y);
    }

    public double getAngle(){
        double[] xy = getComponents();
        return Math.toDegrees(Math.atan2(xy[1], xy[0]));
    }
}
