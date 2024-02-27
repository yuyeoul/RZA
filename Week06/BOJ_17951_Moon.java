import java.util.Scanner;

public class Main {

    static int N, K;
    static int[] correct;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 시험지의 개수
        K = sc.nextInt(); // 시험지를 나눌 그룹의 수
        
        int min = Integer.MAX_VALUE;
        int sum = 0;
        correct = new int[N];
        for (int i = 0; i < N; i++) {
            correct[i] = sc.nextInt();
            min = Integer.min(min, correct[i]); // 가장 작은 값 (맞은 문제 수)
            sum +=correct[i]; // 맞은 문제 수의 총합
        }

        int left = min; // 가장 작은 값 (맞은 문제 수)
        int right = sum; // 맞은 문제 수의 총합

        while (left <= right) {
            int mid = (left + right) / 2; // 최소합 (조건을 만족하는 가장 큰 최소합을 찾아야한다.)
            if (moreGroup(mid)) {
                // 그룹의 수가 K개보다 많거나 같을 때에는 더 큰 최소합을 찾아본다.
                left = mid+1;
            } else {
                // 그룹의 수가 K개보다 작을 때에는 더 작은 최소합을 찾아본다.
                right = mid-1;
            }
        }

        // left > right인 경우에 while문을 탈출하므로
        // right를 출력
        System.out.println(right);
        sc.close();
    }

    public static boolean moreGroup(int mid) {
        int sum = 0;
        int cnt = 0; // 나눠지는 그룹 수

        for (int i = 0; i < N; i++) {
            sum +=correct[i];
            if (sum >= mid) {
                // 최소합이 되는 수보다 커질 때를 찾아
                sum = 0; // 0으로 초기화
                cnt++; // 그룹 수 count
            }
        }

        // K개보다 많거나 같은 수의 그룹이 나오면 true를 반환
        return cnt >= K;
    }
}