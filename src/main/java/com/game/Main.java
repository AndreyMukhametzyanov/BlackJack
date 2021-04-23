package com.game;

public class Main {

    public static void main(String[] args) {
        User user = new User(1000);
        Bot bot = new Bot(1000);

        Game game = new Game(user,bot);
        game.start();
    }
}
