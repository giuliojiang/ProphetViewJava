package prophetView.geometry;

public class VecSpherical
{

    private double theta;
    private double phi;

    public VecSpherical(double theta, double phi)
    {
        this.theta = theta;
        this.phi = phi;
    }

    public double getTheta()
    {
        return theta;
    }

    public void setTheta(double theta)
    {
        this.theta = theta;
    }

    public double getPhi()
    {
        return phi;
    }

    public void setPhi(double phi)
    {
        this.phi = phi;
    }

    /**
     * @return a Y-up Vec3
     */
    public Vec3 toVec3()
    {
        return new Vec3(Math.cos(phi) * Math.sin(theta), Math.sin(phi)
                * Math.sin(theta), Math.cos(theta));
    }

    public double sinTheta()
    {
        return Math.sin(theta);
    }

    public double cosTheta()
    {
        return Math.cos(theta);
    }

    public double sinPhi()
    {
        return Math.sin(phi);
    }

    public double cosPhi()
    {
        return Math.cos(phi);
    }

}
