package com.example.carr63.hello_world;

import java.util.ArrayList;

/**
 * Created by carr63 on 10/15/16.
 */
public class Card {
    private String word;
    private String definition;

    /**
     * @constructor
     * @param word the word side of the card
     * @param definition the definition side of the card
     */
    public Card(String word, String definition){
        this.word = word;
        this.definition = definition;
    }

    /**
     * @return the word side
     */
    public String getWord(){
        return word;
    }

    /**
     * @return the definition side
     */
    public String getDefinition(){
        return definition;
    }

    /**
     * @param word changes the word side to this
     */
    public void setWord(String word){
        this.word = word;
    }

    /**
     * @param definition changes the definition side to this
     */
    public void setDefinition(String definition){
        this.definition = definition;
    }
}
