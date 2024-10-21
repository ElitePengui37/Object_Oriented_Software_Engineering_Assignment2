package main.java.edu.factory;

public interface TownInterface
{
    void setUpdatePopulation(Integer population); // population setter

    String getName();
    Integer getPopulation(); // returns town population for observer pattern
    Integer getStockpile(); // returns resources in stockpile
    void setStockpile(); // called each day for all towns in factory to produce new reources
    void reduceStockpile(Integer reduction);
    void resetGoodsTransported(); // resets gt to 0 at the end of each day
    void addGoodsTransported(Integer goods);
    Integer getGoodsTransported();
}