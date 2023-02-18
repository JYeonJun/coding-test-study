package baekjoon;

import java.util.Scanner;

public class SickKnight {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        if (N == 1) {
            System.out.println(1);
        } else if (N == 2) {
            // N == 2인 경우 2,3번 방법만 사용 가능 -> 최대 3번
            System.out.println(Math.min((M + 1) / 2, 4));
        } else if (N >= 3) {
            if (M < 7) {
                System.out.println(Math.min(M, 4));
            } else {
                System.out.println(M - 2);
            }
        }
    }
}
