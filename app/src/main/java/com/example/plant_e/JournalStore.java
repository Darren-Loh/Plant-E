package com.example.plant_e;

public class JournalStore {
    private String date;
    private String description;

    public JournalStore(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public JournalStore(String date){
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
