package proj364;

/**
 * CarDriver which accelerates always by its maximal value. His goal is to reach
 * the next driver as fast as possible with respect to the speed limit
 * and keep less than the safe distance.
 *
 * @author Team6
 */
public class RecklessDriverP2 extends CarDriver implements HardStopP2 {

        public RecklessDriverP2(){
                super.setMaxSpeed(5);
        }
    /**
     * {{@inheritDoc}}
     */
    @Override
    public void makeDecision(){
        if (getDetection() <= 10){
            hardStop(4.28);
            }

        else if (getDetection() <= 5){
            hardStop(10);
            }

        else if (getDetection() > 20){
            accelerate(5);
            }
        else if (getDetection() > 12){
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
    public void hardStop(double val) {

    setVelocity(getVelocity() - (val * 1.4));
    if (getVelocity() < 0) {
            setVelocity(0);
        }
    }
    
}
