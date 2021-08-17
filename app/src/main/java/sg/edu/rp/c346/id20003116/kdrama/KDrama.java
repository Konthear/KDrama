package sg.edu.rp.c346.id20003116.kdrama;

import java.io.Serializable;

public class KDrama implements Serializable {

    private int id;
    private String name;
    private String desc;
    private String cast;
    private int yearReleased;
    private int stars;

    public KDrama(int id, String name, String desc, String cast, int yearReleased, int stars) {
        this.id = id;
        this.name = name;
        this.cast = cast;
        this.yearReleased = yearReleased;
        this.stars = stars;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
