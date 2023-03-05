package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S5_7568 {

    private static List<int[]> frame = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            frame.add(new int[]{x, y});
        }

        for (int i = 0; i < frame.size(); i++) {
            compareFrame(i);
        }
    }

    private static void compareFrame(int index) {

        int result = 0;

        int[] body = frame.get(index);
        int x = body[0];
        int y = body[1];

        for (int i = 0; i < frame.size(); i++) {

            if (i == index) continue;

            int[] compareBody = frame.get(i);
            int compareX = compareBody[0];
            int compareY = compareBody[1];

            if (x < compareX && y < compareY) {
                result++;
            }
        }

        System.out.print(result + 1 + " ");
    }
}
