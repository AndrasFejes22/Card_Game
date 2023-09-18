package org.example;

import model.Hand;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public enum Result { TIE, WIN, LOSS }

    public static final char SPADE = '\u2660';
    public static final char HEART = '\u2665';
    public static final char DIAMOND = '\u2666';
    public static final char CLUB = '\u2663';

    public static void main(String[] args) {

        System.out.println(SPADE);
        System.out.println(HEART);
        System.out.println(DIAMOND);
        System.out.println(CLUB);

        // Possible values are: 2 3 4 5 6 7 8 9 T J Q K A
        // Possible suites are: s h d c
        // eg.: "2d", "3d", "4d", "5d", "6d" (straight flush)

        /*
        List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        List<String> suits = List.of("s", "h", "d", "c");
        System.out.println(values.subList(0, 5));

        String[] cards = {"2d", "3d", "Jd", "5d", "6d"};
        System.out.println(hand(cards));

         */

        List<String> suits2 = List.of("s", "c", "c", "c");
        Set<String> valuesSet = new HashSet<>(suits2);
        List<String> listDistinct = suits2.stream().distinct().collect(Collectors.toList());

        //System.out.println(valuesSet);
        //System.out.println(listDistinct);
        //String[] pair = {"2d", "Qd", "Jd", "2d", "Ad"};
        String[] pair = {"9d", "9d", "4s", "6c", "2h"};
        System.out.println("pair:");
        System.out.println(valueCalculator(pair));
        System.out.println("----------------------------");
        String[] twoPairs = {"2d", "2d", "Jd", "Jd", "Ad"};
        System.out.println("twoPairs:");
        System.out.println(valueCalculator(twoPairs));
        System.out.println("----------------------------");
        String[] fullHouse = {"2d", "2d", "2d", "Jd", "Jd"};
        System.out.println("fullHouse:");
        System.out.println(valueCalculator(fullHouse));
        System.out.println("----------------------------");
        String[] fourOfAKind = {"2d", "2d", "2d", "2d", "Ad"};
        System.out.println("fourOfAKind:");
        System.out.println(valueCalculator(fourOfAKind));
        System.out.println("----------------------------");
        String[] threeOfAKind = {"2h", "2c", "2d", "Qd", "Ad"};
        System.out.println("threeOfAKind:");
        System.out.println(valueCalculator(threeOfAKind));

        System.out.println("****************************");

        String[] cards2 = {"2d", "3d", "4d", "5d", "6d"}; // straight
        String[] cards1 = {"Kh", "Ah", "Th", "Jh", "Qh"};

        highestRank(cards1, cards2);

        String[] cards5 = {"2d", "3d", "4d", "5d", "6d"};
        String[] cards6 = {"3h", "2h", "4h", "6h", "5h"};

        highestRank(cards5, cards6);

        // Possible suites are: s h d c
        String[] cards3 = {"2d", "2s", "2h", "2c", "6d"};
        String[] cards4 = {"2d", "Jh", "Jd", "Jh", "Js"};

        //highestRank(cards3, cards4);

    }

    public static int score(String[] cards) {
        return 0;
    }

    public static Hand hand(String[] cards){
        List<String> values = new ArrayList<>();
        List<String> suits = new ArrayList<>();
        // Possible values are: 2 3 4 5 6 7 8 9 T J Q K A
        // Possible suites are: s h d c
        // eg.: "2d", "3d", "4d", "5d", "6d" (straight flush)
        String[] elements = new String[cards.length];
        for (int i = 0; i < cards.length; i++) {
            elements = cards[i].split("");
            values.add(elements[0]);
            suits.add(elements[1]);
        }
        return new Hand(values, suits);
    }

    public static boolean sameSuits(List<String> input){ // flush
        for (int i = 0; i < input.size(); i++) {
            if(!input.get(0).equals(input.get(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean consecutiveValues(List<String> input){ //straight
        Set<String> inputSet = new HashSet<>(input);
        List<String> newValues = new ArrayList<>();
        System.out.println("inputSet: " + inputSet);
        Set<String> valuesSet = new HashSet<>();
        List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        //System.out.println(values.subList(0, 4));
        for (int i = 0; i < values.size()-4; i++) {
            valuesSet = new HashSet<>(values.subList(i, i+5));
            if(valuesSet.equals(inputSet)){
                System.out.println("valuesSet: " + valuesSet);
                newValues.addAll(values.subList(i, i+5));
                System.out.println("newValues: " + newValues);
                return true;
            }
        }
        return false;
    }



    public static boolean fourOfAKind(String[] cards){
       if(valueCalculator(cards).equals("14")){
           return true;
       }
       return false;
    }

    public static boolean threeOfAKind(String[] cards){
        if(valueCalculator(cards).equals("113")){
            return true;
        }
        return false;
    }

    public static boolean pair(String[] cards){
        if(valueCalculator(cards).equals("1112")){
            return true;
        }
        return false;
    }

    public static boolean twoPairs(String[] cards){
        if(valueCalculator(cards).equals("122")){
            return true;
        }
        return false;
    }


    public static boolean fullHouse(String[] cards){
        if(valueCalculator(cards).equals("23")){
            return true;
        }
        return false;
    }

    public static boolean highCard(String[] cards){ //De pl. magas lapnál is ha mondjuk K magas mindenkinek, akkor nézik a 2. lapot
        if(valueCalculator(cards).equals("11111")){
            return true;
        }
        return false;
    }

    public static boolean Flush(String[] cards){
        Hand hand = hand(cards);
        if(sameSuits(hand.getSuits())){
            return true;
        }
        return false;
    }

    public static boolean StraightFlush(String[] cards){
        Hand hand = hand(cards);
        if(consecutiveValues(hand.getValues()) && sameSuits(hand.getSuits())){
            return true;
        }
        return false;
    }

    //////////////////// RANKING ////////////////////

    public static void highestRank(String[] cards1, String[] cards2){ //straight
        //List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");

        Hand hand1 = hand(cards1);
        Hand hand2 = hand(cards2);

        /*
        Set<String> valuesSet1;
        Set<String> valuesSet2;

        List<String> inputList1 = hand1.getValues();
        System.out.println("inputList1: " + inputList1);
        List<String> inputList2 = hand2.getValues();

        Set<String> inputSet1 = new HashSet<>(inputList1);
        System.out.println("inputSet1: " + inputSet1);
        Set<String> inputSet2 = new HashSet<>(inputList2);

        List<String> newValues1 = new ArrayList<>();
        List<String> newValues2 = new ArrayList<>();
        // sorba kell rendezni a values szerint a card-okat
        for (int i = 0; i < values.size()-4; i++) {
            valuesSet1 = new HashSet<>(values.subList(i, i+5));
            if(valuesSet1.equals(inputSet1)){
                System.out.println("valuesSet: " + valuesSet1);
                newValues1.addAll(values.subList(i, i+5));
                System.out.println("newValues1: " + newValues1);
            }
        }
        for (int i = 0; i < values.size()-4; i++) {
            valuesSet2 = new HashSet<>(values.subList(i, i+5));
            if(valuesSet2.equals(inputSet2)){
                System.out.println("valuesSet2: " + valuesSet2);
                newValues2.addAll(values.subList(i, i+5));
                System.out.println("newValues2: " + newValues2);

            }
        }

        // ide az uj listák jönnek
        String hand1FirstValue = newValues1.get(0);
        System.out.println("hand1FirstValue: "+hand1FirstValue);
        String hand2FirstValue = newValues2.get(0);
        //int hand1FirstValueIndex = valueIndex(values, hand1FirstValue);
        //int hand2FirstValueIndex = valueIndex(values, hand2FirstValue);



        for (int i = 0; i < values.size(); i++) {
            if(hand1FirstValue.equals(values.get(i))){
                hand1FirstValueIndex = i;
                System.out.println("card1 first i: " + i);
            }
            if(hand2FirstValue.equals(values.get(i))){
                hand2FirstValueIndex = i;
                System.out.println("card2 first i: " + i);
            }
        }
        */

        int hand1FirstValueIndex = highestIndex(hand1.getValues());
        int hand2FirstValueIndex = highestIndex(hand2.getValues());
        if(hand1FirstValueIndex > hand2FirstValueIndex){
            System.out.println("Highest straight flush (card1) wins ("+ hand1.getValues() + ")");
        } else if(hand1FirstValueIndex < hand2FirstValueIndex) {
            System.out.println("Highest straight flush (card2) wins ("+ hand2.getValues() + ")");
            System.out.println(Result.WIN);
        } else {
            System.out.println("Equal straight is tie");
            System.out.println(Result.TIE);
        }

    }

    public static Result rankingHighCards(List<String>  cards1, List<String>  cards2) { //"2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"
        List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        //Hand hand1 = hand(cards1);
        //Hand hand2 = hand(cards2);
        //List<String> values1 = hand1.getValues();
        //List<String> values2 = hand2.getValues();
        //int size1 = 0;
        //int size2 = 0;
        //int size1 = 0;

            //System.out.println("size1 eleje: "+size1);
            //size1 = 0;
            //int size2 = 0;
            //while (cards1.size() == 1 || cards2.size() == 1) {
        while (true) {
            int hand1FirstValueIndex = highestIndex(cards1);
            int hand2FirstValueIndex = highestIndex(cards2);
            String highestValue1 = values.get(hand1FirstValueIndex);
            String highestValue2 = values.get(hand2FirstValueIndex);

            if (hand1FirstValueIndex > hand2FirstValueIndex) {
                System.out.println("HighCards (card1) wins (" + cards1 + ")");
                return Result.WIN;
                //break;
            } else if (hand1FirstValueIndex < hand2FirstValueIndex) {
                System.out.println("HighCards (card2) wins (" + cards2 + ")");
                return Result.WIN;
                //break;
            } else {
                //while (true) {
                    if (cards1.isEmpty() && cards2.isEmpty()) {
                        System.out.println("Equal cards is tie");
                        return Result.TIE;
                        //break;
                    }
                    /*
                    if (cards1.isEmpty()) {
                    System.out.println("cards1");
                    break;
                    }
                    if (cards2.isEmpty()) {
                    System.out.println("cards2");
                    break;
                    }
                    */
                    cards1.remove(highestValue1);
                    cards2.remove(highestValue2);
                    System.out.println(cards1);
                    System.out.println(cards2);
                    //size1 = cards1.size();
                    //size2 = cards2.size();
                    //System.out.println("size: ");
                    //System.out.println("size1: " + size1);
                    //System.out.println(size2);
                    rankingHighCards(cards1, cards2);
                    }

                //}

            } // while
            //System.out.println("size: ");
            //System.out.println(cards1.size());
            //System.out.println(cards2.size());
            //System.out.println(cards1);
            //System.out.println(cards2);

        } //while (size1 != 0);

    //}


    // deprecated?
    public static int valueIndex(List<String> values, String input){
        //List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        int num = 0;
        for (int i = 0; i < values.size(); i++) {
            if(input.equals(values.get(i))){
                num = i;
                System.out.println("num: " + i);
            }
        }
        return num;
    }

    public static int highestIndex(List<String> hand){
        List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        int num = 0;
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                if(values.get(i).equals(hand.get(j))){
                    if(i > num){
                        num = i;
                    }
                }
            }
        }
        return num;
    }

    public static String valueCalculator(String[] cards){
        Hand hand = hand(cards);
        List<String> values = hand.getValues();

        //List<String> lettersinAWord = new ArrayList<>();
        List<String> value1 = new ArrayList<>();
        List<String> value2 = new ArrayList<>();
        List<String> value3 = new ArrayList<>();

        int totalNumberOfLetters = 0;
        Map<String, Integer> valueStatistics = new TreeMap<>();

        for (String value : values) {
            totalNumberOfLetters++;
            if(valueStatistics.containsKey(value)) {
                Integer count = valueStatistics.get(value);
                count++;
                valueStatistics.put(value, count);
            } else {
                valueStatistics.put(value, 1);
            }

        }

        Set<Map.Entry<String, Integer>> entrySet = valueStatistics.entrySet();


        List<Integer> valueNumbers = new ArrayList<>();
        List<String> separatedValues = new ArrayList<>();
        List<String> separatedValues2 = new ArrayList<>();
        String[] strings = new String[2];
        String[] strings2 = new String[2];
        for (Map.Entry<String, Integer> entry : entrySet) {
            valueNumbers.add(entry.getValue());
            System.out.println(entry.getKey() + " --> " + entry.getValue() + " db");
            // full house
            if(entry.getValue() == 2){
                //separatedValues = new ArrayList<>();
                for (int i = 0; i < entry.getValue(); i++) {
                    //separatedValues.add(entry.getKey());
                    strings[i] = entry.getKey();
                }
            }
            /*
            if(entry.getValue() == 2 && !strings[0].equals(entry.getKey())){
                //separatedValues = new ArrayList<>();
                for (int i = 0; i < entry.getValue(); i++) {
                    separatedValues2.add(entry.getKey());
                    strings2[i] = entry.getKey();
                }
            }
            */

        }
        List<String> stringsFromArray = Arrays.asList(strings);
        System.out.println("FROM: "+stringsFromArray);
        for (Map.Entry<String, Integer> entry : entrySet) {
            //valueNumbers.add(entry.getValue());
            //System.out.println(entry.getKey() + " --> " + entry.getValue() + " db");
            // full house
            if(entry.getValue() == 2 && !stringsFromArray.contains(entry.getKey())){
                //separatedValues = new ArrayList<>();
                for (int i = 0; i < entry.getValue(); i++) {
                    separatedValues2.add(entry.getKey());
                    //strings[i] = entry.getKey();
                }
            }
            /*
            if(entry.getValue() == 2 && !strings[0].equals(entry.getKey())){
                //separatedValues = new ArrayList<>();
                for (int i = 0; i < entry.getValue(); i++) {
                    separatedValues2.add(entry.getKey());
                    strings2[i] = entry.getKey();
                }
            }
            */

        }




        /*
        if(entry.getValue() > 1){
            //separatedValues = new ArrayList<>();
            for (int i = 0; i < entry.getValue(); i++) {
                separatedValues.add(entry.getKey());
            }
        }
        if(entry.getValue() > 1 && !separatedValues.contains(entry.getKey())){
            //separatedValues = new ArrayList<>();
            for (int i = 0; i < entry.getValue(); i++) {
                separatedValues2.add(entry.getKey());
            }
        }

         */


        System.out.println("separatedValues: "+ separatedValues);
        System.out.println("separatedValues2: "+ separatedValues2);
        System.out.println("strings: "+ Arrays.toString(strings));
        System.out.println("strings2: "+ Arrays.toString(strings2));
        Collections.sort(valueNumbers);
        //System.out.println("valueNumbers: " + valueNumbers);
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer number : valueNumbers){
            stringBuilder.append(number);
        }
        //System.out.println(valueNumbers);
        //System.out.println("value1: "+ value1);
        //System.out.println("value2: "+ value2);
        //System.out.println("value3: "+ value3);
        return stringBuilder.toString();

    }

    /*
    public static boolean hasNSameValues(String[] cards, int num){ // leMap-eles gyanús...
        Hand hand = hand(cards);
        List<String> values = hand.getValues();
        List<String> valuesList = new ArrayList<>();
        //9,9,9,9,3
        Collections.sort(values); // 3,9,9,9,9
        //Collections.sort(values, Collections.reverseOrder());
        System.out.println("values: "+values);
        valuesList.add(values.get(cards.length - num));
        System.out.println("valuesList_eleje: "+valuesList);
        for (int i = 1; i < values.size(); i++) {
            if(valuesList.contains(values.get(i))) {
                valuesList.add(values.get(i));
            }
        }
        if (valuesList.size() == num + 1){
            return true;
        }
        return false;
    }
    */

}