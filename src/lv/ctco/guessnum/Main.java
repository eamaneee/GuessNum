package lv.ctco.guessnum;

import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static List <GameResult> results = new ArrayList<>();


    public static void main(String[] args) {
        do {
            System.out.println("What is your name?");
            String name = scan.next();
            System.out.println("Hello, " + name + "!");
            System.out.println("Guess number from 1 to 100:");
            int myNum = rand.nextInt(100) + 1;
            long t1 = System.currentTimeMillis();

            for (int i = 1; i < 11; i++) {
                int userNum = readUserNum();
                if (myNum == userNum) {
                    System.out.println("You are right " + myNum + "!");
                    GameResult r = new GameResult();
                    r.name = name;
                    r.triesCount = i;
                    r.duration = System.currentTimeMillis() - t1;
                    results.add(r);
                   break;
                } else if (i == 10) {
                    System.out.println("Game Over");
                } else if (myNum > userNum) {
                    System.out.println("My number is greater than yours!");
                } else {
                    System.out.println("My number is less than yours!");
                }
            }
            System.out.println("Would you like to play one more time? Input y.");
        } while ("y".equals(scan.next()));
        for (GameResult r: results) {
            System.out.print(r.name);
            System.out.print(r.triesCount);
            System.out.println(r.duration);
        }
    }


    private static int readUserNum() {
        while (true) {
            try {
                int userNum = scan.nextInt();
                if (userNum < 1 || userNum > 100) {
                    System.out.println("Your number is out of range. Try again.");
                    continue;
                }
                return userNum;
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("You are cheater!");
            }
        }
    }
}


