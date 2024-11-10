package C;

public class JumpGames {
    public int jump(int[] nums) {
        int count=0, next=0, max=0;
        for(int i = 0 ; i<nums.length;i++){
            if(i > max){
                max = next;
                ++count;
            }
            next = Math.max(next,i+nums[i]);
        }
        return count;
    }
}
