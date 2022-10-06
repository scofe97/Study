package 알고리즘.문제코드.이론.LIS;

public class EX_LIS_BruteForce {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 20, 40, 30, 1, 3, 5, 2, 90};
        int result = 1; // 일단 최소한 1이 답이 될 수 있다.

        int[] lis = new int[arr.length]; // LIS 길이 값을 저장하는 배열
        int max_lis = 1;  // LIS의 길이 중 가장 긴 값을 저장

        for (int i = 0; i < arr.length; i++) {
            result = Math.max(result, exhSearch(arr, i));// 각 부분에서 시작해 완전탐색
            lis[i] = result;

            max_lis = Math.max(max_lis, result);
            result = 1;
        }

        System.out.println(max_lis);

        for (int i = 0; i < lis.length; i++) {
            System.out.print(lis[i] + ", ");
        }
        System.out.println();

        // LIS에 따른 배열 출력해보기
        System.out.println("LIS 배열 출력하기");
        int start = 1; // max_lis 까지 순환

        for(int i=0; i < arr.length; i++){
            for(int j=start; j <= max_lis; j++){
                if(lis[i] == j){
                    System.out.print(arr[i] + ", ");
                    start++;
                    break;
                }
            }
        }
    }

    public static int exhSearch(int[] arr, int idx){
        if(idx == 0 ) return 1;
        int ret  = 1;

        for (int i = idx - 1; i >= 0; i--) {
            if(arr[i] < arr[idx]){
                ret = Math.max(ret, 1 + exhSearch(arr, i));
            }
        }
        return ret;
    }
}
