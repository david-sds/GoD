package view;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import main.Game;
import model.*;
import model.items.Diary;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenuView {

    Scanner in;

    // MAIN MENU METHODS

    public int mainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("(1) Continue");
        System.out.println("(2) Load Game");
        System.out.println("(3) New Game");
        System.out.println("(4) Options");
        System.out.println("(0) Quit");
        System.out.print("Option: ");
        return getIntInput();
    }

    public int loadGameMenu(List<Game> savedGames) {
        int counter = 0;
        System.out.println("\nLOAD GAME");
        for(Game game : savedGames) {
            System.out.println("("+(++counter)+") " + game.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public Game newGameMenu() {
        System.out.println("\nNEW GAME");
        System.out.print("Game name: ");
        String gameName = in.nextLine();
        System.out.print("Player name: ");
        String playerName = in.nextLine();
        System.out.print("Difficulty factor: ");
        Double difficultyFactor = getDoubleInput();
        return new Game(gameName, difficultyFactor, playerName);
    }


    // GAME MENU METHODS

    public int gameMenu() {
        System.out.println("\nGAME MENU");
        System.out.println("(1) Journal");
        System.out.println("(2) Inventory");
        System.out.println("(3) Store");
        System.out.println("(4) Stats");
        System.out.println("(0) Quit");
        System.out.print("Option: ");
        return getIntInput();
    }


    // JOURNAL MENU METHODS

    public int journalMenu() {
        System.out.println("\nJOURNAL MENU");
        System.out.println("(1) Complete Quest");
        System.out.println("(2) View Quests");
        System.out.println("(3) Create Quest");
        System.out.println("(4) Delete Quest");
        System.out.println("(5) View Achievements");
        System.out.println("(6) Create Achievement");
        System.out.println("(7) Delete Achievement");
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public int completeQuestMenu(List<Quest> quests) {
        int counter = 0;
        System.out.println("\nSelect quest to complete: ");
        for(Quest quest : quests) {
            System.out.println("("+(++counter)+") " + quest.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public void viewQuestsMenu(List<Quest> quests) {
        System.out.println();
        for(Quest quest : quests) {
            System.out.println(quest);
        }
    }

    public Quest createQuestMenu() {
        System.out.println("\nCREATE QUEST");
        System.out.print("Quest name: ");
        String questName = in.nextLine();
        System.out.print("Quest description: ");
        String questDescription = in.nextLine();
        System.out.print("XP reward: ");
        int xpReward = getIntInput();
        System.out.print("Money reward: ");
        float moneyReward = getFloatInput();
        return new Quest(questName, questDescription, new Reward(xpReward, moneyReward));
    }

    public int deleteQuestMenu(List<Quest> quests) {
        int counter = 0;
        System.out.println("\nSelect quest to delete: ");
        for(Quest quest : quests) {
            System.out.println("("+(++counter)+") " + quest.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public void viewAchievementsMenu(List<Achievement> achievements) {
        System.out.println();
        for(Achievement achievement : achievements) {
            System.out.println(achievement);
        }
    }

    public Achievement createAchievementMenu() {
        System.out.println("\nCREATE ACHIEVEMENT");
        System.out.print("Achievement name: ");
        String questName = in.nextLine();
        System.out.print("Achievement description: ");
        String questDescription = in.nextLine();
        System.out.print("XP reward: ");
        int xpReward = getIntInput();
        System.out.print("Money reward: ");
        float moneyReward = getFloatInput();
        return new Achievement(questName, questDescription, new Reward(xpReward, moneyReward));
    }

    public int deleteAchievementMenu(List<Achievement> achievements) {
        int counter = 0;
        System.out.println("\nSelect achievement to delete: ");
        for(Achievement achievement : achievements) {
            System.out.println("("+(++counter)+") " + achievement.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    // INVENTORY MENU METHODS

    public int inventoryMenu() {
        System.out.println("\nINVENTORY MENU");
        System.out.println("(1) Use Item");
        System.out.println("(2) View Items");
        System.out.println("(3) Destroy Item");
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public int useItemMenu(List<Item> items) {
        int counter = 0;
        System.out.println("\nSelect item to use: ");
        for(Item item : items) {
            System.out.println("("+(++counter)+") " + item.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public void viewItemsMenu(List<Item> items) {
        System.out.println();
        for(Item item : items) {
            System.out.println(item);
        }
    }

    public int destroyItemsMenu(List<Item> items) {
        int counter = 0;
        System.out.println("\nSelect item to destroy: ");
        for(Item item : items) {
            System.out.println("("+(++counter)+") " + item.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }


    // STORE MENU METHODS

    public int storeMenu() {
        System.out.println("\nSTORE MENU");
        System.out.println("(1) Buy Item");
        System.out.println("(2) View Items");
        System.out.println("(3) Sell Item");
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public int buyItemMenu(List<Item> items) {
        int counter = 0;
        System.out.println("\nSelect item to buy: ");
        for(Item item : items) {
            System.out.println("("+(++counter)+") " + item.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    // Look up! viewItemsMenu is up there.

    public int sellItemsMenu(List<Item> items) {
        int counter = 0;
        System.out.println("\nSelect item to sell: ");
        for(Item item : items) {
            System.out.println("("+(++counter)+") " + item.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }


    // OPTIONS MENU METHODS

    public int optionsMenu() {
        System.out.println("\nOPTIONS MENU");
        System.out.println("(1) Manage Store");
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public int manageStoreMenu() {
        System.out.println("\nMANAGE STORE MENU");
        System.out.println("(1) Add Item");
        System.out.println("(2) View items");
        System.out.println("(3) Remove Item");
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public int addItemMenu(List<Class<? extends Item>> catalogue) {
        int counter = 0;
        System.out.println("\nSelect item to buy: ");
        for(Class<? extends Item> itemClass : catalogue) {
            System.out.println("("+(++counter)+") " + itemClass.getSimpleName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    public Item createItemMenu(Class<? extends Item> itemClass, Item item) {
        System.out.println("\nCREATE ITEM");
        System.out.print("Item name: ");
        String name = in.nextLine();
        System.out.print("Item description: ");
        String description = in.nextLine();
        System.out.print("Item price: ");
        float price = getFloatInput();
        boolean consumable = getBooleanInput("Do you want it to be consumable?");
        itemClass.cast(item);
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setConsumable(consumable);
        return item;
    }

    public Item createDiaryMenu() {
        System.out.println("\nCREATE DIARY");
        System.out.print("Item name: ");
        String name = in.nextLine();
        System.out.print("Item description: ");
        String description = in.nextLine();
        System.out.print("Item price: ");
        float price = getFloatInput();
        boolean consumable = getBooleanInput("Do you want it to be consumable?");
        return new Diary(name, description, price, consumable);
    }

    public Item createDayCounterMenu() {
        System.out.println("\nCREATE DAY COUNTER");
        System.out.print("Item name: ");
        String name = in.nextLine();
        System.out.print("Item description: ");
        String description = in.nextLine();
        System.out.print("Item price: ");
        float price = getFloatInput();
        boolean consumable = getBooleanInput("Do you want it to be consumable?");
        return new Diary(name, description, price, consumable);
    }

    public int removeItemMenu(List<Item> items) {
        int counter = 0;
        System.out.println("\nSelect item to destroy: ");
        for(Item item : items) {
            System.out.println("("+(++counter)+") " + item.getName());
        }
        System.out.println("(0) Back");
        System.out.print("Option: ");
        return getIntInput();
    }

    // OTHER METHODS

    public void viewStats(Player player) {
        System.out.println("\n" + player);
    }

    public int printMenu(String[] options) {
        System.out.println("\n"+options[0]);
        for(int i = 1; i < options.length - 1; i++) {
            System.out.println("("+(i)+") " + options[i]);
        }
        System.out.println("(0) " + options[options.length - 1]);
        return getIntOption();
    }

    public Object printObjectFromList(String title, List<Item> items, String closeOptName) {
        System.out.println("\n " + title);
        for(int i = 1; i < items.size() - 1; i++) {
            System.out.println("("+i+") " + items.get(i).getName());
        }
        System.out.println("(0) " + closeOptName);
        return getIntOption();
    }

    public int getIntOption() {
        System.out.print("Option: ");
        return getIntInput();
    }

    public boolean isSave(String saveQuestion) {
        System.out.print("\n"+saveQuestion+" (y/n): ");
        String answer = in.nextLine();
        if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
            return answer.equalsIgnoreCase("y");
        } else {
            invalidOption();
            isSave(saveQuestion);
        }
        return true;
    }

    public int getIntInput() {
        int input;
        try {
            input = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            input = -1;
        }
        return input;
    }

    public float getFloatInput() {
        float input;
        try {
            input = Float.parseFloat(in.nextLine());
        } catch (NumberFormatException e) { ;
            input = -1;
        }
        return input;
    }

    public double getDoubleInput() {
        double input;
        try {
            input = Double.parseDouble(in.nextLine());
        } catch (NumberFormatException e) {
            input = -1;
        }
        return input;
    }

    public boolean getBooleanInput(String question) {
        System.out.print(question + " (y/n): ");
        String response = in.nextLine();
        if(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")) {
            return response.equalsIgnoreCase("y");
        } else {
            invalidOption();
            getBooleanInput(question);
        }
        return false;
    }


    public void invalidOption() {
        System.out.println("*** Invalid Option ***");
    }

    public void sayHi() {
        System.out.println("Hello there.");
    }

    public void sayBye() {
        System.out.println("\nFarewell!\n");
    }

    public ConsoleMenuView() {
        in = new Scanner(System.in);
    }

}
