import java.util.Scanner;

public class BOJ_12015_Bae {
    static int N;
    static int[] arr, dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        //입력받을 배열
        arr = new int[N];
        //최대 수열 저장 배열
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        //초기 최대수열 값
        int ans = 1;
        //초기값은 배열의 맨 처음 값
        //flow-> 배열을 순회하면서 dp배열에 저장해놓은 값보다 큰 값은 증가하는 수열로 판단
        //ans를 인덱스로 두고 dp배열의 해당인덱스에 arr 배열값을 채워주고 ans 하나 증가시켜준다
        //만약 arr 배열값이 더 작다면  이분탐색을 사용해 해당값을 넣어줄 인덱스를 찾는다
        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            if (dp[ans - 1] < arr[i]) {
                dp[ans] = arr[i];
                ans++;
            } else {
                //작다면 증가하지 않지만 앞에 나왔던 숫자들보다는 크다면 그것도 증가하는 수열의 일부가 될 수 있다.
                //따라서 증가하는 시점이 끊긴 곳에서 앞의 값보다 큰지점에 다시 갱신시켜주고 수열을 채워나간다.
                int idx = binary(0, ans, arr[i]);
                dp[idx] = arr[i];
            }

        }
        System.out.println(ans);
    }

    public static int binary(int start, int end, int num) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (dp[mid] < num) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}
