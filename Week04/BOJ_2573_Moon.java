import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2573_Moon {

    static int N, M;
    static int[][] iceberg;
    static boolean[][] isIce;
    static int cntIce;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static Queue<Ice> queue = new LinkedList<>();
    static int days;
    static boolean[][] visited;
    static boolean split;

    static class Ice {
        int r, c;

        Ice(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        iceberg = new int[N][M];
        isIce = new boolean[N][M];
        cntIce = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                iceberg[r][c] = sc.nextInt();
                if (iceberg[r][c] != 0) {
                    isIce[r][c] = true;
                    cntIce++;
                }
            }
        }

        days = 0;
        split = false;

        while (!split) {
            days++;
            melt();

            visited = new boolean[N][M];

            for (int r = 1; r < N-1; r++) {
                for (int c = 1; c < M-1; c++) {
                    if (!visited[r][c] && isIce[r][c]) {
                        queue.add(new Ice(r,c));
                        visited[r][c] = true;
                        bfs();
                    }
                }
            }

            if (cntIce == 0) {
                System.out.println(0);
                System.exit(0);
            }
        }

        System.out.println(days);

        sc.close();
    }

    public static void melt() {
        int[][] melt = new int[N][M];

        // 모든 칸 순회하면서 각 칸의 빙산이 얼만큼씩 녹을지 체크
        for (int r = 1; r < N-1; r++) {
            for (int c = 1; c < M-1; c++) {
                if (isIce[r][c]) {
                    int cnt = 0; // 인접한 칸에 0이 몇 개 있는지 저장
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (iceberg[nr][nc] == 0) {
                            cnt++;
                        }
                    }
                    melt[r][c] = cnt;
                }
            }
        }

        // 빙산이 녹아요
        for (int r = 1; r < N-1; r++) {
            for (int c = 1; c < M-1; c++) {

                if (isIce[r][c] && iceberg[r][c] <= melt[r][c]) {
                    iceberg[r][c] = 0;
                    isIce[r][c] = false;
                    cntIce--;
                } else {
                    iceberg[r][c] -=melt[r][c];
                }
            }
        }
    }

    public static void bfs() {
        int cntBfs = 0;

        while (!queue.isEmpty()) {
            Ice ice = queue.poll();
            int r = ice.r;
            int c = ice.c;
            cntBfs++;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (!visited[nr][nc] && isIce[nr][nc]) {
                    queue.add(new Ice(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        } // while

        if (cntBfs != cntIce) {
            split = true;
        }
    }
}