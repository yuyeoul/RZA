import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static class Index implements Comparable<Index> {
        int r, c, dist;

        public Index(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Index o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 최단거리 초기화를 위한 적당히 큰 수
        final int INF = 987654321;

        int N = sc.nextInt();
        int prob = 0; // while문이 몇 번 반복되었는지 기록할 정수

        while (N > 0) {
            prob++;

            int[][] thief = new int[N][N]; // 각 칸의 도둑루피
            int[][] total = new int[N][N]; // 그동안 획득한 도둑루피의 합
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    thief[i][j] = sc.nextInt();
                    total[i][j] = INF;
                }
            }

            // BFS
            PriorityQueue<Index> pq = new PriorityQueue<Index>();
            total[0][0] = thief[0][0];
            pq.offer(new Index(0, 0, total[0][0]));

            while (!pq.isEmpty()) {
                Index idx = pq.poll();

                for (int n = 0; n < 4; n++) {
                    int nr = idx.r + dr[n];
                    int nc = idx.c + dc[n];

                    if (isRange(nr, nc, N)) {
                        int newTotal = total[idx.r][idx.c] + thief[nr][nc];
                        if (total[nr][nc] > newTotal) {
                            // 기록해둔 최단거리가 새로 구한 최단거리보다 길면 짧은것으로 갱신한다
                            total[nr][nc] = newTotal;
                            pq.offer(new Index(nr, nc, total[nr][nc]));
                        }
                    }
                }
            }

            // 출력
            System.out.println("Problem "+prob+": "+total[N-1][N-1]);

            N = sc.nextInt(); // 여기서 0이 입력되면 while문 종료
        }

        sc.close();
    }

    public static boolean isRange(int r, int c, int N) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}