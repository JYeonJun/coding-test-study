package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Virus {

    private static int cnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = Integer.parseInt(br.readLine());
        int pair = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < total; i++) {
            map.put(i + 1, new LinkedList<>());
        }

        for (int i = 0; i < pair; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int firstNum = Integer.parseInt(st.nextToken());
            int secondNum = Integer.parseInt(st.nextToken());
            addToMap(map, firstNum, secondNum);
            addToMap(map, secondNum, firstNum);
        }

        boolean[] check = new boolean[total];

        bfs(1, map, check);
        System.out.println(cnt);
    }

    private static void bfs(int key, Map<Integer, List<Integer>> map, boolean[] check) {

        Queue<Integer> q = new LinkedList<>();

        check[key - 1] = true;
        q.add(key);

        while (q.size() != 0) {

            Integer tmp = q.poll();

            List<Integer> list = map.get(tmp);
            for (Integer connectedNum : list) {
                if (!check[connectedNum - 1]) {
                    check[connectedNum - 1] = true;
                    cnt++;
                    q.add(connectedNum);
                }
            }
        }
    }


    private static void addToMap(Map<Integer, List<Integer>> map, int key, int value) {
        List<Integer> list = map.get(key);
        list.add(value);
    }
}
