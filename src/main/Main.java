package main;

import controller.MenuController;
import model.Player;
import model.items.Diary;

public class Main  {

    public static void main(String[] args) {

//        MenuController mc = new MenuController();

        Menu menu = new Menu();

        Player player = menu.getSavedGames().get(0).getPlayer();

        Diary diary = new Diary("diary", "diary desc", 10, false);
        diary.createNewQuestMarker(player.getJournal().getQuests(), "saves/game1");
        diary.writeDatesUltilToday("saves/game1", "diary.txt");
        diary.fillAllEmptyFields("saves/game1", "diary.txt");
    }

}



// Humble beginnings in 11/01/2021