import java.util.*;

public class BOJ_3190_Bae {
    static class info{
        int r;
        int c;

        public info(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, K, L;
    static int[][] arr;
    static List<info> list;
    static Queue<info> q;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) {
        //입력 받고~
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();
        K=sc.nextInt();

        arr=new int[N][N];
        list=new ArrayList<>();
        q=new LinkedList<>();
        //0행0열이 아니라 1행1열 기준이니까 하나씩 뺀 자리에 채워준다
        for(int i=0;i<K;i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            arr[x-1][y-1]=1;
        }
        L=sc.nextInt();
        for(int i=0;i<L;i++){
            int t=sc.nextInt();
            String d=sc.next();
            //"D"면 1로, "L"이면 -1로 방향저장해서 리스트에 넣어주기
            int dir=1;
            if(d.equals("L")){
                dir=-1;
            }
            list.add(new info(t,dir));
        }
        //뱀의 시작위치
        arr[0][0]=2;
        //정답
        int ans=0;
        //뱀이 방향 바꾸는 횟수
        int time=0;
        //방향
        int dir=0;
        //대가리 위치 큐에 넣어주고
        info head=new info(0,0);
        q.add(head);

        while(true){
            //시간 증가
            ans++;
            //뱀 대가리 새로운 위치
            int nr = head.r + dr[dir];
            int nc = head.c + dc[dir];
            //범위 벗어났거나 벽에 부딪혔다면 종료
            if(nr < 0 || nr >= N || nc < 0 || nc>=N  || arr[nr][nc] == 2){
                break;
            }
            //사과가 없는 자리라면 뱀이 수축하니까 꼬리자리 0으로
            if(arr[nr][nc] != 1){
                info tail = q.poll();
                arr[tail.r][tail.c] = 0;
            }
            //새로운대가리 넣어주고
            head = new info(nr, nc);
            q.add(head);
            arr[nr][nc] = 2;
            //더 바꿀 방향이 남아있고 해당방향으로의 진행시간이 다되었다면
            if(time < L && list.get(time).r == ans){
                //방향바꿔주기~
                dir = (dir + list.get(time).c) % 4;
                if(dir==-1){
                    dir=3;
                }

                time++;
            }
        }

        System.out.println(ans);


    }
}
