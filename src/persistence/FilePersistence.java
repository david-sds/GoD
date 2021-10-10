package persistence;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class FilePersistence {

    private final Path path;

    public void writeLineByKey(String line, String key, String regex, String fileName) {
        ArrayList<String> fileData = fileToArrayList(fileName);
        for(int i = 0; i < fileData.size(); i++) {
            String[] fileDatumFields = fileData.get(i).split(regex);
            if(fileDatumFields[0].equals(key)) {
                fileData.set(i, line);
                arrayListToFile(fileData, fileName);
                break;
            }
        }
    }

    public void writeNewLine(String line, String fileName) {
        Path absolutePath = Paths.get(path.toString(), fileName);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString(), true))) {
            bw.write("\n" + line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String line, String fileName) {
        Path absolutePath = Paths.get(path.toString(), fileName);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString(),false))) {
            bw.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLineByKey(String key, String regex, String fileName) {
        Path absolutePath = Paths.get(path.toString(), fileName);
        String[] fields;
        String line = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(absolutePath.toString()))) {
            do {
                line = reader.readLine();
                fields = line.split(regex);
                if(fields[0].equals(key)) break;
            } while((line = reader.readLine()) != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public String readFile(String fileName) {
        Path absolutePath = Paths.get(path.toString(), fileName);
        String data = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line = reader.readLine();
            while(line != null) {
                data = data.concat(line + "\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String readLastLine(String fileName) {
        Path absolutePath = Paths.get(path.toString(), fileName);
        String lastLine = "";
        String line;
        try {
            BufferedReader input = new BufferedReader(new FileReader(absolutePath.toString()));
            while ((line = input.readLine()) != null) {
                lastLine = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastLine;
    }

    public ArrayList<String> fileToArrayList(String fileName) {
        Path absolutePath = Paths.get(path.toString(), fileName);
        ArrayList<String> fileData = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(absolutePath.toString()))) {
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

    public void arrayListToFile(ArrayList<String> fileData, String fileName) {
        for(int i = 0; i < fileData.size(); i++) {
            if(i == 0)
                writeFile(fileData.get(0), fileName);
            else
                writeNewLine(fileData.get(i), fileName);
        }
    }

    public void serialize(Object obj, Path path) {
        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream(path.toString());
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serialize(Object obj, String path) {
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

    public Object deserialize(String path) {
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

    public Object deserialize(Path path) {
        try {
            FileInputStream fileInputStream
                    = new FileInputStream(path.toString());
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

    public Path getPath() {
        return path;
    }

    public FilePersistence(String path) {
         this.path = Paths.get(path);
    }
}
