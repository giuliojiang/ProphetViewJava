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
        return Math.sqrt(x*x + y*y + z*z);
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
        return new Vec3(x*invLen, y*invLen, z*invLen);
    }
    
    public double dot(Vec3 other)
    {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
    
    public Vec3 cross(Vec3 b)
    {
        Vec3 a = this;
        
        return new Vec3(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x);
    }
    
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
    
    public Vec3 times(double r)
    {
        return new Vec3(x*r, y*r, z*r);
    }
    
    @Override
    public String toString()
    {
        return "x=" + x + ",y=" + y + ",z=" + z;
    }

    // ############################################
    // TEST

    public static void main(String[] args)
    {
        System.out.println("Vec3 tests");

        Vec3 v1 = new Vec3(1.1,2.9,3.65);

        System.out.println("x=" + v1.getX());
        System.out.println("y=" + v1.getY());
        System.out.println("z=" + v1.getZ());
        
        System.out.println("length=" + v1.length());
        
        System.out.println("normalized:" + v1.getNormalized());
    }

}
