class Solution {
    public int[] maxProductPair(int[] nums, int target) {
        int[] res=new int[]{-1,-1};
        long max_product=Long.MIN_VALUE;
        Map<Integer,Integer> map=new HashMap<>();
        for(int k=0;k<nums.length;k++){
            int complement=target -nums[k];
            if(map.containsKey(complement) && nums[k]!=complement){
                int prev=map.get(complement);
                long current_product=(long)nums[k]*complement;
                if(current_product>max_product){
                    max_product=current_product;
                    if(nums[k]>complement){
                        res[0]=k;
                        res[1]=prev;
                    }else{
                        res[0]=prev;
                        res[1]=k;
                    }
                }
            }
            map.put(nums[k],k);
        }
        return res;
    }
}
