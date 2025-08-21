package com.minesweeper.service;

import com.minesweeper.model.Grid;
import com.minesweeper.model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Grid grid;

    @BeforeEach
    void setUp() {
        game = new Game(3, 1); // 3x3 grid, no mines
        grid = getGridFromGame(game);
    }

    private Grid getGridFromGame(Game game) {
        try {
            Field field = Game.class.getDeclaredField("grid");
            field.setAccessible(true);
            return (Grid) field.get(game);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testRevealSafeSquare() throws Exception {
        grid.getSquare(0, 0).setMine(true);
        grid.calculateAdjacentMines();

        // Reveal a safe square
        Method revealMethod = Game.class.getDeclaredMethod("reveal", int.class, int.class);
        revealMethod.setAccessible(true);
        revealMethod.invoke(game, 1, 1);

        Square square = grid.getSquare(1, 1);
        assertTrue(square.isRevealed());
        assertFalse(game.checkWin());
    }

    @Test
    void testRevealMineEndsGame() throws Exception {
        grid.getSquare(0, 0).setMine(true);
        grid.calculateAdjacentMines();

        Method revealMethod = Game.class.getDeclaredMethod("reveal", int.class, int.class);
        revealMethod.setAccessible(true);
        revealMethod.invoke(game, 0, 0);

        Square square = grid.getSquare(0, 0);
        assertTrue(square.isRevealed());
        assertFalse(game.checkWin());
    }

    @Test
    void testWinCondition() throws Exception {
        // No mines, reveal all squares
        Method revealMethod = Game.class.getDeclaredMethod("reveal", int.class, int.class);
        revealMethod.setAccessible(true);

        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                revealMethod.invoke(game, i, j);
            }
        }

        assertTrue(game.checkWin());
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                assertTrue(grid.getSquare(i, j).isRevealed());
            }
        }
    }

}
