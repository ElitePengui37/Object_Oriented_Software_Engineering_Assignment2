package main.java.edu.State;

import main.java.edu.Factory.RailwayInterface;

public class RailwayController
{
    private RailState state;
    private RailwayInterface railway;

    public RailwayController(RailwayInterface railway) // constrcutors initial state is building
    {
        this.railway = railway;
        this.state = new BuildingState();
    }

    public void startBuilding()
    {
        state.build(this);
    }

    public void setState(RailState state)
    {
        this.state = state;
    }

    public boolean isBuilt()
    {
        return state.isBuilt();
    }

    public void updateTwoWay()
    {
        this.setState(new BuildingState());
    }

    public RailwayInterface getRailway()
    {
        return railway;  // Provide access to the railway managed by this controller
    }
}