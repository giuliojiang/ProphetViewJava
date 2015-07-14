package prophetView.utils;

public class ColorUtils
{

    public static double mix(double a, double b, double mix)
    {
        return b * mix + a * (1 - mix);
    }
    
}
