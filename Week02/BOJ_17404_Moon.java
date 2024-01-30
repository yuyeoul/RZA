import java.util.Scanner;

public class BOJ_17404_Moon {

    static final int INF = 999999999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] cost = new int[3][N];

        // 첫번째 집이 무슨색인지에 따라 다르게 가보자
        int[][] minCostRed = new int[3][N];
        int[][] minCostGreen = new int[3][N];
        int[][] minCostBlue = new int[3][N];

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < 3; i++) {
                cost[i][j] = sc.nextInt();
            }
        }

        // 첫 줄 채워넣기
        for (int i = 0; i < 3; i++) {
            minCostRed[i][0] = INF;
            minCostGreen[i][0] = INF;
            minCostBlue[i][0] = INF;
        }
        minCostRed[0][0] = cost[0][0];
        minCostGreen[1][0] = cost[1][0];
        minCostBlue[2][0] = cost[2][0];

        // 나머지 줄 채워넣기
        for (int i = 1; i < N; i++) {

            /*
             * 0번 열의 정보를 채워 넣을 때에는
             * 앞의 행 1번이나 2번 열의 값 중 최소값을 채택해
             * 해당 집을 0번 색으로 칠하는 값을 더해 기록한다.
             */

            // Red
            minCostRed[0][i] = Math.min(minCostRed[1][i - 1], minCostRed[2][i - 1]) +cost[0][i];
            minCostRed[1][i] = Math.min(minCostRed[0][i - 1], minCostRed[2][i - 1]) +cost[1][i];
            minCostRed[2][i] = Math.min(minCostRed[0][i - 1], minCostRed[1][i - 1]) +cost[2][i];
            // Green
            minCostGreen[0][i] = Math.min(minCostGreen[1][i - 1], minCostGreen[2][i - 1]) +cost[0][i];
            minCostGreen[1][i] = Math.min(minCostGreen[0][i - 1], minCostGreen[2][i - 1]) +cost[1][i];
            minCostGreen[2][i] = Math.min(minCostGreen[0][i - 1], minCostGreen[1][i - 1]) +cost[2][i];
            // Blue
            minCostBlue[0][i] = Math.min(minCostBlue[1][i - 1], minCostBlue[2][i - 1]) +cost[0][i];
            minCostBlue[1][i] = Math.min(minCostBlue[0][i - 1], minCostBlue[2][i - 1]) +cost[1][i];
            minCostBlue[2][i] = Math.min(minCostBlue[0][i - 1], minCostBlue[1][i - 1]) +cost[2][i];

        }

        int min = Math.min(minCostRed[1][N-1], minCostRed[2][N-1]);
        min = Math.min(min, minCostGreen[0][N-1]);
        min = Math.min(min, minCostGreen[2][N-1]);
        min = Math.min(min, minCostBlue[0][N-1]);
        min = Math.min(min, minCostBlue[1][N-1]);

        System.out.println(min);

        sc.close();
    }
}