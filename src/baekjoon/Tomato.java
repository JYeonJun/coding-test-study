package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class tomato {
    int x;
    int y;
    int z;

    tomato(int z, int x, int y) {
        this.z = z;
        this.x = x;
        this.y = y;
    }
}

public class Tomato {

    static int M;
    static int N;
    static int H;

    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    static int[][][] board;

    static Queue<tomato> queue;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();

        board = new int[H][N][M];

        queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    board[i][j][k] = sc.nextInt();
                    if (board[i][j][k] == 1) {
                        queue.add(new tomato(i, j, k));
                    }
                }
            }
        }

        System.out.println(BFS());
    }

    public static int BFS() {

        while (!queue.isEmpty()) {

            tomato t = queue.remove();
            int z = t.z;
            int x = t.x;
            int y = t.y;

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if (nx >= 0 && ny >= 0 && nz >= 0 && nx < N && ny < M && nz < H) {
                    if (board[nz][nx][ny] == 0) {
                        queue.add(new tomato(nz, nx, ny));
                        board[nz][nx][ny] = board[z][x][y] + 1;
                    }
                }
            }
        }

        int result = Integer.MIN_VALUE;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (board[i][j][k] == 0) {
                        return -1;
                    }
                    result = Math.max(result, board[i][j][k]);
                }
            }
        }

        if (result == 1) {
            return 0;
        } else {
            return result - 1;
        }
    }
}
