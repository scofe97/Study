package 알고리즘.문제코드.백준.싸피_스터디.연습1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S2_2615_solution {
    static int[] dx = {1,-1,0,1};
    static int[] dy = {0,1,1,1};

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[19][19];


        for(int i = 0;i<19;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<19;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0;i<19;i++) {
            for(int j=0;j<19;j++) {

                // 돌이 있는 경우
                if(arr[i][j]==1||arr[i][j]==2) {

                    // 4가지 방향 확인
                    for(int k=0;k<4;k++) {
                        int cnt = 1;
                        int nx = 0;
                        int ny = 0;

                        for(int l = 1;l<19;l++) {
                            // 이동
                            nx = i + dx[k]*l;
                            ny = j + dy[k]*l;

                            // 범위벗어나면 밴
                            if(nx>=19 || nx<0 || ny>=19 || ny<0) break;

                            // 타입이 같다면 추가
                            if(arr[nx][ny]==arr[i][j]) {
                                cnt++;
                            }
                            // 타입이 다르면 밴
                            else if(arr[nx][ny]!=arr[i][j]) {
                                break;
                            }

                            // 개수초과하면 밴
                            if(cnt>5) {
                                break;
                            }
                        }

                        // 만약 반대방향이 본인과 같으면 넘어간다
                        if((i-dx[k]<19 && i-dx[k]>=0 && j-dy[k]<19 && j-dy[k]>=0) && (arr[i-dx[k]][j-dy[k]]==arr[i][j])) {
                            continue;
                        }

                        // 5개면 출력
                        if(cnt==5) {
                            System.out.println(arr[i][j]);
                            System.out.println((i+1)+" "+(j+1));
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(0);
    }
}
