package proj364;

/**
 * CarDriver which accelerates moderately. His goal is to reach
 * the next driver at safe pace and keep  safe distance.
 *
 * @author Team6
 */
public class RegularDriverP1 extends CarDriver implements SoftAcceleration,HardStop {
        public RegularDriverP1(){
                super.setMaxSpeed(5);
        }
        /**
         * {{@inheritDoc}}
         */
        public void makeDecision(){
                if (getDetection() < 11){
                        hardStop();
                } else if (getDetection() < 20) {
                        decelerate(0.4);

                } else if (getDetection() < 30){
                        decelerate(0.1);
                }
                else if (getDetection() >= 30){
                	    softAcceleration(0.3);
                }
                else if (getDetection() > 70){
                        softAcceleration(0.5);
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
        public void hardStop() {
                setVelocity(0);
        }

        /**
         * {{@inheritDoc}}
         */
        @Override
        public void softAcceleration(double val) {
                setVelocity(getVelocity() + (val*0.8));
        }
}

