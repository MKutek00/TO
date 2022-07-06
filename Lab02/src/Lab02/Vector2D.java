package Lab02;

public class Vector2D implements IVector {

    private final double x;
    private final double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double[] getComponents(){
        return new double[] {x, y};
    }

    @Override
    public double abs(){
        return Math.sqrt(x*x + y*y);
    }


    @Override
    public double cdot(IVector param) {
        double[] a = param.getComponents();
        return this.x * a[0] + this.y * a[1];
    }

}
