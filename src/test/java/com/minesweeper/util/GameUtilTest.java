package com.minesweeper.util;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameUtilTest {

    @Test
    void testGetValidGridSize_withValidInput() {
        String input = "5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int size = GameUtil.getValidGridSize(scanner);
        assertEquals(5, size);
    }

    @Test
    void testGetValidGridSize_withInvalidThenValidInput() {
        String input = "1\n30\n4\n"; // 1 and 30 invalid, 4 valid
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int size = GameUtil.getValidGridSize(scanner);
        assertEquals(4, size);
    }

    @Test
    void testGetValidGridSize_withNonNumericInput() {
        String input = "abc\n3\n"; // non-numeric then valid
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int size = GameUtil.getValidGridSize(scanner);
        assertEquals(3, size);
    }

    @Test
    void testGetValidMineCount_withValidInput() {
        int gridSize = 5; // max mines = 5*5*0.35 = 8
        String input = "7\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int mines = GameUtil.getValidMineCount(scanner, gridSize);
        assertEquals(7, mines);
    }

    @Test
    void testGetValidMineCount_exceedMaxMines() {
        int gridSize = 5; // max mines = 8
        String input = "20\n"; // exceed max, should auto-set to 8
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int mines = GameUtil.getValidMineCount(scanner, gridSize);
        assertEquals(8, mines);
    }

    @Test
    void testGetValidMineCount_withInvalidThenValidInput() {
        int gridSize = 5; // max mines = 8
        String input = "-1\n0\n5\n"; // negative and 0 invalid, 5 valid
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int mines = GameUtil.getValidMineCount(scanner, gridSize);
        assertEquals(5, mines);
    }

    @Test
    void testGetValidMineCount_withNonNumericInput() {
        int gridSize = 5; // max mines = 8
        String input = "abc\n3\n"; // non-numeric then valid
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int mines = GameUtil.getValidMineCount(scanner, gridSize);
        assertEquals(3, mines);
    }
}
