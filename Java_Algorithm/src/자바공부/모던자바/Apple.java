package 자바공부.모던자바;

import java.awt.*;

public class Apple {
    Color color;
    int weight;

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return this.color;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.valueOf(color) + " : " + weight;
    }
}
