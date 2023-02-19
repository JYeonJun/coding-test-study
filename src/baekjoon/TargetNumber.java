package baekjoon;


public class TargetNumber {

    private static int ans = 0;

    public static void main(int[] numbers, int target) {

        // numbers 수를 전부 합친 것보다 크면 return 0
        // numbers 수를 전부 뺀 것 보다 작으면 return 0
        int plusSum = 0;
        int minusSum = 0;
        for (int number : numbers) {
            plusSum += number;
            minusSum -= number;
        }
        if (plusSum < target || minusSum > target) {
            System.out.println(0);
            return;
        }
        if (plusSum == target || minusSum == target) {
            System.out.println(1);
            return;
        }

        dfs(numbers, 0, target, 0);

        System.out.println(ans);
    }

    // 재귀함수
    private static void dfs(int[] numbers, int index, int target, int sum) {

        int loop = numbers.length;

        if (index == loop) {
            if (sum == target) ans++;
        } else {
            dfs(numbers, index + 1, target, sum + numbers[index]);
            dfs(numbers, index + 1, target, sum - numbers[index]);
        }
    }
}
