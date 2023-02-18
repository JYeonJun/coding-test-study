package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Matrix {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int line = Integer.parseInt(st.nextToken()); // 행
        int low = Integer.parseInt(st.nextToken()); // 열

        int[][] A = new int[line][low];
        int[][] B = new int[line][low];

        for (int i = 0; i < line; i++) {
            A[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < line; i++) {
            B[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int ans = 0; // A -> B 연산 횟수의 최솟값

        // 3x3 크기
        for (int i = 0; i < line - 2; i++) {
            for (int j = 0; j < low - 2; j++) {
                if (A[i][j] != B[i][j]) {
                    // 3 x 3 바꾸기
                    for (int k = i; k < i + 3; k++) {
                        for (int q = j; q < j + 3; q++) {
                            A[k][q] = A[k][q] == 0 ? 1 : 0;
                        }
                    }
                    ans++;
                }
            }
        }

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < low; j++) {
                if (A[i][j] != B[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(ans);
    }
}
