package persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePersistence {

    private String path;
    private final FileWriter fileWriter;
    private final FileReader fileReader;


    // READ METHODS

    public String readLineByKey(String key, int index, String regex) {
        String line = "";
        try {
            line = fileReader.readLineByKey(key, index, regex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public String readLineByItsNumber(int num) {
        List<String> fileData = fileReader.fileToList();
        if(num > 0 && num < fileData.size())
            return fileData.get(num - 1);
        return "";
    }

    public List<String> fileToList() {
        return fileReader.fileToList();
    }

    public Object readObject() {
        return fileReader.deserialize();
    }


    // WRITE METHODS

    public void write(String str) {
        fileWriter.write(str);
    }

    public void writeLineByKey(String key, String line, String regex) {
        List<String> fileData = fileReader.fileToList();
        for(int i = 0; i < fileData.size(); i++) {
            String[] fileDatumFields = fileData.get(i).split(regex);
            if(fileDatumFields[0].equals(key)) {
                fileData.set(i, line);
                fileWriter.listToFile(fileData);
                break;
            }
        }
    }

    public void writeLineByItsNumber(String line, int num) {
        List<String> fileData = fileReader.fileToList();
        if(num == 1 && fileData.size() == 0)
            fileData.add(line);
        else if(num > 0 && num <= fileData.size())
            fileData.set(num - 1, line);
        fileWriter.listToFile(fileData);
    }

    public void writeNewLine(String line) {
        fileWriter.write("\n" + line);
    }

    public void listToFile(List<String> fileData) {
        fileWriter.listToFile(fileData);
    }

    public void writeObject(Object obj) {
        fileWriter.serialize(obj);
    }


    // OTHER METHODS

    public Path createFile(Path filePath) throws IOException {
        return Files.createFile(filePath);
    }

    public Path createFile(String filePath) throws IOException {
        return Files.createFile(Paths.get(filePath));
    }

    public Path createFolder(Path path) throws IOException{
        return Files.createDirectories(path);
    }

    public Path createFolder(String path) throws IOException{
        return Files.createDirectories(Paths.get(path));
    }

    public boolean isPathAFile() {
        return Files.isRegularFile(Paths.get(path));
    }

    public boolean isPathAFile(String path) {
        return Files.isRegularFile(Paths.get(path));
    }

    public boolean isPathAFile(Path path) {
        return Files.isRegularFile(path);
    }

    public boolean isPathAFolder() {
        return Files.isDirectory(Paths.get(path));
    }

    public boolean isPathAFolder(String path) {
        return Files.isDirectory(Paths.get(path));
    }

    public boolean isPathAFolder(Path path) {
        return Files.isDirectory(path);
    }

    public boolean isPath() {
        return Files.exists(Paths.get(path));
    }

    public boolean isPath(String path) {
        return Files.exists(Paths.get(path));
    }

    public boolean isPath(Path path) {
        return Files.exists(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPath(Path path) {
        this.path = path.toString();
    }

    public FilePersistence(Path path) {
        this.path = path.toString();
        fileWriter = new FileWriter();
        fileReader = new FileReader();
    }

    public FilePersistence(String path) {
        this.path = path;
        fileWriter = new FileWriter();
        fileReader = new FileReader();
    }

    public FilePersistence() {
        this.path = "";
        fileWriter = new FileWriter();
        fileReader = new FileReader();
    }


    class FileWriter {

        public void write(String line) {
            write(line, true);
        }

        public void write(String line, boolean append) {
            try(BufferedWriter bw = getBufferedWriter(append)) {
                bw.write(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public BufferedWriter getBufferedWriter(boolean append) throws IOException {
            if(isPath(path)) {
                return new BufferedWriter(new java.io.FileWriter(path, append));
            }
            return null;
        }

        public void listToFile(List<String> fileData) {
            for(int i = 0; i < fileData.size(); i++) {
                if(i == 0)
                    write(fileData.get(0), false);
                else
                    write("\n" + fileData.get(i));
            }
        }

        public void serialize(Object obj) {
            try {
                FileOutputStream fileOutputStream
                        = new FileOutputStream(path);
                ObjectOutputStream objectOutputStream
                        = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    class FileReader {

        public String readLineByKey(String key, int index, String regex) throws IOException {
            String line = "";
            if(isPath(path)) {
                BufferedReader reader = new BufferedReader(new java.io.FileReader(path));
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(regex);
                    if(fields[index].equals(key)) break;
                }
            }
            return line;
        }

        public List<String> fileToList() {
            List<String> fileData = new ArrayList<>();
            try(BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {
                String line = reader.readLine();
                while(line != null) {
                    fileData.add(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fileData;
        }

        public Object deserialize() {
            try {
                FileInputStream fileInputStream
                        = new FileInputStream(path);
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(fileInputStream);
                Object obj = objectInputStream.readObject();
                objectInputStream.close();
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
