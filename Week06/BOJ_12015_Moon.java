import java.util.Scanner;

public class Main {

    static int[] seq, LIS;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // input
        int N = sc.nextInt();
        seq = new int[N]; // 원본 수열
        for (int i = 0; i < N; i++) {
            seq[i] = sc.nextInt();
        }

        // 가장 긴 증가하는 부분 수열
        // Longest Increasing Subsequences

        // solve
        LIS = new int[N+1];
        int length = 0;
        LIS[++length] = seq[0];
        for (int i = 1; i < N; i++) {
            if (LIS[length] < seq[i]) {
                // LIS의 마지막 원소보다 큰 수
                LIS[++length] = seq[i];
            } else {
                // LIS의 마지막 원소보다 작은 수
                int idx = binarySearch(1, length+1, seq[i]);
                LIS[idx] = seq[i];
            }
        }

        // print
        System.out.println(length);
        sc.close();
    }

    public static int binarySearch(int start, int end, int target) {
        // target보다 작은 수 중에서 가장 큰 값의 index를 반환해야 한다.

        while (start < end) {
            int mid = (start+end)/2;
            if (target > LIS[mid]) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        // return
        return start;
    }
}