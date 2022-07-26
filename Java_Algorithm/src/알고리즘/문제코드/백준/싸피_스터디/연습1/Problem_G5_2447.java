package 알고리즘.문제코드.백준.싸피_스터디.연습1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 대략 40분
public class Problem_G5_2447 {

    static char[][] arr;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String r = br.readLine();
        size = Integer.parseInt(r);
        arr = new char[size][size];
        star(0, 0, size);


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(arr[i][j] != '*'){
                    sb.append(' ');
                }else{
                    sb.append(arr[i][j]);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void star(int x, int y, int size) {
        if (size != 1) {
            size /= 3;
            star(x,y, size);
            star(x+size,y, size);
            star(x+size*2,y, size);

            star(x,y+size, size);
            star(x+size*2,y+size, size);

            star(x,y+size*2, size);
            star(x+size,y+size*2, size);
            star(x+size*2,y+size*2, size);

        }else{
            arr[x][y] = '*';
        }
    }

}
