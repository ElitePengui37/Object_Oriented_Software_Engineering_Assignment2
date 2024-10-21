package main.java.edu.state;

import java.util.logging.Logger;

public class BuildingState implements RailState
{
    private static final Logger log = Logger.getLogger(BuildingState.class.getName()); // imports logger
    private Integer daysLeft = 5; // the number of days left until railway is BUILT


    @Override
    public void build(RailwayController controller)
    {
        if(daysLeft > 0)
        {
            String logString;
            daysLeft--; // decrement days left every time build is called in the object factory

            logString =  "\n\n\nDEBUG   Railways being built days remaining: " + daysLeft;
            log.info(logString);
        }
        else if(daysLeft <= 0)
        {
            controller.setState(new BuiltState()); // if build is called transition state to built if days have expired
            log.info("\n\n\nDEBUG      Railway state transition to BUILT");
        }
    }


    @Override
    public boolean isBuilt()
    {
        return false;
    }
}