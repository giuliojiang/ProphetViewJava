package prophetView.integrator.directLighting;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Set;

import prophetView.geometry.Vec3;
import prophetView.geometry.meshes.Sphere;
import prophetView.integrator.Integrator;

public class DirectLightingSpheres implements Integrator
{

    public static final int MAX_RAY_DEPTH = 5;
    public static final double INFINITY = 1e8;
    public static final double M_PI = 3.141592653589793;
    private BufferedImage image;

    public DirectLightingSpheres(int width, int height)
    {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void render()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Image getImage()
    {
        // TODO Auto-generated method stub
        return null;
    }

    private Vec3 trace(Vec3 rayorig, Vec3 raydir, Set<Sphere> spheres, int depth)
    {
        double tnear = INFINITY;
        Sphere sphere = null;

        // test intersection with spheres
        for (Sphere s : spheres)
        {
            Sphere.IntersectResult intres = new Sphere.IntersectResult(INFINITY, INFINITY);
            if (s.intersect(rayorig, raydir, intres))
            {
                if (intres.t0 < 0)
                {
                    intres.t0 = intres.t1;
                }
                if (intres.t0 < tnear)
                {
                    tnear = intres.t0;
                    sphere = s;
                }
            }

        }
        
        // TODO
    }
}
