package main;

import controller.ConsoleMenuController;
import model.items.DayCounter;

public class Main  {

    public static void main(String[] args) {

        try {

            new ConsoleMenuController();


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void printProjStartDate() {
        DayCounter dayCounter = new DayCounter();
        dayCounter.setStartDate("2021-01-11");
        dayCounter.end();
        System.out.println("This project started " + dayCounter.getNumberOfDaysElapsed() + " days ago.");
    }
}

// Humble beginnings in 11/01/2021