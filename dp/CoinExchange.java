package main.dp;

public class CoinExchange {

    public static int coinExchangeRec(int[] coins, int n, int sum) {
        if(sum == 0) return 1;

        if(sum < 0) return 0;
        if(n <= 0 && sum >= 0) return 0;

        return coinExchangeRec(coins, n-1, sum) + coinExchangeRec(coins, n, sum - coins[n-1]);
    }

    public static int coinExchangeDP(int[] coins, int n, int sum) {
        int[][] ways = new int[n+1][sum+1];

        ways[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if(j < coins[i-1]) {
                    ways[i][j] = ways[i-1][j];
                } else {
                    // No of way when coin is excluded + when coin is included.
                    ways[i][j] = ways[i-1][j] + ways[i][j-coins[i-1]];
                }
            }
        }
        return ways[n][sum];
    }

    public static void main(String[] args) {
        int[] coins = {2,3,5,4,1};
        System.out.println(coinExchangeRec(coins, coins.length, 5));
        System.out.println(coinExchangeDP(coins, coins.length, 6));

    }


}
