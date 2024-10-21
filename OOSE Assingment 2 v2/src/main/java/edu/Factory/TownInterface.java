package main.java.edu.Factory;

public interface TownInterface
{
    void setUpdatePopulation(Integer population); // population setter

    String getName(); // getter later used by observer pattern to get town name
    Integer getPopulation(); // getter for observer pattern


    Integer getStockpile(); // returns resources in stockpile to observer objects
    void setStockpile(); // called each day for all town in factory to produce new reources

    void reduceStockpile(Integer reduction);    
    void resetGoodsTransported(); // resets gt to 0 at the end of each day

    void addGoodsTransported(Integer goods);

    Integer getGoodsTransported();
}