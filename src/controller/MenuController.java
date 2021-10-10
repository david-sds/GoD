package controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import main.Game;
import main.Menu;
import model.*;
import persistence.FilePersistence;
import view.MenuView;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MenuController {

    private FilePersistence fp;
    private Menu menu;
    private MenuView menuView;
    private Game game;
    private Store store;

    // MAIN MENU METHODS

    public void launchGame() {

        menuView.sayHi();
        boolean keepRunning = mainMenu();

        while (keepRunning) {
            keepRunning = mainMenu();
        }
        menuView.sayBye();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            System.out.println("Something went wrong with my farewell, but fear not, we're closing anyway.");
        }

    }

    public boolean mainMenu() {
        switch(menuView.mainMenu()) {
            case 1:
                continueGameMenu();
                break;
            case 2:
                loadGameMenu();
                break;
            case 3:
                newGameMenu();
                break;
            case 4:
                launchOptionsMenu();
                break;
            case 0:
                return false;
            default:
                menuView.invalidOption();
                break;
        }
        return true;
    }

    public void continueGameMenu() {
        Game game = menu.continueGame();
        if(game != null) {
            this.game = game;
            launchGameMenu();
        } else
            menuView.invalidOption();
    }

    public void loadGameMenu() {
        List<Game> savedGames = menu.getSavedGames();
        int opt = checkIfInputIsValid(menuView.loadGameMenu(savedGames), savedGames.size());
        if(opt == 0) return;
        game = savedGames.get(opt - 1);
        launchGameMenu();
    }

    public void newGameMenu() {

        game = menuView.newGameMenu();
        launchGameMenu();
    }

    public void launchGameMenu() {
        boolean keepRunning = gameMenu();
        while (keepRunning) {
            keepRunning = gameMenu();
        }
    }


    // GAME MENU METHODS

    public boolean gameMenu() {
        switch(menuView.gameMenu()) {
            case 1:
                launchJournalMenu();
                break;
            case 2:
                launchInventoryMenu();
                break;
            case 3:
                launchStoreMenu();
                break;
            case 4:
                statsMenu();
                break;
            case 0:
                shouldSave();
                return false;
            default:
                menuView.invalidOption();
                break;
        }
        return true;
    }

    public void launchJournalMenu() {
        boolean keepRunning = journalMenu();
        while (keepRunning) {
            keepRunning = journalMenu();
        }
    }

    public void launchInventoryMenu() {
        boolean keepRunning = inventoryMenu();
        while (keepRunning) {
            keepRunning = inventoryMenu();
        }
    }

    public void launchStoreMenu() {
        boolean keepRunning = storeMenu();
        while (keepRunning) {
            keepRunning = storeMenu();
        }
    }


    // JOURNAL MENU METHODS

    public boolean journalMenu() {

        int option = menuView.printMenu(
                new String[]{
                        "JOURNAL MENU",
                        "Complete Quest",
                        "View Quests",
                        "Create Quest",
                        "Delete Quest",
                        "View Achievements",
                        "Create Achievement",
                        "Delete Achievement",
                        "Back"
                });

        switch(option) {
            case 1:
                completeQuestMenu();
                break;
            case 2:
                viewQuestsMenu();
                break;
            case 3:
                createQuestMenu();
                break;
            case 4:
                deleteQuestMenu();
                break;
            case 5:
                viewAchievementsMenu();
                break;
            case 6:
                createAchievementMenu();
                break;
            case 7:
                deleteAchievementMenu();
                break;
            case 0:
                return false;
            default:
                menuView.invalidOption();
                break;
        }
        return true;
    }

    public void completeQuestMenu() {
        List<Quest> quests = game.getPlayer().getJournal().getQuests();
        int opt = checkIfInputIsValid(menuView.completeQuestMenu(quests), quests.size());
        if(opt != 0)
            game.getPlayer().complete(quests.get(opt -1));
    }

    public void viewQuestsMenu() {
        List<Quest> quests = game.getPlayer().getJournal().getQuests();
        menuView.viewQuestsMenu(quests);
    }

    public void createQuestMenu() {
        Quest quest = menuView.createQuestMenu();
        game.getPlayer().getJournal().addQuest(quest);
    }

    public void deleteQuestMenu() {
        List<Quest> quests = game.getPlayer().getJournal().getQuests();
        int opt = checkIfInputIsValid(menuView.deleteQuestMenu(quests), quests.size());
        if(opt != 0)
            quests.remove(opt -1);
    }

    public void viewAchievementsMenu() {
        List<Achievement> achievements = game.getPlayer().getJournal().getAchievements();
        menuView.viewAchievementsMenu(achievements);
    }

    public void createAchievementMenu() {
        Achievement achievement = menuView.createAchievementMenu();
        game.getPlayer().getJournal().addAchievement(achievement);
    }

    public void deleteAchievementMenu() {
        List<Achievement> achievements = game.getPlayer().getJournal().getAchievements();
        int opt = checkIfInputIsValid(menuView.deleteAchievementMenu(achievements), achievements.size());
        if(opt != 0)
            achievements.remove(opt -1);
    }

    // INVENTORY MENU METHODS

    public boolean inventoryMenu() {
        switch(menuView.inventoryMenu()) {
            case 1:
                useItemMenu();
                break;
            case 2:
                viewPlayerItemsMenu();
                break;
            case 3:
                destroyItemMenu();
                break;
            case 0:
                return false;
            default:
                menuView.invalidOption();
                break;
        }
        return true;
    }

     public void useItemMenu() {
         Player player = game.getPlayer();
         List<Item> items = player.getInventory().getItems();
         int opt = checkIfInputIsValid(menuView.useItemMenu(items), items.size());
         if(opt != 0)
             player.use(player.getInventory().get(opt -1));
     }

     public void viewPlayerItemsMenu() {
        menuView.viewItemsMenu(game.getPlayer().getInventory().getItems());
     }

     public void destroyItemMenu() {
         Player player = game.getPlayer();
         List<Item> items = player.getInventory().getItems();
         int opt = checkIfInputIsValid(menuView.useItemMenu(items), items.size());
         if(opt != 0)
             player.getInventory().remove(opt -1);
     }


    // STORE MENU METHODS

    public boolean storeMenu() {
        switch(menuView.storeMenu()) {
            case 1:
                buyItemMenu();
                break;
            case 2:
                viewStoreItemsMenu();
                break;
            case 3:
                sellItemsMenu();
                break;
            case 0:
                return false;
            default:
                menuView.invalidOption();
                break;
        }
        return true;
    }

    public void buyItemMenu() {
        Player player = game.getPlayer();
        List<Item> items = store.getStock();
        int opt = checkIfInputIsValid(menuView.buyItemMenu(items), items.size());
        if(opt != 0) {
            boolean isPurchase = player.buy(store.getStock().get(opt - 1));
            if(isPurchase)
                store.getStock().remove(opt -1);
        }
    }

    public void viewStoreItemsMenu() {
        menuView.viewItemsMenu(store.getStock());
    }

    public void sellItemsMenu() {
        Player player = game.getPlayer();
        Item item;
        List<Item> items = player.getInventory().getItems();
        int opt = checkIfInputIsValid(menuView.useItemMenu(items), items.size());
        if(opt != 0) {
            item = player.getInventory().getItems().get(opt - 1);
            store.addNewItemToStock(item);
            player.sell(item);
        }
    }

    // STATS MENU METHODS

    public void statsMenu() {
        menuView.viewStats(game.getPlayer());
    }


    // OPTION METHODS

    public void launchOptionsMenu() {
        boolean keepRunning = optionsMenu();
        while (keepRunning) {
            keepRunning = optionsMenu();
        }
    }

    public boolean optionsMenu() {
        switch(menuView.optionsMenu()) {
            case 1:
                launchManageStoreMenu();
                break;
            case 0:
                return false;
            default:
                menuView.invalidOption();
                break;
        }
        return true;
    }

    public void launchManageStoreMenu() {
        boolean keepRunning = manageStoreMenu();
        while (keepRunning) {
            keepRunning = manageStoreMenu();
        }
    }

    public boolean manageStoreMenu() {
        switch (menuView.manageStoreMenu()) {
            case 1:
                addItemMenu();
                break;
            case 2:
                viewStoreItemsMenu();
                break;
            case 3:
                removeItemMenu();
                break;
            case 0:
                shouldSaveStore();
                return false;
            default:
                menuView.invalidOption();
                break;
        }
        return true;
    }

    public void addItemMenu() {
        int opt = checkIfInputIsValid(menuView.addItemMenu(store.getCatalogue()), store.getCatalogue().size());
        if(opt != 0) {
            createItemMenu(store.getCatalogue().get(opt -1));
        }
    }

    public void createItemMenu(Class<? extends Item> itemClass) {
        try {
            Class<?> c = Class.forName(itemClass.getName());
            Constructor<?> cons = c.getConstructor();
            Object obj = cons.newInstance();
            Item item = (Item) obj;
            menuView.createItemMenu(itemClass, item);
            store.addNewItemToStock(item);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeItemMenu() {
        int opt = checkIfInputIsValid(menuView.removeItemMenu(store.getStock()), store.getStock().size());
        if(opt != 0) {
            store.getStock().remove(opt -1);
        }
    }

    // OTHER METHODS

    public int checkIfInputIsValid(int input, int arrayLength) {
        if(input == 0)
            return 0;
        if(input - 1 < 0 || input - 1 >= arrayLength) {
            menuView.invalidOption();
            return 0;
        }
        return input;
    }

    public void shouldSave() {
        if(menuView.isSave()) {
            menu.saveGame(game);
            fp.serialize(store, "src/store.ser");
        }
    }

    public void shouldSaveStore() {
        if(menuView.isSave()) {
            fp.serialize(store, "src/store.ser");
        }
    }

    public MenuController() {
        menu = new Menu();
        menuView = new MenuView();
        fp = new FilePersistence("src");
        store = (Store) fp.deserialize("src\\store.ser");
        launchGame();
    }

}
