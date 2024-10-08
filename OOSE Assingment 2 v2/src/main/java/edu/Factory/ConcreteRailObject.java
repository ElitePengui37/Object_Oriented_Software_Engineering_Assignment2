package main.java.edu.Factory;

public class ConcreteRailObject implements RailwayInterface
{
    private boolean twoWay;

    public ConcreteRailObject() {
        this.twoWay = false;  // Default is one-way
    }

    @Override
    public void setRailwayDuplication() {
        this.twoWay = true;  // Directly set to true
        System.out.println("Railway is now two-way.");
    }
}