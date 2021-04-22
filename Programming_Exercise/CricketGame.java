/**
 *  Program :   Model and Implement a cricket game
 *  Author  :   Damsy De Silva
 *  Date    :   31/03/2021
 *  Description :      
 *      This java file contains the main program
 */

import Controllers.*;
import Controllers.Implementations.FiveOverMatch;

public class CricketGame {
    public static void main(String[] args) {
        Match match = new FiveOverMatch();
        match.startGame();        
    }
}
