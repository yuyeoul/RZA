import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2239_Moon {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] sudoku;
    static ArrayList<Point> blankList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String[] temp = sc.next().split("");
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(temp[j]);
            }
        }

        // 비어있는 칸의 좌표 정보를 blankList에 추가
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    blankList.add(new Point(i, j));
                }
            }
        }

        dfs(0);

        sc.close();
    }

    public static void dfs(int depth) {

        // 모든 빈칸이 채워졌다면 출력하고 끝내기
        if (depth == blankList.size()) {

            // 출력
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(sudoku[i][j]);
                }
                System.out.println();
            }

            // exit
            System.exit(0);
        }

        // 빈칸 채우기
        Point point = blankList.get(depth);

        for (int value = 1; value <= 9; value++) {

            if (isRepeated(point.x, point.y, value)) {
                sudoku[point.x][point.y] = value;
                dfs(depth + 1);
                sudoku[point.x][point.y] = 0;
            }

        }
    }

    public static boolean isRepeated(int x, int y, int value) {

        /*
         * x행 y열에 n을 넣을건데
         * 같은 행, 열, 큐브 안에 n이 있는지 없는지 검사하는 method
         * 겹치지 않아 이 숫자를 채택할 수 있다면 return true;
         * 중복인 숫자라면 return false;
         */

        // 테스트
        for (int i = 0; i < 9; i++) {

            // row
            if (sudoku[x][i] == value && i != y)
                return false;

            // column
            if (sudoku[i][y] == value && i != x)
                return false;

            // cube
            int nx = x/3 *3 +i/3;
            int ny = y/3 *3 +i%3;
            if (sudoku[nx][ny] == value && !( nx == x && ny == y ))
                return false;

        }

        // 모든 테스트를 통과해 여기까지 도달하면 해당 숫자 채택
        return true;
    }
}