package main.java.edu.Factory;

public class ConcreteTownObject implements TownInterface
{
    public int population;  // Public variable

    public ConcreteTownObject() {
        this.population = 0;  // Default population
    }

    @Override
    public void setUpdatePopulation(Integer population) {
        this.population = population;
        System.out.println("Population set to: " + population);
    }
}