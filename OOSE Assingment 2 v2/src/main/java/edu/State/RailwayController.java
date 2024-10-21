package main.java.edu.state;

import main.java.edu.factory.RailwayInterface;

public class RailwayController
{
    private RailState state;
    private RailwayInterface railway;

    public RailwayController(RailwayInterface railway) // constrcutors initial state is building
    {
        this.railway = railway;
        this.state = new BuildingState();
    }

    public void startBuilding() // method displays time left in logger if called whilst building otherwise displays finished building
    {
        state.build(this);
    }

    public void setState(RailState state) // state setter used to go back to building state when making bidirectional rails
    {
        this.state = state;
    }

    public boolean isBuilt() // checks if rail is built used mostly in observer pattern
    {
        return state.isBuilt();
    }

    public void updateTwoWay() // goes back into building state when rail is upgraded
    {
        this.setState(new BuildingState());
    }

    public RailwayInterface getRailway() // Provide access to the railway managed by this controller
    {
        return railway;
    }
}