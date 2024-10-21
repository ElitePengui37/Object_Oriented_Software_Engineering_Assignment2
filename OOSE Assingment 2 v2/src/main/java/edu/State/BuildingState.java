package main.java.edu.State;

public class BuildingState implements RailState
{
    private Integer daysLeft = 5; // the number of days left until railway is BUILT


    @Override
    public void build(RailwayController controller)
    {
        if(daysLeft > 0)
        {
            daysLeft--; // decrement days left every time build is called in the object factory
            //System.out.println("DEBUG   Railways being built days remaining: " + daysLeft);  LOG THIS
        }
        else if(daysLeft <= 0)
        {
            controller.setState(new BuiltState()); // if build is called transition state to built if days have expired
            //System.out.println("DEBUG      Railway state transition to BUILT");   LOG THIS
        }
    }


    @Override
    public boolean isBuilt()
    {
        return false;
    }
}