package proj364;

/**
 * CarDriver which accelerates moderately. His goal is to reach
 * the next driver at safe with respect to the speed limit pace and keep safe distance.
 *
 * @author Team6
 */
public class RegularDriverP2 extends CarDriver implements SoftAcceleration,HardStopP2 {
        public RegularDriverP2(){
                super.setMaxSpeed(4.7);
        }
        /**
         * {{@inheritDoc}}
         */
        public void makeDecision() {
                if (getDetection() <= 7){
                        hardStop(4.28);
                }
                else if (getDetection() < 5){
                        hardStop(10);
                }
                else if (getDetection() > 20) {
                        softAcceleration(6.25);
                }
                 else if (getDetection() > 12) {
                        softAcceleration(3.75);
                }
        }

        /**
         * {{@inheritDoc}}
         */
        public String getClassName(){
                return "Regular Driver";
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

        /**
         * {{@inheritDoc}}
         */
        @Override
        public void softAcceleration(double val) {
                setVelocity(getVelocity() + (val*0.8));
        }
}


