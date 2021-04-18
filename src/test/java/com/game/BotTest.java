package com.game;

import org.junit.Assert;
import org.junit.Test;

public class BotTest {
    @Test
    public void nextMoveTest() {
        Bot bot = new Bot(1000);
        bot.getHand().add(new Card("A", "♦"));
        bot.getHand().add(new Card("A", "♦"));
        bot.getHand().add(new Card("A", "♦"));

        Assert.assertEquals("call",bot.nextMove());

        bot.getHand().clear();
        bot.getHand().add(new Card("10", "♦"));
        bot.getHand().add(new Card("10", "♦"));
        Assert.assertEquals("fold",bot.nextMove());
    }

}