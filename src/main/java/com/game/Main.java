package com.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var deck = Card.deck();
        System.out.println("Исходная колода карт" + deck);
        System.out.println("Количество карт в колоде " + deck.size() + "шт.");

        var deckShuffle = Card.shuffle(Card.deck());
        System.out.println("Перетасованная колода карт " + deckShuffle);

        var pullOut = Card.pullOutCard(deckShuffle);
        System.out.println("Вытащили карту : " + pullOut + " Номиналом " + Card.rankOfCard(pullOut));


        ArrayList<Card> hand = new ArrayList<>();
        hand.add(Card.pullOutCard(deckShuffle));
        hand.add(Card.pullOutCard(deckShuffle));
        hand.add(Card.pullOutCard(deckShuffle));
        System.out.println("Раздал на руки эти карты " + hand);

        System.out.println("У вас " + Card.pointsCount(hand) + " очков");
//   Проверка вытаскивания 3 карт
        var pullOutOfCards = Card.pullOutCards(deckShuffle,3);
        System.out.println(pullOutOfCards);

//        ArrayList<Card> hand2 = new ArrayList<>();
//        hand2= Card.pullOutCards(deckShuffle,3);
//        System.out.println(hand2);
//        Card[] asd = {new Card("A", "♠"),new Card("A", "♠"),new Card("A", "♠")};
//        ArrayList<Card> hand2 = new ArrayList<>(Arrays.asList(asd));
//        System.out.println(Arrays.toString(asd));
//        System.out.println("У вас " + Card.pointsCount(hand2) + " очков");

    }
}
