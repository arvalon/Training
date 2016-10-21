package ru.arvalon.mytraining.Model;

/**
 * Created by arvalon on 15.07.2016.
 */
public class Muscle {
    private int ID;
    private int Group;
    private String muscle_name;
    private byte[] image;
    private String description;
    private boolean checked;

    public Muscle(int ID, int group, String muscle_name , byte[] image, String description) {
        this.ID = ID;
        Group = group;
        this.muscle_name = muscle_name;
        this.image = image;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMuscle_name() {
        return muscle_name;
    }

    public void setMuscle_name(String muscle_name) {
        this.muscle_name = muscle_name;
    }

    public int getGroup() {
        return Group;
    }

    public void setGroup(int group) {
        Group = group;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getChecked() {return checked;}

    public void setChecked(boolean checked) {this.checked = checked;}


}
