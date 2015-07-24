package prophetView.integrator.directLighting;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import prophetView.geometry.Vec3;
import prophetView.geometry.meshes.Sphere;
import prophetView.integrator.Integrator;
import prophetView.utils.Tuple;

public class DirectLightingSpheres implements Integrator {

	public static final int MAX_RAY_DEPTH = 5;
	public static final double INFINITY = 1e8;
	public static final double M_PI = 3.141592653589793;
	private BufferedImage image;
	private int height;
	private int width;
	// TODO change the temporary spheres to a proper Scene object
	private List<Sphere> spheres;

	public DirectLightingSpheres(int width, int height, List<Sphere> spheres) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.width = width;
		this.height = height;
		this.spheres = spheres;
	}

	@Override
	public void render() {
		double invWidth = 1 / width;
		double invHeight = 1 / height;

		double fov = 30;
		double aspectRatio = width * invHeight;
		double angle = Math.tan(M_PI * 0.5 * fov / 180.0);

		// trace rays
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double xx = (2 * ((x + 0.5) * invWidth) - 1) * angle * aspectRatio;
				double yy = (1 - 2 * ((y + 0.5) * invHeight)) * angle;
				Vec3 rayDir = new Vec3(xx, yy, -1);
				rayDir = rayDir.getNormalized();
			    
				System.out.print("Tracing ray " + x + " " + y);
				
				Vec3 pixel = trace(new Vec3(0), rayDir, spheres, 0);
				System.out.println(" pixel " + pixel);
				int r = (int) pixel.getX();
				int g = (int) pixel.getY();
				int b = (int) pixel.getZ();
				image.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | (b));

			}
		}

		File output = new File("output.png");
		try {
			ImageIO.write(image, "png", output);
		} catch (IOException e) {
			System.out.println("Error when writing image to disk");
			e.printStackTrace();
		}

	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	private Vec3 trace(Vec3 rayorig, Vec3 raydir, List<Sphere> spheres, int depth) {
		double tnear = INFINITY;
		Sphere sphere = null;

		// test intersection with spheres
		for (Sphere s : spheres) {
			Tuple<Double, Double> intres = new Tuple<Double, Double>(INFINITY, INFINITY);
			if (s.intersect(rayorig, raydir, intres)) {
				if (intres.a < 0) {
					intres.a = intres.b;
				}
				if (intres.a < tnear) {
					tnear = intres.a;
					sphere = s;
				}
			}
		}

		// case when there is no intersection
		if (sphere == null) {
			return new Vec3(2);
		}

		// color of the object intersected by the ray
		Vec3 surfaceColor = new Vec3(0);

		// point of intersection
		Vec3 phit = rayorig.plus(raydir).times(tnear);

		// normal at intersection
		Vec3 nhit = phit.minus(sphere.getCenter());

		// normalize normal
		nhit = nhit.getNormalized();

		// flip normal if necessary
		double bias = 1e-4;
		boolean inside = false;
		if (raydir.dot(nhit) > 0) {
			nhit = nhit.times(-1);
			inside = true;
		}

		// recursive reflection algorithm
		if ((sphere.getTransparency() > 0 || sphere.getReflection() > 0) && depth < MAX_RAY_DEPTH) {
			double facingratio = -raydir.dot(nhit);
			double fresneleffect = prophetView.utils.ColorUtils.mix(Math.pow(1 - facingratio, 3), 1, 0.1);

			// compute reflection direction
			Vec3 refldir = raydir.minus(nhit.times(2).times(raydir.dot(nhit)));
			refldir = refldir.getNormalized();
			Vec3 reflection = trace(phit.plus(nhit.times(bias)), refldir, spheres, depth + 1);
			Vec3 refraction = new Vec3(0);

			// if sphere is transparent compute transmission ray
			if (sphere.getTransparency() > 0) {
				double ior = 1.5;
				double eta = (inside) ? ior : 1 / ior;
				double cosi = -nhit.dot(raydir);
				double k = 1 - eta * eta * (1 - cosi * cosi);
				Vec3 refrdir = raydir.times(eta).plus(nhit.times(eta * cosi - Math.sqrt(k)));
				refrdir = refrdir.getNormalized();
				refraction = trace(phit.minus(nhit.times(bias)), refrdir, spheres, depth + 1);
			}

			// get mix
			surfaceColor = ((reflection.times(fresneleffect))
					.plus(refraction.times(1 - fresneleffect).times(sphere.getTransparency())))
							.scalarTimes(sphere.getSurfaceColor());
		} else { // it's diffuse. Shooting shadow ray
			for (Sphere s : spheres) {
				if (s.getEmission().length() > 0) { // it is a light
					Vec3 transmission = new Vec3(1);
					Vec3 lightDirection = s.getCenter().minus(phit);
					lightDirection = lightDirection.getNormalized();
					for (Sphere j : spheres) {
						if (s != j) {
							Tuple<Double, Double> intres2 = new Tuple<Double, Double>(0.0, 0.0);
							if (j.intersect(phit.plus(nhit.times(bias)), lightDirection, intres2)) {
								transmission = new Vec3(1);
								break;
							}
						}
					}
					surfaceColor = surfaceColor.plus(sphere.getSurfaceColor().scalarTimes(transmission)
							.times(Math.max(0.0, nhit.dot(lightDirection))).scalarTimes(s.getEmission()));
				}
			}
		}

		return surfaceColor.plus(sphere.getEmission());
	}
}
