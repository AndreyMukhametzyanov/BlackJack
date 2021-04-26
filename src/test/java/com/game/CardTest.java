package com.game;

import org.junit.*;

import java.util.*;

public class CardTest {

    @Test
    public void equalsTest() {
        Card cardOne = new Card("10", "♠");
        Card cardTwo = new Card("8", "♦");

        Assert.assertNotEquals(cardOne, cardTwo);

        cardOne = new Card("10", "♠");
        cardTwo = new Card("10", "♠");

        Assert.assertEquals(cardOne, cardTwo);
    }

    @Test
    public void rankOfCardTest() {
        Card card = new Card("8", "♠");

        Assert.assertEquals(Card.rankOfCard(card), 8);
    }

    @Test
    public void deckTest() {
        List<Card> excepted = new ArrayList<>();
        String[] numbersOfCardTest = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; // номер карты B
        String[] suitsTest = {"♠", "♥", "♦", "♣"}; //масть пики червы крести буби в unicode
        for (String suit : suitsTest) {
            for (String number : numbersOfCardTest) {
                excepted.add(new Card(number, suit));
            }
        }

        Assert.assertEquals(excepted.toString(), Card.deck().toString());
    }

    @Test
    public void shuffleTest() {
        ArrayList<Card> cards = Card.deck();
        int count = cards.size();
        Card.shuffle(cards);

        Assert.assertEquals(cards.size(), count);
    }

    @Test
    public void pullOutCardTest() {
        Card excepted = Card.deck().remove(0);

        Assert.assertEquals(excepted.toString(), Card.deck().get(0).toString());
    }

    @Test
    public void pullOutCardsTest() {
        int count = 3; // сколько кард необходимо вытащить
        List<Card> cardsTest = new ArrayList<>();// пустой массив
        List<Card> cardsTest2 = new ArrayList<>(Card.deck()); // массив копия колоды
        for (int i = 0; i < count; i++) {
            cardsTest.add(cardsTest2.remove(0));//удаляю из копии и кладу в пустой массив
        }

        Assert.assertEquals(Card.pullOutCards(Card.deck(), count), cardsTest);
    }

    @Test
    public void isAceTest() {
        Card card = new Card("A", "♦");

        Assert.assertEquals(card.getNumberOfCard(), "A");
    }

    @Test
    public void pointsCountTest() {
        ArrayList<Card> excepted = new ArrayList<>();
        excepted.add(new Card("A", "♦"));
        excepted.add(new Card("A", "♦"));
        excepted.add(new Card("A", "♦"));

        Assert.assertEquals(Card.pointsCount(excepted), 13);
    }

    @Test
    public void showCardTest() {
        Card card = new Card("8", "♠");
        String[] exp = {
                "┌─────────┐",
                "│  8      │",
                "│         │",
                "│         │",
                "│    ♠    │",
                "│         │",
                "│         │",
                "│       8 │",
                "└─────────┘"};
        Assert.assertArrayEquals(card.showCard(),exp);
    }

    @Test
    public void showHiddenCardTest() {
        Card card = new Card("8", "♠");
        String[] exp = {
                "┌─────────┐",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "└─────────┘",};
        Assert.assertArrayEquals(card.showHiddenCard(), exp);
    }
}