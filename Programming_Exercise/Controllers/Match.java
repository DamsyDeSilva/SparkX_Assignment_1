package Controllers;

/**
 *  Program :   Model and Implement a cricket game
 *  Author  :   Damsy De Silva
 *  Date    :   31/03/2021
 *  Description :      
 *      This java file contains class structure for creating the Player instances
 */

import Models.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Match {
    
    public final static int NO_OF_TEAMS = 2;
    public final static int NO_OF_PLAYERS = 6;

    public static int targetScore = -1;
    public static int chasedScore = -1;

    protected abstract int playInning(Team currentTeam, int targetScore);

    // get team names and set toss and start the game
    public void startGame() {

        ArrayList<Team> team_List = new ArrayList<Team>();
        createTeams(team_List);
        
        // Toss
        Random random = new Random();
        int toss = random.nextInt(2);

        // if toss == 0 -> toss won --> will bat first
        Team firstBatTeam = team_List.get(toss);
        Team secondBatTeam = team_List.get(Math.abs(toss - 1));

        firstBatTeam.setWonToss(true);
        secondBatTeam.setWonToss(false);

        System.out.println("Team " + firstBatTeam.getTeamName() + " won the toss and will bat first");

        // play the innings
        targetScore = playInning(firstBatTeam, targetScore);
        System.out.println("\n---- Team " + firstBatTeam.getTeamName() + " scored " + targetScore + " runs and Team "
                + secondBatTeam.getTeamName() + " need " + (targetScore + 1) + " runs to win ---");
        chasedScore = playInning(secondBatTeam, targetScore);

        // after both innings
        if (targetScore > chasedScore) {
            System.out.println("\n---- Team " + firstBatTeam.getTeamName() + " won by " + (targetScore - chasedScore)
                    + " runs ----");
        } else if (targetScore < chasedScore) {
            System.out.println("\n---- Team " + secondBatTeam.getTeamName() + " won by "
                    + (NO_OF_PLAYERS - secondBatTeam.getNumOfWickets()) + " wickets ----");
            // NO_OF_PLAYERS = Maximum wickets
        } else {
            System.out.println("\n---- Match has drawn!! ----");
        }

        System.out.println("\n------------ ScoreBoard --------------");
        displayScoreboard(firstBatTeam);
        System.out.println("\n--------------------------------------");
        displayScoreboard(secondBatTeam);
        System.out.println("\n--------------------------------------");

    }

    private void createTeams(ArrayList<Team> team_List){

        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < NO_OF_TEAMS; i++) {
            System.out.println("Insert Team Name " + (i + 1) + " : ");
            String teamName = scanner.nextLine();

            while (teamName == null || teamName.isEmpty()) {
                System.out.println("Insert a valid Team Name");
                teamName = scanner.nextLine();
            }
            Team team = new Team(teamName, NO_OF_PLAYERS);
            team_List.add(team);
        }
    }

    private void displayScoreboard(Team team) {
        System.out.println("Team " + team.getTeamName() + " :   " + team.getTeamScore() + " / " + team.getNumOfWickets()
                + "    " + team.getOversPlayed());
        ArrayList<Player> playerList = team.getPlayerList();
        for (int i = 0; i < playerList.size(); i++) {
            Player player = playerList.get(i);
            if (player.getWicketType().equals("")) {
                System.out.println("Player_" + player.getId());
            } else {
                System.out.println(
                        "Player_" + player.getId() + "      " + player.getWicketType() + "      " + player.getScore());
            }
        }
    }
}
