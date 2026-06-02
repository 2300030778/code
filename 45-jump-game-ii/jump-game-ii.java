class Solution {
    public int jump(int[] nums) {
        int J = 0;
        int F = 0;
        int C = 0;

        for (int i = 0; i < nums.length - 1; i++){
            F = Math.max(F, i + nums[i]);

            if (i == C){
                J++;
                C = F;
            }
        }
        return J;
        
    }
}