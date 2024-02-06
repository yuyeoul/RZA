import java.util.Scanner;

public class BOJ_1937_Moon {

    static int N, max;
    static int[][] bamboo;
    static int[][] save;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        bamboo = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bamboo[i][j] = sc.nextInt();
            }
        }

        // DFS
        save = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max( dfs(i, j), max );
            }
        }

        System.out.println(max);
        sc.close();
    }

    public static int dfs(int r, int c) {
        // dfs(r, c)는 (r, c) 칸에서 출발했을 때 지나갈 수 있는 최대 칸의 수를 반환한다.

        if (save[r][c] == 0) {
            int depth = 1; // 일단 여기서 출발해서 당장 끝나도 한 칸은 간 것
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isRange(nr, nc) && bamboo[nr][nc] > bamboo[r][c]) {
                    // 다음 칸인 (nr, nc)로 갈 수 있으면
                    // depth(해당 칸에서 출발해서 최대로 갈 수 있는 칸의 수)를 갱신한다.
                    // 이 때, dfs(nr, nc) (다음 칸에서 출발해서 최대로 갈 수 있는 칸의 수)에 1을 더한 값과 비교하여
                    // 더 큰 값을 채택한다.
                    depth = Math.max(depth, dfs(nr, nc)+1);
                }
            }
            // save라는 이차원 배열에는 해당 칸에서 출발해서 최대로 갈 수 있는 칸의 수를 기록해둔다.
            save[r][c] = depth;
        }
        return save[r][c];
    }

    public static boolean isRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }
}