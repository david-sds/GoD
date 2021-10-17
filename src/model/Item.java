package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

public abstract class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private float price;
    private boolean consumable;
    private boolean passive;
    private boolean active;
    private int uses;
    private transient String path;

    public void use() {
        setUses(getUses() + 1);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", consumable=" + consumable +
                ", passive=" + passive +
                ", active=" + active +
                ", uses=" + uses +
                '}';
    }

    protected Calendar getTodayCalendar() {
        Calendar today = Calendar.getInstance();
        TimeZone brt = TimeZone.getTimeZone("BRT");
        today.setTimeZone(brt);
        return today;
    }

    protected Calendar strDateToCalendar(String date) {
        String[] dateFields = date.split("-");
        Calendar c = Calendar.getInstance();
        int year = Integer.parseInt(dateFields[0]);
        int month = Integer.parseInt(dateFields[1]);
        int day = Integer.parseInt(dateFields[2]);
        c.set(year, month-1, day);
        return c;
    }

    protected String calendarToStrDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return formatDate(year, month+1, day);
    }

    protected String formatDate(int year, int month, int day) {
        String date = year + "-";
        if(month < 10) date += 0;
        date +=  month + "-";
        if(day < 10) date += 0;
        date += day;
        return date;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public boolean isPassive() {
        return passive;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void toggleActive() {
        setActive(!isActive());
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
        passive = false;
        uses = -1;
    }

    public Item(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
        consumable = false;
        passive = false;
        uses = -1;
    }

    public Item() {
        name = "";
        description = "";
        consumable = false;
        passive = false;
        uses = -1;
    }
}
