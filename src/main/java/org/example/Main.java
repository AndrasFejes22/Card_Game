package org.example;

import java.util.*;
import java.util.stream.Collectors;

import static model.PokerHand.highestRank;
import static model.PokerHand.valueCalculator;

public class Main {



    public static final char SPADE = '\u2660';
    public static final char HEART = '\u2665';
    public static final char DIAMOND = '\u2666';
    public static final char CLUB = '\u2663';

    public static void main(String[] args) {

        System.out.println(SPADE);
        System.out.println(HEART);
        System.out.println(DIAMOND);
        System.out.println(CLUB);

        List<String> suits2 = List.of("s", "c", "c", "c");
        Set<String> valuesSet = new HashSet<>(suits2);
        List<String> listDistinct = suits2.stream().distinct().collect(Collectors.toList());

        //System.out.println(valuesSet);
        //System.out.println(listDistinct);
        //String[] pair = {"2d", "Qd", "Jd", "2d", "Ad"};
        String pair = "2S 2H 4H 5S KC";
        System.out.println("pair:");
        System.out.println(valueCalculator(pair));
        System.out.println("----------------------------");
        String twoPairs = "AH AC 5H 6H 5S";
        System.out.println("twoPairs:");
        System.out.println(valueCalculator(twoPairs));
        System.out.println("----------------------------");
        String fullHouse = "2S AH 2H AS AC";
        System.out.println("fullHouse:");
        System.out.println(valueCalculator(fullHouse));
        System.out.println("----------------------------");
        String fourOfAKind = "AS AH 2H AD AC";
        System.out.println("fourOfAKind:");
        System.out.println(valueCalculator(fourOfAKind));
        System.out.println("----------------------------");
        String threeOfAKind = "AH AC 5H 6H AS";
        System.out.println("threeOfAKind:");
        System.out.println(valueCalculator(threeOfAKind));
        System.out.println("----------------------------");
        String highCard = "AH QC 5H 6H KS";
        System.out.println("highCard:");
        System.out.println(valueCalculator(highCard));


        System.out.println("****************************");

        String cards2 = "2S 3H 4H 5S 6C"; // straight
        String cards1 = "4S 5H 6H 7S 8C";

        highestRank(cards1, cards2);

        String cards5 = "2S 3H 4H 5S 6C";
        String cards6 = "2S 3H 4H 5S 6C";

        highestRank(cards5, cards6);

        // Possible suites are: s h d c
        String cards3 = "2S 3H 4H 5S 6C";
        String cards4 = "2S 3H 4H 5S 6C";

        //highestRank(cards3, cards4);

    }





}