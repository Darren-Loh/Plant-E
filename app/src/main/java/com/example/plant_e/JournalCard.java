package com.example.plant_e;


public class JournalCard {
    String datestamp;
    String Timestamp;
    String Title;
    String description;
    String plant;
    boolean isexpanded;

    public JournalCard(String datestamp, String timestamp, String title, String description, String plant) {
        this.datestamp = datestamp;
        Timestamp = timestamp;
        Title = title;
        this.description = description;
//        this.image = image;
        this.plant = plant;
        this.isexpanded = false;
    }

    public boolean isIsexpanded() {
        return isexpanded;
    }

    public void setIsexpanded(boolean isexpanded) {
        this.isexpanded = isexpanded;
    }

    public String getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(String datestamp) {
        this.datestamp = datestamp;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }
}

