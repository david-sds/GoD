package model.items;

import model.Item;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DayCounter extends Item implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date startDate;
    private Date finalDate;
    private int daysPaused;
    private DayCounter daysPausedCounter;

    public void start() {
        if(startDate == null)
            startDate = Calendar.getInstance().getTime();
        else if(isPaused()) {
            daysPaused += daysPausedCounter.getNumberOfDaysElapsed();
            daysPausedCounter = null;
        }
    }

    public void pause() {
        if(!isPaused()) {
            daysPausedCounter = new DayCounter();
            daysPausedCounter.start();
        }
    }

    public void end() {
        finalDate = Calendar.getInstance().getTime();
    }

    public void reset() {
        startDate = null;
        finalDate = null;
        daysPaused = 0;
        daysPausedCounter = null;
    }

    public void use() {
        setUses(getUses() + 1);
    }

    public long getNumberOfDaysElapsed() {
        if(finalDate == null) {
            Date currentDate = Calendar.getInstance().getTime();
            return millisecondsToDays(currentDate.getTime() - startDate.getTime()) - daysPaused;
        } else {
            return millisecondsToDays(finalDate.getTime() - startDate.getTime()) - daysPaused;
        }
    }

    public boolean isPaused() {
        return daysPausedCounter != null;
    }

    public long millisecondsToDays(long ms) {
        return TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public DayCounter(String name, String description, float price, boolean consumable) {
        super(name, description, price, consumable);
        daysPaused = 0;
    }

    public DayCounter(String name, String description, float price) {
        super(name, description, price);
        daysPaused = 0;
    }

    public DayCounter() {
        daysPaused = 0;
    }
}
