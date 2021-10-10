package model;

import java.io.Serializable;
import java.nio.file.Path;

public abstract class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private float price;
    private boolean consumable;
    private int uses;
    private Path path;

    public abstract void use();

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", consumable=" + consumable +
                ", uses=" + uses +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public Item(String name, String description, float price, boolean consumable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.consumable = consumable;
        uses = -1;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        consumable = false;
        uses = -1;
    }

    public Item() {
        name = "";
        description = "";
        consumable = false;
        uses = -1;
    }
}
