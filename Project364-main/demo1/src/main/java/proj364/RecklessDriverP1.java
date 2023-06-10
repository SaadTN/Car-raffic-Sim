package proj364;

/**
 * CarDriver which accelerates always by its maximal value. His goal is to reach
 * the next driver as fast as possible and keep less than tha safe distance.
 *
 * @author Team6
 */
public class RecklessDriverP1 extends CarDriver implements HardStop {

        public RecklessDriverP1(){
                super.setMaxSpeed(7);
        }
    /**
     * {{@inheritDoc}}
     */
    @Override
    public void makeDecision(){
        if (getDetection() < 10){
                hardStop();

        } else if (getDetection()< 15) {
            decelerate(1.5);

        } else if (getDetection() < 18) {
            decelerate(1);

        } else if (getDetection() < 20){
                decelerate(0.7);

        }
        else if (getDetection() > 20){
            accelerate(1);
        }
        else if (getDetection() > 25){
                accelerate(3);
        }

    }
    /**
     * {{@inheritDoc}}
     */
    public String getClassName(){

        return "Reckless Driver";
    }
    /**
     * {{@inheritDoc}}
     */
    @Override
    public void hardStop() {
        setVelocity(0);
    }
    
}
