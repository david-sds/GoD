package main;

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

    public void saveGame(Game game) {
        Path directory = Paths.get(fp.getPath().toString(), game.getName());
        if (!Files.exists(directory)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fp.serialize(game, Paths.get(directory.toString(), "game.ser").toString());
    }

    public Game continueGame() throws StreamCorruptedException {
        try {
            List<Path> savedGamesPaths = getSavedGamePaths();
            if(savedGamesPaths.size() != 0) {
                BasicFileAttributes attr = Files.readAttributes(savedGamesPaths.get(0), BasicFileAttributes.class);
                FileTime lastDateModified = attr.lastModifiedTime();
                Path lastGamePlayedPath = savedGamesPaths.get(0);

                for (Path path : savedGamesPaths) {
                    attr = Files.readAttributes(path, BasicFileAttributes.class);
                    FileTime dateModified = attr.lastModifiedTime();
                    if (lastDateModified.compareTo(dateModified) < 0) {
                        lastDateModified = dateModified;
                        lastGamePlayedPath = path;
                    }
                }
                return (Game) fp.deserialize(lastGamePlayedPath.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Game> getSavedGames() {
        List<Game> games = new ArrayList<>();
        try {
            List<Path> paths = getSavedGamePaths();
            for(Path path : paths) {
                if(path.endsWith("game.ser"))
                    games.add( (Game) fp.deserialize(path.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }

    private List<Path> getSavedGamePaths() throws IOException {
        List<Path> result;
        try(Stream<Path> walk = Files.walk(Paths.get(fp.getPath().toString()), 2)) {
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public void createSavesPath() {
        if(!Files.exists(Paths.get(savesPath))) {
            try {
                Files.createDirectories(Paths.get(savesPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getSavesPath() {
        return savesPath;
    }

    public void setSavesPath(String savesPath) {
        this.savesPath = savesPath;
        createSavesPath();
        System.out.println("WARNING: Your saves are in the old folder, copy and paste them manually.");
    }

    public Menu() {
        this.savesPath = "saves";
        createSavesPath();
        fp = new FilePersistence(savesPath);
    }
}

