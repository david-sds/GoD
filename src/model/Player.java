package model;

import main.Game;
import model.items.Diary;
import persistence.FilePersistence;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nickname;
    private final Game game;
    private long xp;
    private float money;
    private Journal journal;
    private Inventory inventory;

    public void addQuestArray(Quest[] quests) {
        for(Quest quest : quests) {
            journal.addQuest(quest);
        }
    }

    public void addAchievementArray(Achievement[] achievements) {
        for(Achievement achievement : achievements) {
            journal.addAchievement(achievement);
        }
    }

    public void useActiveItemsRelatedToQuest(Quest quest) throws IOException {
        List<Item> items = game.getPlayer().getActiveItems();
        for(Item item : items) {
            if(item.isPassive() && item.isActive()) {
                if(item.getClass().equals(Diary.class))
                    applyDiary((Diary) item, quest);
            }
        }
    }

    public void applyDiary(Diary diary, Quest quest) throws IOException {
        diary.use(
                diary.getTodayCalendar(),
                quest.getName(),
                "X",
                game.getPlayer(),
                new FilePersistence(Paths.get("saves", game.getName()))
        );
    }

    public void complete(Quest quest) {
        quest.complete();
        addXp(quest.getReward().xp);
        money += quest.getReward().money;
    }

    public List<Item> getActiveItems() {
        List<Item> items = inventory.getItems();
        List<Item> activeItems = new ArrayList<>();
        for(Item item : items) {
            if(item.isActive()) {
                activeItems.add(item);
            }
        }
        return activeItems;
    }

    public void achieve(Achievement achievement) {
        achievement.achieve();
        addXp(achievement.getReward().xp);
        money += achievement.getReward().money;
    }

    public void use(Item item) {
        item.use();
    }

    public boolean buy(Item item) {
        if(money >= item.getPrice()) {
            boolean isPurchase = inventory.add(item);
            if(isPurchase) {
                money -= item.getPrice();
                return true;
            } else {
                System.out.println("ERROR: INVENTORY IS FULL");
                return false;
            }
        } else {
            System.out.println("ERROR: NOT ENOUGH MONEY TO BUY THIS ITEM");
        }
        return false;
    }

    public void sell(Item item) {
        if(getInventory().isItem(item)) {
            money += item.getPrice() / 2;
            inventory.remove(item);
        } else {
            System.out.println("ERROR: ITEM NOT IN INVENTORY");
        }
    }

    public void addXp(long xp) {
        long cont = this.xp;
        if(getXpNeededToLevelUp() <= xp) {
            while(getXpFloorOnGivenLevel(getLevel(cont)+1) <= this.xp+xp) {
                cont = getXpFloorOnGivenLevel(getLevel(cont)+1);
                System.out.println("LEVEL UP!");
            }
        }
        this.xp += xp;
    }

    public int getLevel() {
        return getLevel(xp);
    }

    public int getLevel(long xp) {
        int level = 1;
        while(getXpFloorOnGivenLevel(level+1) <= xp) {
            level++;
        }
        return level;
    }

    public long getXpRemainderOfLevel() {
        int cont = 1;
        while (getXpFloorOnGivenLevel(cont) < xp){
            cont++;
        }
        return getXpFloorOnGivenLevel(cont) - xp;
    }

    public long getXpNeededToLevelUp() {
        long minimalXpToReachNextLevel = getXpFloorOnGivenLevel(getLevel() + 1);
        return (minimalXpToReachNextLevel - xp);
    }

    public long getXpFloorOnGivenLevel(int level) {
        return Math.round(100 * Math.pow(game.getGamemode(), level -1)) - 100;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", xp=" + xp +
                ", money=" + money +
                ", journal=" + journal +
                ", inventory=" + inventory +
                '}';
    }

    public Journal getJournal() {
        return journal;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Player(String nickname, Game game) {
        this.nickname = nickname;
        this.game = game;
        xp = 0;
        money = 0.0f;
        journal = new Journal();
        inventory = new Inventory(5);
    }

}
