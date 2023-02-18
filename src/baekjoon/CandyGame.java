package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 3085 사탕 게임
 */
public class CandyGame {

    static int check(char[][] board) {

        int N = board.length;

        int ans = 1;

        for (int i = 0; i < N; i++) {

            int cnt = 1;
            for (int j = 1; j < N; j++) {

                if (board[i][j] == board[i][j - 1]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                if(ans < cnt) ans = cnt;
            }

            cnt = 1;
            for (int j = 1; j < N; j++) {

                if (board[j][i] == board[j - 1][i]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                if(ans < cnt) ans = cnt;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드의 크기 N * N
        int N = Integer.parseInt(br.readLine());

        char[][] board = new char[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                // N * N 테이블에서 임의로 한 곳을 선택해 오른쪽, 아래 방향 각각 변경
                if (j + 1 < N) {
                    char tmp = board[i][j];
                    board[i][j] = board[i][j + 1];
                    board[i][j + 1] = tmp;

                    int checkNum = check(board);

                    if(ans < checkNum) ans = checkNum;

                    // 원상복구 필요
                    tmp = board[i][j];
                    board[i][j] = board[i][j+1];
                    board[i][j+1] = tmp;
                }

                if (i + 1 < N) {
                    char tmp = board[i][j];
                    board[i][j] = board[i+1][j];
                    board[i+1][j] = tmp;

                    int checkNum = check(board);

                    if(ans < checkNum) ans = checkNum;

                    tmp = board[i][j];
                    board[i][j] = board[i+1][j];
                    board[i+1][j] = tmp;
                }
            }
        }

        System.out.println(ans);
    }
}