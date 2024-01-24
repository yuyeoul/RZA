import java.util.Scanner;

public class BOJ_14499 {
    static int N,M,x,y,K;
    static int[][] map;
    static int[] dr={0,0,-1,1};
    static int[] dc={1,-1,0,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();
        M=sc.nextInt();
        x=sc.nextInt();
        y=sc.nextInt();
        K=sc.nextInt();
        map = new int[N][M];
        int[] dice= {0,0,0,0,0,0,0};

        int r=x;
        int c=y;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j]=sc.nextInt();
            }
        }
        //동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
//          2
//        4 1 3
//          5
//          6

        for(int i=0;i<K;i++){
            int move=sc.nextInt();
            r+=dr[move-1];
            c+=dc[move-1];
            if(r<0||r>=N||c<0||c>=M){
                r-=dr[move-1];
                c-=dc[move-1];
                continue;
            }

            int bot=dice[6];
            //동쪽
            if(move==1){
                dice[6]=dice[3];
                dice[3]=dice[1];
                dice[1]=dice[4];
                dice[4]=bot;
                //서쪽
            } else if (move==2){
                dice[6]=dice[4];
                dice[4]=dice[1];
                dice[1]=dice[3];
                dice[3]=bot;
                //북쪽
            } else if (move==3) {
                dice[6]=dice[2];
                dice[2]=dice[1];
                dice[1]=dice[5];
                dice[5]=bot;
                //남쪽
            }else{
                dice[6]=dice[5];
                dice[5]=dice[1];
                dice[1]=dice[2];
                dice[2]=bot;
            }

            //바닥 0이면 주사위 숫자 복사
            if(map[r][c]==0){
                map[r][c]=dice[6];
                //아니라면 주사위에 바닥 숫자 복사 후 0으로 초기화
            }else{
                dice[6]=map[r][c];
                map[r][c]=0;
            }
            System.out.println(dice[1]);

        }



    }
}
