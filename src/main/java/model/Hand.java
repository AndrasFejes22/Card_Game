package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    List<String> values = new ArrayList<>();
    List<String> suits = new ArrayList<>();

    public Hand(List<String> values, List<String> suits) {
        this.values = values;
        this.suits = suits;
    }

    public List<String> getValues() {
        return values;
    }

    public List<String> getSuits() {
        return suits;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "values=" + values +
                ", suits=" + suits +
                '}';
    }
}
