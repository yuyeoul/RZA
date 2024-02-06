import java.util.Scanner;

public class BOJ_1405_Moon {

    static int N;
    static int[] dir;
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static double[][] prob;
    static double answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // N번 이동
        dir = new int[4];
        for (int i = 0; i < 4; i++) {
            dir[i] = sc.nextInt();
        }

        // 초기화
        prob = new double[29][29];
        prob[14][14] = 1;

        // DFS
        dfs(14, 14, 0);

        System.out.println(answer);
        sc.close();
    }

    public static void dfs(int r, int c, int depth) {
        // N번 이동하는 동안 단순한 이동경로를 유지하였을 경우에만 최종 정답으로 채택한다.
        if (depth == N) {
            answer +=prob[r][c];
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 다음으로 방문 예정인 칸의 정보
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 만약 방문처리 되지 않은 칸이라면 방문한다
            if (prob[nr][nc] == 0) {
                prob[nr][nc] = prob[r][c] * ((double)dir[i] / 100);
                dfs(nr, nc, depth+1);
                prob[nr][nc] = 0;
            }
        }
    }
}