package model;

import java.util.*;

public class PokerHand {

    public enum Result { TIE, WIN, LOSS }

    PokerHand(String hand)
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

    public static boolean straight(String cards){
        Hand hand = hand(cards);
        if(consecutiveValues(hand.getValues()) && !sameSuits(hand.getSuits())){
            return true;
        }
        return false;
    }

    public static boolean fourOfAKind(String cards){
        if(valueCalculator(cards).equals("14")){
            return true;
        }
        return false;
    }

    public static boolean threeOfAKind(String cards){
        if(valueCalculator(cards).equals("113")){
            return true;
        }
        return false;
    }

    public static boolean pair(String cards){
        if(valueCalculator(cards).equals("1112")){
            return true;
        }
        return false;
    }

    public static boolean twoPairs(String cards){
        if(valueCalculator(cards).equals("122")){
            return true;
        }
        return false;
    }


    public static boolean fullHouse(String cards){
        if(valueCalculator(cards).equals("23")){
            return true;
        }
        return false;
    }

    public static boolean highCard(String cards){ //De pl. magas lapnál is ha mondjuk K magas mindenkinek, akkor nézik a 2. lapot
        if(valueCalculator(cards).equals("11111")){
            return true;
        }
        return false;
    }

    public static boolean Flush(String cards){
        Hand hand = hand(cards);
        if(sameSuits(hand.getSuits())){
            return true;
        }
        return false;
    }

    public static boolean StraightFlush(String cards){
        Hand hand = hand(cards);
        if(consecutiveValues(hand.getValues()) && sameSuits(hand.getSuits())){
            return true;
        }
        return false;
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
                //return Result.WIN;
            } else if (hand1FirstValueIndex < hand2FirstValueIndex) {
                System.out.println("HighCards (card2) wins (" + cards2 + ")");
                //return Result.WIN;
            } else {
                if (cards1.isEmpty() && cards2.isEmpty()) {
                    System.out.println("Equal cards is tie");
                    //return Result.TIE;
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

    public static String valueCalculator(String cards){
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

        }


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
