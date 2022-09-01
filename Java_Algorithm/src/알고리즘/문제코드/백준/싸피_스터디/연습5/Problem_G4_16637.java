package 알고리즘.문제코드.백준.싸피_스터디.연습5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_G4_16637 {
	
	static int size;
	static List<Integer> list;
	
	static int[] num;
	static char[] op;
	static boolean[] visited;
	static int result;
	
	public static void main(String[] args) throws IOException {

		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		size = Integer.parseInt(br.readLine());
		String[] token = br.readLine().split("");
		num = new int[size/2 + 1];
		op = new char[size/2];
		list = new ArrayList<>();
		visited = new boolean[size/2];
		result = Integer.MIN_VALUE;
		
		
		for (int i = 0, j = 0; i < size/2; i++) {
			num[i] = Integer.parseInt(token[j++]);
			op[i] = token[j++].charAt(0);
		}
		num[size/2] = Integer.parseInt(token[token.length-1]);
		
		dfs(0, num[0]);
		System.out.println(result);
		
		
	}
	
	static void dfs(int step, int sum) {
		if(step == size/2) {
			result = Math.max(result, sum);
			return;
		}
		dfs(step+1, calculate(sum, num[step+1], op[step]));
		
		if(step + 1 < size/2) {
			int temp = calculate(num[step+1], num[step+2], op[step+1]);
			int sumTotal = calculate(sum, temp, op[step]);
			dfs(step+2, sumTotal);
		}
	}
	
	static int calculate(int sum, int plus, char sep) {
		if(sep=='+')
            return sum + plus;
        if(sep=='-')
            return sum - plus;
        return sum * plus;
	}
}
