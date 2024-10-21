package main.java.edu.factory;

import java.util.logging.Logger;

public class ConcreteTownObject implements TownInterface
{
    private static final Logger log = Logger.getLogger(ConcreteTownObject.class.getName()); // imports logger
    public Integer population;
    public String name;
    private Integer stockpile = 0;
    private Integer goodsTransported = 0;

    public ConcreteTownObject(String name, Integer population) // creates town object with name ond population
    {
        this.population = population;
        this.name = name;
    }

    @Override
    public void setUpdatePopulation(Integer population) // setter to update population
    {
        this.population = population;
        log.info("\nPopulation of town " + name + " set to: " + population + "\n");
    }

    @Override
    public String getName() // town name getter
    {
        return name;
    }

    @Override
    public Integer getPopulation() // town population getter
    {
        return population;
    }

    @Override
    public Integer getStockpile() // town stockpile getter
    {
        return stockpile;
    }

    @Override
    public void setStockpile() // setter to increment daily stockpile for town
    {
        stockpile += population; // produce 1 resource per 1 population per 1 day
    }

    @Override
    public void reduceStockpile(Integer reduction) // setter to reduce stockpile to simulate the movement of goods
    {
        this.stockpile -= reduction;
    }

    @Override
    public void addGoodsTransported(Integer goods) // goods transported setter used by graphics observer
    {
        goodsTransported += goods;
    }

    @Override
    public void resetGoodsTransported() // resets gt to 0 at the end of each day to prevent gt from maving into next day
    {
        this.goodsTransported = 0;
    }

    @Override
    public Integer getGoodsTransported() // getter for gt for observer pattern
    {
        return goodsTransported;
    }

}