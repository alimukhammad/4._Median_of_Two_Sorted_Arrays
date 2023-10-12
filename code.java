class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array for simplicity.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // Get the lengths of both arrays.
        int x = nums1.length;
        int y = nums2.length;
        
        // Initialize binary search boundaries.
        int low = 0;
        int high = x;

        while (low <= high) {
            // Partition the smaller array into two halves.
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            // Get the maximum and minimum elements from both halves.
            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
            int minY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxX <= minY && maxY <= minX) {
                // If conditions are met, we have found the median.
                double leftMax = Math.max(maxX, maxY);
                double rightMin = Math.min(minX, minY);
                
                // Check if the total length is even or odd and return accordingly.
                if ((x + y) % 2 == 0) {
                    return (leftMax + rightMin) / 2.0;
                } else {
                    return leftMax;
                }
            } else if (maxX > minY) {
                // Adjust the binary search boundaries.
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        // If the input arrays are not sorted, throw an exception.
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}
