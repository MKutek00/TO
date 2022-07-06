package Lab02;

public class Polar2DAdapter implements IPolar2D, IVector{
    private final Vector2D scrVector;

    public Polar2DAdapter(Vector2D param){
        this.scrVector = param;
    }

    @Override
    public double getAngle() {
        double[] xy = this.getComponents();
        return Math.toDegrees(Math.atan2(xy[1], xy[0]));
    }

    @Override
    public double abs() {
        return this.scrVector.abs();
    }

    @Override
    public double cdot(IVector param) {
        return this.scrVector.cdot(param);
    }

    @Override
    public double[] getComponents() {
        return this.scrVector.getComponents();
    }
}
