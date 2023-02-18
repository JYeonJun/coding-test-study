package baekjoon;

/* 아이디어 */
// 배열을 사용해 인덱스에 이동한 횟수 넣기 - bfs
// 주의! - 만약 U층 위, 또는 D층 아래에 해당 층이 없으면 엘리베이터는 움직이지 않는다.
// g층 빼고 전부 이동한 기록이 있으면 "use the stairs" 출력

/* 자료구조 */
// 1층부터 f층까지 기록할 int[]

/* 시간복잡도 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class StartLink {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int f = Integer.parseInt(input[0]); // 건물 층수
        int s = Integer.parseInt(input[1]); // 강호의 현재 위치
        int g = Integer.parseInt(input[2]); // 스타트링크 위치
        int u = Integer.parseInt(input[3]); // 위로 몇층
        int d = Integer.parseInt(input[4]); // 아래로 몇층

        int[] building = new int[f + 1];
        int cnt = 0; // 방문한 층의 개수
        Queue<Integer> que = new LinkedList<>();

        building[s] = 1;
        cnt++;
        que.add(s);

        while (que.size() != 0) {

            Integer currentFloor = que.poll();

            if (currentFloor == g) {
                System.out.println(building[g] - 1);
                return;
            }

            for (int i = 0; i < 2; i++) {

                int next = 0;
                if (i == 0) {
                    next = currentFloor + u;
                } else {
                    next = currentFloor - d;
                }

                if (next >= 1 && next <= f) {

                    if (building[next] == 0) {
                        building[next] = building[currentFloor] + 1;
                        cnt++;
                        que.add(next);
                    }
                }
            }
        }
        System.out.println("use the stairs");
    }
}
