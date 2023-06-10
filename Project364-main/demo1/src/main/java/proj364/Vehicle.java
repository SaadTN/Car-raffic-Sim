package proj364;

/**
 * Vehicle which is used by CarDrivers.
 *
 * @author Team6
 */
public class Vehicle {
    /**
     * Minimal acceleration  [m/s^2]
     */
    private double		minAcceleration;
    /**
     * Maximum acceleration  [m/s^^]
     */
    private double		maxAcceleration;
    /**
     * Minimal velocity  [m/s]=0
     */
    private double		minVelocity;
    /**
     * Maximum velocity  [m/s]
     */
    private double		maxVelocity;
    /**
     * Vehicle length in [m]
     */
    private double		length;
    /**
     * Vehicle Width in [m]
     */
    private double		width= 1.7;


    /**
     * Vehicle coordinate (x,y)
     */
    private Coordinate vehiclCoordinate;

    /**
     * No param Constructor that sets initial values
     */
    public Vehicle() {
        this(0,4,0,30,50,new Coordinate());
    }

    /**
     * @param minAcceleration
     * @param maxAcceleration
     * @param minVelocity
     * @param maxVelocity
     * @param length
     * @param vehiclCoordinate
     */
    public Vehicle( double minAcceleration,  double maxAcceleration,
                    double minVelocity,  double maxVelocity,  double length,
                    Coordinate vehiclCoordinate) {
        vehiclCoordinate = new Coordinate();
        if (minVelocity < 0 || maxVelocity < 0 || length < 0) {
            throw new IllegalArgumentException("below zero");
        }
        if (minAcceleration > maxAcceleration || minVelocity > maxVelocity) {
            throw new IllegalArgumentException("min > max");
        }
        this.minAcceleration	= minAcceleration;
        this.maxAcceleration	= maxAcceleration;
        this.minVelocity		= minVelocity;
        this.maxVelocity		= maxVelocity;
        this.length				= length;

        this.vehiclCoordinate	= vehiclCoordinate;
    }



    public Coordinate getVehicleCoordinate() {
        return vehiclCoordinate;
    }

    /**
     * Getter
     * @return minAcceleration
     */
    public double getMinAcceleration() {
        return minAcceleration;
    }

    /**
     * Getter
     * @return maxAcceleration
     */
    public double getMaxAcceleration() {
        return maxAcceleration;
    }

    /**
     * Getter
     * @return minVelocity
     */
    public double getMinVelocity() {
        return minVelocity;
    }

    /**
     * Getter
     * @return maxVelocity
     */
    public double getMaxVelocity() {
        return maxVelocity;
    }

    /**
     * Getter
     * @return length
     */
    public double getLength() {
        return length;
    }

    /**
     * Getter
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Setter
     * @param newX
     * @param newY
     */
    public void setVehicleCoordinate(double newX, double newY) {
        vehiclCoordinate.setX(newX);
        vehiclCoordinate.setY(newY);


    }



}