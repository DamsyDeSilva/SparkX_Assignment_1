package Controllers.Implementations;

/**
 *  Program :   Model and Implement a cricket game
 *  Author  :   Damsy De Silva
 *  Date    :   23/04/2021
 *  Description :      
 *      This java file contains class extends the Match class and implement the five over game
 */

import java.util.Random;
import java.util.Scanner;

import Controllers.Match;
import Models.*;

public class FiveOverMatch extends Match {

    public final static int NO_OF_OVERS = 5;
    public final static int NO_OF_BALLS = 3;
    public final static int TOT_NO_OF_BALLS = NO_OF_OVERS * NO_OF_BALLS;

    public final static String NOTOUT = "NotOut"; 
    public final static String CAUGHT_OUT = "Caught"; 
    public final static String BOWLED_OUT = "Bowled"; 

    @Override
    protected int playInning(Team currentTeam, int targetScore) {

        System.out.println("-------- Team " + currentTeam.getTeamName() + " started to bat --------");
        Random randomGen = new Random();

        Player player = currentTeam.getCurrentPlayer();
        player.setIsPlaying(true);
        player.setWicketType(NOTOUT);

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < TOT_NO_OF_BALLS; i++) {
            System.out.println("Press p to play  : ");
            String input = scanner.nextLine();

            while (input == null || !input.equalsIgnoreCase("p")) {
                System.out.println("Invalid Input -- Press p to play : ");
                input = scanner.nextLine();
            }

            /**
             * Runs can be 0,1,2,3,4,6 Using 5 - caught, 7 - boweld (modes of getting out)
             */
            int run_value = randomGen.nextInt(8);

            if (run_value == 5 || run_value == 7) {
                player.setIsPlaying(false);
                String wicketType = (run_value == 5) ? CAUGHT_OUT : BOWLED_OUT;
                System.out.println("Player " + player.getId() + " got out by " + wicketType);
                player.setWicketType(wicketType);
                player = currentTeam.updateCurrentPlayer();

                if (player == null) {
                    System.out.println("Team " + currentTeam.getTeamName() + " All out !!!");
                    break;
                }

                System.out.println("Player " + player.getId() + " is now playing");
                run_value = 0; // when player got out run_value should not be added

            } else {
                System.out.println(run_value + " runs scored");
                player.updateScore(run_value);
            }

            currentTeam.updateScore(run_value);
            currentTeam.updateOversPlayed(i + 1, NO_OF_BALLS);
            System.out.println("   Score : " + currentTeam.getTeamScore() + " \n" + "   Overs : "
                    + currentTeam.getOversPlayed() + "\n" + "   Wickets : " + currentTeam.getNumOfWickets());

            if (targetScore > -1 && currentTeam.getTeamScore() > targetScore) {
                break;
            }
        }
        return currentTeam.getTeamScore();
    }
  
}
