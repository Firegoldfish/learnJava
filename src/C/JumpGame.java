class JumpGame{
    public boolean canJump(int[] nums) {
        int step = 1;
        for(int i=0;i<nums.length&&step>=1;i++){
            step = Math.max(step-1,nums[i]);
            if(i+step>=nums.length-1) return true;
        }
        return false;
    }
}