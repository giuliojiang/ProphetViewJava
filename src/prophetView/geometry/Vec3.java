package prophetView.geometry;

public class Vec3<T extends Number>
{

    private T x;
    private T y;
    private T z;

    public Vec3(T u)
    {
        x = u;
        y = u;
        z = u;
    }

    public Vec3(T x, T y, T z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public T getX()
    {
        return x;
    }

    public void setX(T x)
    {
        this.x = x;
    }

    public T getY()
    {
        return y;
    }

    public void setY(T y)
    {
        this.y = y;
    }

    public T getZ()
    {
        return z;
    }

    public void setZ(T z)
    {
        this.z = z;
    }

    // ############################################
    // TEST

    public static void main(String[] args)
    {
        System.out.println("Vec3 tests");

        Vec3<Double> v1 = new Vec3<Double>(new Double(1.1), new Double(2.9),
                new Double(3.5));

        System.out.println("x=" + v1.getX());
        System.out.println("y=" + v1.getY());
        System.out.println("z=" + v1.getZ());
    }

}
