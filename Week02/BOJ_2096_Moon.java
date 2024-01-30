import java.util.Scanner;

public class BOJ_2096_Moon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 줄의 수
        int[][] map = new int[3][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                map[j][i] = sc.nextInt();
            }
        }

        // DP 값을 저장해둘 배열
        int[] max = new int[3];
        int[] min = new int[3];

        // 시작값 저장
        for (int i = 0; i < 3; i++) {
            max[i] = map[i][0];
            min[i] = map[i][0];
        }

        // 임시로 DP 값을 저장해둘 배열
        // 한 행에 대해 계산하는 과정중에 값이 바뀌면 안되기 때문에 배열을 더 만들어준다.
        int[] nextMax = new int[3];
        int[] nextMin = new int[3];

        for (int i = 1; i < N; i++) {
            // 최대값 저장
            nextMax[0] = Math.max(max[0], max[1]) + map[0][i];
            nextMax[1] = Math.max(max[0], Math.max(max[1], max[2])) + map[1][i];
            nextMax[2] = Math.max(max[1], max[2]) + map[2][i];
            
            // 최소값 저장
            nextMin[0] = Math.min(min[0], min[1]) + map[0][i];
            nextMin[1] = Math.min(min[0], Math.min(min[1], min[2])) + map[1][i];
            nextMin[2] = Math.min(min[1], min[2]) + map[2][i];

            // 배열 복사
            max = nextMax.clone();
            min = nextMin.clone();
        }

        // 최종 정답 선택
        int finalMax = Math.max(max[0], Math.max(max[1], max[2]));
        int finalMin = Math.min(min[0], Math.min(min[1], min[2]));

        // 출력
        System.out.println(finalMax+" "+finalMin);

        sc.close();
    }
}