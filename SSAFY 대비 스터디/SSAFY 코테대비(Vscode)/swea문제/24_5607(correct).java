import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_5607_조합_Solution {
	static int t,n,r;
	static final long MOD=1234567891;
	static long[] fact;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		fact=new long[1000001];
		fact[0]=1;
		for (int i = 1; i <1000001; i++) {
			fact[i]=fact[i-1]*i;
			fact[i]%=MOD;// 나머지로 계속 나눠준다.
		}
		
		t=Integer.parseInt(br.readLine());
		for (int tc = 1; tc <=t; tc++) {
			st=new StringTokenizer(br.readLine());
            
			n=Integer.parseInt(st.nextToken());
			r=Integer.parseInt(st.nextToken());
			
			long up=fact[n];
			long down=(fact[n-r]*fact[r])%MOD;
			long refactoredDown=pow(down,MOD-2);
			
			System.out.printf("#%d %d\n",tc,(up*refactoredDown)%MOD);
		}
		
	}

	private static long pow(long a, long N) {// a의 n승 구하기 최적화방법
		if(N==0)return 1;
		if(N==1)return a;
		if(N%2==0) {
			long temp=pow(a,N/2);
			return (temp*temp)%MOD;
		}
		long temp=pow(a,N-1)%MOD;
		return (temp*a)%MOD;
		
	}
}