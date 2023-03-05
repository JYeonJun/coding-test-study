package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_14503 {
    private static int N, M, r, c, d, result;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer roomSize = new StringTokenizer(br.readLine());
        N = Integer.parseInt(roomSize.nextToken());
        M = Integer.parseInt(roomSize.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        StringTokenizer posAndDirection = new StringTokenizer(br.readLine());
        r = Integer.parseInt(posAndDirection.nextToken());
        c = Integer.parseInt(posAndDirection.nextToken());
        // d가 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽
        // 0 -> 3 -> 2 -> 1 -> 0 ...
        d = Integer.parseInt(posAndDirection.nextToken());

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                // 0인 경우 청소되지 않은 빈 칸, 1인 경우 벽
                map[i][j] = Integer.parseInt(row[j]);
            }
        }

        result = 0;
        bfs(c, r);

        // 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.
        // 로봇 청소기가 있는 칸은 항상 빈 칸이다.
        System.out.println(result);
    }

    private static void bfs(int x, int y) {

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x, y});

        while (que.size() != 0) {

            int[] posXY = que.poll();
            int nx = posXY[0];
            int ny = posXY[1];

            if (map[ny][nx] == 0 && !visited[ny][nx]) { // 현재 칸이 아직 청소되지 않은 경우 청소한다.
                result++;
                visited[ny][nx] = true;
            }

            if (isAroundClean(nx, ny)) { // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                if (!isMoveBack(nx, ny)) { // 2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                    return;
                } else { // 바라보는 방향을 기준으로 한 칸 후진할 수 있다면 한 칸 후진
                    int[] backPos = calBackPos(nx, ny);
                    que.add(backPos);
                }
            } else { // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                d = calDirection(); // 3-1 반시계 방향으로 90도 회전
                // 3-2 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
                // 앞쪽 칸으로 이동 가능한지 검사
                // 앞쪽 칸이 청소되지 않은 빈 칸인지 검사
                if (isCanMoveForward(nx, ny)) {
                    int[] forwardPos = calForwardPos(nx, ny);
                    assert forwardPos != null;
                    int forwardX = forwardPos[0];
                    int forwardY = forwardPos[1];
                    if (!visited[forwardY][forwardX]) {
                        que.add(new int[]{forwardX, forwardY});
                    } else {
                        que.add(new int[]{nx, ny});
                    }
                } else {
                    que.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isAroundClean(int nx, int ny) {

        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};

        // 현재 칸으로부터 주변 4칸 중 청소되지 않은 빈 칸이 있는지 검사

        for (int i = 0; i < 4; i++) {
            int nextX = nx + dx[i];
            int nextY = ny + dy[i];

            if (!isPosValid(nextX, nextY)) {
                continue;
            }

            // 빈 칸인데 방문하지 않은 경우 false 반환
            if (map[nextY][nextX] == 0 && !visited[nextY][nextX]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isMoveBack(int nx, int ny) {

        // 바라보는 방향의 뒤쪽으로 이동 가능한지 검사
        // 이동할 수 없으면 false 반환

        int tmpX = nx;
        int tmpY = ny;

        // d가 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽
        if (d == 0) {
            tmpY = ny + 1;
        } else if (d == 1) {
            tmpX = nx - 1;
        } else if (d == 2) {
            tmpY = ny - 1;
        } else if (d == 3) {
            tmpX = nx + 1;
        }

        if (map[tmpY][tmpX] == 1 || !isPosValid(tmpX, tmpY)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isCanMoveForward(int nx, int ny) {

        // 바라보는 방향의 앞쪽으로 이동 가능한지 검사
        // 이동할 수 없으면 false 반환

        int tmpX = nx;
        int tmpY = ny;

        // d가 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽
        if (d == 0) {
            tmpY = ny - 1;
        } else if (d == 1) {
            tmpX = nx + 1;
        } else if (d == 2) {
            tmpY = ny + 1;
        } else if (d == 3) {
            tmpX = nx - 1;
        }

        if (map[tmpY][tmpX] == 1 || !isPosValid(tmpX, tmpY)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isPosValid(int tmpX, int tmpY) {
        if (tmpX < 0 || tmpX >= M || tmpY < 0 || tmpY >= N) {
            return false;
        } else {
            return true;
        }
    }

    private static int[] calBackPos(int nx, int ny) {
        if (d == 0) {
            return new int[]{nx, ny + 1};
        } else if (d == 1) {
            return new int[]{nx - 1, ny};
        } else if (d == 2) {
            return new int[]{nx, ny - 1};
        } else if (d == 3) {
            return new int[]{nx + 1, ny};
        } else {
            return null;
        }
    }

    private static int[] calForwardPos(int nx, int ny) {
        if (d == 0) {
            return new int[]{nx, ny - 1};
        } else if (d == 1) {
            return new int[]{nx + 1, ny};
        } else if (d == 2) {
            return new int[]{nx, ny + 1};
        } else if (d == 3) {
            return new int[]{nx - 1, ny};
        } else {
            return null;
        }
    }

    private static int calDirection() {
        if (d - 1 != -1) {
            return d - 1;
        } else {
            return 3;
        }
    }
}
