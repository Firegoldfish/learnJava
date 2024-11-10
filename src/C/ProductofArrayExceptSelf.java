package C;

class ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int pre = 1, suf=1;
        int[] ans = new int[n];
        for(int i = 0;i<nums.length;i++){
            ans[i]=pre;
            pre*=nums[i];
        }
        for(int i = nums.length-1;i>=0;i--){
            ans[i]*=suf;
            suf*=nums[i];
        }
        return ans;
    }
}