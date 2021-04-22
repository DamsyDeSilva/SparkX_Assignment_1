package Models;

/**
 *  Program :   Model and Implement a cricket game
 *  Author  :   Damsy De Silva
 *  Date    :   31/03/2021
 *  Description :      
 *      This java file contains the Model structure for creating the Player instances
 */

public class Player{
    
    private int id;             //  Player id : Player1, player2, ...
    private int score;
    private boolean isPlaying;  // to kepp track whether the palyer has come to play or not
    private String wicketType;  // to store how player got out 

    // class constructor
    Player(int id){
        this.id = id;
        this.score = 0;
        this.isPlaying = false;
        this.wicketType = "";
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setWicketType(String wicketType){
        this.wicketType = wicketType;
    }
    
    public String getWicketType(){
        return this.wicketType;
    }

    public void setIsPlaying(boolean playingStatus){
        this.isPlaying = playingStatus;
    }

    public boolean getIsPlaying(){
        return this.isPlaying;
    }

    public void updateScore(int run_value){
        this.score += run_value;
    }

    public int getScore(){
        return this.score;
    }

}