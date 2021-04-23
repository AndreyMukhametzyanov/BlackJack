package com.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private User user;
    private Bot bot;
    private int bank = 0;
    private final int defaultBet = 100;

    public Game(User user, Bot bot) { // конструктор класса
        this.user = user;
        this.bot = bot;
    }

    public void start() { //метод игры
        System.out.println("Игра запущена");
        // userWin - пользователь вин
        // botWin - бот вин
        // endGame - завершить игру

        while (true) {
            switch (round()) {
                case "userWin":
                    System.out.println("Вы победили!");
                    break;
                case "botWin":
                    System.out.println("Вас победили!");
                    break;
                case "endGame":
                    System.out.println("Спасибо за игру!");
                    break;
                default:
                    continue;
            }
            break;
        }
    }

    private String round() {
        ArrayList<Card> deck = Card.deck(); // создание и перемешивание колоды
        Card.shuffle(deck);

        user.getHand().clear(); // очищение рук игроков
        bot.getHand().clear();

        user.getHand().addAll(Card.pullOutCards(deck, 2)); // подсчет очков для 2 карт и раздача карт
        bot.getHand().addAll(Card.pullOutCards(deck, 2));

        user.setMoney(user.getMoney() - defaultBet); // ставка игрока
        bot.setMoney(bot.getMoney() - defaultBet); // ставка бота
        bank += defaultBet * 2; // положили ставку в банк

        System.out.printf("Первоначальная ставка %d", defaultBet);
        scoreRender();
        showHands();  // Вывод рук и подсчет очков в руке игрока

        String userChoose = userChoose();
        String botChoose = bot.nextMove();

        if (userChoose.equals("call")) { // если игрок выбрал "Call" добавить карту в его руку
            user.getHand().add(Card.pullOutCard(deck));
            render();
        }
        System.out.println("Ожидание хода соперника...");
        waiting((int) ((Math.random() * 2) + 1)); // рандомные размышления бота

        if (botChoose.equals("call")) {  // если бот выбрал "Call" добавить карту в его руку
            bot.getHand().add(Card.pullOutCard(deck));
            render();
        }

        whoIsWinner(); // метод вычисления победителя

        if (bot.getMoney() == 0) { // если остаток на счету бота = 0 то победил игрок
            return "userWin";
        }
        if (user.getMoney() == 0) { // если остаток на счету игрока = 0 то победил бот
            return "botWin";
        }

        scoreRender();
        System.out.println();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // завершить или продолжить игру
        System.out.printf("Введите 1,чтобы продложить играть %nВведите любой другой символ для выхода%n");
        try {
            if (!reader.readLine().equals("1")) {
                return "endGame";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String userChoose() { // Ввод выбора игрока
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите действие: ");
        System.out.println("1) Пропуск хода");
        if (user.getHand().size() < 3) {
            System.out.println("2) Взять карту");
        }
        try {
            switch (reader.readLine()) {
                case "1":
                    return "fold";
                case "2":
                    if (user.getHand().size() < 3) return "call";
                default:
                    System.out.println("Введенный ответ некоректен");
                    return userChoose();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return userChoose();
        }
    }

    private void render() {
        render(false);
    }

    private void render(boolean isFinal) { //отрисовка экрана
        clearScreen();
        System.out.printf("Первоначальная ставка %d %n", defaultBet);
        System.out.printf("На счету игрока: %d %n", user.getMoney());
        System.out.printf("На счету соперника: %d %n", bot.getMoney());
        System.out.printf("В банке: %d %n", bank);
        System.out.println("======================================");

        for (String line : concatCards(bot.getHand(), isFinal)) {  // показать руку бота
            System.out.println(line);
        }
        System.out.println();
        if (isFinal) {
            System.out.printf("Количество очков соперника :%d %n", Card.pointsCount(bot.getHand()));
        }
        for (String line : concatCards(user.getHand(), true)) {  // показать руку игрока
            System.out.println(line);
        }

        System.out.println();

        System.out.printf("Количество очков данной руки:%d %n", Card.pointsCount(user.getHand()));
        System.out.println("======================================");
    }

    public static void clearScreen() { // очистить консоль
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void waiting(int seconds) { // добавляем задержку потока
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scoreRender() {
        System.out.println();
        System.out.printf("На счету игрока: %d %n", user.getMoney());
        System.out.printf("На счету соперника: %d %n", bot.getMoney());
    }

    private void showHands() {
        for (String line : concatCards(bot.getHand(), false)) {  // показать руку бота
            System.out.println(line);
        }
        System.out.println();

        for (String line : concatCards(user.getHand(), true)) {  // показать руку бота
            System.out.println(line);
        }
        System.out.println();
        System.out.printf("Количество очков данной руки:%d %n", Card.pointsCount(user.getHand())); // подсчет очков в руке игрока
    }

    private void whoIsWinner() {
        if (Card.pointsCount(user.getHand()) <= 21) {
            if (Card.pointsCount(user.getHand()) > Card.pointsCount(bot.getHand())) {
                user.setMoney(user.getMoney() + bank);
                bank = 0;
                render(true);
                System.out.println("Вы победили");
            }
        }

        if (Card.pointsCount(bot.getHand()) <= 21) {
            if (Card.pointsCount(user.getHand()) < Card.pointsCount(bot.getHand())) {
                bot.setMoney(bot.getMoney() + bank);
                bank = 0;
                render(true);
                System.out.println("Соперник победил");
            }
        }

        if ((Card.pointsCount(bot.getHand()) == Card.pointsCount(user.getHand()) || ((Card.pointsCount(bot.getHand()) > 21) && (Card.pointsCount(user.getHand())) > 21))) {
            bot.setMoney(bot.getMoney() + bank / 2);
            user.setMoney(user.getMoney() + bank / 2);
            bank = 0;
            render(true);
            System.out.println("Ничья");
        }

        if ((Card.pointsCount(bot.getHand()) <= 21) && (Card.pointsCount(user.getHand()) > 21)) {
            bot.setMoney(bot.getMoney() + bank);
            bank = 0;
            render(true);
            System.out.println("Соперник победил");
        }

        if ((Card.pointsCount(bot.getHand()) > 21) && (Card.pointsCount(user.getHand()) <= 21)) {
            user.setMoney(user.getMoney() + bank);
            bank = 0;
            render(true);
            System.out.println("Вы победили");
        }
    }

    private String[] concatCards(ArrayList<Card> hand, boolean notHidden) { // если notHidden true - открытая карта
        String[] buffer = new String[9];
        String s;
        for (int i = 0; i < buffer.length; i++) {
            s = "";
            for (Card card : hand) {
                if (notHidden) {
                    s = String.join("   ", s, card.showCard()[i]);
                } else {
                    s = String.join("   ", s, card.showHiddenCard()[i]);
                }
            }
            buffer[i] = s;
        }
        return buffer;
    }
}
