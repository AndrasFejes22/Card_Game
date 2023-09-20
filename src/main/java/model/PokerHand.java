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
        //Hand player = hand(this.hand);
        String opponentCards = hand.getHand();
        String playerCards = getHand();
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
            //List<String> playerValuesList = new ArrayList<String>(Arrays.asList(playerCards.split(" ")));
            //List<String> opponentValuesList = new ArrayList<String>(Arrays.asList(opponentCards.split(" ")));
            //if(playerScore == 9 || playerScore == 6 || playerScore == 5) {
            if(playerScore == 1 || playerScore == 2 || playerScore == 4) { //egyéb?
                //return highestRank(playerCards, opponentCards);
                return rankingHighCards(playerValues, opponentValues);

            } else if(playerScore == 7){ //full house
                return fullHouseRanking(playerValues, opponentValues);
            }
            else {
                //return rankingHighCards(playerValuesList, opponentValuesList);
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
            values.add(element[0]);
            suits.add(element[1]);
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

    /*
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
    */

    public static int Flush(String cards){
        Hand hand = hand(cards);
        if(sameSuits(hand.getSuits())){
            return 6;
        }
        return 0;
    }

    public static String StraightFlush(String cards){
        Hand hand = hand(cards);
        if(consecutiveValues(hand.getValues()) && sameSuits(hand.getSuits())){
            return "straight flush";
        }
        return "";
    }

    //////////////////// RANKING ////////////////////

    public  Result highestRank(List<String> playerValues, List<String>  opponentValues){ //straight

        System.out.println(playerValues);
        System.out.println(opponentValues);

        int hand1FirstValueIndex = highestIndex(playerValues);
        System.out.println("hand1FirstValueIndex: "+hand1FirstValueIndex);

        int hand2FirstValueIndex = highestIndex(opponentValues);
        System.out.println("hand2FirstValueIndex: "+hand2FirstValueIndex);
        if(hand1FirstValueIndex > hand2FirstValueIndex){
            System.out.println("Highest straight flush (card1) wins ("+ playerValues + ")");
            return Result.WIN;
        } else if(hand1FirstValueIndex < hand2FirstValueIndex) {
            System.out.println("Highest straight flush (card2) wins ("+ opponentValues + ")");
            return Result.LOSS;
        } else {
            System.out.println("Equal straight is tie");
            return Result.TIE;
        }

    }

    //public static PokerHand.Result rankingHighCards(String pokerHand1, String  pokerHand2) { //"2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"
    public PokerHand.Result rankingHighCards(List<String> playerValues, List<String>  opponentValues) { //"2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"
        System.out.println("rankingHighCards in process");

        System.out.println("player: "+playerValues);
        System.out.println("opponent: "+opponentValues);
        //Hand hand1 = hand(pokerHand1);
        //Hand hand2 = hand(pokerHand2);
        //List<String>  cards1 = hand1.getValues();
        //List<String>  cards2 = hand2.getValues();

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
                //if (cards1.isEmpty() && cards2.isEmpty()) {
                if (playerValues.size()==1 && opponentValues.size()==1 && playerValues.get(0).equals(opponentValues.get(0))) {
                    System.out.println("Equal cards is tie");
                    return Result.TIE;
                } else {
                    playerValues.remove(highestValue1);
                    opponentValues.remove(highestValue2);
                    System.out.println(playerValues);
                    System.out.println(opponentValues);
                    // nemjó, ez az eredeti pókerhandddal kezdi
                    //ide new Pokerhand kell
                    //String dummy1 = dummyPokerHand(cards1);
                    //String dummy2 = dummyPokerHand(cards2);
                    rankingHighCards(playerValues, opponentValues);
                }
            }
        }
    }
    public static PokerHand.Result rankingFullHouses(List<String> playerValues, List<String>  opponentValues){
        List<String> fromPlayer1 = new ArrayList<>();
        List<String> fromPlayer2 = new ArrayList<>();
        List<String> fromOpponent1 = new ArrayList<>();
        List<String> fromOpponent2 = new ArrayList<>();
        fromPlayer1.add(playerValues.get(0));
        fromOpponent1.add(opponentValues.get(0));

        for (int i = 0; i < 5; i++) {
            System.out.println(playerValues.get(i));
            if(fromPlayer1.contains(playerValues.get(i))){
                fromPlayer1.add(playerValues.get(i));
            } else {
                fromPlayer2.add(playerValues.get(i));
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(opponentValues.get(i));
            if(fromPlayer1.contains(opponentValues.get(i))){
                fromOpponent1.add(opponentValues.get(i));
            } else {
                fromOpponent2.add(opponentValues.get(i));
            }
        }
        System.out.println(fromPlayer1);
        System.out.println(fromPlayer2);
        System.out.println(fromOpponent1);
        System.out.println(fromOpponent2);
        return Result.TIE;
    }


    public static String dummyPokerHand(List<String> dummy){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dummy.size(); i++) {
            stringBuilder.append(dummy.get(i)).append("S").append(" ");

        }
        return stringBuilder.toString();
    }




    // deprecated?
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
