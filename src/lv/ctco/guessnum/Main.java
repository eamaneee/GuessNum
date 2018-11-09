package lv.ctco.guessnum;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("What is your name?");
        String name = scan.next();
        System.out.println("Hello, " + name + "!");
        System.out.println("Guess number from 1 to 100:");
        int myNum = rand.nextInt(100) + 1;
        for (int i = 1; i < 11; i++) {
            int number;
            try {
                number = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You are cheater!");
                return;
            }
            //System.out.println("Your number is " + number);
            // System.out.println("Spoiler: " + myNum);
            if (myNum == number) {
                System.out.println("You are right " + myNum + "!");
                break;
            } else if (i == 10) {
                System.out.println("Game Over");
            } else if (myNum > number) {
                System.out.println("My number is greater than yours!");
            } else {
                System.out.println("My number is less than yours!");
            }
        }
    }
}
