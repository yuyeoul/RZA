import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_20058_Moon {

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static Queue<Place> queue = new LinkedList<>();
    static boolean[][] visited;

    static int N, Q, size;
    static int[][] plate;
    static int cnt, max;

    static class Place {
        int r, c;
        public Place(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 얼음판의 크기 2^N
        Q = sc.nextInt(); // 파이어스톰을 총 Q번 시전
        size = (int) Math.pow(2, N);

        plate = new int[size][size];
        visited = new boolean[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                plate[r][c] = sc.nextInt();
            }
        }

        // 마법 ㄱ
        for (int p = 0; p < Q; p++) {
            int L = sc.nextInt();
            if (L == 0) {
                // 돌아가지 않고 녹이기만 한다
                melt();
                continue;
            }

            // 1. storm 마법 (돌리기)
            int stormSize = (int) Math.pow(2, L);
            for (int i = 0; i < (size/stormSize); i++) {
                for (int j = 0; j < (size/stormSize); j++) {
                    int[][] temp = new int[stormSize][stormSize];
                    for (int r = 0; r < stormSize; r++) {
                        for (int c = 0; c < stormSize; c++) {
                            temp[c][stormSize-1-r] = plate[i*stormSize + r][j*stormSize + c];
                        }
                    }
                    for (int r = 0; r < stormSize; r++) {
                        for (int c = 0; c < stormSize; c++) {
                            plate[i*stormSize + r][j*stormSize + c] = temp[r][c];
                        }
                    }
                }
            }

            // 2. fire 마법 (녹이기)
            melt();
        }

        // 출력값 1. 남아있는 얼음의 합
        int sum = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                sum +=plate[r][c];
            }
        }

        // 출력값 2. 가장 큰 덩어리가 차지하는 칸의 개수(덩어리가 없으면 0)
        // bfs() : 해당 덩어리가 차지하는 칸의 개수
        // max : 가장 큰 덩어리가 차지하는 칸의 개수
        max = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (!visited[r][c] && plate[r][c] != 0) {
                    queue.add(new Place(r, c));
                    visited[r][c] = true;
                    int bfs = bfs();
                    if (bfs > max) {
                        max = bfs;
                    }
                }
            }
        }

        // 출력
        System.out.println(sum);
        System.out.println(max);
        sc.close();
    }

    public static void melt() {
        boolean[][] clone = new boolean[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // 이 칸에서 인접한 칸에 세 개 이상의 얼음이 있는지 확인 필요
                int cntIce = 0;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (isRange(nr, nc) && plate[nr][nc] != 0) {
                        cntIce++;
                    }
                }
                if (cntIce < 3 && plate[r][c] > 0) {
                    clone[r][c] = true;
                }
            }
        }

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (clone[r][c]) {
                    plate[r][c]--;
                }
            }
        }
    }

    public static int bfs() {

        int cnt = 0; // 해당 덩어리가 차지하는 칸의 개수를 count

        while (!queue.isEmpty()) {
            Place place = queue.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nr = place.r + dr[i];
                int nc = place.c + dc[i];
                if (isRange(nr, nc) && !visited[nr][nc] && plate[nr][nc] > 0) {
                    queue.add(new Place(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }

        return cnt;
    }

    public static boolean isRange(int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }
}