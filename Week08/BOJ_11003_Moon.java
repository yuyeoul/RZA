import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11003_Moon {

    static class Number implements Comparable<Number> {
        int idx, num;

        public Number(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Number o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input 받기
        int N = Integer.parseInt(st.nextToken()); // 총 숫자의 개수
        int L = Integer.parseInt(st.nextToken()); // 최솟값을 찾아야하는 범위의 길이
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Number> dq = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int nowNum = nums[i];

            // 새로운 숫자를 삽입하기 전에 현재 삽입할 숫자보다 큰 숫자는 미리 제거한다.
            while (!dq.isEmpty() && dq.peekLast().num >= nowNum) {
                dq.pollLast();
            }

            // 삽입
            dq.offer(new Number(i, nowNum));

            // 출력하기 전 슬라이드 윈도우의 인덱스를 벗어난 숫자는 제거한다.
            if (dq.peekFirst().idx < i-L+1) {
                dq.pollFirst();
            }

            // 출력
            bw.write(dq.peekFirst().num+" ");
        }

        br.close();
        bw.close();
    }
}