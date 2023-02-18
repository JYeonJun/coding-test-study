package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CaringCalender {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = Integer.parseInt(br.readLine());

        // (x-1), (y-1)가 각각 M과 N의 나머지가 아니라면 -1 출력/**/

        for (int i = 0; i < caseNum; i++) {
            String[] line = br.readLine().split(" ");
            int m = Integer.parseInt(line[0]);
            int n = Integer.parseInt(line[1]);
            int x = Integer.parseInt(line[2]) - 1;
            int y = Integer.parseInt(line[3]) - 1;
            boolean ok = false;
            for (int k = x; k < (m * n); k += m) {
                if (k % n == y) {
                    System.out.println(k + 1);
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                System.out.println(-1);
            }
        }
    }
}
