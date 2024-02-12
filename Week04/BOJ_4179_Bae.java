import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_4179_Bae {
    static int R,C;
    static String[][]arr;
    static class info{
        int r,c;

        public info(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static Queue<info> jq=new LinkedList<>();
    static Queue<info> fq=new LinkedList<>();
    static boolean [][] jvisited;
    static boolean [][] fvisited;
    static int dr[]={-1,1,0,0};
    static int dc[]={0,0,-1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R=sc.nextInt();
        C=sc.nextInt();
        arr=new String[R][C];
        jvisited=new boolean[R][C];
        fvisited=new boolean[R][C];

        for(int i=0;i<R;i++){
            arr[i]=sc.next().split("");
        }
        for (int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(arr[i][j].equals("J")){
                    jq.add(new info(i,j));
                    jvisited[i][j]=true;
                }
                if(arr[i][j].equals("F")){
                    fq.add(new info(i,j));
                    fvisited[i][j]=true;
                }
            }
        }
        //J=지훈이 위치, F=불난 곳
        //불이 먼저? 지훈이가 도망가는게 먼저?
        BFS();
        System.out.println("IMPOSSIBLE");
    }

    public static void BFS(){
        int ans=0;
        //불이먼저!
        while (!jq.isEmpty()){
            //큐의 사이즈를 미리 저장해주고 한번 돌때 얼마나 돌건지 해놔야한다
            //안그러면 큐의 사이즈는 계속 변동되기 때문에 답이없다
            //이런....
            int fsize=fq.size();
            int jsize=jq.size();
            for(int i=0;i<fsize;i++){
                info in =fq.poll();
                for(int d=0;d<4;d++){
                    int nr=in.r+dr[d];
                    int nc=in.c+dc[d];
                    //범위 안쪽이고 벽아니고 방문안했으면
                    if(nr>=0&&nr<R&&nc>=0&&nc<C && !arr[nr][nc].equals("#")&&!fvisited[nr][nc]){
                        //해당위치 큐에 넣고 방문처리하고 불장판 생성
                        fq.add(new info(nr,nc));
                        fvisited[nr][nc]=true;
                        arr[nr][nc]="F";
                    }
                }
            }

            for(int i=0;i<jsize;i++){
                info inj =jq.poll();
                for(int d=0;d<4;d++){
                    int nr=inj.r+dr[d];
                    int nc=inj.c+dc[d];
                    //범위밖으로 벗어났다면 탈출한거기 때문에 종료해준다
                    if(nr<0||nr>=R||nc<0||nc>=C){
                        ans++;
                        System.out.println(ans);
                        System.exit(0);
                    }
                    //불아니고 벽아니고 방문안했으면 큐에 넣고 방문처리
                    if(arr[nr][nc].equals(".")&&!jvisited[nr][nc]){
                        jq.add(new info(nr,nc));
                        jvisited[nr][nc]=true;
                    }
                }
            }
            ans++;
        }
    }
}
