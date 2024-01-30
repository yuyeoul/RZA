import java.util.Scanner;

public class BOJ_1309 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        int[] dp=new int[N+1];
        dp[1]=3;
        //3까지 그려보니 규칙이 나타났다......
        if(N>1){
            dp[2]=7;
            for(int i=3;i<=N;i++){
                dp[i]=(dp[i-2]+dp[i-1]*2)%9901;
            }
            System.out.println(dp[N]);
        }else{
            System.out.println(dp[N]);
        }
    }
}
