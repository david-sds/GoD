package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Achievement extends Feat implements Serializable {

    private static final long serialVersionUID = 1L;

    private String completionDate;

    public void achieve() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        completionDate = dtf.format(now);
    }

    @Override
    public String toString() {
        return "Quest{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", '" + getReward() + '\'' +
                ", 'completionDate=" + completionDate +
                '}';
    }

    public Achievement(String name, String description, Reward reward) {
        super(name, description, reward);
    }

    public Achievement(String name, String description) {
        super(name, description);
        setReward(new Reward());
    }

}
