import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_4485_Bae {
    //젤다는 공주님이다

    static int N;
    static int[][] arr,cost;
    //우선 순위 큐 위해서 Comparable
    static class Info implements Comparable<Info>{
        //좌표,뺏기는 루피
        int r,c,rupee;

        public Info(int r, int c , int rupee) {
            this.r = r;
            this.c = c;
            this.rupee=rupee;
        }

        @Override
        public int compareTo(Info o) {
            return rupee-o.rupee;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int INF=Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc=1;
        while (true){
            N=sc.nextInt();
            if(N==0){
                break;
            }
            //배열 채워주고
            arr=new int[N][N];
            cost=new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    arr[i][j]=sc.nextInt();
                    cost[i][j]=INF;
                }
            }
            //다익스트라~
            dijkstra();
            System.out.println("Problem "+tc+": "+cost[N-1][N-1]);
            tc++;
        }
    }
    public static void dijkstra(){
        //우선순위큐 써주고
        PriorityQueue<Info> pq=new PriorityQueue<>();
        //초기좌표 0,0
        pq.add(new Info(0,0,arr[0][0]));
        //비용배열 바꿔주고
        cost[0][0]=arr[0][0];
        //BFS돌려~
        while (!pq.isEmpty()){
            //하나 꺼내서
            Info in =pq.poll();
            //델타탐색돌리고
            for(int d=0;d<4;d++){
                int nr=in.r+dr[d];
                int nc=in.c+dc[d];
                //범위 안쪽이고 현재까지 뺏긴 루피가 비용배열 값보다 작을 경우
                if(nr>=0&&nr<N&&nc>=0&&nc<N&&cost[nr][nc]>in.rupee+arr[nr][nc]){
                    //비용 배열을 현재까지 뺏긴 루피로 바꿔준다
                    cost[nr][nc]=in.rupee+arr[nr][nc];
                    //우선순위큐에 넣고 다시 뺑뺑이
                    pq.add(new Info(nr,nc,in.rupee+arr[nr][nc]));
                }
            }
        }
    }
}
