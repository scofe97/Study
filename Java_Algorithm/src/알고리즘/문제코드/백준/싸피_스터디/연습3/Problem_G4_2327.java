package 알고리즘.문제코드.백준.싸피_스터디.연습3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Problem_G4_2327 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[H+1];

		List<Student> students = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			students.add(new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		Collections.sort(students);

		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[H+1];
			if(dp[students.get(i).height] < students.get(i).velocity){
				visited[students.get(i).height] = true;
				dp[students.get(i).height] = students.get(i).velocity;
			}

			for (int j = 1; j < (H+1) - students.get(i).height; j++) {
				if(!visited[j] && dp[j + students.get(i).height] < Math.min(dp[j] , students.get(i).velocity)){
					visited[j + students.get(i).height] = true;
					dp[j + students.get(i).height] = Math.min(dp[j] , students.get(i).velocity);
				}
			}
		}

		System.out.println(dp[H]);

	}

	static class Student implements Comparable<Student> {
		int height;
		int velocity;

		public Student(int height, int velocity) {
			this.height = height;
			this.velocity = velocity;
		}

		@Override
		public int compareTo(Student o) {
			if (this.velocity == o.velocity) {
				return this.height - o.height;
			} else {
				return o.velocity - this.velocity;
			}
		}

	}
}
