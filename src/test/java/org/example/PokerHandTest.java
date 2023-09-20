package org.example;

import model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {

    @Test
    void valueCalculatorTest() {
        String cards = "2H 3H 4H 5H 6H";

        //assertEquals("straight flush", PokerHand.valueCalculator(cards));
    }

    @Test
    void sameSuitTest() {
        List<String> flush = List.of("s", "s", "s", "s", "s");
        assertTrue(PokerHand.sameSuits(flush));

        List<String> notFlush = List.of("s", "h", "s", "s", "s");
        assertFalse(PokerHand.sameSuits(notFlush));
    }

    @Test
    void highestIndexTest() {
        List<String> consecutiveValues = List.of("K", "A", "Q", "T", "J");
        //assertEquals(12, PokerHand.highestIndex(consecutiveValues));

        List<String> consecutiveValues2 = List.of("5", "3", "4", "2", "6");
        //assertEquals(4, PokerHand.highestIndex(consecutiveValues2));
    }

    @Test
    void consecutiveValuesTest() {
        //List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");

        List<String> consecutiveValues = List.of("7", "9", "8", "T", "J");
        assertTrue(PokerHand.consecutiveValues(consecutiveValues));


        List<String> consecutiveValues2 = List.of("5", "3", "4", "2", "6");
        assertTrue(PokerHand.consecutiveValues(consecutiveValues2));

        //"T", "J", "Q", "K", "A"
        List<String> consecutiveValues3 = List.of("T", "J", "Q", "K", "A");
        assertTrue(PokerHand.consecutiveValues(consecutiveValues3));

        // not Consecutive Values
        List<String> notConsecutiveValues = List.of("2", "3", "J", "5", "6");
        assertFalse(PokerHand.consecutiveValues(notConsecutiveValues));

    }

    /*
    @Test
    void scoreTest() {// not good yet
        String[] cards = {"2d", "3d", "4d", "5d", "6d"};
        assertEquals(9, Main.score(cards));
    }


    @Test
    void fourOfAKindTest() {
        String cards = "4d 4d 4s 9c 4h";
        assertEquals(8, PokerHand.fourOfAKind(cards));
    }

    @Test
    void threeOfAKindTest() {
        String cards = "9d 9d 4s 9c 2h";
        assertEquals(4, PokerHand.threeOfAKind(cards));
    }

    @Test
    void pairTest() {

        String cards = "9d 9d 4s 6c 2h";
        assertEquals(2, PokerHand.pair(cards));

        String cards2 = "5d 5c 4s Qc Ah";
        assertEquals(2, PokerHand.pair(cards2));

        String cards3 = "2d 2h 4s Jc 5h";
        assertEquals(2, PokerHand.pair(cards3));
    }

    @Test
    void twoPairTest() {

        String cards = "Qd Qc 4s 4c 2h";
        assertEquals(3, PokerHand.twoPairs(cards));

        String cards2 = "5d 5c 4s Qc Qh";
        assertEquals(3, PokerHand.twoPairs(cards2));

        String cards3 = "5d 2h 2s Jc Jh";
        assertEquals(3, PokerHand.twoPairs(cards3));
    }

    @Test
    void fullHouseTest() {
        String cards2 = "9c 9d 4s 4c 4h";
        assertEquals(7, PokerHand.fullHouse(cards2));
    }

    @Test
    void StraightFlushTest() {
        String cards = "2d 3d 4d 5d 6d";
        assertEquals(9, PokerHand.StraightFlush(cards));

    }

    //"2S 3H 4H 5S 6C"
    @Test
    void StraightTest() {
        String cards = "2S 3H 4H 5S 6C";
        assertEquals(5, PokerHand.straight(cards));

    }

    @Test
    void FlushTest() {
        String cards = "2d 7d Jd Ad 4d";
        assertEquals(6, PokerHand.Flush(cards));
    }

    @Test
    void highCardTest() {
        String cards = "2d 7s Jc Ac 4h";
        assertEquals(1, PokerHand.highCard(cards));
    }
    */


    @Test
    void rankingHighCardsTest() {

        //"2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"
        //[6S AD 7H 4S AS]
        //[AH AC 5H 6H 7S]
        PokerHand cards1 = new PokerHand("6S AD 7H 4S AS");
        List<String>card1Values = PokerHand.getValues(cards1);

        PokerHand cards2 = new PokerHand("AH AC 5H 6H 7S");
        List<String>card2Values = PokerHand.getValues(cards2);

        //assertEquals(PokerHand.Result.LOSS, PokerHand.rankingHighCards(card1Values, card2Values));




    }


    @Test
    void highestRankTest() {
        String player = "2H 3H 4H 5H 6H";
        String opponent = "KS AS TS QS JS";
        //assertEquals(PokerHand.Result.LOSS, PokerHand.highestRank(player,opponent));
    }

    @Test
    void valueIndexTest() {
        assertEquals(0, PokerHand.valueIndex("2"));
        assertEquals(12, PokerHand.valueIndex("A"));
    }

    private PokerHand.Result loss = PokerHand.Result.LOSS;
    private PokerHand.Result win = PokerHand.Result.WIN;
    private PokerHand.Result tie = PokerHand.Result.TIE;

    @Test
    public void PokerHandRankingTest()
    {

        //Test("Highest straight flush wins",        loss, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
        //Test("Straight flush wins of 4 of a kind", win,  "2H 3H 4H 5H 6H", "AS AD AC AH JD");
        //full houses
        Test("win", win,  "2H 2C 3S 3H 3D", "2D 2C 3S 2H 3D");
        Test("loss", loss,  "2H 2C 3S 3H 3D", "4D 4C 3S 3H 3D");
        Test("tie", tie,  "2H 2C 3S 3H 3D", "2D 2C 3S 3H 3D");
/*
        Test("Highest 4 of a kind wins",           win,  "AS AH 2H AD AC", "JS JD JC JH 3D");
        Test("4 Of a kind wins of full house",     loss, "2S AH 2H AS AC", "JS JD JC JH AD");
        Test("Full house wins of flush",           win,  "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
        Test("Highest flush wins",                 win,  "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
        Test("Flush wins of straight",             win,  "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C");
        Test("Equal straight is tie", 	  	     tie,  "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S");
        Test("Straight wins of three of a kind",   win,  "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
        Test("3 Of a kind wins of two pair",       loss, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");

        Test("Highest 3 Of a kind wins",            loss, "AH AC 5H 6H AS", "AH AC 7H 6H AS");

        Test("2 Pair wins of pair",                win,  "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
        Test("Highest pair wins",                  loss, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
        Test("Pair wins of nothing",               loss, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
        Test("Highest card loses",                 loss, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
        Test("Highest card wins",                  win,  "4S 5H 6H TS AC", "3S 5H 6H TS AC");
        Test("Equal cards is tie",		        tie,  "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");

 */

    }

    private void Test(String description, PokerHand.Result expected, String playerHand, String opponentHand)
    {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        //assertEquals(description + ":", expected, player.compareWith(opponent));
        assertEquals(expected, player.compareWith(opponent));
    }
}