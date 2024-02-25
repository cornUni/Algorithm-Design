import java.util.Scanner;

public class Main {

    public static int removal_count(char[] arr){
        int length = arr.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++)
                dp[i][j] = j - i + 1;
        }
        for (int i = 1; i < length; i++) {
            if (arr[i - 1] == arr[i])
                dp[i - 1][i] = 1;
        }
        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                if (arr[i] == arr[j])
                    dp[i][j] = dp[i + 1][j - 1];
                for (int k = i; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
            }
        }
        return dp[0][length - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        int minimumRemovalCount = removal_count(data.toCharArray());
        System.out.println(minimumRemovalCount);
    }
}
