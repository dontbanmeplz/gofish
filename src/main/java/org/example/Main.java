package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Start {
    public static final String BLACK = "\u001B[40m";
    public static final String RED = "\u001B[41m";
    public static final String GREEN = "\u001B[42m";
    public static final String YELLOW = "\u001B[43m";
    public static final String BLUE = "\u001B[44m";
    public static final String PURPLE = "\u001B[45m";
    public static final String CYAN = "\u001B[46m";
    public static final String WHITE = "\u001B[47m";
    public static final String RESET = "\u001B[0m";
    public static int match1 = 0;
    public static int match2 = 0;
    public static DeckOfCards d = new DeckOfCards();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList < Card > p1 = new ArrayList < Card > ();
        ArrayList < Card > p2 = new ArrayList < Card > ();
        d.shuffle();
        for (int i = 0; i < 7; i++) {
            p1.add(d.deal());
            p2.add(d.deal());
        }
        for (Card i: p2) {
            int count = 0;
            for (Card j: p2) {
                if (i.getFace() == j.getFace()) {
                    count++;
                }
            }
            if (count == 2) {
                p2.remove(i);
                p2.remove(i);

            }
        }
        System.out.println("Player 1's hand:");
        System.out.print(BLUE);
        for (Card i: p1) {
            System.out.print(i.getFace() + ", ");
        }
        System.out.print(RESET);
        System.out.println();
        boolean running = true;
        while (running) {
            boolean running1 = true;
            boolean ask = true;
            while (running1) {
                System.out.println("1. Make Match");
                System.out.println("2. Ask for card");
                System.out.println("3. End Turn");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        makeMatch(p1);
                        break;
                    case 2:
                        if (ask) {
                            askForCard(p1, p2);
                            ask = false;
                        } else {
                            System.out.println("You already asked for a card this turn.");
                        }
                        break;
                    case 3:
                        running1 = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                System.out.println("Player 1's hand:");
                System.out.print(BLUE);
                for (Card i: p1) {
                    System.out.print(i.getFace() + ", ");
                }
                System.out.print(RESET);
                System.out.println();
            }
            for (Card i: p2) {
                int count = 0;
                for (Card j: p2) {
                    if (i.getFace() == j.getFace()) {
                        count++;
                    }
                }
                if (count == 2) {
                    p2.remove(i);
                    p2.remove(i);
                    match2++;
                }
            }

            int random = (int)(Math.random() * p2.size());
            Card card = p2.get(random);
            System.out.println("Player 2 asked for a " + card.getFace());
            boolean hasCard = false;
            for (Card i: p1) {
                if (i.getFace() == card.getFace()) {
                    hasCard = true;
                    p1.remove(i);
                    p2.remove(card);
                    break;
                }
            }
            if (hasCard) {
                System.out.println("Player 1 had a " + card.getFace());
            } else {
                System.out.println("Player 1 did not have a " + card.getFace());
                p2.add(d.deal());
            }
            System.out.println("Player 2 has " + p2.size() + " cards");
            System.out.println("There are " + d.getNumCardsInDeck() + " cards left in the deck");
            for (Card i: p2) {
                int count = 0;
                for (Card j: p2) {
                    if (i.getFace() == j.getFace()) {
                        count++;
                    }
                }
                if (count == 2) {
                    p2.remove(i);
                    p2.remove(i);

                }
            }

            System.out.println("Player 1's hand:");
            System.out.print(BLUE);
            for (Card i: p1) {
                System.out.print(i.getFace() + ", ");
            }
            System.out.print(RESET);
            System.out.println();
        }

    }

    private static void askForCard(ArrayList < Card > p1, ArrayList < Card > p2) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the card you want to ask for: ");
        int card = scanner.nextInt();
        System.out.println("Player 1 asked for a " + card);
        boolean hasCard = false;
        for (Card i: p2) {
            if (i.getFace() == card) {
                hasCard = true;
                p2.remove(i);
                break;
            }
        }
        if (hasCard) {
            System.out.println("Player 2 had a " + card);
        } else {
            System.out.println("Player 2 did not have a " + card);
            p1.add(d.deal());
        }
    }

    private static void makeMatch(ArrayList < Card > p1) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the card you want to match: ");
        int card = scanner.nextInt();
        int count = 0;
        for (Card i: p1) {
            count++;
            if (i.getFace() == card) {
                int count1 = 0;
                for (Card j: p1) {
                    count1++;
                    if (j.getFace() == card) {
                        if (count != count1) {
                            p1.remove(i);
                            p1.remove(j);
                            match1++;
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("You do not have those cards.");
    }
}