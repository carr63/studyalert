package com.example.carr63.hello_world;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by izanger on 10/16/16.
 */
public class CountdownToDate {
    private long targetDateInSeconds;
    private long secondsUntilTargetDate;
    private long currentDateInSeconds;
    private String targetDateStringFormat;
    private String title; //examples may include, "Calculus Test Day", "CS180/MA165 Finals"
    private static ArrayList<CountdownToDate> countdowns;



    //@AYUSH - user must input target date in the format "MMM dd yyyy". ex: "Dec 16 2016"
    /**
     *
     * @param title A label for the countdown; see comment by instance variable for examples
     * @param targetDateStringFormat The date the countdown is counting
     */
    public CountdownToDate(String title, String targetDateStringFormat) {
        this.title = title;
        this.targetDateStringFormat = targetDateStringFormat;
        this.targetDateInSeconds = longDateToSeconds(targetDateStringFormat);
        this.currentDateInSeconds = getCurrentDateInSeconds();
        this.secondsUntilTargetDate = getSecondsDifferential();

        countdowns.add(this);

    }

    /**
     *
     * @param longFormatDate a date in the "MMM dd yyyy" format
     * @return the timestamp, in seconds, of the given date
     */
    public static long longDateToSeconds(String longFormatDate){
        DateFormat format = new SimpleDateFormat("MMM dd yyyy");
        long milliseconds;
        try {
            milliseconds = format.parse(longFormatDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            milliseconds = 0;
        }
        long seconds = milliseconds / 1000;
        return seconds;

    }

    /**
     *
     * @return The timestamp, in seconds, of the the current date
     */
    public static long getCurrentDateInSeconds() {
        return System.currentTimeMillis();

    }

    /**
     *
     * @return The difference between the target date and the current date, in seconds (AKA how long before the target date)
     */
    public long getSecondsDifferential() {
        long diff = targetDateInSeconds - currentDateInSeconds;
        return diff;
    }

    /**
     * Updates a countdown's current date and differential based on when the method is called
     */
    public void updateCountdown() {
        this.currentDateInSeconds = getCurrentDateInSeconds();
        this.secondsUntilTargetDate = getSecondsDifferential();

    }


    // @AYUSH run this every time the app starts (input will be "countdowns", the arraylist of all countdowns
    /**
     *
     * @param list the ArrayList containing all objects of the CountdownToDate class.
     */
    public static void updateAllCountdowns(ArrayList<CountdownToDate> list) {
        for (int i = 0; i < countdowns.size(); i++) {
            countdowns.get(i).updateCountdown();
        }

        for (int i = 0; i < Stack.getStackArrayList().size(); i++) {
            if (Stack.getStackArrayList().get(i).getHasCountdownActive() == true) {
                Stack.getStackArrayList().get(i).setFreqBasedOnCountdown();
            }
        }

    }
}
