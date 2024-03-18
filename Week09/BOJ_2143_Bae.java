import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_2143_Bae {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //인풋 값 받고~
        int T = sc.nextInt();

        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = sc.nextInt();
        }
        //누적합 구해줄 리스트
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        //중간에 건너뛰거나 역순으로 더하는건 불가능~
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                listA.add(sum);
            }
        }
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                listB.add(sum);
            }
        }
        //투포인터 사용하기위해 정렬해주고
        Collections.sort(listA);
        Collections.sort(listB);
        //투포인터에 사용할 인덱스 잡아주고
        int idxA = 0;
        int idxB = listB.size() - 1;
        long ans = 0;
        //투포인터 돌리기
        while (idxA < listA.size() && idxB >= 0) {
            //합
            int sum = listA.get(idxA) + listB.get(idxB);
            //A부분배열 합과 B부분배열합의 합이 T와 같다면
            if (sum == T) {
                //해당 값 꺼내서
                int a = listA.get(idxA);
                int b = listB.get(idxB);
                long aCnt = 0;
                long bCnt = 0;
                //중복되는 값들이 몇개인지 체크해주기
                while (idxA < listA.size() && listA.get(idxA) == a) {
                    aCnt++;
                    idxA++;
                }
                while (idxB >= 0 && listB.get(idxB) == b) {
                    bCnt++;
                    idxB--;
                }

                //경우의 수
                ans += aCnt * bCnt;
                //합이 목표 값 보다 작다면 A의 인덱스를 하나 우측으로
            } else if (sum < T){
                idxA++;
                //합이 목표 값 보다 크다면 B의 인덱스를 하나 좌측으로
            } else if (sum > T){
                idxB--;
            }

        }

        System.out.println(ans);
    }
}
