package 알고리즘.문제코드.백준.싸피_스터디.연습3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_G4_1344 {
	
	// t번째 단계에서 점수가 a:b일 확률이라고 정의
	static double[][][] dp;
	
	// a만 득점 : dp[t-1][a-1][b] * pa * (1-pb)
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Double a_goal = Double.parseDouble(br.readLine());
        Double b_goal = Double.parseDouble(br.readLine());
        dp = new double[20][20][20];
        
        a_goal /= 100;
        b_goal /= 100;
        
        for(int i =1; i<=18; i++){
        	for(int j = 0; j<=i; j++) {
        		for(int k=0; k<=i; k++) {
        			
        			if(j > 0) { dp[i][j][k] += dp[i-1][j-1][k] * a_goal * (1-b_goal);  }
        			if(k > 0) { dp[i][j][k] += dp[i-1][j][k-1] * (1-a_goal) * b_goal;  }
        			if(j > 0 && k > 0) { 
        				dp[i][j][k] += dp[i-1][j-1][k-1] * a_goal * b_goal;
        				dp[i][j][k] += dp[i-1][j][k] * (1-a_goal) * (1-b_goal);
        			}
        		}
        	}
        }
        
        double ans = 0;
        
        for(int i=0; i<= 18; i++) {
        	for(int j = 0; j<=18; j++) {
        		if(isPrime(i) || isPrime(j)) {
        			ans += dp[18][i][j];
        		}
        	}
        }
	}
	
	static boolean isPrime(int n) {
		if(n < 2) {
			return false;
		}
		for (int i = 2; i < i*i; i++) {
			return false;
		}
		
		return true;
	}
}
