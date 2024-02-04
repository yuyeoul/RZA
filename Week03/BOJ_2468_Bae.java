import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_2468_Bae {
    static int N;
    static int[][]arr;
    static boolean visited[][];
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        N=sc.nextInt();

        arr=new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int num=sc.nextInt();
                arr[i][j]=num;
            }
        }
        //구분되는 영역 최대값
        int max=0;
        int rain=100;
        for(int i=1;i<=rain;i++){
            //리스트에서 물에 잠길 높이 하나씩 꺼내기
            int h = i;
            //방문배열
            visited=new boolean[N][N];
            //안전영역 수
            int safe=0;
            //배열 돌면서
            for (int a=0;a<N;a++){
                for(int b=0;b<N;b++){
                    //방문하지 않았고 물에 잠긴 높이보다 높거나 같으면
                    //비가 오지 않는 경우도 고려해야한다. 으악으악으악
                    //높은 경우만 고려하면 틀려....
                    if(!visited[a][b]&&arr[a][b]>=h){
                        //해당 칸에서 DFS호출
                        DFS(a,b,h);
                        //여까지 오면 하나의 안전영역 생긴거
                        safe++;
                    }
                }
            }
            max=Math.max(max,safe);
        }
        System.out.println(max);
    }
    static void DFS(int a,int b,int h){
        //해당칸 방문처리 해주고
        visited[a][b]=true;
        //델타탐색 돌아서 물에 안잠긴 영역 돌기
        for(int i=0;i<4;i++){
            int nr=a+dr[i];
            int nc=b+dc[i];
            //범위 안쪽이고 방문 안했고 물에잠긴높이보다 높거나 같으면
            if(nr>=0&&nc>=0&&nr<N&&nc<N &&!visited[nr][nc]&&arr[nr][nc]>=h){
                //해당 위치에서 다시 DFS
                DFS(nr,nc,h);
            }
        }
    }
}
