class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n=items.length;
        //Step1: calculate the reward for each items
        //Reward =1 purchased copy+free copies received.
        int[] reward=new int [n];
        for (int i = 0; i < n; i++) {
            reward[i] = 1;

            for (int j = 0; j < n; j++) {

                // Item i gives a free copy of item j
                // if factor[i] divides factor[j].
                // An item cannot give itself for free.
                if (i != j && items[j][0] % items[i][0] == 0) {
                    reward[i]++;
                }
            }
        }

        // Step 2: Sort items by price in descending order.
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) ->
            Integer.compare(items[b][1], items[a][1])
        );

        // dp[b] = maximum reward using exactly b budget,
        // considering all items processed so far.
        int NEG = -1000000000;

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        int ans = 0;

        // Step 3: Process items group by group
        // according to their prices.
        int idx = 0;

        while (idx < n) {

            int price = items[order[idx]][1];

            // has[b] = maximum reward using exactly b budget,
            // with at least one item of the current price.
            int[] has = new int[budget + 1];
            Arrays.fill(has, NEG);

            // Process all items having the same price.
            while (idx < n &&
                   items[order[idx]][1] == price) {

                int i = order[idx];
                int cost = items[i][1];
                int value = reward[i];

                // 0/1 Knapsack: traverse backwards
                // to avoid selecting the same item twice.
                for (int b = budget; b >= cost; b--) {

                    if (dp[b - cost] != NEG) {

                        has[b] = Math.max(
                            has[b],
                            dp[b - cost] + value
                        );
                    }

                    dp[b] = Math.max(
                        dp[b],
                        dp[b - cost] + value
                    );
                }

                idx++;
            }

            // Step 4: Calculate additional copies.
            // The cheapest selected item costs 'price'.
            for (int b = 0; b <= budget; b++) {

                if (has[b] != NEG) {

                    int extraCopies = (budget - b) / price;

                    ans = Math.max(
                        ans,
                        has[b] + extraCopies
                    );
                }
            }
        }

        return ans;
    }
}