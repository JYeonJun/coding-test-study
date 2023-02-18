package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//===아이디어===
// 연결된 숫자들을 넘버링 해야되므로 bfs 보다는 dfs가 맞다고 생각함
// dfs를 사용하므로 재귀호출 사용

//===시간복잡도===
// 25 * 25 * 4(상하좌우 검사) = 2500

//===자료구조===
// 상하좌우를 나타내는 int형 배열 x, y
// 맵을 저장할 int형 2차원 배열
// 각 인덱스를 지나갔는지 검사하는 boolean형 2차원 배열
// 총 단지의 수를 저장할 int
// 각 단지의 번호와 각 단지내 집의 수를 저장할 Map<Integer, Integer>

public class BlockNumbering {

    private static int numbering = 1;
    // 현재 좌표의 값이 0이면 numbering 1증가

    private static Map<Integer, Integer> result = new HashMap<>();

    private static int[] x = {0, 1, 0, -1};
    private static int[] y = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        boolean[][] check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - 48;
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i == 0 && j == 0) {
                    if (!check[i][j] && map[i][j] == 1) {
                        if (result.containsKey(numbering)) {
                            check[i][j] = true;
                            map[i][j] = numbering;
                            int num = result.get(numbering);
                            result.put(numbering, num + 1);
                        } else {
                            check[i][j] = true;
                            map[i][j] = numbering;
                            result.put(numbering, 1);
                        }
                    }
                }
                dfs(Arrays.asList(j, i), map, check);
            }
        }

        System.out.println(result.size());

        List<Integer> values = new ArrayList<>(result.values());
        Collections.sort(values);
        for (Integer value : values) {
            System.out.println(value);
        }
    }

    private static void dfs(List<Integer> location, int[][] map, boolean[][] check) {

        int N = map.length;

        int nx = location.get(0);
        int ny = location.get(1);

        if (map[ny][nx] != numbering && !check[ny][nx]) {
            numbering++;
        }

        for (int i = 0; i < 4; i++) {
            int xPos = nx + x[i];
            int yPos = ny + y[i];

            if (xPos >= 0 && xPos < N && yPos >= 0 && yPos < N) {
                if (!check[yPos][xPos] && map[yPos][xPos] == 1) {
                    if (result.containsKey(numbering)) {
                        check[yPos][xPos] = true;
                        map[yPos][xPos] = numbering;
                        int num = result.get(numbering);
                        result.put(numbering, num + 1);
                    } else {
                        check[yPos][xPos] = true;
                        map[yPos][xPos] = numbering;
                        result.put(numbering, 1);
                    }
                    dfs(Arrays.asList(xPos, yPos), map, check);
                }
            }
        }
    }
}
