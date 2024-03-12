import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_11003_Bae {
    public static void main(String[] args) throws IOException {
//      Scanner sc=new Scanner(System.in);
        //아오 시간초과 때메 버퍼드+스트링빌더 써야해...
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        //입력받기
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine()," ");
        int[] arr =new int[N];
        Deque<Integer> dq =new ArrayDeque<>();
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<N;i++) {
            //숫자하나뽑아서
            int num=arr[i];
            //덱이 비어있지않고 맨뒤에 저장된 인덱스의 숫자가 현재 숫자보다 크다면 덱에서 제거해준다
            while(!dq.isEmpty()&&arr[dq.getLast()]>num) {
                dq.removeLast();
            }
            //맨뒤에 인덱스로 저장해준다
            dq.offerLast(i);
            //덱에 맨앞 인덱스가 범위를 벗어난다면 제거해준다
            if(dq.getFirst()<i-L+1) {
                dq.removeFirst();
            }
            //덱의 맨앞인덱스가 가르키는 숫자가 제일 작으므로 해당 수 출력
            sb.append(arr[dq.peekFirst()]+" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
