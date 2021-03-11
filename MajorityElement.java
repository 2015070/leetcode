package leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/majority-element/
public class MajorityElement {

    public int majorityElement(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int tmp;
        for(int i = 0; i< nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                tmp = map.get(nums[i]);
                map.put(nums[i], tmp+1);
            }
            tmp = map.get(nums[i]);
            if(tmp >= nums.length/2.0) {
                return nums[i];
            }
        }
        return 0;
    }
}
