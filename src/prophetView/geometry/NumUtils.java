package prophetView.geometry;

public class NumUtils
{
    public static boolean VERBOSE_TEST = true;
    
    /**
     * returns true iff the relative difference between a and b is less than 1%
     * 
     */
    public static boolean equalsRelativeEpsilon(double a, double b)
    {
        if (Math.abs(a) < 0.01 && Math.abs(b) < 0.01)
        {
            return true;
        }
        return Math.abs((a - b) / ((a+b)/2)) < 0.01;
    }
}
