package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1_2468 {

    private static int n;

    private static int[][] map;

    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Set<Integer> heightSet = new HashSet<>();

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int height = row[j].charAt(0) - 48;
                map[i][j] = height;
                heightSet.add(height);
            }
        }

        int max = 0;
        for (Integer height : heightSet) {

            int cnt = 0;
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > height && !visited[i][j]) {
                        cnt += bfs(j, i, height);
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        System.out.println(max);
    }

    private static int bfs(int x, int y, int height) {

        Queue<List<Integer>> que = new LinkedList<>();

        visited[y][x] = true;
        que.add(Arrays.asList(x, y));

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while (que.size() != 0) {

            List<Integer> posXY = que.poll();
            Integer nx = posXY.get(0);
            Integer ny = posXY.get(1);

            for (int i = 0; i < 4; i++) {
                int tmpX = nx + dx[i];
                int tmpY = ny + dy[i];

                if (tmpX >= 0 && tmpX < n && tmpY >= 0 && tmpY < n) {
                    if (!visited[tmpY][tmpX] && map[tmpY][tmpX] > height) {
                        visited[tmpY][tmpX] = true;
                        que.add(Arrays.asList(tmpX, tmpY));
                    }
                }
            }
        }

        return 1;
    }
}
