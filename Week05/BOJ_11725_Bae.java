import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_11725_Bae {
    //어렵다 어려워
    static int N;
    //트리구실 할 ArrayList만들어주고
    static ArrayList<ArrayList<Integer>> tree= new ArrayList<>();
    static boolean[] visited;
    //부모노드 저장해서 출력할 배열하나 만들어주고
    static int[] pn;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();
        //트리에 저장될 개수만큼 ArrayList넣어주고
        for(int i=0;i<N+1;i++){
            tree.add(new ArrayList<>());
        }
        //노드 정보 주어지면 트리에 각각 저장
        for(int i=0;i<N-1;i++){
            int p=sc.nextInt();
            int c= sc.nextInt();
            tree.get(p).add(c);
            tree.get(c).add(p);
        }
        //방문배열 및 부모노드저장 배열 크기 설정해주고
        visited=new boolean[N+1];
        pn=new int[N+1];
        //트리 순회 시작,시작노드 1
        DFS(1);
        //부모 노드 출력
        for (int i=2;i<N+1;i++){
            System.out.println(pn[i]);
        }

    }
    public static void DFS(int start){
        //방문처리 해주고
        visited[start]=true;
        //해당 노드에 연결되어있는 노드들 하나씩 뽑아서
        for(int n : tree.get(start)){
            //방문하지 않았다면
            if (!visited[n]){
                //시작노드를 부모노드로 저장
                pn[n]=start;
                //다시 DFS
                DFS(n);
            }
        }
    }
}
