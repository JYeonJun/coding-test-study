package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.*;

// 14500번 테트로미노
public class Tetromino {

    static int[][][] block = {
            {{0, 1}, {0, 2}, {0, 3}},
            {{1, 0}, {2, 0}, {3, 0}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{0, 1}, {1, 0}, {2, 0}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
            {{}, {}, {}},
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                StringTokenizer stArr = new StringTokenizer(br.readLine());
                arr[i][j] = parseInt(stArr.nextToken());
            }
        }

        int ans = 0;
    }
}
