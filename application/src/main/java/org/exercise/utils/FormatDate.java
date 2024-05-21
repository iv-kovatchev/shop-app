package org.exercise.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatDate {
    private int day;
    private int month;
    private int year;

    public FormatDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private String formatDate() {
        Date date = new Calendar.Builder()
                .setDate(year, month - 1, day)
                .build()
                .getTime();

        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
