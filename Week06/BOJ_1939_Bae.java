import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1939_Bae {
    //N:섬의 개수,M:다리 정보 개수
    static int N, M;

    //연결되어있는 섬과 다리의 중량제한 값 저장할 클래스
    static class info {
        int dest, weight;

        public info(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static boolean[] visited;
    //트리구조로 만들거야~
    static ArrayList<ArrayList<info>> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        //트리 만들어주고
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        //이분탐색 범위 잡아줄 변수
        int first = 0;
        int last = 0;

        for (int i = 0; i < M; i++) {
            int f = sc.nextInt();
            int l = sc.nextInt();
            int w = sc.nextInt();
            //트리에 값 추가해 주고
            list.get(f).add(new info(l, w));
            list.get(l).add(new info(f, w));
            //최대 중량을 갱신 해서 이분탐색 끝값으로 써준다
            last = Math.max(last, w);
        }
        //시작 섬, 도착 섬 변수 받고
        int start = sc.nextInt();
        int end = sc.nextInt();

        //이분탐색 시작
        while (first <= last) {
            int mid = (first + last) / 2;
            visited = new boolean[N + 1];
            //BFS돌려서 이분탐색 시작점, 끝점 갱신해주기
            if (BFS(start, end, mid)) {
                //마지막 섬에 도달했다면 중량제한에 안걸렸으니까 최대 중량 늘려주기
                first = mid + 1;
            } else {
                //도달 못했으면 중량제한 걸린거니까 줄여주기
                last = mid - 1;
            }
        }
        System.out.println(last);

    }

    public static boolean BFS(int s, int e, int m) {
        //m은 최대 중량값
        Queue<Integer> q = new LinkedList<>();
        //섬 넣어주고 방문처리
        q.add(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            int n = q.poll();
            //섬 끝에 도달 했다면 true 반환
            if (n == e) {
                return true;
            }
            //섬하나 꺼내서 방문 안했고 해당 섬과 연결된 다리의 중량이 m보다 크다면 지나갈 수 있으니까 방문처리 후 q에 넣기
            for (info i : list.get(n)) {
                if (!visited[i.dest] && m <= i.weight) {
                    visited[i.dest] = true;
                    q.add(i.dest);
                }
            }
        }
        //도달 못했으면 false
        return false;
    }
}
