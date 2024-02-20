import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_11437_Moon {

    static int N;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int[] parents;
    static int[] depth;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 노드의 개수
        parents = new int[N+1]; // 각 node의 부모 정보를 저장할 배열 (root는 0)
        depth = new int[N+1]; // 각 node의 깊이 정보를 저장할 배열 (root는 0)

        // 인접리스트 만들기
        list = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list[a].add(b);
            list[b].add(a);
        }

        // parents, depth 행렬 채우기
        findChild(1);

        int M = sc.nextInt(); // 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            // 두 node의 깊이가 같지 않으면 같을 때까지 위로 이동한다.
            while (depth[a] != depth[b]) {
                if (depth[a] > depth[b]) {
                    a = parents[a];
                } else {
                    b = parents[b];
                }
            }

            // 두 node의 깊이가 같아졌으니 같은 node가 될 때까지 위로 이동한다.
            while (a != b) {
                a = parents[a];
                b = parents[b];
            }

            // a와 b가 같은 node에 도달했으니 둘 중 아무거나 출력한다.
            System.out.println(a);
        }

        sc.close();
    }

    public static void findChild(int p) {

        // p = 부모 노드의 번호
        for (int i = 0; i < list[p].size(); i++) {
            int c = list[p].get(i);
            if (parents[c] == 0 && c != 1) {
                parents[c] = p;
                depth[c] = depth[p]+1;
                findChild(c);
            }
        }
    }
}