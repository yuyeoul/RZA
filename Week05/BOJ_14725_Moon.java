import java.util.*;

public class BOJ_14725_Moon {

    static class TrieNode {
        Map<String, TrieNode> childNode = new HashMap<>();
        TrieNode() {
            // empty
        }
        public void insert(String input) {
            TrieNode trieNode = this;
            String[] str = input.split(",");
            for (String s : str) {
                // 현재 trieNode의 자식 노드로 s가 존재하지 않으면 생성
                trieNode.childNode.putIfAbsent(s, new TrieNode());
                // 다음 문자를 처리하기 위해 새로 생성한 자식 노드로 이동
                trieNode = trieNode.childNode.get(s);
            }
        }

        public static void print(TrieNode trieNode, int depth) {
            // 자식 노드가 있을 때
            if (trieNode.childNode != null) {
                List<String> list = new ArrayList<>(trieNode.childNode.keySet());
                Collections.sort(list);
                for (String str : list) {
                    // 깊이만큼 "--"를 반복 출력해준 후
                    for (int i = 0; i < depth; i++) {
                        System.out.print("--");
                    }
                    // 문자를 출력하고
                    System.out.println(str);
                    // 다음 문자를 출력하기 위해 method를 다시 호출
                    print(trieNode.childNode.get(str), depth+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrieNode trie = new TrieNode();

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {

            // 입력 받아서 한 줄 씩 StringBuilder로 합침
            int K = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < K; j++) {
                sb.append(sc.next()).append(",");
            }

            // TrieNode에 insert
            trie.insert(sb.toString());
        }

        // 출력
        trie.print(trie, 0);
        sc.close();
    }
}