import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class BOJ_14500_Moon {

    static int[][] map;
    static boolean[][] visited;
    static int N, M, max, now;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] dxSpecial = { {-1, 1, 0}, {-1, 1, 0}, {-1, 0, 0}, {1, 0, 0} };
    static int[][] dySpecial = { {0, 0, -1}, {0, 0, 1}, {0, -1, 1}, {0, -1, 1} };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 세로 크기
        M = sc.nextInt(); // 가로 크기

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[N][M]; // 방문처리 할 boolean 배열
        max = 0; // 현재까지 나온 최고값

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                now = 0;
                dfs(i, j, 0); // dfs 탐색(depth 4까지)
            }
        }

        // ㅗ 모양은 dfs 탐색 불가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                specialCase(i, j);
            }
        }

        System.out.println(max);
        sc.close();
    }

    public static void dfs(int x, int y, int depth) {
        if (depth == 4) {
            if (now > max) {
                max = now;
            }
            return;
        }

        visited[x][y] = true;
        now +=map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (isOnMap(nx, ny) && !visited[nx][ny]) {
                dfs(nx, ny, depth+1);
            }
        }
        now -=map[x][y];
        visited[x][y] = false;

    }

    public static void specialCase(int x, int y) {
        for (int i = 0; i < 4; i++) {
            now = map[x][y];
            one: for (int j = 0; j < 3; j++) {
                int nx = x + dxSpecial[i][j];
                int ny = y + dySpecial[i][j];
                if (isOnMap(nx, ny)) {
                    now +=map[nx][ny];
                } else {
                    break one;
                }
            }
            if (now > max) {
                max = now;
            }
        }
    }

    public static boolean isOnMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}