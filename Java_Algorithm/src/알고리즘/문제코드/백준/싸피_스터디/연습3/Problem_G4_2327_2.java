package 알고리즘.문제코드.백준.싸피_스터디.연습3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Problem_G4_2327_2 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			students.add(new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		Collections.sort(students);
		List<Set<Integer>> dp = new ArrayList<>();
		dp.add(new HashSet<>());
		int result = -1;
		
		for (int i = 0; i < N; i++) {
			if(i == 0) {
				dp.get(0).add(students.get(0).height);
			}else {
				Set<Integer> temp = new HashSet<>();
				for (Integer t : dp.get(i - 1)) {
					temp.add(t + students.get(i).height);
					temp.add(t);
				}
				dp.add(temp);
			}
			
			if (dp.get(i).contains(H)) {
				result = students.get(i).velocity;
				break;
			}
			
		}

		System.out.println(result);

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
