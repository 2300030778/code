class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int minLandEnd = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            minLandEnd = Math.min(minLandEnd, landStartTime[i] + landDuration[i]);
        }

        int minWaterEnd = Integer.MAX_VALUE;
        for (int i = 0; i < waterStartTime.length; i++) {
            minWaterEnd = Math.min(minWaterEnd, waterStartTime[i] + waterDuration[i]);
        }

        int ans = Integer.MAX_VALUE;

        // Land -> Water
        for (int i = 0; i < waterStartTime.length; i++) {
            int finish = waterDuration[i]
                    + (minLandEnd <= waterStartTime[i]
                       ? waterStartTime[i]
                       : minLandEnd);

            ans = Math.min(ans, finish);
        }

        // Water -> Land
        for (int i = 0; i < landStartTime.length; i++) {
            int finish = landDuration[i]
                    + (minWaterEnd <= landStartTime[i]
                       ? landStartTime[i]
                       : minWaterEnd);

            ans = Math.min(ans, finish);
        }

        return ans;
    }
}