package com.example.carr63.hello_world;

import java.util.ArrayList;

/**
 * Created by carr63 on 10/15/16.
 */
public class Stack {
    private static ArrayList<String> subjects = new ArrayList<String>();
    private String name;
    private String subject;
    private ArrayList<Card> words;

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
        active = false;
    }

    /**
     * @constructor
     * @param name - name of the stack
     * @param frequency - frequency of the stack, 0 - rare, 1 - uncommon, 2 - normal, 3 - common,
     *                    4 - often
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
}
