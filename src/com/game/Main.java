package com.game;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Card card = new Card("2", "♠");
        var deck = Card.deck();
        System.out.println("Исходная колода карт" + deck);
        System.out.println("Количество карт в колоде "+ deck.size() + "шт.");

        var deckShuffle = Card.shuffle(Card.deck());
        System.out.println("Перетасованная колода карт "+ deckShuffle);

        var pullOut = Card.pullOutCard(deckShuffle);
        System.out.println("Вытащили карту : "+ pullOut + " Номиналом " + Card.rankOfCard(pullOut));




    }
}
