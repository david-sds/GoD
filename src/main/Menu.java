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

    public Game continueGame() {
        try {
            List<Path> savedGamesPaths = getSavedGamePaths();
            BasicFileAttributes attr = Files.readAttributes(savedGamesPaths.get(0), BasicFileAttributes.class);
            FileTime lastDateModified = attr.lastModifiedTime();
            Path lastGamePlayedPath = savedGamesPaths.get(0);

            for(Path path : savedGamesPaths) {
                attr = Files.readAttributes(path, BasicFileAttributes.class);
                FileTime dateModified = attr.lastModifiedTime();
                if(lastDateModified.compareTo(dateModified) < 0) {
                    lastDateModified = dateModified;
                    lastGamePlayedPath = path;
                }
            }
            return (Game) fp.deserialize(lastGamePlayedPath.toString());
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

    public Menu() {
        fp = new FilePersistence("saves");
    }
}

