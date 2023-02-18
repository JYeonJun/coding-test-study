package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 문제 */
// 부모 - 자식 : 1촌
// 형제 - 형제 : 2촌
// 아버지 - 할아버지 : 1촌 -> 나 - 할아버지 : 2촌
// 할아버지 - 아버지 형제들 : 1촌 -> 나 -> 아버지 형제들 : 3촌

/* 아이디어 */
// 우선 양방향인지 단방향인지 판단
// -> 양방향으로 그래프 탐색할 수 있도록
// edge의 개수를 구하는 것이 필요
// p1부터 dfs로 시작!!!

/* 시간복잡도 */


/* 자료구조 */


public class VillageCount {

    private static int cnt = 0;
    private static int ans = 0;

    private static boolean resultStatus = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전체 사람 수
        int n = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> village = new HashMap<>();

        for (int i = 0; i < n; i++) {
            village.put(i + 1, new ArrayList<>());
        }

        // 촌수를 계산해야 하는 서로 다른 두 사람의 번호
        String[] peopleNum = br.readLine().split(" ");
        int p1 = Integer.parseInt(peopleNum[0]);
        int p2 = Integer.parseInt(peopleNum[1]);

        // 부모 자식들 간의 관계의 개수
        int m = Integer.parseInt(br.readLine());


        // 부모 자식간의 관계를 나타내는 두 번호 x, y
        // x는 y의 부모 번호를 나타낸다.
        for (int i = 0; i < m; i++) {
            String[] rel = br.readLine().split(" ");
            int parent = Integer.parseInt(rel[0]);
            int child = Integer.parseInt(rel[1]);

            // 양방향 그래프 생성
            addToMap(village, parent, child);
            addToMap(village, child, parent);
        }

        boolean[] check = new boolean[village.size()];

        dfs(village, check, p1, p2);
        if (resultStatus) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }

    private static void dfs(Map<Integer, List<Integer>> village, boolean[] check, int standard, int destination) {

        check[standard - 1] = true;

        List<Integer> list = village.get(standard);
        for (Integer num : list) {
            if (!check[num - 1]) {
                cnt++;
                if (num == destination) {
                    resultStatus = true;
                    ans = cnt;
                    return;
                }
                dfs(village, check, num, destination);
                cnt--;
            }
        }
    }

    private static void addToMap(Map<Integer, List<Integer>> village, int key, int valule) {
        List<Integer> list = village.get(key);
        list.add(valule);
    }
}
