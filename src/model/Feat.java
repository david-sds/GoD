package model;

import java.io.Serializable;

public abstract class Feat implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Reward reward;

    public Feat(String name, String description, Reward reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
    }

    public Feat(String name, String description) {
        this.name = name;
        this.description = description;
        reward = new Reward();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
