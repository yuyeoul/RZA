import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static class Node implements Comparable<Node> {
        int r, c, cnt;

        public Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int INF = 987654321;

        // 입력
        int N = sc.nextInt();
        int[][] room = new int[N][N];
        int[][] min = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = sc.next().split("");
            for (int j = 0; j < N; j++) {
                room[i][j] = Integer.parseInt(temp[j]);
                min[i][j] = INF;
            }
        }

        // 초기화
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        // Dijkstra
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (min[node.r][node.c] > node.cnt) {
                min[node.r][node.c] = node.cnt;
            } else {
                continue;
            }

            for (int n = 0; n < 4; n++) {
                int nr = node.r + dr[n];
                int nc = node.c + dc[n];

                // offer 하기 전에 검은방을 최소로 지나는 경로가 맞는지 검증하는 단계가 필요함
                // 내일 추가하기
                if (isRange(nr, nc, N)) {
                    if (room[nr][nc] == 1) {
                        // 흰 방이면 그대로 진행
                        pq.offer(new Node(nr, nc, node.cnt));
                    } else {
                        // 검은 방이면 뚫는다
                        pq.offer(new Node(nr, nc, node.cnt+1));
                    }
                }
            }
        }

        System.out.println(min[N-1][N-1]);
        sc.close();
    }

    public static boolean isRange(int r, int c, int N) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}