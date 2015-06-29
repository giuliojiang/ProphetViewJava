package prophetView.geometry;

/**
 * @author Giulio Jiang - Homogeneous point class
 *
 */
public class Vec4
{

    private double x;
    private double y;
    private double z;
    private double w;

    public Vec4(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 1;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getZ()
    {
        return z;
    }

    public void setZ(double z)
    {
        this.z = z;
    }

    public double getW()
    {
        return w;
    }

    public void setW(double w)
    {
        this.w = w;
    }

    public Vec3 toVec3()
    {
        return new Vec3(x / w, y / w, z / w);
    }

}
