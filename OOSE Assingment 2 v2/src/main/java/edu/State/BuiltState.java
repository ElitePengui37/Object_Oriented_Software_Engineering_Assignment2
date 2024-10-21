package main.java.edu.state;

import java.util.logging.Logger;

public class BuiltState implements RailState
{
    private static final Logger log = Logger.getLogger(BuiltState.class.getName()); // imports logger
    @Override
    public void build(RailwayController controller) // do nothing if already built
    {
        log.info("\n\n\nALREADY BUILT");
    }


    @Override
    public boolean isBuilt()
    {
        return true;
    }
}