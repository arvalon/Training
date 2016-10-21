package ru.arvalon.mytraining.Model;

/**
 * Created by arvalon on 11.07.2016.
 */
public class MuscleGroup {
    private int id;
    private String name;
    private int power;
    private byte[] img;
    private String description;

    public MuscleGroup(int id, String name, int power, byte[] img, String description) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.img = img;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int hashCode(){
        return getId();
    }

    @Override
    public boolean equals(Object o) {
        if ((o!=null) && (o instanceof MuscleGroup)) {
            if (this.getId()==((MuscleGroup) o).getId()) return true;
        }
        return false;
    }
}