package prophetView.geometry;

/**
 * @author Giulio Jiang
 * 
 *         4x4 Matrix class data[i][j] where i is the i is the y axys and j is
 *         the x axys
 *
 */
public class Mat4
{

    private double[][] data;

    public Mat4()
    {
        data = new double[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 },
                { 0, 0, 0, 1 } };
    }

    public double get(int i, int j)
    {
        assert (0 <= i && i < 4);
        assert (0 <= j && j < 4);
        return data[i][j];
    }

    public Mat4 mul(Mat4 rhs)
    {
        Mat4 mult = new Mat4();
        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                mult.data[i][j] = data[i][0] * rhs.data[0][j] + data[i][1]
                        * rhs.data[1][j] + data[i][2] * rhs.data[2][j]
                        + data[i][3] * rhs.data[3][j];
            }
        }

        return mult;
    }

}
