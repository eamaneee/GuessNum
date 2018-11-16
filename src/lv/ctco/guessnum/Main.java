package lv.ctco.guessnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static List <GameResult> results = new ArrayList<>();
    public static final File RESULTS_FILE = new File("results.txt");


    public static void main(String[] args) {
        loadResults();
        String endGame;
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
            do{
            endGame = scan.next();
            if(! (endGame.equals("y") || endGame.equals("n") )){
                System.out.println("Please input y or n");
            }
            } while (!(endGame.equals("y") || endGame.equals("n")));
        } while ("y".equals(endGame));

        // displayResults(results);
        displayResults();
        saveResults();
    }


    private static void displayResults() {
//private static void displayResults(List<GameResult> results){
        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r -> r.triesCount)
                                  .thenComparingLong(r -> r.duration))
                .limit(3)
                .forEach(r -> {
                    System.out.print(r.name + " ");
                    System.out.print(r.triesCount + " ");
                    System.out.println(r.duration);
                });
    }
       // variant 1
              // for (GameResult r: results) {
         //   System.out.print(r.name + " ");
           // System.out.print(r.triesCount + " ");
           // System.out.println(r.duration);
        //}
        // variant 2
    // for (GameResult r : results) {
        // System.out.printf("%s %d %.2t sec\n",
        // r.name,
        // r.triesCount,
        // duration / 1000,0);}



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


    private static void saveResults() {
        try (PrintWriter fileOut = new PrintWriter(RESULTS_FILE)) {

            int skipCount = results.size() - 5;

            for (GameResult r : results) {
                if (skipCount <=0) {
                    // fileOut.printf("%s %d %d\n", r.name, r.triesCount, r.duration);
                    // }
                    fileOut.print(r.name + " ");
                    fileOut.print(r.triesCount + " ");
                    fileOut.println(r.duration);
                }
                skipCount--; //uminshaem kazdij raz na edinichku

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void loadResults() {
        try (Scanner in = new Scanner(RESULTS_FILE)) {

            while (in.hasNext()) {
                GameResult gr = new GameResult();
                gr.name = in.next();
                gr.triesCount = in.nextInt();
                gr.duration = in.nextLong();

                results.add(gr);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



