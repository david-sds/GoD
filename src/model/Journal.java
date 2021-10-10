package model;

import java.io.Serializable;
import java.util.*;

public class Journal implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Quest> quests;
    private List<Achievement> achievements;

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    public void printQuests() {
        for(Quest q : quests) {
            System.out.println(q);
        }
    }

    public void printAchievements() {
        for(Achievement a : achievements) {
            System.out.println(a);
        }
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public Journal() {
        quests = new ArrayList<>();
        achievements = new ArrayList<>();
    }

}
