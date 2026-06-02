class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {
            for (int j = 0; j < waterStartTime.length; j++) {

                int landFinish = landStartTime[i] + landDuration[i];
                int waterFinish = Math.max(waterStartTime[j], landFinish) + waterDuration[j];
                ans = Math.min(ans, waterFinish);


                int waterFinshFirst = waterStartTime[j] + waterDuration[j];
                int landFinishSecond = Math.max(landStartTime[i], waterFinshFirst) + landDuration[i];
                ans = Math.min(ans, landFinishSecond);

                
            }
        }

        return ans;
        
    }
}