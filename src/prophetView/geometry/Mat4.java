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

    /**
     * Initializes a new identity matrix
     */
    public Mat4()
    {
        data = new double[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 },
                { 0, 0, 0, 1 } };
    }

    /**
     * returns the corresponding number
     * 
     * @param i
     *            the Y coordinate in the matrix
     * @param j
     *            the X coordinate in the matrix
     * @return the value
     */
    public double get(int i, int j)
    {
        assert (0 <= i && i < 4);
        assert (0 <= j && j < 4);
        return data[i][j];
    }

    /**
     * sets the corresponding number in the matrix
     * 
     * @param i
     *            the Y coordinate in the matrix
     * @param j
     *            the X coordinate in the matrix
     * @param n
     *            the number to be stored
     */
    public void set(int i, int j, double n)
    {
        assert (0 <= i && i < 4);
        assert (0 <= j && j < 4);
        this.data[i][j] = n;
    }

    /**
     * loads the matrix using a 4x4 array
     * 
     * @param data
     *            the data to be loaded
     */
    public void load(double[][] data)
    {
        if (data.length == 4)
        {
            for (int i = 0; i < 4; i++)
            {
                if (data[i].length != 4)
                {
                    System.out
                            .println("data size incompatible. Data not loaded");
                    return;
                }
                this.data = data;
            }
        }
        System.out.println("data size incompatible. Data not loaded");
        return;
    }

    /**
     * multiplies two matrices
     * 
     * @param rhs
     *            the second operand
     * @return the new result matrix
     */
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
