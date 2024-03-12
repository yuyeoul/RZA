import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3078_Moon {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 총 학생 수
        int K = Integer.parseInt(st.nextToken()); // 슬라이드 윈도우의 크기 - 1

        Queue<Integer> queue = new LinkedList<>();
        int[] cnt = new int[21];
        long answer = 0;
        for (int i = 0; i < N; i++) {
            // 좋은 친구가 queue에 몇 명 있는지 세어보기
            int in = br.readLine().length();
            answer +=cnt[in];

            // 다음으로 들어올 친구들에게 좋은 친구 후보가 될 수 있도록 queue에 삽입
            queue.offer(in); // queue에 현재 길이 집어넣기
            cnt[in]++;

            // 등수가 K 이상 차이나는 친구는 좋은 친구가 아님
            if (queue.size() > K) {
                int out = queue.poll();
                cnt[out]--;
            }
        }
        
        // 출력
        bw.write(String.valueOf(answer));
        
        br.close();
        bw.close();
    }
}