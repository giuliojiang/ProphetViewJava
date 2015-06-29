package prophetView.geometry;

public class Vec3
{

    private double x;
    private double y;
    private double z;

    public Vec3()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3(double u)
    {
        x = u;
        y = u;
        z = u;
    }

    public Vec3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public double length()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void setValues(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void divideByScalar(double d)
    {
        x /= d;
        y /= d;
        z /= d;
    }

    public Vec3 getNormalized()
    {
        double len = dot(this);
        if (len <= 0)
        {
            return this;
        }
        double invLen = 1 / Math.sqrt(len);
        return new Vec3(x * invLen, y * invLen, z * invLen);
    }

    /**
     * vector dot product
     */
    public double dot(Vec3 other)
    {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * vector cross product
     */
    public Vec3 cross(Vec3 b)
    {
        Vec3 a = this;

        return new Vec3(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y
                - a.y * b.x);
    }

    /**
     * vector-vector addition
     */
    public Vec3 plus(Vec3 b)
    {
        Vec3 a = this;
        return new Vec3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public Vec3 minus(Vec3 b)
    {
        Vec3 a = this;
        return new Vec3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    /**
     * Vector by scalar multiplication
     */
    public Vec3 times(double r)
    {
        return new Vec3(x * r, y * r, z * r);
    }

    /**
     * @return the corresponding homogeneous point
     */
    public Vec4 toVec4()
    {
        return new Vec4(x, y, z, 1);
    }

    /**
     * Multiplies the POINT by a Mat4
     * 
     * @param matrix
     * @return a new result vector (already normalized)
     */
    public Vec3 mulVecMatrix(Mat4 matrix)
    {
        double[][] m = matrix.getData();
        Vec3 dst = new Vec3();
        Vec3 src = this;

        dst.x = src.x * m[0][0] + src.y * m[1][0] + src.z * m[2][0] + m[3][0];
        dst.y = src.x * m[0][1] + src.y * m[1][1] + src.z * m[2][1] + m[3][1];
        dst.z = src.x * m[0][2] + src.y * m[1][2] + src.z * m[2][2] + m[3][2];
        double w = src.x * m[0][3] + src.y * m[1][3] + src.z * m[2][3]
                + m[3][3];
        if (w != 1 && w != 0)
        {
            dst.x = x / w;
            dst.y = y / w;
            dst.z = z / w;
        }
        return dst;
    }

    /**
     * VECTOR-matrix multiplication. No translations applied
     * 
     * @param matrix
     * @return
     */
    public Vec3 mulDirMatrix(Mat4 matrix)
    {
        double[][] m = matrix.getData();
        Vec3 dst = new Vec3();
        Vec3 src = this;

        dst.x = src.x * m[0][0] + src.y * m[1][0] + src.z * m[2][0] + m[3][0];
        dst.y = src.x * m[0][1] + src.y * m[1][1] + src.z * m[2][1] + m[3][1];
        dst.z = src.x * m[0][2] + src.y * m[1][2] + src.z * m[2][2] + m[3][2];

        return dst;
    }

    @Override
    public String toString()
    {
        return "x=" + x + ",y=" + y + ",z=" + z;
    }

    // ############################################
    // TEST

    public static void test()
    {
        if (NumUtils.VERBOSE_TEST)
        {
            System.out.println("Vec3 tests");
        }

        Vec3 v1 = new Vec3(1.1, 2.9, 3.65);

        if (NumUtils.VERBOSE_TEST)
        {
            System.out.println("x=" + v1.getX());
            System.out.println("y=" + v1.getY());
            System.out.println("z=" + v1.getZ());
            System.out.println("length=" + v1.length());
        }
        assert (NumUtils.equalsRelativeEpsilon(v1.getX(), 1.1));
        assert (NumUtils.equalsRelativeEpsilon(v1.getY(), 2.9));
        assert (NumUtils.equalsRelativeEpsilon(v1.getZ(), 3.65));
        assert (NumUtils.equalsRelativeEpsilon(v1.length(), 4.7898329));

        if (NumUtils.VERBOSE_TEST)
        {
            System.out.println("normalized:" + v1.getNormalized());
        }
        assert (NumUtils.equalsRelativeEpsilon(v1.getNormalized().getX(),
                0.229653101));

    }

}
