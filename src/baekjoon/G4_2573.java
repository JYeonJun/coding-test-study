package baekjoon;

// 2차원 배열에 빙산의 높이 정보가 양의 정수로 저장된다.
// 빙산 이외의 바다에서 해당되는 칸에는 0이 저장된다.
// 빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어든다.
// 빙산의 높이는 일년마다 그 칸에 동서남북 네 방향을 ㅗ붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
// 각 칸에 저장된 높이는 0보다 더 줄지 않는다.
// 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년) 구하기!! -> 동서남북에 모두 0인게 2개인 경우 종료
// 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.  -> 모두 0이 되었는데도 2가 안 되면 0 출력

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 해결못함
public class G4_2573 {

    private static int n;
    private static int m;

    private static int[][] map;
    private static int[][] copyMap;
    private static boolean[][] visited;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputData = br.readLine().split(" ");

        n = inputData[0].charAt(0) - 48;
        m = inputData[1].charAt(0) - 48;

        map = new int[n][m];
        copyMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");

            for (int j = 0; j < m; j++) {

                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        int ans = 0;
        boolean result = false;

        while (true) {

            int cnt;
            // 높이가 0보다 큰 지역의 동서남북이 모두 0인지 확인 - 모두 0이면 cnt++ - 아님!!
            // 영역을 나누는 방법이 필요함
            cnt = seperateNum();

            if (cnt == 0) {
                System.out.println(0);
                return;
            }

            if (cnt >= 2) {
                System.out.println(ans);
                return;
            }

            visited = new boolean[n][m];

            copyMap = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    copyMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    if (!visited[i][j] && map[i][j] > 0) {
                        ans++;
                        // bfs 호출해서 높이 줄이기
                        bfs(j, i);
                    }
                }
            }

            map = copyMap;
        }
    }

    private static int seperateNum() {

        boolean[][] check = new boolean[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && !check[i][j]) {
                    dfs(j, i, check);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void dfs(int x, int y, boolean[][] check) {
        check[y][x] = true;

        int tmpX, tmpY;
        for (int i = 0; i < 4; i++) {
            tmpX = x + dx[i];
            tmpY = y + dy[i];

            if (tmpX < 0 || tmpY < 0 || tmpX >= m || tmpY >= n) {
                continue;
            }

            if (map[tmpY][tmpX] != 0 && !check[tmpY][tmpX]) {
                dfs(tmpX, tmpY, check);
            }
        }
    }

    private static void bfs(int x, int y) {

        Queue<List<Integer>> que = new LinkedList<>();
        visited[y][x] = true;
        que.add(List.of(x, y));

        while (que.size() != 0) {

            List<Integer> posXY = que.poll();
            Integer nx = posXY.get(0);
            Integer ny = posXY.get(1);

            if (ny == 1 && nx == 2) {
                System.out.print("");
            }

            for (int i = 0; i < 4; i++) {
                int tmpX = nx + dx[i];
                int tmpY = ny + dy[i];

                if (tmpX >= 0 && tmpX < m && tmpY >= 0 && tmpY < n) {
                    if (map[tmpY][tmpX] == 0 && copyMap[ny][nx] > 0) {
                        copyMap[ny][nx] -= 1;
                    }
                    if (map[tmpY][tmpX] > 0 && !visited[tmpY][tmpX]) {
                        visited[tmpY][tmpX] = true;
                        que.add(List.of(tmpX, tmpY));
                    }
                }
            }

        }

    }
}
