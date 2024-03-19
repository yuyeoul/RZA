import java.io.*;
import java.util.StringTokenizer;

public class BOJ_25682_Moon {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] chess = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                chess[i][j] = str.charAt(j);
            }
        }

        // 가장 왼쪽 위 칸의 색을 기준으로 두 종류의 체스판이 존재할 수 있다.
        int[][] black = new int[N+1][M+1];
        int[][] white = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 기본 값 채워주기
                black[i][j] = black[i-1][j] + black[i][j-1] - black[i-1][j-1];
                white[i][j] = white[i-1][j] + white[i][j-1] - white[i-1][j-1];

                if ((i+j) % 2 == 0) {
                    // 가장 왼쪽 위 칸의 같은 색이어야 하는 칸
                    if (chess[i-1][j-1] == 'W') {
                        black[i][j]++;
                    } else {
                        white[i][j]++;
                    }
                } else {
                    // 가장 왼쪽 위 칸이랑 다른 색이어야 하는 칸
                    if (chess[i-1][j-1] == 'B') {
                        black[i][j]++;
                    } else {
                        white[i][j]++;
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = K; i <= N; i++) {
            for (int j = K; j <= M; j++) {
                int minBlack = black[i][j] - black[i][j-K] - black[i-K][j] + black[i-K][j-K];
                int minWhite = white[i][j] - white[i][j-K] - white[i-K][j] + white[i-K][j-K];
                int minValue = Math.min(minBlack, minWhite);

                answer = Math.min(answer, minValue);
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();;
    }
}