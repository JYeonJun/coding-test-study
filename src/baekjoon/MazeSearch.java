package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MazeSearch {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NAndM = br.readLine().split(" ");

        int N = Integer.parseInt(NAndM[0]);
        int M = Integer.parseInt(NAndM[1]);

        int[][] maze = new int[N][M];
        boolean[][] check = new boolean[N][M];

        // 미로 초기화
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - 48;
            }
        }

        Queue<List<Integer>> queue = new LinkedList<>();

        // 위쪽, 오른쪽, 아래쪽, 왼쪽 방향
        int[] x = {0, 1, 0, -1};
        int[] y = {1, 0, -1, 0};

        check[0][0] = true;
        queue.add(Arrays.asList(0, 0));

        while (queue.size() != 0) {

            List<Integer> list = queue.poll();
            assert list != null;
            Integer yPos = list.get(0);
            Integer xPos = list.get(1);

            if (yPos == N - 1 && xPos == M - 1) {
                break;
            }

            for (int k = 0; k < 4; k++) {
                int ny = yPos + y[k];
                int nx = xPos + x[k];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (!check[ny][nx] && maze[ny][nx] != 0) {
                        check[ny][nx] = true;
                        maze[ny][nx] = maze[yPos][xPos] + 1;
                        queue.add(Arrays.asList(ny, nx));
                    }
                }
            }
        }

        System.out.println(maze[N - 1][M - 1]);
    }
}