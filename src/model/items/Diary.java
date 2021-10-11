package model.items;

import model.Item;
import model.Quest;
import persistence.FilePersistence;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Diary extends Item implements Serializable {

    private static final long serialVersionUID = 1L;

    public void createNewQuestMarker(List<Quest> quests, String path) {
        FilePersistence fp = new FilePersistence(path);
        String fileName = getName() + ".txt";
        StringBuilder str = new StringBuilder();
        if(!Files.exists(Paths.get(path, fileName))) {
            str.append("Days");
            for (Quest quest : quests) {
                str.append("\t").append(quest.getName());
            }
            fp.writeFile(str.toString(), getName() + ".txt");
        } else {
            System.out.println("ERROR: QuestMarker already exists.");
        }
    }

    public void writeDatesUltilToday(String path, String fileName) {
        FilePersistence fp = new FilePersistence(path);
        Calendar today = getTodayCalendar();

        ArrayList<String> diaryData = fp.fileToArrayList(fileName);
        if(diaryData.size() == 1) {
            fp.writeNewLine(calendarToStrDate(today), fileName);
        } else {
            Calendar lastDate = strDateToCalendar(diaryData.get(diaryData.size() - 1).split("\t")[0]);
            while(!calendarToStrDate(lastDate).equals(calendarToStrDate(today))) {
                lastDate.add(Calendar.DATE, 1);
                fp.writeNewLine(calendarToStrDate(lastDate), fileName);
            }
        }
    }

    private Calendar getTodayCalendar() {
        Calendar today = Calendar.getInstance();
        TimeZone brt = TimeZone.getTimeZone("BRT");
        today.setTimeZone(brt);
        return today;
    }

    public void fillAllEmptyFields(String path, String fileName) {
        FilePersistence fp = new FilePersistence(path);
        ArrayList<String> diaryData = fp.fileToArrayList(fileName);
        String[] headerContent = diaryData.get(0).split("\t");
        for(String str : diaryData) {
            int count = 0;
            boolean isEmpty = true;

            String[] fields = str.split("\t");
            while(++count < fields.length) {
                isEmpty = fields[count].equals("");
            }

            if(isEmpty) {
                for(int i = 1; i < headerContent.length; i++) {
                    String[] questActivity = new String[headerContent.length - 1];
                    getInputValues(questActivity);
                    StringBuilder line = new StringBuilder(fields[0]);
                    for (String s : questActivity) {
                        line.append("\t").append(s);
                    }
                    fp.writeLineByKey(line.toString(), fields[0], "\t", fileName);
                }
            }
        }
    }

    public void getInputValues(String[] questActivity) {
        for(int i = 0; i < questActivity.length; i++) {
            questActivity[i] = "" + (i + 1);
        }
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

    public void use() {
        setUses(getUses() + 1);
    }

    public Diary(String name, String description, float price, boolean consumable) {
        super(name, description, price, consumable);
    }

    public Diary(String name, String description, float price) {
        super(name, description, price);
    }

    public Diary() {
    }


}
