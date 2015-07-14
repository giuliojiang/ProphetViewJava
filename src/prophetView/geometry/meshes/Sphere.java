package prophetView.geometry.meshes;

import prophetView.geometry.Vec3;

public class Sphere
{

    private Vec3 center;
    private double radius;
    private Vec3 surfaceColor;
    private Vec3 emissionColor;
    double transparency;
    double reflection;

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
    
    public boolean intersect(Vec3 rayorigin, Vec3 raydir, Sphere.IntersectResult t)
    {
        Vec3 l = center.minus(rayorigin);
        double tca = l.dot(raydir);
        if (tca < 0)
        {
            return false;
        }
        double d2 = l.dot(l) - tca * tca;
        if (d2 > radius*radius)
        {
            return false;
        }
        double thc = Math.sqrt(radius*radius - d2);
        t.t0 = tca - thc;
        t.t1 = tca + thc;
        
        return true;
    }
    
    public class IntersectResult
    {
        public IntersectResult(double t0, double t1)
        {
            this.t0 = t0;
            this.t1 = t1;
        }

        public double t0;
        public double t1;
    }

}
