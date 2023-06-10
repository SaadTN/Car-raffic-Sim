package proj364;

/**
 * Abstract Car Driver which are driving the Vehicle objects in the simulation .
 *
 * @author Team6
 */
public abstract class CarDriver {
    /**
     *  Velocity of this {@code CarDriver}
     */
    private double velocity;
    /**
    * Vehicle of this {@code CarDriver}
    */
    private Vehicle vehicle;
    /**
     * the distance to the nearest  {@code CarDriver}
     */
    private double detection;
    /**
     * ID of the {@code CarDriver}
     */
    private int id;
    /**
     * counter of {@code CarDriver}s
     */
    private static int idCounter;
    /**
     * Maximum speed of the {@code CarDriver}
     */
    private double maxSpeed;
    /**
     * stopping time of the {@code CarDriver}
     */
    private double stoppingTime;


    /** getter method
     * @return stoppingTime
     */
    public double getStoppingTime() {
        return stoppingTime;
    }
    /** setter method
     * @param stoppingTime
     */
    public void setStoppingTime(double stoppingTime) {
        this.stoppingTime += stoppingTime;
    }
    /** getter method
     * @return maxSpeed
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }
    /** setter method
     * @param maxSpeed
     */

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public CarDriver() {
        velocity = 0;
        idCounter += 1;
        this.id = idCounter;
        vehicle = new Vehicle();
    }

    /**
     * moves the {@code CarDriver}
     */
    public void Move(){
        tryMove(velocity);
        makeDecision();
    }

    /** used for {@code Move Method}
     * @param velocity
     */
    private void tryMove(double velocity) {
        /**
         * to make the code clean and prevent more than one call in one line
         */
        Coordinate vehicleCoordinate=vehicle.getVehicleCoordinate();
        double radius = vehicleCoordinate.getCircleRadius();
        double ang = vehicleCoordinate.getAngle();
        double newAng = (ang + velocity);
        double newX = radius * (Math.cos(Math.toRadians(newAng)));
        double newY = radius * (Math.sin(Math.toRadians(newAng)));
        getVehicle().setVehicleCoordinate(newX, newY);
    }
    /**
     * moves the {@code CarDriver}
     * @param distance
     */
    public void Move(double distance){

        tryMove(distance);
    }

    /** accelerate the {@code Car Driver}
     * @param val
     */
    public void accelerate(double val){
        setVelocity(getVelocity() + val);;
    }
    /** decelerate the {@code Car Driver}
     * @param val
     */
    public void decelerate(double val){
        setVelocity(getVelocity()-val);
        if(velocity<0){velocity = 0;}
    }
    /** getter method
     * @return velocity
     */
    public double getVelocity(){
        return velocity;
    }
    /** getter method
     * @return vehicle
     */
    public Vehicle getVehicle(){
        return vehicle;
    }
    /** getter method
     * @return id
     */
    public int getid(){
        return id;
    }
    /** setter method
     * @param velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
        if(velocity>maxSpeed){
            this.velocity = maxSpeed;
        }

        else if(velocity < 0){
            setVelocity(0);
        }
    }
    public void setId(int id) {
        this.id = id;
    }
    /** setter method
     * @param idCounter
     */
    public static void setIdCounter(int idCounter) {
        CarDriver.idCounter = idCounter;
    }
    /** getter method
     * @return detection
     */
    public double getDetection(){
        return this.detection;
    }

    /** setter method
     * takes coordinates of the car in front of it,
     *subtracts it from it's coordinates, and results
     *the detection
     * @param nextDriver
     */
    public void setDetection(CarDriver nextDriver){
        Coordinate nextCar = nextDriver.vehicle.getVehicleCoordinate();

        double currentAng = vehicle.getVehicleCoordinate().getAngle();
        double nextAng = nextCar.getAngle();
        double ang = (nextAng - currentAng);
        if (ang < 0){
            ang += 360;
        }

        detection = ang;
    }

    /**
     * Decision-making cntrol {@code CarDriver}s behaviors
     */
    public abstract void makeDecision();

    /**
     * Getter method
     * @return Class/Driver name
     */
    public abstract String getClassName();

    /**
     * @return car XY array
     */
    public double[] getVehicleXY(){

        Coordinate carCoordinate = vehicle.getVehicleCoordinate();
        double [] carXY = {carCoordinate.getX(), carCoordinate.getY()};

        return carXY;


    }

    /**
     * @return Car Angle
     */
    public double getVehicleAngle(){
        Coordinate carCoordinate = vehicle.getVehicleCoordinate();

        return carCoordinate.getAngle();
    }



}
