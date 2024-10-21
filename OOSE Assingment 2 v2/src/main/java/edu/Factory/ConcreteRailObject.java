package main.java.edu.factory;
import java.util.logging.Logger;

public class ConcreteRailObject implements RailwayInterface
{
    private static final Logger log = Logger.getLogger(ConcreteRailObject.class.getName()); // imports logger
    private boolean twoWay;
    private String town1;
    private String town2;


    public ConcreteRailObject(String town1, String town2) // creates new rail object
    {
        this.town1 = town1;
        this.town2 = town2;
        this.twoWay = false;  // Default is one-way
    }

    @Override
    public void setRailwayDuplication() // makes rail bidirectional
    {
        this.twoWay = true;  // Directly set to true
        log.info("Railway is now two-way.\n");
    }



    @Override
    public String getRailInfo() // retrieves some info needed for rail by observer pattern (town names and rail direction)
    {
        return town1 + " " + town2 + " " + twoWay;
    }
}