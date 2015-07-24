package prophetView.testSuite;

import java.util.ArrayList;
import java.util.List;

import prophetView.geometry.Vec3;
import prophetView.geometry.meshes.Sphere;
import prophetView.integrator.directLighting.DirectLightingSpheres;

public class ScratchapixelSpheresTest {
	
	public static void main(String[] args)
	{
		List<Sphere> spheres = new ArrayList<Sphere>();
		
		
//		 Vec3 center, 
//		double radius, 
//		Vec3 surfaceColor,
//        Vec3 emissionColor, 
//        double transparency, 
//        double reflection
		spheres.add(spheres.size(), new Sphere(
				new Vec3(0.0, -10004, -20),
				10000,
				new Vec3(0.20,0.20,0.20),
				new Vec3(0.0),
				0,
				0
				));
		spheres.add(spheres.size(), new Sphere(
				new Vec3(0.0,0,-20),
				4,
				new Vec3(1,0.32,0.36),
				new Vec3(0.0),
				0.5,
				1
				));
		spheres.add(spheres.size(), new Sphere(
				new Vec3(5,-1,-15),
				2,
				new Vec3(0.9,0.76,0.46),
				new Vec3(0),
				0,
				1
				));
		spheres.add(spheres.size(), new Sphere(
				new Vec3(5,0,-25),
				3,
				new Vec3(0.65,0.77,0.97),
				new Vec3(0),
				0,
				1
				));
		spheres.add(spheres.size(), new Sphere(
				new Vec3(-5.5,0,-15),
				3,
				new Vec3(0.9,0.9,0.9),
				new Vec3(0),
				0,
				1
				));
		spheres.add(spheres.size(), new Sphere(
				new Vec3(0,20,-30),
				3,
				new Vec3(0,0,0),
				new Vec3(300),
				0,
				0
				));
		
		DirectLightingSpheres integrator = new DirectLightingSpheres(800,600,spheres);
		integrator.render();
	}

}
