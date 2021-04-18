package com.game;

import java.util.ArrayList;

public abstract class Player {
    private int money;
    private ArrayList<Card> hand;

    public Player(int money) {
        this.money = money;
        this.hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

