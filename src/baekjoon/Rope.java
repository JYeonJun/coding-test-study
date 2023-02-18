package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Rope {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        List<Integer> ropes = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            ropes.add(Integer.parseInt(br.readLine()));
        }

        ropes.sort(Comparator.naturalOrder());

        // keypoint!
        // 모든 로프를 사용할 필요가 없다!!

        // w <= min * k
        int max = 0;
        int tmp;
        int num = k;
        for (int i = 0; i < k; i++) {
             tmp = ropes.get(i) * num;
            if (tmp > max) {
                max = tmp;
            }
            num--;
        }

        System.out.println(max);
    }
}
