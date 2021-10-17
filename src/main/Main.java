package main;

import controller.MenuController;
import model.Player;
import model.items.Diary;
import persistence.FilePersistence;


public class Main  {

    public static void main(String[] args) {

        try {

            new MenuController();

//            Menu menu = new Menu();
//            Game game = menu.getSavedGames().get(0);
//            Player player = game.getPlayer();
//            Diary diary = new Diary("DIARIO", "", 100);
//            FilePersistence fp = new FilePersistence("saves/game1");
//            diary.use("2021-10-12", "quest2", "-", player, fp);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



// Humble beginnings in 11/01/2021