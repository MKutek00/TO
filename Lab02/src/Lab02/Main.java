package Lab02;

public class Main {
    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(2, 2);

        Polar2DAdapter p1 = new Polar2DAdapter(new Vector2D(5, 5));

        Vector3DDecorator v3dD = new Vector3DDecorator(4, 5, 6);

        Vector3DInheritance v3dI = new Vector3DInheritance(6, 5, 4);

        double[] xy1 = v1.getComponents();
        double[] xy2 = p1.getComponents();
        double[] xyz1 = v3dD.getComponents();
        double[] xyz2 = v3dI.getComponents();

        System.out.println("v1: (" + xy1[0] + ", " + xy1[1] + ")");
        System.out.println();

        System.out.println("p1: (" + p1.getAngle() + " stopni, " + p1.abs() + ")");
        System.out.println("p1: (" + xy2[0] + ", " + xy2[1] + ")");
        System.out.println();

        System.out.println("v3dD: (" +  xyz1[0] + ", " + xyz1[1] + ", " + xyz1[2] + ")");
        System.out.println();

        System.out.println("v3dI: (" +  xyz2[0] + ", " + xyz2[1] + ", " + xyz2[2] + ")");
        System.out.println();


        System.out.println("Iloczyn skalarny v1 i p1: " + v1.cdot(p1));
        System.out.println("Iloczyn skalarny v1 i v3dD: " + v1.cdot(v3dD));
        System.out.println("Iloczyn skalarny v1 i v3dI: " + v1.cdot(v3dI));
        System.out.println("Iloczyn skalarny p1 i v3dD: " + p1.cdot(v3dD));
        System.out.println("Iloczyn skalarny p1 i v3dI: " + p1.cdot(v3dI));
        System.out.println("Iloczyn skalarny v3dD i v3dI: " + v3dD.cdot(v3dI));
        System.out.println();

        System.out.println("Iloczyn wektorowy v3dD i v1: " + v3dD.cross(v1));
        System.out.println("Iloczyn wektorowy v3dD i p1: " + v3dD.cross(p1));
        System.out.println("Iloczyn wektorowy v3dD i v3dI: " + v3dD.cross(v3dI));
        System.out.println("Iloczyn wektorowy v3dI i v1: " + v3dI.cross(v1));
        System.out.println("Iloczyn wektorowy v3dI i p1: " + v3dI.cross(p1));
        System.out.println();

    }
}
