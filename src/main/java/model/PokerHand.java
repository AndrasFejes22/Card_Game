package model;

import java.util.*;

public class PokerHand {

    public enum Result { TIE, WIN, LOSS }

    public PokerHand(String hand)
    {
    }

    public Result compareWith(PokerHand hand) {
        return Result.TIE;
    }

    public static int score(String cards) {
        return 0;
    }

    public static Hand hand(String cards){
        List<String> values = new ArrayList<>();
        List<String> suits = new ArrayList<>();
        // Possible values are: 2 3 4 5 6 7 8 9 T J Q K A
        // Possible suites are: s h d c
        // eg.: "2d", "3d", "4d", "5d", "6d" (straight flush)
        String[] elements = cards.split(" ");
        String[] element = new String[2];
        for (int i = 0; i < elements.length; i++) {
            element = elements[i].split("");
            values.add(element[0].toLowerCase());
            suits.add(element[1].toLowerCase());
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

    public static int straight(String cards){
        Hand hand = hand(cards);
        if(consecutiveValues(hand.getValues()) && !sameSuits(hand.getSuits())){
            return 5;
        }
        return 0;
    }

    public static int fourOfAKind(String cards){
        if(valueCalculator(cards).equals("14")){
            return 8;
        }
        return 0;
    }

    public static int threeOfAKind(String cards){
        if(valueCalculator(cards).equals("113")){
            return 4;
        }
        return 0;
    }

    public static int pair(String cards){
        if(valueCalculator(cards).equals("1112")){
            return 2;
        }
        return 0;
    }

    public static int twoPairs(String cards){
        if(valueCalculator(cards).equals("122")){
            return 3;
        }
        return 0;
    }


    public static int fullHouse(String cards){
        if(valueCalculator(cards).equals("23")){
            return 7;
        }
        return 0;
    }

    public static int highCard(String cards){ //De pl. magas lapnál is ha mondjuk K magas mindenkinek, akkor nézik a 2. lapot
        if(valueCalculator(cards).equals("11111")){
            return 1;
        }
        return 0;
    }

    public static int Flush(String cards){
        Hand hand = hand(cards);
        if(sameSuits(hand.getSuits())){
            return 6;
        }
        return 0;
    }

    public static int StraightFlush(String cards){
        Hand hand = hand(cards);
        if(consecutiveValues(hand.getValues()) && sameSuits(hand.getSuits())){
            return 9;
        }
        return 0;
    }

    //////////////////// RANKING ////////////////////

    public static void highestRank(String cards1, String cards2){ //straight
        //List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");

        Hand hand1 = hand(cards1);
        Hand hand2 = hand(cards2);

        int hand1FirstValueIndex = highestIndex(hand1.getValues());
        int hand2FirstValueIndex = highestIndex(hand2.getValues());
        if(hand1FirstValueIndex > hand2FirstValueIndex){
            System.out.println("Highest straight flush (card1) wins ("+ hand1.getValues() + ")");
        } else if(hand1FirstValueIndex < hand2FirstValueIndex) {
            System.out.println("Highest straight flush (card2) wins ("+ hand2.getValues() + ")");
            //System.out.println(Result.WIN);
        } else {
            System.out.println("Equal straight is tie");
            //System.out.println(Result.TIE);
        }

    }

    public static PokerHand.Result rankingHighCards(List<String>  cards1, List<String>  cards2) { //"2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"
        List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        while (true) {
            int hand1FirstValueIndex = highestIndex(cards1);
            int hand2FirstValueIndex = highestIndex(cards2);
            String highestValue1 = values.get(hand1FirstValueIndex);
            String highestValue2 = values.get(hand2FirstValueIndex);

            if (hand1FirstValueIndex > hand2FirstValueIndex) {
                System.out.println("HighCards (card1) wins (" + cards1 + ")");
                return Result.WIN;
            } else if (hand1FirstValueIndex < hand2FirstValueIndex) {
                System.out.println("HighCards (card2) wins (" + cards2 + ")");
                return Result.WIN;
            } else {
                if (cards1.isEmpty() && cards2.isEmpty()) {
                    System.out.println("Equal cards is tie");
                    return Result.TIE;
                }
                cards1.remove(highestValue1);
                cards2.remove(highestValue2);
                System.out.println(cards1);
                System.out.println(cards2);
                rankingHighCards(cards1, cards2);
            }
        }
    }




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

    public static String valueCalculator(String cards){ // e.g: High Card --> 11111, Full House--> 32
        Hand hand = hand(cards);
        List<String> values = hand.getValues();

        Map<String, Integer> valueStatistics = new TreeMap<>();

        for (String value : values) {
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

        for (Map.Entry<String, Integer> entry : entrySet) {
            valueNumbers.add(entry.getValue());
        }

        Collections.sort(valueNumbers);
        //System.out.println("valueNumbers: " + valueNumbers);
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer number : valueNumbers){
            stringBuilder.append(number);
        }

        return stringBuilder.toString();
    }

}
