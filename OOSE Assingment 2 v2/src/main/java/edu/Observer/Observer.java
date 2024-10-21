package main.java.edu.observer;

import java.util.List;
import java.util.ArrayList;
import main.java.edu.factory.TownInterface;
import main.java.edu.factory.RailwayInterface;

public interface Observer
{
    void update(List<TownInterface> towns, List<RailwayInterface> railways);
}