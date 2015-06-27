package prophetView.geometry;

/**
 * @author Giulio Jiang
 * 
 *         represents an angle. It can manipulate both radiants and degrees.
 *
 */
public class Angle
{

    private double radAngle;

    /**
     * @param data
     *            the angle to be stored
     * @param type
     *            of the angle you are giving.
     */
    public Angle(double data, AngleType type)
    {
        setAngle(data, type);
    }

    /**
     * sets the angle to data
     * 
     * @param data
     *            the angle you want to store
     * @param type
     *            the AngleType type of angle you are storing
     */
    public void setAngle(double data, AngleType type)
    {
        if (type == AngleType.DEGREES)
        {
            radAngle = Math.toRadians(data);
        } else if (type == AngleType.RADIANTS)
        {
            radAngle = data;
        } else
        {
            System.out.println("Impossible! in constructor of Angle " + data
                    + " " + type.toString());
            radAngle = data;
        }
    }

    /**
     * @return the angle in radiants format
     */
    public double getRad()
    {
        return radAngle;
    }

    /**
     * @return the angle in degrees format
     */
    public double getDeg()
    {
        return Math.toDegrees(radAngle);
    }

    /**
     * adds two angles and returns a new Angle result
     * 
     * @param b
     *            the second addend
     * @return a new angle with the result
     */
    public Angle add(Angle b)
    {
        return new Angle(this.radAngle + b.radAngle, AngleType.RADIANTS);
    }

}
