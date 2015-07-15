package prophetView.geometry.meshes;

import prophetView.geometry.Vec3;
import prophetView.utils.Tuple;

public class Sphere
{

    private Vec3 center;
    private Vec3 emissionColor;
    private double radius;
    private double reflection;
    private Vec3 surfaceColor;
    private double transparency;

    public Sphere(Vec3 center, double radius, Vec3 surfaceColor,
            Vec3 emissionColor, double transparency, double reflection)
    {
        super();
        this.center = center;
        this.radius = radius;
        this.surfaceColor = surfaceColor;
        this.emissionColor = emissionColor;
        this.transparency = transparency;
        this.reflection = reflection;
    }

    public Vec3 getCenter()
    {
        return center;
    }

    public double getReflection()
    {
        return reflection;
    }

    public Vec3 getSurfaceColor()
    {
        return surfaceColor;
    }

    public double getTransparency()
    {
        return transparency;
    }
    
    public Vec3 getEmission()
    {
        return emissionColor;
    }

    public boolean intersect(Vec3 rayorigin, Vec3 raydir,
            Tuple<Double, Double> t)
    {
        Vec3 l = center.minus(rayorigin);
        double tca = l.dot(raydir);
        if (tca < 0)
        {
            return false;
        }
        double d2 = l.dot(l) - tca * tca;
        if (d2 > radius * radius)
        {
            return false;
        }
        double thc = Math.sqrt(radius * radius - d2);
        t.a = tca - thc;
        t.b = tca + thc;

        return true;
    }

}
