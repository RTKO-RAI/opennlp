package org.example;


public class RegisterHoursData {
    String hours;
    String date;

    public RegisterHoursData(String hours, String date) {
        this.hours = hours;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDate() {
        return this.date;
    }

    public String getHours() {
        return this.hours;
    }
}
