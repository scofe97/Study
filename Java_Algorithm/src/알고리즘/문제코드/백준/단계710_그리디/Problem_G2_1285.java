package 알고리즘.문제코드.백준.단계710_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_G2_1285 {

    static int size;
    static char[][] graph;
    static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        graph = new char[size][size];
        for (int i = 0; i < size; i++) {
            graph[i] = br.readLine().toCharArray();
        }
        result = size*size;

        dfs(result);

        System.out.println(result);
    }

    static void dfs(int count){

        if(count > result){
            return;
        }

        result = count;

        for (int i = 0; i < size*2; i++) {
            int temp1 = 0;
            int temp2 = 0;
            if(i < size){
                for (int j = 0; j < size; j++) {
                    if(graph[i%size][j] == 'T'){
                        temp1++;
                    }else{
                        temp2++;
                    }
                }

                if(temp1 > temp2){
                    for (int j = 0; j < size; j++) {
                        graph[i%size][j] = graph[i%size][j] == 'T' ? 'H' : 'T';
                    }

                    if(count > check()){
                        dfs(check());
                    }

                    for (int j = 0; j < size; j++) {
                        graph[i%size][j] = graph[i%size][j] == 'T' ? 'H' : 'T';
                    }
                }
            }else{
                for (int j = 0; j < size; j++) {
                    if(graph[j][i%size] == 'T'){
                        temp1++;
                    }else{
                        temp2++;
                    }
                }
                if(temp1 > temp2){
                    for (int j = 0; j < size; j++) {
                        graph[j][i%size] = graph[j][i%size] == 'T' ? 'H' : 'T';
                    }

                    dfs(check());

                    for (int j = 0; j < size; j++) {
                        graph[j][i%size] = graph[j][i%size] == 'T' ? 'H' : 'T';
                    }
                }
            }
        }
    }

    static int check(){
        int temp = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(graph[i][j] == 'T'){
                    temp++;
                }
            }
        }

        return temp;
    }

}
