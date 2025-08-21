package com.minesweeper;

import com.minesweeper.service.Game;
import java.util.Scanner;

import static com.minesweeper.util.GameUtil.getValidGridSize;
import static com.minesweeper.util.GameUtil.getValidMineCount;
/**
 * Entry point for the Minesweeper game.
 * This class handles user interaction for starting and replaying the game.
 **/
public class MineSweeper {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("Welcome to Minesweeper!");

            int size = getValidGridSize(scanner);
            int numMines = getValidMineCount(scanner, size);

            try {
                Game game = new Game(size, numMines);
                game.play(scanner);

                System.out.println("Press any key to play again or type EXIT to quit..");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    playAgain = false;
                    System.out.println("Thanks for playing!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Exiting the game.");
                playAgain = false;
            }
        }

        // Close scanner
        scanner.close();
    }


}