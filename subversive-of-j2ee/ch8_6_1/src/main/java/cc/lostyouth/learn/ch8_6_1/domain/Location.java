package cc.lostyouth.learn.ch8_6_1.domain;

/**
 * Created by endless on 12/14/2017.
 */
public class Location {
    private String place;
    private String year;

    public Location(String place, String year) {

        this.place = place;
        this.year = year;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
