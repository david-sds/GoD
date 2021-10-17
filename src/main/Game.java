package main;

import model.Item;
import model.Player;
import model.Quest;
import model.items.Diary;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private final double gamemode;
    private final Player player;
    private Date dateCreated;

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", player=" + player +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getGamemode() {
        return gamemode;
    }

    public Player getPlayer() {
        return player;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Game(String name, Double gamemode, Player player) {
        this.name = name;
        this.gamemode = gamemode;
        this.player = player;
        dateCreated = new Date(System.currentTimeMillis());
    }

    public Game(String name, Double gamemode, String nickname) {
        this.name = name;
        this.gamemode = gamemode;
        player = new Player(nickname, this);
        dateCreated = new Date(System.currentTimeMillis());
    }

}
