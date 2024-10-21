package main.java.edu.State;

public class BuiltState implements RailState
{
    @Override
    public void build(RailwayController controller) // do nothing if already built
    {
        // LOG HERE ALREADY BUILT
    }


    @Override
    public boolean isBuilt()
    {
        return true;
    }
}