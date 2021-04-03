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
    public void shuffleTest() { // Как сделать тест если метод всегда мешает рандомно?
        List<Card> excepted = new ArrayList<>();
        String[] numbersOfCardTest = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; // номер карты B
        String[] suitsTest = {"♠", "♥", "♦", "♣"}; //масть пики червы крести буби в unicode
        for (String suit : suitsTest) {
            for (String number : numbersOfCardTest) {
                excepted.add(new Card(number, suit));
            }
        }
        Collections.shuffle(excepted);
        Assert.assertEquals(excepted.toString(),Card.shuffle(Card.deck()).toString());
    }

    @Test
    public void pullOutCardTest() {
       Card excepted = Card.deck().remove(0);
       Assert.assertEquals(excepted.toString(),Card.deck().get(0).toString());
    }

    @Test
    public void pullOutCardsTest() { // не проходит проверки выводит java.lang.AssertionError: Expected :[[2♠], [3♠], [4♠]]  Actual   :[[2♠], [3♠], [4♠]]
        int count = 3; // сколько кард необходимо вытащить
        ArrayList<Card> cardsTest = new ArrayList<>();// пустой массив
        ArrayList<Card> cardsTest2 = new ArrayList<>(Card.deck()); // массив копия колоды
        for (int i = 0; i < count; i++) {
            cardsTest.add(cardsTest2.remove(0));//удаляю из копии и кладу в пустой массив
        }
    Assert.assertEquals(Card.pullOutCards(Card.deck(),count),cardsTest);
    }

    @Test
    public void isAceTest() {
        Card card = new Card("A","♦");
        Assert.assertEquals(card.getNumberOfCard(),"A");

    }

    @Test
    public void pointsCountTest() {
        ArrayList <Card> excepted = new ArrayList<>();
        excepted.add(new Card("A","♦"));
        excepted.add(new Card("A","♦"));
        excepted.add(new Card("A","♦"));
        Assert.assertEquals(Card.pointsCount(excepted),(Integer) 13); // почему-то он его не хотел видеть как Integer без явного указания
    }

    @Test
    public void showCardTest() {
        Card card = new Card("8", "♠");
        Assert.assertEquals(card.showCard(),"[8♠]");
    }

    @Test
    public void showHiddenCardTest() {
        Card card = new Card("8", "♠");
        Assert.assertEquals(card.showHiddenCard(),"[##]");
    }
}