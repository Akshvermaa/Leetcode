class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        //option 1: Buy only type 1 and type 2 items
        long option1=(long)need1*cost1+(long)need2*cost2;
        // Option 2: Buy only type 3 items
        // Each item contributes to both requirements
        long option2 = (long) Math.max(need1, need2) * costBoth;

        // Option 3: Buy type 3 items for the common requirement
        // Then buy remaining individual items
        int common = Math.min(need1, need2);

        long option3 = (long) common * costBoth
                     + (long) (need1 - common) * cost1
                     + (long) (need2 - common) * cost2;

        // Return the minimum cost among all strategies
        return Math.min(option1, Math.min(option2, option3));
    
    }
}