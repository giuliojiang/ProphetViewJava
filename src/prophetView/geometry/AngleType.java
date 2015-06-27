package prophetView.geometry;

/**
 * @author Giulio Jiang
 *
 */
public enum AngleType
{

    DEGREES, RADIANTS;
    
    @Override
    public String toString()
    {
        if (this == AngleType.DEGREES)
        {
            return "DEGREES";
        }
        
        if (this == AngleType.RADIANTS)
        {
            return "RADIANTS";
        }
        
        return "unrecognized AngleType";
    }
}
