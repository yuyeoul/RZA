import java.util.Scanner;

public class BOJ_2616_Bae {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //입력받고
        int N=sc.nextInt();
        int[] arr =new int[N+1];
        int[] sum=new int[N+1];
        //원본배열이랑 누적합 배열 채워주기
        for(int i=1;i<=N;i++){
            arr[i]=sc.nextInt();
            sum[i]=sum[i-1]+arr[i];
        }
        //dp배열 만들어 주고
        int[][]dp=new int[4][N+1];
        int M=sc.nextInt();
        //소형기관차는 총 3대
        for(int i=1;i<=3;i++){
            //j=i*M인 이유는 누적합 배열에서 구간에 대해 적용해야하기 때문에
            //ex)N=10, M=3 소형기관차가 3대까지 끌수 있다
            //1번 소형기관차는 1~3을 더한 값부터 시작 = 3번 인덱스부터 시작
            for(int j=i*M;j<=N;j++){
                // dp[i-1][j-M]+sum[j]-sum[j-M]는 현재 i가 2, j가 4라고 해보면 dp[i-1] == dp[1]
                // dp[1]은 하나의 소형 기관차가 운송할 수 있는 최대 손님수
                // j-M는 현재 j가 4라고 하면 하나의 소형 기관차가 운송할 수 있는 만큼 전의 값 + 그 이후부터 현재 j까지의 손님수 합
                // 현재 j index를 포함한 값이 큰지 아니면 바로 전 값이 큰지 비교하고
                // 소형 기관차 2대가 운송할 수 있는 최대의 손님 수를 알 수 있따
                // dp[3]도 똑같이~
                dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j-M]+sum[j]-sum[j-M]);
            }
        }
        System.out.println(dp[3][N]);
    }
}
