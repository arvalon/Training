package ru.arvalon.mytraining.Model;

/**
 * Created by arvalon on 16.06.2016.
 */

public class Equipment {
    private int id;
    private String name;
    private byte[] image;
    private String description;
    private int counterweight;
    private String date_banned;
    private int avaliable;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean checked;

    public int getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    private int measure;


    public Equipment(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public Equipment(int id, String name, byte[] image, int avaliable, int measure) {
        this.id = id;
        this.name = name;
        this.image=image;
        this.avaliable=avaliable;
        this.measure=measure;

    }

    public Equipment(int id, String name, byte[] image, String description,
                     int counterweight, String date_banned, int avaliable, int measure) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.counterweight = counterweight;
        this.date_banned = date_banned;
        this.avaliable = avaliable;
        this.measure=measure;
    }

    public Equipment(int id, String name, byte[] image, String description,
                     int counterweight, String date_banned, int avaliable) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.counterweight = counterweight;
        this.date_banned = date_banned;
        this.avaliable = avaliable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getCounterweight() {
        return counterweight;
    }

    public void setCounterweight(int counterweight) {
        this.counterweight = counterweight;
    }

    public String getDate_banned() {
        return date_banned;
    }

    public void setDate_banned(String date_banned) {
        this.date_banned = date_banned;
    }

    public int getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(int avaliable) {
        this.avaliable = avaliable;
    }

}
