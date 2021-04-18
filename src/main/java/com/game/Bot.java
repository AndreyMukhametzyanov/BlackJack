package com.game;

public class Bot extends Player {

    public Bot(int money) {
        super(money);
    }

    public String nextMove() {
        if (Card.pointsCount(getHand()) < 17) {
            return "call";
        } else return "fold";
    }
}
