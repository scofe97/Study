package 알고리즘.문제코드.백준.단계820_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 이분탐색 진행 -> 이때 탐색하는 범위는 구간에서 발생할 수 있는 최소 ~ 최대값 사이
// 탐색에서 사용하는 중앙값이 문제에서 구하고자하는 최대값의 최소값?
public class Problem_G4_13397 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int result = -1;

        int[] list = new int[n];

        st = new StringTokenizer(br.readLine());
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            list[i] = temp;
            max = Math.max(max, temp);
            min = Math.min(min, temp);
        }

        int left = 0;
        int right = max;
        int mid = 0;
        while(left <= right){
            mid = (left + right) / 2;

            if(canDivideByMid(mid, m, list)){
                result = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        System.out.println(result);
    }


    /**
     * @param mid 이분탐색으로 설정한 mid
     * @param m 쪼갤수 있는 범위 ( 최대값 - 최소값이 mid보다 커지면 카운트추가 )
     * @param nums 리스트
     * @return m범위 이내로 분할이 가능한지 반환
     */
    static boolean canDivideByMid(int mid, int m, int[] nums){
        int countSet = 1;

        int max = nums[0];
        int min = nums[0];

        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);

            if(max - min > mid){
                countSet++;
                min = nums[i];
                max = nums[i];
            }

            if(countSet > m) return false;
        }

        return true;
    }
}
