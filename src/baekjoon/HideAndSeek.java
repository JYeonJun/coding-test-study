package baekjoon;

/* 아이디어 */
// N, M 크기인 0 ~ 100,000 크기만큼 배열 생성
// 수빈이가 이동하는 위치(인덱스)마다 시간 저장

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class HideAndSeek {

    private static int[] position = new int[100_001];
    private static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] initPosition = br.readLine().split(" ");
        int n = Integer.parseInt(initPosition[0]);
        int m = Integer.parseInt(initPosition[1]);

        if (n == m) {
            System.out.println(0);
            return;
        }

        Queue<Integer> que = new LinkedList<>();

        position[n] = 1;
        que.add(n);

        while (que.size() != 0) {
            Integer currentPosition = que.poll();

            for (int i = 0; i < 3; i++) {
                int next = 0;

                if(i == 0) next = currentPosition - 1;
                else if(i == 1) next = currentPosition + 1;
                else next = currentPosition * 2;

                if (next == m) {
                    System.out.println(position[currentPosition]);
                    return;
                }

                if (next >= 0 && next < position.length && position[next] == 0) {
                    position[next] = position[currentPosition] + 1;
                    que.add(next);
                }
            }
        }
    }
}
