package main.java.edu.observer;

import java.util.ArrayList;
import java.util.List;
import main.java.edu.factory.TownInterface;
import main.java.edu.factory.RailwayInterface;

public class Subject // subject class for observer pattern notifies all observers
{
    private List<Observer> observers;
    private List<TownInterface> towns;
    private List<RailwayInterface> railways;

    // constructor
    public Subject()
    {
        this.observers = new ArrayList<>();
        this.towns = new ArrayList<>();
        this.railways = new ArrayList<>();
    }

    // adds observer
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    // recieve updated data before notifying observers
    public void receiveUpdate(List<TownInterface> towns, List<RailwayInterface> railways)
    {
        this.towns.clear(); // purge old data
        this.railways.clear();

        this.railways.addAll(railways); // replace with new data
        this.towns.addAll(towns);
        notifyObservers();
    }

    // update all observers
    private void notifyObservers()
    {
        for(Observer obs : observers)
        {
            obs.update(towns, railways);
        }
    }
}