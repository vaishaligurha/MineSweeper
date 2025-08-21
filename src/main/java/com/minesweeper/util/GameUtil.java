package com.minesweeper.util;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Utility class for Minesweeper game input validation.
 **/
public class GameUtil {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 26;// Limited by A-Z row labels
    private static final double MAX_MINE_DENSITY = 0.35; // Maximum allowed mine density

    public static  int getValidGridSize(Scanner scanner) {
        int size = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the size of the grid (e.g. " + MIN_SIZE + " for a " + MIN_SIZE + "x" + MIN_SIZE + " grid, min " + MIN_SIZE + ", max " + MAX_SIZE + "): ");
            try {
                size = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                if (size < MIN_SIZE) {
                    System.out.println("Grid size must be at least " + MIN_SIZE + ". Please try again.");
                } else if (size > MAX_SIZE) {
                    System.out.println("Grid size cannot exceed " + MAX_SIZE + " (limited by row labels A-Z). Please try again.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return size;
    }

    public static int getValidMineCount(Scanner scanner, int size) {
        int numMines = 0;
        boolean validInput = false;
        int maxMines = (int) (size * size * MAX_MINE_DENSITY);

        while (!validInput) {
            System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
            try {
                numMines = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                if (numMines <= 0) {
                    System.out.println("Number of mines must be greater than 0. Please try again.");
                } else if (numMines > maxMines) {
                    System.out.println("Number of mines exceeds the maximum allowed. Setting to " + maxMines);
                    numMines = maxMines;
                    validInput = true;
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return numMines;
    }
}
