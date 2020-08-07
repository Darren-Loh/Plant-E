package com.example.plant_e;

public class ScreenObject {

    String Title,Description;
    int Screenimg;

    public ScreenObject(String title, String description, int screenimg) {
        Title = title;
        Description = description;
        Screenimg = screenimg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getScreenimg() {
        return Screenimg;
    }

    public void setScreenimg(int screenimg) {
        Screenimg = screenimg;
    }
}

