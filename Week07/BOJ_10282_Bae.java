import java.util.*;

public class BOJ_10282_Bae {
    static int n,d,c,cnt;
    static int[]cost;
    static List<List<info>> list;
    static int INF=Integer.MAX_VALUE;
    static class  info implements Comparable<info>{
        int a,s;

        public info(int a, int s) {
            this.a = a;
            this.s = s;
        }

        @Override
        public int compareTo(info o) {
            return s-o.s;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();

        for(int tc=1;tc<=T;tc++){
            n=sc.nextInt();
            d=sc.nextInt();
            c=sc.nextInt();
            cnt=1;
            //직빵 감염보다 돌아서 감염되는게 빠를수도 있다.
            list= new ArrayList<>();
            for(int i=0;i<=n;i++){
                list.add(new ArrayList<>());
            }
            //걸리는 시간
            cost=new int[n+1];

            Arrays.fill(cost,INF);
            //의존관계에 따라 트리에 저장
            for(int i=0;i<d;i++){
                int a=sc.nextInt();
                int b=sc.nextInt();
                int s=sc.nextInt();
                list.get(b).add(new info(a,s));
            }
            //초기 감염 컴퓨터 0으로 세팅해주고
            cost[c]=0;
            //다익스트라
            dijkstra();
            int max=0;
            //감염된 애들만 가지고 시간 구하기
            for(int i=1;i<=n;i++){
                if(cost[i]!=INF){
                    max=Math.max(max,cost[i]);
                }
            }
            System.out.println(cnt+" "+max);
        }
    }
    public static void dijkstra(){
        PriorityQueue<info> pq=new PriorityQueue<>();

        //초기 감염체 저장해주고
        pq.add(new info(c,0));
        //돌려~
        while (!pq.isEmpty()){
            info in = pq.poll();
            //시간이 더오래걸리면 위에 써놓은거처럼 돌아서 감염되는게 더 빠른경우일수도 있으니 패스
            if(in.s>cost[in.a]){
                continue;
            }
            //의존관계에 있는놈들 하나씩 뺴와서
            for(info i:list.get(in.a)){
                if(cost[i.a]>cost[in.a]+i.s){
                    //감염되지 않았다면 감염 증가시켜주고
                    if(cost[i.a]==INF){
                        cnt++;
                    }
                    //시간 갱신
                    cost[i.a]=cost[in.a]+i.s;
                    pq.add(new info(i.a,cost[i.a]));
                }
            }


        }
    }

}
