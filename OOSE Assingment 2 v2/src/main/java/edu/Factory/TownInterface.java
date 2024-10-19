package main.java.edu.Factory;

public interface TownInterface
{
    void setUpdatePopulation(Integer population); // population setter

    String getName(); // getter later used by observer pattern to get town name
    Integer getPopulation(); // getter for observer pattern
}