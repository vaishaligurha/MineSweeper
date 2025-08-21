# Minesweeper

This is a command-line Minesweeper game written in Java.

## How to Play

1.  The game will prompt you for the grid size and the number of mines.
2.  Enter a grid size (e.g., 4 for a 4x4 grid).
3.  Enter the number of mines to place on the grid.
4.  The game will display the minefield.
5.  Select a square to reveal by entering its coordinates (e.g., A1).
6.  If you reveal a mine, the game is over.
7.  If you reveal a square with no adjacent mines, it will automatically reveal its neighbors.
8.  The game is won when all non-mine squares are revealed.
9.  After a game ends, the user can play again or type EXIT to quit


## Assumptions

1.  The grid is square, minimum size 2×2, maximum 26×26 (A-Z rows).
2.  Maximum mines allowed: 35% of total squares, placed randomly.


## Environment

*   Java Development Kit (JDK) 17+
*   Gradle 8.x+

## Instructions to Run

1.  Open a terminal or command prompt.
2.  Navigate to the `MineSweeper` directory
3.  Build the project using Gradle: `gradlew clean build`
4.  Run the application: `java -jar build/libs/MineSweeper-1.0-SNAPSHOT.jar`
