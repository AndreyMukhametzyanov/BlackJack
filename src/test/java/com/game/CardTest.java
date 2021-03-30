package com.game;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class CardTest {


    @After
    public void tearDown() throws Exception {
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
    }

    @Test
    public void pullOutCardTest() {
    }

    @Test
    public void pullOutCardsTest() {
    }

    @Test
    public void isAceTest() {
    }

    @Test
    public void pointsCountTest() {
    }

    @Test
    public void showCardTest() {
    }

    @Test
    public void showHiddenCardTest() {
    }
}