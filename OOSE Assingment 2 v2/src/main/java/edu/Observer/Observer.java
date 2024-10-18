package main.java.edu.Observer;

import java.util.List;
import java.util.ArrayList;
import main.java.edu.Factory.TownInterface;
import main.java.edu.Factory.RailwayInterface;

public interface Observer
{
    void update(List<TownInterface> towns, List<RailwayInterface> railways);
}