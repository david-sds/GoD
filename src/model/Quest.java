package model;

import java.io.Serializable;

public class Quest extends Feat implements Serializable{

    private static final long serialVersionUID = 1L;

    private long timesCompleted;

    public void complete() {
        timesCompleted += 1;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", " + getReward() + '\'' +
                ", timesCompleted=" + timesCompleted +
                '}';
    }

    public Quest(String name, String description, Reward reward, long timesCompleted) {
        super(name, description, reward);
        this.timesCompleted = timesCompleted;
    }

    public Quest(String name, String description, Reward reward) {
        super(name, description);
        setReward(reward);
        timesCompleted = 0;
    }
}