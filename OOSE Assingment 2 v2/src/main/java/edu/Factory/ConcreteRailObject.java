package main.java.edu.Factory;

public class ConcreteRailObject implements RailwayInterface
{
    private boolean twoWay;
    private String town1;
    private String town2;

    public ConcreteRailObject(String town1, String town2)
    {
        this.town1 = town1;
        this.town2 = town2;
        this.twoWay = false;  // Default is one-way
    }

    @Override
    public void setRailwayDuplication()
    {
        this.twoWay = true;  // Directly set to true
        System.out.println("Railway is now two-way.");
    }



    @Override // retrieves all info needed for rail by observer pattern
    public String getRailInfo()
    {
        return town1 + " " + town2 + " " + twoWay;
    }
}