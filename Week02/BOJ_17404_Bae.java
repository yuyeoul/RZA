
import java.util.Scanner;


public class BOJ_17404_Bae {
    public static void main(String[] args)  {
        Scanner sc= new Scanner(System.in);

        int N = sc.nextInt();
        //입력 값 받을 배열
        int[][] cost = new int[N][3];
        //dp배열
        int[][] dp =new int [N][3];
        //입력값
        for (int i = 0; i < N; i++) {
           for(int j=0;j<3;j++){
               cost[i][j]=sc.nextInt();
           }

        }
        //최대 1000줄에 최대값 1000 == 최대비용 1000000
        int max=1000001;
        //정답 변수
        int ans=1000001;
        //logic
        //맨 처음 칠하는 색을 R G B 각각 나누어서 생각
        //0=R 1=G 2=B
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                //시작 지점에는 0번째줄 비용넣고
                //나머지에는 최대값 채워주기
                if(i==j){
                    dp[0][j]=cost[0][j];
                }else{
                    dp[0][j]=max;
                }
            }
            //dp배열 채워주기
            for (int j = 1; j < N; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2])+cost[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2])+cost[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1])+cost[j][2];
            }
            //i는 시작지점 색
            //맨끝지점에서 같은 색이면 건너뛰고 아니라면 작은값 갱신시켜주기
            for(int j=0;j<3;j++){
                if(i==j){
                    continue;
                }
                ans=Math.min(ans,dp[N-1][j]);
            }
        }

        System.out.println(ans);
    }

}