package model;

import java.io.Serializable;

public class Reward implements Serializable {

    private static final long serialVersionUID = 1L;

    public int xp;
    public float money;

    @Override
    public String toString() {
        return "Reward{" +
                "xp=" + xp +
                ", money=" + money +
                '}';
    }

    public Reward(int xp, float money) {
        this.xp = xp;
        this.money = money;
    }

    public Reward() {
        xp = 0;
        money = 0.0f;
    }

}
