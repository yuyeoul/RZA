import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2143_Moon {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // target number

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = A[i-1] + Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = B[i-1] + Integer.parseInt(st.nextToken());
        }

        // 가능한 부 배열 합의 모든 정보를 ArrayList에 저장
        ArrayList<Integer> sumA = new ArrayList<>();
        ArrayList<Integer> sumB = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                sumA.add( A[j]-A[i] );
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = i+1; j <= m; j++) {
                sumB.add( B[j]-B[i] );
            }
        }

        // 정렬
        Collections.sort(sumA);
        Collections.sort(sumB);

        // two pointers
        int p = 0;
        int q = sumB.size()-1;

        long answer = 0;

        while (p < sumA.size() && q >= 0) {
            int nowSumA = sumA.get(p);
            int nowSumB = sumB.get(q);
            int sum = nowSumA + nowSumB;

            if (sum == T) {
                // T와 sum 값이 일치하는 경우 정답에 포함
                long cntA = 0;
                long cntB = 0;

                while (p < sumA.size() && nowSumA == sumA.get(p)) {
                    p++;
                    cntA++;
                }

                while (q >= 0 && nowSumB == sumB.get(q)) {
                    q--;
                    cntB++;
                }

                answer +=(cntA * cntB);
            } else if (sum > T) {
                // T보다 큰 경우 sum 값을 줄이는 방향으로 포인터 이동
                q--;
            } else {
                // T보다 작은 경우 sum 값을 늘리는 방향으로 포인터 이동
                p++;
            }
        }

        // 출력
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}