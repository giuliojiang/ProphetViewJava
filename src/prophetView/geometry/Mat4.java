package prophetView.geometry;

/**
 * @author Giulio Jiang
 * 
 * 
 *         4x4 Matrix class data[i][j] where i is the i is the y axys and j is
 *         the x axys
 *
 */
public class Mat4
{

    // ==========================================================
    // TODO
    //
    // - add generators for scale and rotation
    //   and translation matrices
    // ==========================================================
    
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
                    System.out.println("y size " + data.length);
                    System.out.println("x size " + data[0].length);
                    return;
                }
                this.data = data;
                return;
            }
        }
        System.out.println("data size incompatible. Data not loaded");
        System.out.println("y size " + data.length);
        System.out.println("x size " + data[0].length);
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
    
    /**
     * @return the data array used for the numbers
     */
    public double[][] getData()
    {
        return data;
    }
    
    public Mat4 transpose()
    {
        Mat4 out = new Mat4();
        double[][] transpMat = new double[4][4];
        
        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                transpMat[i][j] = this.data[j][i];
            }
        }
        out.load(transpMat);
        return out;
    }

    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                out.append(data[i][j] + "\t");
            }
            out.append("\n");
        }
        return out.toString();
    }

    /**
     * equals two matrices allowing 1% relative error
     */
    public boolean equalsEpsilon(Mat4 b)
    {
        Mat4 a = this;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (!NumUtils.equalsRelativeEpsilon(a.data[i][j], b.data[i][j]))
                {
                    return false;
                }
            }
        }
        return true;
    }

    // ============================================
    // TESTS

    public static void test()
    {
        Mat4 m = new Mat4();
        if (NumUtils.VERBOSE_TEST)
        {
            System.out.println(m);
        }

        double[][] newData = new double[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 },
                { 9, 0, 1, 2 }, { 3, 4, 5, 6 } };

        // loading from array
        m.load(newData);
        if (NumUtils.VERBOSE_TEST)
        {
            System.out.println(m);
        }
        assert(m.get(0, 0) == 1);
        assert(m.get(3, 3) == 6);

        // m2 for multiplication test
        Mat4 m2 = new Mat4();
        m2.load(new double[][] { { 1, 2, 1, 5 }, { 1, 2, 1, 9 },
                { 3, 2, 3, 3 }, { -1, -2, 0, -3 } });
        if (NumUtils.VERBOSE_TEST)
        {
            System.out.println(m.mul(m2));

            System.out.println(m.equalsEpsilon(m));
            System.out.println(m.equalsEpsilon(m2));
        }

        Mat4 expected = new Mat4();
        expected.load(new double[][] { { 8, 4, 12, 20 }, { 24, 20, 32, 76 },
                { 10, 16, 12, 42 }, { 16, 12, 22, 48 } });
        assert ((m.mul(m2)).equalsEpsilon(expected));

//        assert false : "Tests finished";
//        System.out.println("Enable assertions!");
    }

}
