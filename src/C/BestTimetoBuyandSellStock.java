public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        int sum = 0, tmp = 0;
        for (int i = 1; i < prices.length; i++) {
            sum += prices[i] - prices[i - 1];
            if (sum < 0)
                sum = 0;
            if (sum > 0 & sum > tmp)
                tmp = sum;
        }
        return tmp;
    }
}
