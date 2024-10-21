package main.java.edu.Factory;

public class ConcreteRailObject implements RailwayInterface
{
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
        //System.out.println("Railway is now two-way."); LOG THIS
    }



    @Override
    public String getRailInfo() // retrieves some info needed for rail by observer pattern (town names and rail direction)
    {
        return town1 + " " + town2 + " " + twoWay;
    }
}