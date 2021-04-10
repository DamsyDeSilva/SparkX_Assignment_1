/**
 *  Program :   Model and Implement a cricket game
 *  Author  :   Damsy De Silva
 *  Date    :   31/03/2021
 *  Description :      
 *      This java file contains the class structure for creating the team instances
 */

import java.util.ArrayList;


public class Team {
    
    private String name;
    private ArrayList<Player> player_List;
    private int teamScore;
    private String oversPlayed;
    private int numOfWickets;
    private boolean isWonToss;
    private int currentPlayer;

    // class constructor
    Team(String name, int numOfPlayers){
        this.name = name;
        this.teamScore = 0;
        this.numOfWickets = 0;
        this.currentPlayer = 0;
        this.isWonToss = false;
        this.oversPlayed = "0.0 over";
        initPlayers(numOfPlayers);
    }

    // method to initialize the player list
    public void initPlayers(int numOfPlayers){
        this.player_List = new ArrayList<Player>();
        for (int i = 0; i < numOfPlayers; i++){
            Player player = new Player(i+1);
            player_List.add(player);
        }
    }

    public String getTeamName(){
        return this.name;
    }

    public int getTeamScore(){
        return this.teamScore;
    }

    public void updateScore(int run_value){
        this.teamScore += run_value;
    }

    public boolean getWonToss(){
        return this.isWonToss;
    }

    public void setWonToss(boolean tossStatus){
        this.isWonToss = tossStatus;
    }

    public int getNumOfWickets(){
        return this.numOfWickets;
    }

    public Player getCurrentPlayer(){
        return this.player_List.get(currentPlayer);
    }

    public Player updateCurrentPlayer(){
        this.currentPlayer++;
        this.numOfWickets++;

        if (this.currentPlayer >= this.player_List.size()){
            return null;
        }
        Player newPlayer = this.player_List.get(this.currentPlayer);
        newPlayer.setIsPlaying(true);
        newPlayer.setWicketType("NotOut");
        return newPlayer;
    }

    public void updateOversPlayed(int ballNumber){
        this.oversPlayed =  "" + (ballNumber / Match.NO_OF_BALLS) + "." + (ballNumber % Match.NO_OF_BALLS) + " overs" ;
    }

    public String getOversPlayed(){
        return this.oversPlayed;
    }

    public ArrayList<Player> getPlayerList(){
        return this.player_List;
    }
}
