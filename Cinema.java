package cinema;

import java.util.Scanner;

public class Cinema {
    private static Scanner sc;
    private static int ROWS;
    private static int SEATS;

    private static char[][] arrangement;
    private static int boughtTickets = 0;
    private static int curIncome = 0;
    private static double percentage = 0;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        boolean chosenTicket = false;
        System.out.println("Enter the number of rows:");
        ROWS = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        SEATS = sc.nextInt();
        createArrangement();
        showMenu();
    }

    private static void printArrangement() {
        System.out.println("Cinema:");
        for (int i = 0; i <= ROWS; ++i) {
            for (int j = 0; j <= SEATS; ++j) {
                if (j == 0 && i == 0) {
                    System.out.print("  ");
                } else if (i == 0 && j > 0) {
                    System.out.print(j);
                    if (j != SEATS) {
                        System.out.print(" ");
                    }
                } else if (i > 0 && j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(arrangement[i - 1][j - 1]);
                    if (j != SEATS) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    private static void calculateProfit() {
        int profit = 0;
        if (ROWS * SEATS <= 60) {
            profit = ROWS * SEATS * 10;
        } else {
            if (ROWS % 2 == 0) {
                profit += ((ROWS * SEATS) / 2) * 10;
                profit += ((ROWS * SEATS) / 2) * 8;
            } else {
                profit += ((ROWS / 2) * SEATS) * 10;
                profit += ((ROWS / 2 + 1) * SEATS) * 8;
            }
        }
        System.out.println("Total income: $" + profit);
    }

    private static void calculateTicketPrice() {
        boolean correctTicket = false;
        int chosenRow = 0;
        int chosenSeat = 0;
        do {
            System.out.println("Enter a row number:");
            chosenRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            chosenSeat = sc.nextInt();
            if (chosenRow < 1 || chosenSeat < 1
                    || chosenRow > ROWS || chosenSeat > SEATS) {
                System.out.println("Wrong input!");
            } else if (arrangement[chosenRow - 1][chosenSeat - 1] != 'S') {
                System.out.println("That ticket has already been purchased!");
            } else {
                correctTicket = true;
                arrangement[chosenRow - 1][chosenSeat - 1] = 'B';
            }
        } while (!correctTicket);
        int ticketPrice = 0;
        if (ROWS * SEATS <= 60) {
            ticketPrice = 10;
        } else {
            if (chosenRow <= ROWS / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        boughtTickets++;
        curIncome += ticketPrice;
        percentage = ((float)boughtTickets / (ROWS * SEATS)) * 100;
        System.out.println("Ticket price: $" + ticketPrice);
    }

    private static void showMenu() {
        System.out.println("1. Show the seats\n2. Buy a ticket\n" +
                "3. Statistics\n0. Exit");
        int choice = sc.nextInt();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    printArrangement();
                    break;
                case 2:
                    calculateTicketPrice();
                    break;
                case 3:
                    showStatistics();
                    break;
            }
            System.out.println("1. Show the seats\n2. Buy a ticket\n" +
                    "3. Statistics\n0. Exit");
            choice = sc.nextInt();
        }
    }

    private static void createArrangement() {
        arrangement = new char[ROWS][SEATS];
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < SEATS; ++j) {
                arrangement[i][j] = 'S';
            }
        }
    }

    private static void showStatistics() {
        System.out.println("Number of purchased tickets: " + boughtTickets);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + curIncome);
        calculateProfit();
    }
}
