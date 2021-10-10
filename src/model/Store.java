package model;

import java.io.Serializable;
import java.util.*;

public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Class<? extends Item>> catalogue;
    private List<Item> stock;

    public void addNewItemToCatalogue(Class<? extends Item> classOfItem) {
        catalogue.add(classOfItem);
    }

    public void addNewItemToStock(Item item) {
        stock.add(item);
    }

    public List<Class<? extends Item>> getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(List<Class<? extends Item>> catalogue) {
        this.catalogue = catalogue;
    }

    public List<Item> getStock() {
        return stock;
    }

    public void setStock(List<Item> stock) {
        this.stock = stock;
    }

    public Store() {
        catalogue = new ArrayList<>();
        stock = new ArrayList<>();
    }
}
