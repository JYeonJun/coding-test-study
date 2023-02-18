package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DfsAndBfs {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호

        boolean[] dfsCheck = new boolean[N];
        boolean[] bfsCheck = new boolean[N];

        HashMap<Integer, List<Integer>> routeMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            routeMap.put(i+1, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer read = new StringTokenizer(br.readLine());

            String first = read.nextToken();
            int firstNum = Integer.parseInt(first);

            String second = read.nextToken();
            int secondNum = Integer.parseInt(second);

            List<Integer> firstList = routeMap.get(firstNum);
            firstList.add(secondNum);

            List<Integer> secondList = routeMap.get(secondNum);
            secondList.add(firstNum);
        }

        // DFS 결과 - 재귀탐색
        List<Integer> routes = routeMap.get(V);
        Collections.sort(routes);
        dfs(V, routeMap, dfsCheck);
        System.out.println();

        // BFS 결과 - Queue 또는 LinkedList
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsCheck[V - 1] = true;
        bfsQueue.add(V);
        while (bfsQueue.size() != 0) {
            Integer num = bfsQueue.poll();
            System.out.print(num + " ");
            List<Integer> bfsRoutes = routeMap.get(num);
            Collections.sort(bfsRoutes);

            for (Integer routeNum : bfsRoutes) {
                if (bfsCheck[routeNum - 1] == false) {
                    bfsCheck[routeNum - 1] = true;
                    bfsQueue.add(routeNum);
                }
            }
        }
    }

    private static void dfs(int start, HashMap<Integer, List<Integer>> routeMap, boolean[] check) {

        List<Integer> routes = routeMap.get(start);
        Collections.sort(routes);

        check[start - 1] = true;

        System.out.print(start + " ");

        for (Integer node : routes) {
            if (check[node - 1] == false) {
                dfs(node, routeMap, check);
            }
        }
    }
}
