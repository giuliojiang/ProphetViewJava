package prophetView.testSuite;

import prophetView.geometry.Mat4;
import prophetView.geometry.NumUtils;
import prophetView.geometry.Vec3;

public class RunTests
{

    private static boolean VERBOSE = false;
    
    public static void main(String[] args)
    {
        NumUtils.VERBOSE_TEST = VERBOSE;
        
        System.out.print("Running Vec3 tests... ");
        Vec3.test();
        System.out.println("OK");
        
        System.out.print("Running Mat4 tests... ");
        Mat4.test();
        System.out.println("OK");
        
        assert false: "TESTS COMPLETED";
        System.out.println("Enable assertions!");
    }
    
}
