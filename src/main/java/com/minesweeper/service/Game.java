package com.minesweeper.service;

import com.minesweeper.model.Grid;
import com.minesweeper.model.Square;

import java.util.Scanner;
/**
 * Represents a MineSweeper game.
 * This class handles the game logic including user input, revealing squares,
 * checking win conditions, and displaying the grid. It uses recursion to
 * auto-reveal safe squares (squares with 0 adjacent mines).
 **/
public class Game {
    private final Grid grid;
    private boolean gameOver;

    public Game(int size, int numMines) {
        this.grid = new Grid(size);
        grid.placeMines(numMines);
        grid.calculateAdjacentMines();
    }

    public void play(Scanner scanner) {
        while (!gameOver) {
            displayGrid();
            System.out.print("Select a square to reveal (e.g. A1): ");
            String input = scanner.nextLine().trim();

            if (isValidInput(input)) {
                int row = Character.toUpperCase(input.charAt(0)) - 'A';
                int col = Integer.parseInt(input.substring(1)) - 1;

                    Square square = grid.getSquare(row, col);

                    if (square.isRevealed()) {
                        System.out.println("This square has already been revealed. Choose another one.");
                        continue;
                    }
                        // check for mine,reveal the square and  connected non mine squares if selected square has no adjacent mines
                        reveal(row, col);
                        System.out.println("This square contains " + square.getAdjacentMines() + " adjacent mines.");
                        System.out.println();

                        // Check if player won
                        if (checkWin()) {
                            displayGrid();
                            System.out.println("Congratulations, you have won the game!");
                            gameOver = true;
                        }
            } else {
                System.out.println("Invalid input. Please use the format like 'A1' and the square should be with in the GRID.");
            }
        }
    }


    private boolean isValidInput(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }
        char rowChar = Character.toUpperCase(input.charAt(0));
        if (rowChar < 'A' || rowChar >= 'A' + grid.getSize()) {
            return false;
        }
        try {
            int col = Integer.parseInt(input.substring(1));
            if (col < 1 || col > grid.getSize()) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void reveal(int row, int col) {
            Square square = grid.getSquare(row, col);
            if (square.isRevealed()) {
                return;
            }
            square.setRevealed(true);

            if (square.isMine()) {
                displayGrid();
                System.out.println("Oh no, you detonated a mine! Game over.");
                gameOver = true;
            } else if (square.getAdjacentMines() == 0) {
                // Auto-reveal surrounding squares
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (i >= 0 && i < grid.getSize() && j >= 0 && j < grid.getSize()) {
                            reveal(i, j);
                        }
                    }
                }
            }
    }



    public boolean checkWin() {
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (!grid.getSquare(i, j).isMine() && !grid.getSquare(i, j).isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void displayGrid() {
        System.out.println();
        System.out.println("Here is your minefield:");
        // Print column headers
        System.out.print("  ");
        for (int i = 1; i <= grid.getSize(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Print grid
        for (int i = 0; i < grid.getSize(); i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < grid.getSize(); j++) {
                Square square = grid.getSquare(i, j);
                if (square.isRevealed()) {
                    if (square.isMine()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(square.getAdjacentMines() + " ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}