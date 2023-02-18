package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Remocon {

    // 고장난 버튼 (true)
    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());

        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        // 정답의 초기값: 숫자버튼을 누르지 않는 경우
        int ans = N - 100;
        if (ans < 0) ans = -ans;

        // 이동할 채널 결정
        for (int i = 0; i < 1_000_000; i++) {

            int c = i;

            // 이동할 수 있는 채널인지 확인
            // len == 0이면 해당 채널로 이동 불가능
            int len = possible(c);

            if (len > 0) {
                int press = c - N;
                if (press < 0) {
                    press = -press;
                }

                // 최소값 확인
                if (ans > len + press) {
                    ans = len + press;
                }
            }
        }

        System.out.println(ans);
    }

    // 이동이 불가능하면 0
    private static int possible(int c) {

        // 이동하고 싶은 채널이 0번일 경우
        if (c == 0) {
            // 0번 버튼으 고장났다면
            if (broken[0]) {
                return 0;
            } else {
                return 1;
            }
        }

        int len = 0;
        while (c > 0) {
            if (broken[c % 10]) {
                return 0;
            }
            len++;
            c /= 10;
        }

        return len;
    }
}
