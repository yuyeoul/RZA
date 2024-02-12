import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2573_Bae {
    static int N,M;
    static int[][] arr;
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};
    static boolean[][] visited;
    static boolean flag=false;
    static int cnt;
    static class info{
        int r,c;

        public info(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();
        M=sc.nextInt();

        arr=new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                arr[i][j]=sc.nextInt();
            }
        }


        //BFS로 나뉘어진 구역 몇 개인지 세기+얼음 녹이는 작업
        //정답
        int ans=0;

        while (true){
            //빙산탐색부터 하는이유:
            //이미 두 덩이 이상일수도있고 이미 다 녹은 상태일 수도 있다.
            BFS();
            //두 덩이 이상이면 종료
            if(flag){
                break;
            }
            //다 녹아서 분리 될 빙산이 없으면 종료
            if(cnt==0){
                ans=0;
                break;
            }
            //빙산 녹이기
            melt();
            //1년 추가
            ans++;
        }
        System.out.println(ans);

    }

    public static void melt(){
        //얼음은 동시다발적으로 녹는다고 생각해야 하기 때문에 원본배열이 아닌 복제배열을 사용해서 녹는 처리를 해줘야한다
        int[][]dupliarr = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                dupliarr[i][j]=arr[i][j];
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                //빙산이 위치한지역이라면
                if(dupliarr[i][j]!=0){
                    int zero=0;
                    //델타돌려서 0몇개인지 세고
                    for (int d=0;d<4;d++){
                        int nr=i+dr[d];
                        int nc=j+dc[d];
                        if(nr>=0&&nr<N&&nc>=0&&nc<M){
                            if(dupliarr[nr][nc]==0){
                                zero++;
                            }

                        }
                    }
                    //0과 빙산-0중 큰값 원본배열에 채워넣기
                    arr[i][j]=Math.max(0,dupliarr[i][j]-zero);
                }
            }
        }
    }
    public static void BFS(){
        visited=new boolean[N][M];
        cnt=0;

        for (int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                //0이 아니고 방문하지 않은 지역이라면 덩어리 ++
                if(arr[i][j]!=0 &&!visited[i][j]){
                    cnt++;
                    //두 덩어리라면 flag바꿔주고 종료
                    if (cnt==2){
                        flag=true;
                        break;
                    }
                    //아니라면 방문처리 후 BFS돌려서 이어져있는 덩어리들 처리하기
                    visited[i][j]=true;
                    Queue<info> q =new LinkedList<>();
                    q.add(new info(i,j));
                    while (!q.isEmpty()){
                        info in =q.poll();
                        for (int d=0;d<4;d++){
                            int nr = in.r+dr[d];
                            int nc = in.c+dc[d];
                            if(nr>=0&&nr<N&&nc>=0&&nc<M){
                                if(arr[nr][nc]!=0 &&!visited[nr][nc]){
                                    visited[nr][nc]=true;
                                    q.add(new info(nr,nc));
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
