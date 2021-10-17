package main;

import model.Store;
import persistence.FilePersistence;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu{

    private FilePersistence fp;
    private String savesPath;

    public void saveGame(Game game) throws IOException {
        fp.setPath(Paths.get(fp.getPath(), game.getName()));
        if (!fp.isPath())
            fp.createFolder();
        fp.writeObject(game, Paths.get(fp.getPath(), "game.ser"));
        fp.setPath(savesPath);
    }

    public Game continueGame() throws IOException {
        List<Path> savedGamesPaths = getSavedGamePaths();
        if (savedGamesPaths.size() != 0) {
            Path lastGamePlayedPath = fp.getLastModifiedFile(savedGamesPaths);
            return (Game) fp.readObject(lastGamePlayedPath.toString());
        }
        return null;
    }

    public List<Game> getSavedGames() {
        List<Game> games = new ArrayList<>();
        try {
            List<Path> paths = getSavedGamePaths();
            for(Path path : paths) {
                if(path.endsWith("game.ser"))
                    games.add((Game) fp.readObject(path.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }

    private List<Path> getSavedGamePaths() throws IOException {
        List<Path> result;
        try(Stream<Path> walk = Files.walk(Paths.get(fp.getPath()), 2)) {
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public void createSavesPath() throws IOException {
        if(!fp.isPath())
            fp.createFolder();
    }

    public String getSavesPath() {
        return savesPath;
    }

    public void setSavesPath(String savesPath) throws IOException {
        this.savesPath = savesPath;
        createSavesPath();
        System.out.println("WARNING: Your saves are in the old folder, copy and paste them manually.");
    }

    public void saveStore(Store store) {
        fp.writeObject(store, "src/store.ser");
    }

    public Store loadStore() {
        return (Store) fp.readObject("src/store.ser");
    }

    public Menu() throws IOException {
        this.savesPath = "saves";
        fp = new FilePersistence(savesPath);
        createSavesPath();
    }
}

