package 자바공부.정렬;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 100);
		}
		System.out.println(Arrays.toString(arr));
		quick_sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void quick_sort(int[] arr) {
		quick_sort(arr, 0, arr.length - 1);
	}

	public static void quick_sort(int[] arr, int start, int end) {

		if (end - start < 1) {
			return;
		}

		int pivot = part(arr, 0, end);

		quick_sort(arr, start, pivot -1);
		quick_sort(arr, pivot + 1, end);
	}

	public static int part(int[] arr, int start
			, int end) {
		int pivot = arr[end];
		int i = start;
		int j = end - 1;

		while (i <= j) {
			while (i < j && arr[i] < pivot)
				i++;
			while (i < j && arr[j] > pivot)
				j--;

			if (i < j) {
				swap(arr, i, j);
			}else if(i == j) {
				swap(arr, i, end);
				break;
			}
		}
		System.out.println(Arrays.toString(arr));
		return i;
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
