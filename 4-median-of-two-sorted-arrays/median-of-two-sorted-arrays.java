class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;

        while (low <= high) {
            int i = (low + high) / 2;
            int j = (m+ n + 1) / 2 - i;

            int left1 = (i > 0) ? nums1 [i - 1]: Integer.MIN_VALUE;
            int right1 = (i < m) ? nums1 [i]: Integer.MAX_VALUE;
            int left2 = (j > 0) ? nums2 [j - 1]: Integer.MIN_VALUE;
            int right2 = (j < n) ? nums2 [j]: Integer.MAX_VALUE;


            if (left1 <= right2 && left2 <= right1) {
                if((m + n) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;

                }else {
                    return Math.max(left1, left2);
                }
            }

            else if (left1 > right2) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted or invalid");


        
    }
}