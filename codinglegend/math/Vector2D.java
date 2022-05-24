package codinglegend.math;

/** The vector class can be used for easy vector calculations */
public class Vector2D {
    private double x;
    private double y;

    /** Create a 2d vector using cartesan coordinates */
    public Vector2D(double x, double y){
        this.x = x; 
        this.y = y;
    }


    /** Create a 2d vector with a direction (in radians) and magnitude */
    public static Vector2D createVectorRad(double radians, double magnitude){
        return new Vector2D(magnitude*Math.cos(radians),magnitude*Math.sin(radians));
    }

    /** Creates a 2d vector width a direction (in degrees) and magnitude*/
    public static Vector2D createVectorDeg(double degrees, double magnitude){
        return createVectorRad(Math.toRadians(degrees),magnitude);
    }

    public double getX(){ return x; }
    public double getY(){ return y; }

    /** Returns the magnitude of the current vector */
    public double getMagnitude(){
        return Math.sqrt(x*x+y*y);
    }

    /** Returns the angle of the current vector in radians */
    public double getAngle(){
        return Math.atan2(y, x);
    }

    /** Returns the angle of the current vector in degrees */
    public double getAngleDeg(){
        return Math.toDegrees(Math.atan2(y, x));
    }

    /** Adds two vectors together */
    public void add(Vector2D v){
        x += v.x;
        y += v.y;
    }

    /** Returns the dot product of this and another vector */
    public double dotProduct(Vector2D v){
        return x*v.x + y*v.y;
    }

    /** Returns the vector cross product of this and another vector */
    public Vector3D crossProduct(Vector2D v){
        return new Vector3D(
            0,  //Cx = AyBz - AzBy
            0,  //Cy = AzBx - AxBz 
            x*v.y - y*v.x   //Cz = AxBy - AyBx
            );
    }
}
