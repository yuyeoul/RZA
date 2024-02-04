
import java.util.Scanner;

public class BOJ_1405_Bae {
    static int N;
    static double[] probability;
    static boolean[][] vistied;
    //동서남북
    static int[] dr={0,0,1,-1};
    static int[] dc={1,-1,0,0};
    static double ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();
        probability= new double[4];
        for(int i=0;i<4;i++){
            probability[i]= Double.parseDouble(sc.next());
        }
        //로봇 현재 위치
        int r=14;
        int c=14;
        //N<=14, 로봇 중앙위치 시키고 움직인다고 가정하면 중앙기준으로 -14 +14범위 움직이니까 30칸으로 설정
        vistied=new boolean[30][30];
        //시작점 방문처리
        vistied[r][c]=true;
        //DFS시작
        DFS(r,c,0,1);
        System.out.println(ans);


    }
    static void DFS(int r,int c,int cnt, double pro){
        //N만큼 돌렸으면 단순하니까 확률 더해주기
        if(cnt==N){
            ans+=pro;
            return;
        }
        //델타탐색진행하면서
        for(int i=0;i<4;i++){
            int nr= r+dr[i];
            int nc= c+dc[i];
            //범위안쪽이고 방문하지 않았다면
            if(nr>=0 && nc>=0 && nr<30 && nc<30 &&!vistied[nr][nc]){
                //방문처리해주고 해당 방향으로 움직일 확률 넣어서 DFS다시호출
                vistied[nr][nc]=true;
                DFS(nr,nc,cnt+1,pro*probability[i]/100);
                //빠꾸먹었으면 미방문처리해주면서 다시 올라오기
                vistied[nr][nc]=false;
            }
        }
    }
}
