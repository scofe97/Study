package 자바공부.정렬;

import java.util.Arrays;

public class QuickSort2 {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

		System.out.println(Arrays.toString(arr));
		sort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int start, int end) {
        if (end - start < 1) {
            return;
        }

        int pivot = partition(arr, start, end);
		System.out.println(Arrays.toString(arr) + " " + arr[pivot] + " "+start + " "+end);


        sort(arr, pivot + 1, end);
        sort(arr, start, pivot - 1);

    }

    private static int partition(int arr[], int start, int end) {
		int pivot = arr[start];
		int i = start;
		int j = end;

		// <= 범위는 start 피벗이면 i에게 주고 end 피벗이면 j에게 줌
		while(i < j){
			while(i < j && arr[j] > pivot) j--;
			while(i < j && arr[i] <= pivot) i++;

			if(i < j) swap(arr, i, j);
		}

		swap(arr, i, start);
		return i;
    }


    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
