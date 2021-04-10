/**
 *  Program :   Model and Implement a cricket game
 *  Author  :   Damsy De Silva
 *  Date    :   31/03/2021
 *  Description :      
 *      This java file contains class structure for creating the Player instances
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Match {
    public final static int NO_OF_TEAMS = 2;
    public final static int NO_OF_OVERS = 5;
    public final static int NO_OF_BALLS = 3;
    public final static int NO_OF_PLAYERS = 6;
    public final static int TOT_NO_OF_BALLS = NO_OF_OVERS * NO_OF_BALLS;

    public static int targetScore = -1;
    public static int chasedScore = -1;

    // get team names and set toss and start the game
    public void startGame(){

        Scanner scanner = new Scanner(System.in);
        ArrayList<Team> team_List = new ArrayList<Team>();
            
        // creating teams
        for (int i = 0; i < NO_OF_TEAMS; i++){
            System.out.println("Insert Team Name " + (i+1) + " : ");
            String teamName = scanner.nextLine();

            while(teamName == null || teamName.isEmpty()){
                System.out.println("Insert a valid Team Name");
                teamName = scanner.nextLine();
            }
            Team team = new Team(teamName, NO_OF_PLAYERS);
            team_List.add(team);
        }

        // Toss 
        Random random = new Random();
        int toss = random.nextInt(1);

        // if toss == 0 -> toss won --> will bat first
        Team firstBatTeam = team_List.get(toss);
        Team secondBatTeam = team_List.get(Math.abs(toss-1));
            
        firstBatTeam.setWonToss(true);
        secondBatTeam.setWonToss(false);

        System.out.println("Team " + firstBatTeam.getTeamName() + " won the toss and will bat first");

        // play the innings
        targetScore = playInning(firstBatTeam);
        System.out.println("\n---- Team " + firstBatTeam.getTeamName() + " scored " + targetScore + " runs and Team " + secondBatTeam.getTeamName() + " need " + (targetScore + 1) + " runs to win ---" );
        chasedScore = playInning(secondBatTeam);

        // after both innings
        if (targetScore > chasedScore){
            System.out.println("\n---- Team " + firstBatTeam.getTeamName() + 
            " won by " + (targetScore - chasedScore) + " runs ----" );
        } 
        else if (targetScore < chasedScore){
            System.out.println("\n---- Team " + secondBatTeam.getTeamName() +
             " won by " + (NO_OF_PLAYERS - secondBatTeam.getNumOfWickets()) + " wickets ----");
            // NO_OF_PLAYERS = Maximum wickets
        }
        else{
            System.out.println("\n---- Match has drawn!! ----");
        }

        System.out.println("\n------------ ScoreBoard --------------");
        displayScoreboard(firstBatTeam);
        System.out.println("\n--------------------------------------");
        displayScoreboard(secondBatTeam);
        System.out.println("\n--------------------------------------");

        // scanner.close();
    }

    // return the total score played from the current team
    private int playInning(Team currentTeam){
        
        System.out.println("-------- Team " + currentTeam.getTeamName() + " started to bat --------");
        Random randomGen = new Random();
        
        Player player = currentTeam.getCurrentPlayer();
        player.setIsPlaying(true);
        player.setWicketType("NotOut");

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < TOT_NO_OF_BALLS; i++){
            System.out.println("Press p to play  : ");
            String input = scanner.nextLine();

            while(input == null || !input.equalsIgnoreCase("p")){
                System.out.println("Invalid Input -- Press p to play : ");
                input = scanner.nextLine();
            }

            /**
             *  Runs can be 0,1,2,3,4,6
             *  Using 5 - caught, 7 - boweld  (modes of getting out)
             */
            int run_value = randomGen.nextInt(7);
            
            if (run_value == 5 || run_value == 7){
                player.setIsPlaying(false);
                String wicketType = (run_value == 5) ?  "caught" : "bowled";  
                System.out.println("Player " + player.getId() + " got out by " + wicketType);
                player.setWicketType(wicketType);
                player = currentTeam.updateCurrentPlayer();
                
                if (player == null){
                    System.out.println("Team "+ currentTeam.getTeamName() +" All out !!!");
                    break;
                }

                System.out.println("Player " + player.getId() + " is now playing");
                run_value = 0;      // when player got out run_value should not be added

            }else{
                System.out.println(run_value + " runs scored");
                player.updateScore(run_value);
            }

            currentTeam.updateScore(run_value);
            currentTeam.updateOversPlayed(i+1);
            System.out.println("   Score : " + currentTeam.getTeamScore() +
                             " \n" + "   Overs : " + currentTeam.getOversPlayed() +
                             "\n" + "   Wickets : " + currentTeam.getNumOfWickets());

            if(targetScore > -1 && currentTeam.getTeamScore() > targetScore){
                break;
            }    
        }
        
        // scanner.close();
        return currentTeam.getTeamScore();
    }  


    private void displayScoreboard(Team team){
        System.out.println("Team " + team.getTeamName() + " :   " + team.getTeamScore() + " / " + team.getNumOfWickets() + "    " + team.getOversPlayed());
        ArrayList<Player> playerList = team.getPlayerList();
        for (int i = 0; i < playerList.size(); i++) {
            Player player = playerList.get(i);
            if (player.getWicketType().equals("")){
                System.out.println("Player_" + player.getId());
            }else{
                System.out.println("Player_" + player.getId() + "      " + player.getWicketType() + "      " + player.getScore());
            }
        }  
    }
}
