package baekjoon;

import java.io.*;
import java.util.*;

public class G5_9205 {

    private static int n, nx, ny, dx, dy;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());

            List<int[]> storePosList = new ArrayList<>();
            for (int j = 0; j < n + 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int posX = Integer.parseInt(st.nextToken());
                int posY = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    nx =posX;
                    ny = posY;
                } else if (j == n + 1) {
                    dx = posX;
                    dy = posY;
                }else {
                    storePosList.add(new int[]{posX, posY});
                }
            }

            System.out.println(bfs(storePosList)? "happy" : "sad");
        }
    }

    private static boolean bfs(List<int[]> list) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{nx, ny});

        boolean[] visited = new boolean[n];

        while (q.size() != 0) {
            int[] pos = q.poll();
            int posX = pos[0];
            int posY = pos[1];

            int dis = Math.abs(posX - dx) + Math.abs(posY - dy);
            if (dis <= 1000) {
                return true;
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int[] storePos = list.get(i);
                    int storeX = storePos[0];
                    int storeY = storePos[1];

                    int tmpDis = Math.abs(posX - storeX) + Math.abs(posY - storeY);
                    if (tmpDis <= 1000) {
                        visited[i] = true;
                        q.add(new int[]{storeX, storeY});
                    }
                }
            }
        }

        return false;
    }
}
