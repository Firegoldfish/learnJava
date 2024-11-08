import java.util.Arrays;

class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1.length == 0 || nums2.length == 0) {
            return;
        }
        if (m < 0 || n < 0) {
            return;
        }
        for (int i = 0; i<n ; i++){
            nums1[m+i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}