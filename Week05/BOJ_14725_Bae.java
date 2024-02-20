import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class BOJ_14725_Bae {
    static int N;
    static class Node{
        HashMap<String,Node> nm =new HashMap<>();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        //새로운 노드 만들고~
        Node root = new Node();
        for(int i=0;i<N;i++){
            int len=Integer.parseInt(sc.next());
            //시작노드는 비워줘야 한다
            Node now = root;
            for(int j=0;j<len;j++){
                String str =sc.next();
                //if처리 안해주면 맨 처음값 안들어간다
                if(!now.nm.containsKey(str)){
                    now.nm.put(str,new Node());
                }
                //트리구조로 새로운 Node Class를 불러와 String을 저장해준다
                now=now.nm.get(str);
            }
        }
        print(root,"");

    }
    //해쉬맵 맨 아래있는 애까지 갔다와야해서 클래스로 만들고 재귀호출 해준다
    public static void print(Node root,String s){
        Object[] obj=root.nm.keySet().toArray();
        Arrays.sort(obj);
        for(Object o:obj){
            //o가 key=문자열들
            System.out.println(s+o);
            print(root.nm.get(o),s+"--");
        }
    }
}
