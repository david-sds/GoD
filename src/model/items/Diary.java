package model.items;

import com.sun.deploy.util.StringUtils;
import model.Item;
import model.Player;
import model.Quest;
import persistence.FilePersistence;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.*;

public class Diary extends Item implements Serializable {

    private static final long serialVersionUID = 1L;

    public void use(Calendar date, String questName, String value, Player player, FilePersistence fp) {
        try {
            setup(player, fp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dateStr = calendarToStrDate(date);
        fillFieldWithValue(dateStr, questName, value, fp);
    }

    public void use(String date, String questName, String value, Player player, FilePersistence fp) {
        try {
            setup(player, fp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fillFieldWithValue(date, questName, value, fp);
    }

    public void use() {
        toggleActive();
    }

    public void setup(Player player, FilePersistence fp) throws IOException {
        String fileName = getName() + ".txt";
        fp.setPath(Paths.get(fp.getPath(), fileName));
        createNewQuestMarkerFile(player.getJournal().getQuests(), fp);
        writeDatesUntilToday(fp);
        createFields(fp);
    }

    public void createNewQuestMarkerFile(List<Quest> quests, FilePersistence fp)
            throws IOException {
        if(!fp.isPath()) {
            fp.createFile(fp.getPath());
            writeQuestsInQuestMarker(quests, fp);
        }
    }

    public void writeQuestsInQuestMarker(List<Quest> quests, FilePersistence fp) {
        StringBuilder str = new StringBuilder("Date");
        for (Quest quest : quests) {
            str.append("\t").append(quest.getName());
        }
        fp.writeLineByItsNumber(str.toString(), 1);
    }

    public void writeDatesUntilToday(FilePersistence fp) {
        Calendar today = getTodayCalendar();
        List<String> diaryData = fp.fileToList();
        if(diaryData.size() <= 1) {
            fp.writeNewLine(calendarToStrDate(today));
        } else {
            Calendar lastDate = strDateToCalendar(diaryData.get(diaryData.size() - 1).split("\t")[0]);
            while(!calendarToStrDate(lastDate).equals(calendarToStrDate(today))) {
                lastDate.add(Calendar.DATE, 1);
                fp.writeNewLine(calendarToStrDate(lastDate));
            }
        }
    }

    public Calendar getTodayCalendar() {
        Calendar today = Calendar.getInstance();
        TimeZone brt = TimeZone.getTimeZone("BRT");
        today.setTimeZone(brt);
        return today;
    }

    public void createFields(FilePersistence fp) {
        List<String> fileData = fp.fileToList();
        String[] header = fileData.get(0).split("\t");

        for(int i = 1; i < fileData.size(); i++) {
            List<String> fields = new ArrayList<>
                    (Arrays.asList(fileData.get(i).split("\t")));

            for(int j = 1; j < header.length; j++) {
                if(j < fields.size()) {
                    if (fields.get(j).equals("")) fields.set(j, "-");
                } else {
                    fields.add("-");
                }
            }

            fileData.set(i, StringUtils.join(fields, "\t"));
        }
        fp.listToFile(fileData);
    }

    public void fillFieldWithValue(String date, String questName, String value, FilePersistence fp) {
        String[] header = fp.readLineByKey("Date", 0, "\t").split("\t");
        String[] line = fp.readLineByKey(date, 0, "\t").split("\t");
        for(int i = 1; i < line.length; i++) {
            if(questName.equals(header[i])) {
                line[i] = value;
            }
        }
        fp.writeLineByKey(date, String.join("\t", line), "\t");
    }

    private Calendar strDateToCalendar(String date) {
        String[] dateFields = date.split("-");
        Calendar c = Calendar.getInstance();
        int year = Integer.parseInt(dateFields[0]);
        int month = Integer.parseInt(dateFields[1]);
        int day = Integer.parseInt(dateFields[2]);
        c.set(year, month-1, day);
        return c;
    }

    private String calendarToStrDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return formatDate(year, month+1, day);
    }

    private String formatDate(int year, int month, int day) {
        String date = year + "-";
        if(month < 10) date += 0;
        date +=  month + "-";
        if(day < 10) date += 0;
        date += day;
        return date;
    }

    public Diary(String name, String description, float price, boolean consumable) {
        super(name, description, price, consumable);
        setPassive(true);
    }

    public Diary(String name, String description, float price) {
        super(name, description, price);
        setConsumable(false);
        setPassive(true);
    }

    public Diary() {}

}
