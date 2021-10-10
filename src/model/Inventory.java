package model;

import model.items.DayCounter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Item> items;
    private int slots;

    public List<Item> get(Class<?> itemType) {
        List<Item> selectedItems = new ArrayList<>();
        for(Item item : items) {
            if(itemType == item.getClass()) {
                System.out.println(item.getClass());
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    public Item get(int index) {
        if(!isEmpty())
            return items.get(index);
        return null;
    }

    public boolean add(Item item) {
         if(!isFull()) {
             items.add(item);
             return true;
         }
         return false;
    }

    public void remove(Item item) {
        if(!isEmpty())
            items.remove(item);
    }

    public void remove(int index) {
        if(!isEmpty())
            items.remove(index);
    }

    public boolean isItem(Item item) {
        for(Item i: items) {
            if(i.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return slots == items.size();
    }

    public boolean isEmpty() {
        return 0 == items.size();
    }

    public int getSlots() {
        return slots;
    }

    public List<Item> getItems() {
        return items;
    }

    public Inventory(int slots) {
        items = new ArrayList<>();
        this.slots = slots;
    }
}
