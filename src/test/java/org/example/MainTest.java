package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void sameSuitTest() {
        List<String> flush = List.of("s", "s", "s", "s", "s");
        assertTrue(Main.sameSuits(flush));

        List<String> notFlush = List.of("s", "h", "s", "s", "s");
        assertFalse(Main.sameSuits(notFlush));
    }

    @Test
    void highestIndexTest() {
        List<String> consecutiveValues = List.of("7", "9", "8", "T", "J");
        assertEquals(9, Main.highestIndex(consecutiveValues));

        List<String> consecutiveValues2 = List.of("5", "3", "4", "2", "6");
        assertEquals(4, Main.highestIndex(consecutiveValues2));
    }

    @Test
    void consecutiveValuesTest() {
        //List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");

        List<String> consecutiveValues = List.of("7", "9", "8", "T", "J");
        assertTrue(Main.consecutiveValues(consecutiveValues));


        List<String> consecutiveValues2 = List.of("5", "3", "4", "2", "6");
        assertTrue(Main.consecutiveValues(consecutiveValues2));

        //"T", "J", "Q", "K", "A"
        List<String> consecutiveValues3 = List.of("T", "J", "Q", "K", "A");
        assertTrue(Main.consecutiveValues(consecutiveValues3));

        // not Consecutive Values
        List<String> notConsecutiveValues = List.of("2", "3", "J", "5", "6");
        assertFalse(Main.consecutiveValues(notConsecutiveValues));

    }

    /*
    @Test
    void scoreTest() {// not good yet
        String[] cards = {"2d", "3d", "4d", "5d", "6d"};
        assertEquals(9, Main.score(cards));
    }
    */

    @Test
    void fourOfAKindTest() {
        String[] cards = {"4d", "4d", "4s", "9c", "4h"};
        assertTrue(Main.fourOfAKind(cards));
    }

    @Test
    void threeOfAKindTest() {
        String[] cards = {"9d", "9d", "4s", "9c", "2h"};
        assertTrue(Main.threeOfAKind(cards));
    }

    @Test
    void pairTest() {

        String[] cards = {"9d", "9d", "4s", "6c", "2h"};
        assertTrue(Main.pair(cards));

        String[] cards2 = {"5d", "5c", "4s", "Qc", "Ah"};
        assertTrue(Main.pair(cards2));

        String[] cards3 = {"2d", "2h", "4s", "Jc", "5h"};
        assertTrue(Main.pair(cards3));
    }

    @Test
    void twoPairTest() {

        String[] cards = {"Qd", "Qc", "4s", "4c", "2h"};
        assertTrue(Main.twoPairs(cards));

        String[] cards2 = {"5d", "5c", "4s", "Qc", "Qh"};
        assertTrue(Main.twoPairs(cards2));

        String[] cards3 = {"5d", "2h", "2s", "Jc", "Jh"};
        assertTrue(Main.twoPairs(cards3));
    }

    @Test
    void fullHouseTest() {
        String[] cards2 = {"9c", "9d", "4s", "4c", "4h"};
        assertTrue(Main.fullHouse(cards2));
    }

    @Test
    void StraightFlushTest() {
        String[] cards = {"2d", "3d", "4d", "5d", "6d"};
        assertTrue(Main.StraightFlush(cards));

        String[] cards2 = {"Td", "Jd", "7d", "8d", "9d"};
        assertTrue(Main.StraightFlush(cards2));
    }

    @Test
    void FlushTest() {
        String[] cards = {"2d", "7d", "Jd", "Ad", "4d"};
        assertTrue(Main.Flush(cards));
    }

    @Test
    void highCardTest() {
        String[] cards = {"2d", "7s", "Jc", "Ac", "4h"};
        assertTrue(Main.highCard(cards));
    }

    @Test
    void rankingHighCardsTest() {
        /*
        //"2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"
        List<String> cards1 = new ArrayList<>();
        cards1.add("2");
        cards1.add("4");
        cards1.add("6");
        cards1.add("7");
        cards1.add("9");
        List<String> cards2 = new ArrayList<>();
        cards2.add("7");
        cards2.add("4");
        cards2.add("3");
        cards2.add("6");
        cards2.add("9");
        Main.rankingHighCards(cards1, cards2);
        */

        //Equal cards is tie
        List<String> cards3 = new ArrayList<>();
        cards3.add("3");
        cards3.add("4");
        cards3.add("6");
        cards3.add("7");
        cards3.add("9");
        List<String> cards4 = new ArrayList<>();
        cards4.add("7");
        cards4.add("4");
        cards4.add("3");
        cards4.add("6");
        cards4.add("9");
        assertEquals(Main.Result.TIE, Main.rankingHighCards(cards4, cards3));

    }

    /*
    @Test
    void hasNSameValuesTest() {
        String[] cards = {"2d", "2d", "6d", "5d", "4d"};
        assertTrue(Main.hasNSameValues(cards, 2));

        String[] cards2 = {"9d", "9d", "4s", "9c", "9h"};
        assertTrue(Main.hasNSameValues(cards2, 4));

        String[] cards3 = {"9d", "9d", "4s", "5c", "6h"};
        assertTrue(Main.hasNSameValues(cards3, 2));

    }
    */
}