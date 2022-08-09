package 자바공부.유튜브_라이브;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex_Scanner {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("D:\\study\\Java_Algorithm\\src\\자바공부\\유튜브_라이브\\inputTC.txt");
        Scanner sc = new Scanner(f);

        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }

        long start = System.nanoTime();
        System.setIn(new FileInputStream("D:\\study\\Java_Algorithm\\src\\자바공부\\유튜브_라이브\\inputTC.txt"));
        Scanner sc2 = new Scanner(System.in);

        int TC = sc2.nextInt();
        for (int tc = 0; tc < TC; tc++) {

            int N = sc2.nextInt();

            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sum += sc2.nextInt();
                }
            }
            System.out.println("#" +tc + " " + sum);
        }
        long end = System.nanoTime();
        System.out.println((end-start)/1_000_000_000.0+"s");




        /*String temp = sc.next();
        System.out.println(temp);
        sc.nextLine();
        temp = sc.nextLine();
        System.out.println(temp);*/


    }
}
