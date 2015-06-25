package prophetView.geometry;

public class Mat4
{
    
    private double[][] data;
    
    public Mat4()
    {
        data = new double[4][4];
    }
    
    public double get(int x, int y)
    {
        return data[x][y];
    }
    
    

}
