package com.minesweeper.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void testGridInitialization() {
        Grid grid = new Grid(5);
        assertEquals(5, grid.getSize());
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                Square sq = grid.getSquare(i, j);
                assertNotNull(sq);
                assertFalse(sq.isMine());
                assertFalse(sq.isRevealed());
                assertEquals(0, sq.getAdjacentMines());
            }
        }
    }

    @Test
    void testPlaceMines() {
        Grid grid = new Grid(5);
        grid.placeMines(5);
        int mineCount = grid.getMineCount();
        assertEquals(5, mineCount);

        // Ensure all squares are still valid
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                assertNotNull(grid.getSquare(i, j));
            }
        }
    }


    @Test
    void testCalculateAdjacentMines() {
        Grid grid = new Grid(3);
        // Manually set mines for predictable result
        grid.getSquare(0, 0).setMine(true);
        grid.getSquare(0, 1).setMine(true);
        grid.calculateAdjacentMines();

        assertEquals(2, grid.getSquare(1, 0).getAdjacentMines());
        assertEquals(2, grid.getSquare(1, 1).getAdjacentMines());
        assertEquals(1, grid.getSquare(1, 2).getAdjacentMines());
        assertEquals(0, grid.getSquare(2, 2).getAdjacentMines());
    }

    @Test
    void testGetMineCount() {
        Grid grid = new Grid(4);
        grid.getSquare(0, 0).setMine(true);
        grid.getSquare(1, 1).setMine(true);
        grid.getSquare(2, 2).setMine(true);
        assertEquals(3, grid.getMineCount());
    }
}
