package codinglegend.math;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    public Vector3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){ return x; }
    public double getY(){ return y; }
    public double getZ(){ return z; }

    // /** Create a 3d vector with a direction (in radians) and magnitude */
    // public static Vector3D createVectorRad(double radiansXY, double radiansXZ, double magnitude){
    //     return new Vector3D(magnitude*Math.cos(radiansXY),magnitude*Math.sin(radiansXZ),magnitude);
    // }

    // /** Creates a 3d vector width a direction (in degrees) and magnitude*/
    // public static Vector3D createVectorDeg(double degreesXY, double degreesXZ, double magnitude){
    //     return createVectorRad(Math.toRadians(degreesXY),Math.toRadians(degreesXZ),magnitude);
    // }
}
