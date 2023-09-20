package model;

import java.util.*;

public class PokerHand {

    public final int STRAIGHT_FLUSH_SCORE = 9;

    String hand;

    public enum Result { TIE, WIN, LOSS }


    public PokerHand(String hand) { //PokerHand hand = new PokerHand("KS 2H 5C JD TD");
        this.hand = hand;
    }

    public String getHand() {
        return hand;
    }

    public static List<String> getValues(PokerHand hand){
        String valuesString = hand.getHand();
        List<String> values = new ArrayList<>();
        String[] elements = valuesString.split(" ");
        String[] element = new String[2];
        for (int i = 0; i < elements.length; i++) {
            element = elements[i].split("");
            values.add(element[0]);
        }
        return values;
    }

    public static List<String> getSuits(PokerHand hand){
        List<String> suits = new ArrayList<>();
        String suitsString = hand.getHand();
        String[] elements = suitsString.split(" ");
        String[] element = new String[2];
        for (int i = 0; i < elements.length; i++) {
            element = elements[i].split("");
            suits.add(element[1]);
        }
        return suits;
    }

    public Result compareWith(PokerHand hand) {
        int opponentScore = score(hand);
        int playerScore = score(this);
        System.out.println("playerScore: "+playerScore);
        System.out.println("opponentScore: "+opponentScore);
        List<String>  playerValues = getValues(this);
        List<String>  opponentValues = getValues(hand);
        if(playerScore > opponentScore){
            return Result.WIN;
        } else if (playerScore < opponentScore){
            return Result.LOSS;
        } else if(playerScore == opponentScore){
            if(playerScore == 1 || playerScore == 2 || playerScore == 4 || playerScore == 3 || playerScore == 8|| playerScore == 6) { //egyéb?
                return rankingHighCards(playerValues, opponentValues);
            } else if(playerScore == 7){ //full house
                return fullHouseRanking(playerValues, opponentValues);
            }
            else {
                return highestRank(playerValues, opponentValues);
            }
        }
        return Result.TIE;
    }


    public  int score(PokerHand hand) {

        String result = valueCalculator(hand);
        int score;

        switch (result){
            case "straight flush" :
                score = 9;
                break;
            case "14" :
                score = 8;
                break;
            case "23" :
               score = 7;
                break;
            case "flush" :
                score = 6;
                break;
            case "straight" :
                score = 5;
                break;
            case "113" :
                score = 4;
                break;
            case "122" :
                score = 3;
                break;
            case "1112" :
                score = 2;
                break;
            case "11111" :
                score = 1;
                break;
            default:
                return 0;
        }
        return score;
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

    //////////////////// RANKING ////////////////////

    public  Result highestRank(List<String> playerValues, List<String>  opponentValues){ //straight
        System.out.println("highestRank method in process...");
        System.out.println(playerValues);
        System.out.println(opponentValues);

        int hand1FirstValueIndex = highestIndex(playerValues);
        System.out.println("hand1FirstValueIndex: "+hand1FirstValueIndex);

        int hand2FirstValueIndex = highestIndex(opponentValues);
        System.out.println("hand2FirstValueIndex: "+hand2FirstValueIndex);
        if(hand1FirstValueIndex > hand2FirstValueIndex){
            System.out.println("Highest (card1) wins ("+ playerValues + ")");
            return Result.WIN;
        } else if(hand1FirstValueIndex < hand2FirstValueIndex) {
            System.out.println("Highest (card2) wins ("+ opponentValues + ")");
            return Result.LOSS;
        } else {
            System.out.println("Equal cards is a tie");
            return Result.TIE;
        }

    }


    public PokerHand.Result rankingHighCards(List<String> playerValues, List<String>  opponentValues) { //"2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"
        System.out.println("rankingHighCards in process...");

        System.out.println("player: "+playerValues);
        System.out.println("opponent: "+opponentValues);

        List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        while (true) {
            System.out.println("WHILE");
            int hand1FirstValueIndex = highestIndex(playerValues);
            System.out.println("INT1: "+hand1FirstValueIndex);
            int hand2FirstValueIndex = highestIndex(opponentValues);
            System.out.println("INT2: "+hand2FirstValueIndex);
            String highestValue1 = values.get(hand1FirstValueIndex);
            String highestValue2 = values.get(hand2FirstValueIndex);

            if (hand1FirstValueIndex > hand2FirstValueIndex) {
                System.out.println("HighCards (card1) wins (" + playerValues + ")");
                return Result.WIN;
            } else if (hand1FirstValueIndex < hand2FirstValueIndex) {
                System.out.println("HighCards (card2) wins (" + opponentValues + ")");
                return Result.LOSS;
            } else {
                if (playerValues.size()==1 && opponentValues.size()==1 && playerValues.get(0).equals(opponentValues.get(0))) {
                    System.out.println("Equal cards is tie");
                    return Result.TIE;
                } else {
                    playerValues.remove(highestValue1);
                    opponentValues.remove(highestValue2);
                    System.out.println(playerValues);
                    System.out.println(opponentValues);
                    rankingHighCards(playerValues, opponentValues);
                }
            }
        }
    }


    public static String dummyPokerHand(List<String> dummy){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dummy.size(); i++) {
            stringBuilder.append(dummy.get(i)).append("S").append(" ");

        }
        return stringBuilder.toString();
    }

    public static int valueIndex(String input){
        List<String> values = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        int num = 0;
        for (int i = 0; i < values.size(); i++) {
            if(input.equals(values.get(i))){
                num = i;
                System.out.println("num: " + i);
            }
        }
        return num;
    }

    public int highestIndex(List<String> hand){
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

    public String valueCalculator(PokerHand hand){ // e.g: High Card --> 11111, Full House--> 32
        //List<String>  playerValues = getValues(this);
        List<String>  values = getValues(hand);
        List<String>  suits = getSuits(hand);
        //Hand hand = hand(cards);
        if(consecutiveValues(values) && sameSuits(suits)){
            return "straight flush";
        } else if(consecutiveValues(values) && !sameSuits(suits)){
            return "straight";
        } else if(sameSuits(suits)){
            return "flush";
        } else {

            Map<String, Integer> valueStatistics = new TreeMap<>();

            for (String value : values) {
                if (valueStatistics.containsKey(value)) {
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
            for (Integer number : valueNumbers) {
                stringBuilder.append(number);
            }

            return stringBuilder.toString();
        }
    }

    public static String[] handMapper(List<String> values){
        String[] results = new String[2];
        Map<String, Integer> valueStatistics = new TreeMap<>();

        for (String value : values) {
            if (valueStatistics.containsKey(value)) {
                Integer count = valueStatistics.get(value);
                count++;
                valueStatistics.put(value, count);
            } else {
                valueStatistics.put(value, 1);
            }

        }

        Set<Map.Entry<String, Integer>> entrySet = valueStatistics.entrySet();

        List<Integer> valueNumbers = new ArrayList<>();
        List<String> threes = new ArrayList<>();
        List<String> twos = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : entrySet) {
            //valueNumbers.add(entry.getValue());
            if(entry.getValue() == 3){
                threes.add(entry.getKey());
            } else {
                twos.add(entry.getKey());
            }

        }
        System.out.println("threes: "+threes);
        System.out.println("twos: "+twos);
        results[0] = threes.get(0);
        results[1] = twos.get(0);

        return results;
    }

    public Result fullHouseRanking(List<String> playerValues, List<String>  opponentValues){

        String valueOfPlayerThrees = handMapper(playerValues)[0];
        String valueOfOpponentThrees = handMapper(opponentValues)[0];
        int playerIndexThrees = valueIndex(valueOfPlayerThrees);
        int opponentIndexThrees = valueIndex(valueOfOpponentThrees);

        String valueOfPlayerTwos = handMapper(playerValues)[1];
        String valueOfOpponentTwos = handMapper(opponentValues)[1];
        int playerIndexTwos = valueIndex(valueOfPlayerTwos);
        int opponentIndexTwos = valueIndex(valueOfOpponentTwos);


        if(playerIndexThrees > opponentIndexThrees){
            return Result.WIN;
        } else if(playerIndexThrees < opponentIndexThrees){
            return Result.LOSS;
        } else {
            if(playerIndexTwos > opponentIndexTwos){
                return Result.WIN;
            } else if(playerIndexTwos < opponentIndexTwos){
                return Result.LOSS;
            }
            else {
                return Result.TIE;
            }
        }


    }

}
