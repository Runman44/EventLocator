package nl.mranderson.viewpagerexample;

import android.location.Location;

import java.util.Date;

/**
 * Created by MrAnderson1 on 22/12/15.
 */
public class Event {

    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Location location;

    public Event(String title, String description, Date startDate, Date endDate, Location location) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public Event(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Event() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
