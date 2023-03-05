package baekjoon;

// 1차 서류심사
// 2차 면접시험
// 서류심사, 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발
// = 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.
// 신규 사원 채용에서 선발할 수 있는 신입사원의 최대 인원수

/* 아이디어 */
// 각 지원자의 성적마다 자신보다 높은 지원자가 있는지 체크 그리고 그 지원자랑 다른 점수도 체크 -> 둘 다 자신보다 높다면 그 사람은 안됨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S1_1946 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        List<int[]> candidateScoreList = new ArrayList<>();

        for (int i = 0; i < t; i++) {

            int candidateNum = Integer.parseInt(br.readLine());

            boolean[] check = new boolean[candidateNum];

            for (int j = 0; j < candidateNum; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int docScore = Integer.parseInt(st.nextToken());
                int interviewScore = Integer.parseInt(st.nextToken());
                candidateScoreList.add(new int[]{docScore, interviewScore});
            }

            for (int q = 0; q < candidateNum; q++) {
                int[] scores = candidateScoreList.get(q);
                int docScore = scores[0];
                int interviewScore = scores[1];

                for (int k = 0; k < candidateNum; k++) {
                    if (!check[q] && k != q && docScore < candidateScoreList.get(k)[0] && interviewScore < candidateScoreList.get(k)[1]) {
                        check[q] = true;
                    }
                }
            }

            int result = 0;
            for (boolean b : check) {
                if (!b) result++;
            }

            System.out.println(result);
        }
    }
}
