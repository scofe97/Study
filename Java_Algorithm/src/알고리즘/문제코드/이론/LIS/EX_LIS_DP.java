package 알고리즘.문제코드.이론.LIS;

public class EX_LIS_DP {
    static int[] arr; // 원 배열
    static int[] lis; // LIS 길이 저장
    static int[] V;   // 이전 인덱스 저장

    public static void main(String[] args) {

        arr = new int[]{10, 20, 40, 30, 1, 3, 5, 2, 90};
        lis = new int[arr.length]; // lis 길이
        V = new int[arr.length]; // 이전 idx

        // 가장 긴 lis값을 갖는 index를 반환함
        int result = dp();
        System.out.println("LIS 길이 : " + lis[result] + "\n");
        // *********** 여기까지가 LIS 길이 구하는 부분. ***************


        // LIS 길이 값 출력하기
        System.out.println("각 index의 LIS 값 : ");
        for(int i=0; i < lis.length; i++){
            System.out.print(lis[i] + ", ");
        }
        System.out.println();

        // LIS 배열 출력해보기
        // 출력 시 뒤에서부터 lis 길이를 기준으로 써내려가야 한다.
        // 앞에서부터 하면 제일 앞의 값이 가장 큰 값일 때, 그것을 걸러낼 수 없다.
        printLis(result);
    }

    public static int dp(){
        int max_lis = 0;
        int last = 0;

        for (int i = 0; i < lis.length; i++) {
            lis[i] = 1;
            V[i] = -1;

            for (int j = i-1; j >= 0; j--) {
                if(arr[i] > arr[j] && lis[i] <= lis[j]){
                    lis[i] = lis[j] + 1;
                    V[i] = j;
                }

                if(arr[i] > max_lis) {
                    max_lis = arr[i];
                    last = i;
                }
            }
        }
        return last;
    }

    // 재귀를 통해 LIS 배열 출력
    private static void printLis(int idx) {
        // 더 갈 수 없는 가장 이전 index 까지 간 경우
        if(V[idx] == -1) {
            System.out.print(arr[idx] + " ");
            return;
        }
        printLis(V[idx]);
        System.out.print(arr[idx] + " ");
    }
}
