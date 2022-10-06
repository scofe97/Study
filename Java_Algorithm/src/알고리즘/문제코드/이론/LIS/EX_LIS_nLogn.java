package 알고리즘.문제코드.이론.LIS;

import java.util.Arrays;

public class EX_LIS_nLogn {

    static int[] arr;
    static int[] lis;

    public static void main(String[] args) {
        arr = new int[]{10, 20, 40, 30, 1, 3, 5, 2, 90};
        lis = new int[arr.length];
        Arrays.fill(lis, Integer.MAX_VALUE);

        lis[0] = arr[0]; // 최초의 index 0의 값은 arr[0]이 된다.

        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            // 만약 원 배열이 탐색 중 더 큰 숫자라면 그대로 이어서 붙인다.
            if (lis[idx] < arr[i]) {
                lis[++idx] = arr[i];
                // 그렇지 않고 작다면 이진 탐색(Binary Search)를 통해 교체를 수행한다.
            } else {
                int target_index = binarySearch(lis, arr[i]);
                lis[target_index] = arr[i];
            }
        }

        // 현재 idx의 값에서 1을 더한 것이 바로 전체 LIS의 길이가 된다.
        System.out.println("LIS 길이 : " + (idx + 1));
    }

    // 반복문을 이용한 이진 탐색 방식
    public static int binarySearch(int[] arr, int x) {
        int start = 0;
        int end = arr.length - 1;

        // 현재 탐색한 위치가 찾고자 하는 값보다 크냐 작냐에 따라 중간 index 계산을 위한 start / end 값을 변경함
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        // 일치값을 찾지 못했을 때, -1이 아니라 그 적절한 위치를 반환해야 함
        return start;
    }
}
