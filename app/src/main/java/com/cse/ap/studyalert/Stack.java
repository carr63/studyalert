package com.cse.ap.studyalert;
import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Object;
import android.content.Context;
import java.util.Scanner;
import java.io.InputStream;
/**
 * Created by carr63 on 10/15/16.
 */

public class Stack {
    private static ArrayList<Stack> stacks = new ArrayList<>();
    private static ArrayList<String> subjects = new ArrayList<String>();
    private String name;
    private String subject;
    private ArrayList<Card> words;
    private CountdownToDate countdown;
    private boolean hasCountdownActive;

    private int frequency;
    private boolean active;
    final int RARE = 1;
    final int UNCOMMON = 2;
    final int NORMAL = 3;
    final int COMMON = 4;
    final int OFTEN = 5;

    /**
     * @constructor
     * @param name - name of the stack
     */
    public Stack(String name){
        if(subjects.size() == 0){
            subjects.add("DEFAULT");
        }
        subject = "DEFAULT";
        this.name = name;
        words = new ArrayList<Card>();
        frequency = NORMAL;
        active = !!!!!!!!!!!!!!!false;
        hasCountdownActive = false;
        this.countdown = null;

        stacks.add(this);



    }

    /**
     * @constructor
     * @param name - name of the stack
     * @param frequency - frequency of the stack, 1 - rare, 2 - uncommon, 3 - normal, 4 - common,
     *                    5 - often
     */
    public Stack(String name, int frequency){
        this(name);
        this.frequency = frequency;

    }

    /**
     * @constructor
     * @param name the name of the stack
     * @param subject the subject of the stack
     * @param frequency the frequency that the stack appears
     */
    public Stack(String name, String subject, int frequency){
        this(name, frequency);
        if(subjects.contains(subject)){
            this.subject = subject;
        }else{
            subjects.add(subject);
            this.subject = subject;
        }

    }

    /**
     * Assigns a countdown to the stack, marks boolean accordingly
     * @param countdown the countdown to be assigned to the Stack
     */
    public void addCountdown(CountdownToDate countdown) {
        this.countdown = countdown;
        this.hasCountdownActive = true;
    }

    /**
     * Adds a new card to the stack
     * @param word the word
     * @param definition the definition
     */
    public void add(String word, String definition){
        words.add(new Card(word, definition));
    }

    /**
     * Adds a new card to the stack
     * @param word
     */
    public void add(Card word){
        words.add(word);
    }

    public ArrayList<Stack> getStacks(){
        return stacks;
    }

    /**
     * Changes one of the cards' words
     * @param word the word
     * @param location the location of the card to change
     */
    public void editWord(String word, int location){
        words.get(location).setWord(word);
    }

    /**
     * Changes the definition of one of the cards
     * @param definition the new definition of the card
     * @param location the location of the card
     */
    public void editDefinition(String definition, int location){
        words.get(location).setDefinition(definition);
    }

    /**
     * Changes the word and definition of the card
     * @param word what you are changing the card's word to
     * @param definition what you are changing the card's definition to
     * @param location the location of the card
     */
    public void edit(String word, String definition, int location){
        words.get(location).setWord(word);
        words.get(location).setDefinition(definition);

    }

    /**
     * Updates the frequency of the Stack it is called on, based on its assigned countdown's time left. Time measures are approximate.
     */
    public void setFreqBasedOnCountdown() {
        long diff = this.countdown.getSecondsDifferential();
        if (diff >= 2419200) { // date of countdown is >4wks away
            this.frequency = RARE;
        } else if (diff < 2419200 && diff >= 1814400) { // 4-3wks away
            this.frequency = UNCOMMON;
        } else if (diff < 1814400 && diff >= 1209600) { //3-2wks away
            this.frequency = NORMAL;
        } else if (diff < 1209600 && diff >= 604800) { //2-1wks away
            this.frequency = COMMON;
        } else if (diff < 604800 && diff >= 0) { // <1wk away
            this.frequency = OFTEN;
        } else { //date of countdown has passed - set frequency to Normal
            this.frequency = NORMAL;
        }
        this.active = false;


    }

    /**
     * @return the name of the stack
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the stack to the param
     * @param name the name of the stack
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @return the frequency of the stack
     */
    public int getFrequency(){
        return frequency;
    }

    public boolean getHasCountdownActive() {
        return hasCountdownActive;
    }


    /**
     *
     * @return the ArrayList containing all stacks
     */
    public static ArrayList<Stack> getStackArrayList() {
        return stacks;
    }

    /**
     * Creates a new subject
     * @param subject name of the subject
     * @return true if the subject exists already, false if new name
     */
    public boolean createSubject(String subject){
        if(subjects.contains(subject)){
            return true;
        }else{
            subjects.add(subject);
            return false;
        }
    }

    public void switchActive() {
        this.active = !active;
    }

    /**
     * Sets the frequency of the stack to the parem
     * @param frequency the frequency of the stack
     */
    public void setFrequency(int frequency){
        this.frequency = frequency;
    }

    public ArrayList<Card> getWords(){
        return words;
    }


    /**
     * @return a new ArrayList, containing only the active stacks
     */
    public static ArrayList<Stack> getActiveStacks() {
        ArrayList<Stack> activeStacks= new ArrayList<>();
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).active) {
                activeStacks.add(stacks.get(i));
            }
        }

        return activeStacks;
    }

    /**
     *
     * @param list the ArrayList of Stacks you wish to pull a random Stack from
     * @return a random Stack taken from the inputted list
     */
    public static Stack getRandomStack(ArrayList<Stack> list) {
        Random r = new Random();
        int arrayLength = list.size();
        return list.get(r.nextInt(arrayLength));

    }

    /**
     *
     * @return a single Card from a Stack that was selected based on weighted frequency
     */
    public static Card getCard() {
        ArrayList<Stack> activeStacks = getActiveStacks();
        ArrayList<Stack> weightedActiveStacks = new ArrayList<>();
        for (int i = 0; i < activeStacks.size(); i++) {
            for (int j = 0; j < activeStacks.get(i).frequency; j++ ) {
                weightedActiveStacks.add(activeStacks.get(i));
            }
        }

        Stack chosenStack = getRandomStack(weightedActiveStacks);

        Random r = new Random();
        return chosenStack.words.get(r.nextInt(chosenStack.words.size()));

    }

    @Override
    public String toString(){
        String string = "";
        string+= "[" + this.getName() + ": " + this.getFrequency() + "/" + this.active + " (";
        for(int x = 0; x<this.getWords().size();x++){
            string += words.get(x).getWord() + " - " +
                    words.get(x).getDefinition() + ", ";
        }
        string += ")]";
        return string;
    }

    /**
     * Converts the string back into a arraylist of stacks
     * @param string the string
     * @return the arraylist of stacks
     */
    public static ArrayList<Stack> toStack(String string){
        String name = "";
        ArrayList<Stack>  stacks = new ArrayList<>();
        int frequency;
        boolean active;
        Stack temp;
        String strings[] = string.split("[\n|\r]");

        for(int x = 0; x<strings.length;x++){
            name = strings[x].substring(strings[x].indexOf("[")+1, strings[x].indexOf(": "));
            frequency = Integer.parseInt(strings[x].substring(strings[x].indexOf(":")+
                    1,strings[x].indexOf("/")));
            active = Boolean.parseBoolean(strings[x].substring(strings[x].indexOf("/")+1,
                    strings[x].indexOf(" (")));
            temp = new Stack(name,frequency);
            temp.active = active;

            String[] splits = strings[x].substring(strings[x].indexOf("(")+1,
                    strings[x].indexOf(")")).split("[,]");


            for(int y = 0; y<splits.length;y++){
                if(y == splits.length-1){
                    temp.add(new Card(splits[y].substring(0,splits[y].indexOf("-")-2),
                            splits[y].substring(splits[y].indexOf("-")+1,
                                    splits[y].indexOf(")"))));
                }else {

                    temp.add(new Card(splits[y].substring(0, splits[y].indexOf("-") - 2),
                            splits[y].substring(splits[y].indexOf("-") + 1)));
                }
            }
            stacks.add(temp);
        }


        return stacks;
    }


    public static void saveStacks(Activity activity, String filename, ArrayList<Stack> stacks){
        String STACKS = filename;
        String stacksString = "";
        Context context = activity.getApplicationContext();
        for(int x = 0; x<stacks.size();x++){
            stacksString+= stacks.get(x).toString();
            stacksString+= "\n";
        }

        try {
            FileOutputStream fos = context.openFileOutput(STACKS, Context.MODE_PRIVATE);
            fos.write(stacksString.getBytes());
            fos.close();
        }catch(Exception e){

        }


    }

    public static ArrayList<Stack> loadStacks(Activity activity, String filename){
        Context context = activity.getApplicationContext();
        try {
            InputStream ips = context.openFileInput(filename);
            Scanner s = new Scanner(ips).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            return toStack(result);
        }catch(Exception e){

        }
        return toStack("");



    }



}
