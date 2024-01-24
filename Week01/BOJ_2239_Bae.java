import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2239_Bae {
    static int[][] sdoku;
    static ArrayList<int[]> zeroList;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] s=new String[9][9];
        for (int i=0;i<9;i++){
            s[i]=sc.next().split("");
        }
        sdoku=new int[9][9];
        //채워야 하는 좌표 저장해놓기
        //가장 작은 수를 출력하라했으므로 앞에서부터 숫자 늘려나가며 비교
        //따라서 0인좌표를 앞에서 부터 저장시켜놓기
        zeroList=new ArrayList<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                sdoku[i][j]=Integer.parseInt(s[i][j]);
                if(sdoku[i][j]==0){
                    zeroList.add(new int[]{i, j});
                }
            }
        }
        dfs(0);
    }

    static void dfs(int d) {
        if(zeroList.size()==d){
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    System.out.print(sdoku[i][j]);
                }
                System.out.println();
            }
//          return;
            //스도쿠판이 여러가지 만들어 질 수 있기 때문에 return말고 종료해줘야 한다.
            //출력초과 이슈....
            System.exit(0);
        }
        int r=zeroList.get(d)[0];
        int c=zeroList.get(d)[1];
        boolean[] exist = new boolean[10];
        //가로줄 비교, 세로줄 비교, 네모칸 비교->걸리면 박살
        //가로줄에 존재하는 수라면 체크
        for(int i=0;i<9;i++){
            if(sdoku[r][i]!=0){
                exist[sdoku[r][i]]=true;
            }
        }
        //세로줄에 존재하는 수라면 체크
        for(int i=0;i<9;i++){
            if(sdoku[i][c]!=0){
                exist[sdoku[i][c]]=true;
            }
        }
        //3x3칸에 존재하는 수라면 체크
        int x=r/3*3;
        int y=c/3*3;
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                if(sdoku[i][j]!=0){
                    exist[sdoku[i][j]]=true;
                }
            }
        }
        //존재하지 않는수 채우고 다음 0인칸 재귀호출
        //넣을 수가 없다면 해당 칸 0으로 다시 만들고 전단계로 회귀
        for(int i=1;i<=9;i++){
            if(!exist[i]){
                sdoku[r][c]=i;
                dfs(d+1);
                sdoku[r][c]=0;
            }

        }

    }
}
