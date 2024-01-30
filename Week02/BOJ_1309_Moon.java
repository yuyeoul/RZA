import java.util.Scanner;

public class BOJ_1309_Moon {

    static int[] lion;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 우리의 크기
        int[][] lion = new int[2][N+1]; // 사자를 배치하는 경우의 수

        /*
         * lion[0][N]에는 N번째 줄에는 사자가 없는 경우의 수
         * lion[1][N]에는 N번째 줄의 왼쪽에 사자가 있는 경우의 수
         * lion[2][N]에는 N번째 줄의 오른쪽에 사자가 있는 경우의 수를 저장한다.
         *
         * 최종적으로 lion[0][N] + lion[1][N] + lion[2][N] 를 정답으로 출력하면 된다.
         *
         * ... 수정
         * lion[1][N]과 lion[2][N]은 항상 같은 값을 가지므로
         * 계산을 줄일 수 있다.
         */

        lion[0][1] = 1;
        lion[1][1] = 1;

        // DP
        for (int i = 2; i <= N; i++) {
            lion[0][i] = ( lion[0][i-1] + lion[1][i-1]*2 ) % 9901;
            lion[1][i] = ( lion[0][i-1] + lion[1][i-1] ) % 9901;
        }

        // 정답 계산
        int totalCase = ( lion[0][N] + lion[1][N]*2 ) % 9901;

        // 출력
        System.out.println(totalCase);

        sc.close();
    }
}