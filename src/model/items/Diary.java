package model.items;

import model.Item;

import java.io.Serializable;
import java.util.Calendar;

public class Diary extends Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private Calendar strDateToCalendar(String date) {
        String[] dateFields = date.split("-");
        Calendar c = Calendar.getInstance();
        int year = Integer.parseInt(dateFields[0]);
        int month = Integer.parseInt(dateFields[1]);
        int day = Integer.parseInt(dateFields[2]);
        c.set(year, month-1, day);
        return c;
    }

    private String calendarToStrDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return formatDate(year, month, day);
    }

    private String formatDate(int year, int month, int day) {
        String date = year + "-";
        if(month < 10) date += 0;
        date +=  month + "-";
        if(day < 10) date += 0;
        date += day;
        return date;
    }

    public void use() {
        setUses(getUses() + 1);
    }

    public Diary(String name, String description, float price, boolean consumable) {
        super(name, description, price, consumable);
    }

    public Diary(String name, String description) {
        super(name, description);
    }

    public Diary() {
    }


}
