package main.java.edu.State;

public class BuiltState implements RailState
{
    @Override
    public void build(RailwayController controller)
    {
        System.out.println("Already built build callded");
    }


    @Override
    public boolean isBuilt()
    {
        return true;
    }
}