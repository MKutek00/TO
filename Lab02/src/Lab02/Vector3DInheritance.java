package Lab02;

public class Vector3DInheritance extends Vector2D{
    private final double z;

    public Vector3DInheritance(double x, double y, double z){
        super(x, y);
        this.z = z;
    }

    @Override
    public double abs() {
        double[] xy = this.getComponents();
        return Math.sqrt(xy[0]*xy[0] + xy[1]*xy[1] + z*z);
    }

    @Override
    public double cdot(IVector param) {
        double[] xy1 = this.getComponents();
        double[] xy2 = param.getComponents();
        double result = 0;

        for (int i = 0; i < Math.min(xy1.length, xy2.length); i++){
            result += xy1[i] * xy2[i];
        }
        return result;
    }

    @Override
    public double[] getComponents() {
        double[] sup = super.getComponents();

        return new double[] {sup[0], sup[1], this.z} ;
    }

    public Vector3DInheritance cross(IVector param){
        double[] xyz1 = this.getComponents();
        double[] xyz2 = param.getComponents();
        if (xyz2.length == 2){
            xyz2 = new double[] {xyz2[0], xyz2[1], 0};
        }
        double crossX = xyz1[1] * xyz2[2] - xyz1[2] * xyz2[1];
        double crossY = xyz1[2] * xyz2[0] - xyz1[0] * xyz2[2];
        double crossZ = xyz1[0] * xyz2[1] - xyz1[1] * xyz2[0];

        return new Vector3DInheritance(crossX, crossY, crossZ);
    }

    public IVector getScrV(){
        return (Vector2D)this;
    }

    @Override
    public String toString() {
        double[] xy = this.getComponents();
        return xy[0] + " " + xy[1] + " " + this.z;
    }
}
