package com.game;

public class Main {

    public static void main(String[] args) {
        Card card = new Card("2", "♠");
        var deck = Card.deck();
        System.out.println(deck.size());
    }
}
