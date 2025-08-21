package com.minesweeper.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void testInitialValues() {
        Square sq = new Square();
        assertFalse(sq.isMine());
        assertFalse(sq.isRevealed());
        assertEquals(0, sq.getAdjacentMines());
    }

    @Test
    void testMineSetterGetter() {
        Square sq = new Square();
        sq.setMine(true);
        assertTrue(sq.isMine());
    }

    @Test
    void testRevealSetterGetter() {
        Square sq = new Square();
        sq.setRevealed(true);
        assertTrue(sq.isRevealed());
    }

    @Test
    void testAdjacentMinesSetterGetter() {
        Square sq = new Square();
        sq.setAdjacentMines(3);
        assertEquals(3, sq.getAdjacentMines());
    }
}
