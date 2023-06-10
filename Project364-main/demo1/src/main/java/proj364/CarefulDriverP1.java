package proj364;

/**
 * CarDriver which accelerates always by its minimal value. His goal is to reach
 * the next driver as slow as possible and keep more than safe distance.
 *
 * @author Team6
 */
public class CarefulDriverP1 extends CarDriver implements SoftAcceleration{
CarefulDriverP1(){super.setMaxSpeed(3); }
    /**
     * {{@inheritDoc}}
     */
    @Override    
    public void makeDecision(){
        if (getDetection() < 10){
                decelerate(3);

        }
        else if (getDetection() < 30){
                decelerate(0.5);
        }
        else if (getDetection() > 30){
                softAcceleration(0.5);
        }
        else if (getDetection() > 40){
                softAcceleration(1);
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
