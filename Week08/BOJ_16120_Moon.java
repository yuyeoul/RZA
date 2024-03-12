import java.util.Scanner;
import java.util.Stack;

public class BOJ_16120_Moon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        String str = sc.next();

        // 풀이
        // 한 글자씩 stack에 push한다.
        // stack에 push 될 글자가 A라면 P 두개를 pop한다.
        // 꼭 stack이어야 할 필요는 없을 것 같지만 stack으로 풀어보자.
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length()-1; i++) {
            char now = str.charAt(i);
            char next = str.charAt(i+1);

            if (now == 'A' && next == 'A') {
                // 연속된 A가 나오면 탈락
                System.out.println("NP");
                System.exit(0);
            } else if (now == 'A' && stack.size() < 2) {
                // stack에 P가 두 개 이상 없으면
                System.out.println("NP");
                System.exit(0);
            } else if (now == 'A') {
                stack.pop();
                stack.pop();
            } else {
                stack.push(now);
            }
        }

        if (str.charAt(str.length()-1) == 'A') {
            System.out.println("NP");
            System.exit(0);
        } else {
            stack.push(str.charAt(str.length()-1));
        }

        // 출력
        if (stack.size() == 1) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }

        sc.close();
    }
}