package proj364;


/** CarDriver which accelerates always by its minimal value. His goal is to reach
* the next driver as slow as possible with respect to the speed limit
 * and keep more than safe distance.
* @author Team6
*/
public class CarefulDriverP2 extends CarDriver implements SoftAcceleration{
CarefulDriverP2(){super.setMaxSpeed(4.3); }


    /**
     * {{@inheritDoc}}
     */
    @Override
    public void makeDecision(){
        if (getDetection() < 7){
                decelerate(3);
        }
        else if (getDetection() < 5){
                decelerate(7);
        }
        else if (getDetection() > 20){
                softAcceleration(6.25);
        }
        else if (getDetection() > 12){
                softAcceleration(3.75);
        }
    }

    /**
     * {{@inheritDoc}}
     */
    public String getClassName(){

        return "Careful Driver";
    }
    /**
     * {{@inheritDoc}}
     */
    @Override
    public void softAcceleration(double val) {

        setVelocity(getVelocity() + (val*0.8));
    }
}
