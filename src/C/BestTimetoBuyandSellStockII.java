package C;

public class BestTimetoBuyandSellStockII {
    public int maxProfit(int[] prices) {
        int sum = 0 ;
        for (int i=prices.length-2;i>=0;i--){
            if(prices[i+1]-prices[i]>0){
                sum+=prices[i+1]-prices[i];
            }
        }
        return sum;
    }
}
