package 알고리즘.문제코드.SSAFY.시험_A형대비.SWEA_코드배틀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_No3_Shuffle_O_Matic {

    static int cnt;
    static int result;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            cnt = Integer.parseInt(br.readLine()); // 카드개수
            result = Integer.MAX_VALUE; // 결과값
            map = new HashMap<>();
            List<Integer> card = new ArrayList<>(); // 카드넣을 변수

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cnt; i++) {
                int temp = Integer.parseInt(st.nextToken());
                card.add(temp);
            }

            // 순환시작
            dfs(0, card);

            // 만약 순환이 안되서 최대값이 INF 이면 -1, 아니면 result 출력
            result =  result == Integer.MAX_VALUE ? -1 : result;

            // tc 저장
            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(result)
                    .append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int step, List<Integer> stepCard) {
        // 5번을 넘어가면 멈춘다.
        if (step == 6) return;

        // 내림차순 혹은 오름차순이 가능하면 멈추고, 셔플개수를 넣어줌
        if (isSortUp(stepCard) || isSortDown(stepCard)) {
            result = Math.min(result, step);
            return;
        }else if(step == 5){
            return;
        }

        map.putIfAbsent(stepCard.toString(), step);

        // 좌우 카드를 넣을 변수
        Queue<Integer> lCard = new LinkedList<>();
        Queue<Integer> rCard = new LinkedList<>();

        // 각 셔플마다 순서가 변하는 카드를 넣을 변수
        List<Integer> shuffle = new ArrayList<>();;


        // 총 카드개수 만큼의 셔플방식이 존재한다
        for (int i = 0; i < cnt; i++) {

            // 좌우카드 배치
            for (int j = 0; j < cnt; j++) {
                if(j < cnt/2) lCard.offer(stepCard.get(j));
                else rCard.offer(stepCard.get(j));
            }

            // 가능한 모든 셔플의 경우로 DFS 를 돌린다
            // 카드개수 6으로 가정해서 주석설정


            // 셔플방식 x가 카드사이즈 절반 보다 작은경우
            if(i < cnt/2){

                // 좌측전체 카드 - i 개를 모두 뽑아옴
                for (int j = 0; j < cnt/2 - i; j++) {
                    shuffle.add(lCard.poll());
                }

                // i번 좌우카드 번갈아서 놓음
                for (int j = 0; j < i; j++) {
                    shuffle.add(rCard.poll());
                    shuffle.add(lCard.poll());
                }

                // 우측전체 카드 - i 개를 모두 뽑아옴
                for (int j = 0; j < cnt/2 - i; j++) {
                    shuffle.add(rCard.poll());
                }

                if(map.containsKey(shuffle.toString())){
                    if(map.get(shuffle.toString()) > step+1){
                        map.put(shuffle.toString(), step+1);
                        dfs(step+1, shuffle);
                    }
                }else{
                    dfs(step+1, shuffle);
                }

            }
            // 셔플방식 x가 카드사이즈 절반 보다 크거나 같은경우
            else{

                // 우측전체 카드 - i 개를 모두 뽑아옴
                for (int j = 0; j < i - cnt/2 + 1; j++) {
                    shuffle.add(rCard.poll());
                }

                // i번 좌우카드 번갈아서 놓음
                for (int j = 0; j < cnt - i - 1; j++) {
                    shuffle.add(lCard.poll());
                    shuffle.add(rCard.poll());
                }

                // 좌측전체 카드 - i 개를 모두 뽑아옴
                for (int j = 0; j < i - cnt/2 + 1; j++) {
                    shuffle.add(lCard.poll());
                }

                // 다음셔플 이동
                if(map.containsKey(shuffle.toString())){
                    if(map.get(shuffle.toString()) > step+1){
                        map.put(shuffle.toString(), step+1);
                        dfs(step+1, shuffle);
                    }
                }else{
                    dfs(step+1, shuffle);
                }


            }

            shuffle.clear();
        }
    }

    // 오름차순이 가능한가?
    static boolean isSortUp(List<Integer> card) {
        int min = -1;
        for (Integer integer : card) {
            if (min < integer) min = integer;
            else return false;
        }
        return true;
    }

    // 내림차순이 가능한가?
    static boolean isSortDown(List<Integer> card) {
        int max = Integer.MAX_VALUE;
        for (Integer integer : card) {
            if (max > integer) max = integer;
            else return false;
        }
        return true;
    }
}
