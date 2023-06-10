package proj364;
/**
 * The {@code Coordinate} class defines a point representing a location
 * in {@code (x,y)} coordinate space.
 *
 * @author	Team6
 */
public class Coordinate {
	/**
	 * The X coordinate of this {@code Coordinate}.
	 */
	private double x;
	/**
	 * The Y coordinate of this {@code Coordinate}.
	 */
	private double y;
	/**
	 * The circleRadius of {@code Coordinate}.
	 */
	final static double circleRadius = 300;

	/**
	 * Constructor that sets initial values for x and y
	 */
	public Coordinate() {
		x = 100;
		y =0;

		}

	/**
	 * Converts Coordinate placement from x,y points to angle in the circle
	 * @return angle
	 */
	public double getAngle(){
		double angle = Math.toDegrees(Math.atan((y/x)));
		if(x>0 && y>0){

		} else if (x<0 && y>0){
			angle += 180;
		} else if (x<0 && y<0) {
			angle = 180 +angle;
		} else if (x>0 && y<0) {
			angle = 360 + angle;
		}
		return angle;
	}

	/**
	 * Getter
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Setter
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 *  Getter
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/** Setter
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}


	/**
	 *  Getter
	 * @return circleRadius
	 */
	public double getCircleRadius() {
		return circleRadius;
	}

}
