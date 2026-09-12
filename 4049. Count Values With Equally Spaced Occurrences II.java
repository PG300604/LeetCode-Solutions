class Solution {
    private boolean indexcheck(List<Integer> index){
        int size=index.size();
        int cur=index.get(1)-index.get(0);
        int prev=cur;
        int im_1=1;
        int im=2;
        while(im<size){
            cur=index.get(im)-index.get(im_1);
            if(prev==cur){
               im++;
               im_1++; 
            }else{
                return false;
            }
        }
        return true;
    }
    public int countSpecialIntegers(int[] nums) {
        Map<Integer,List<Integer>> map=new HashMap<>();
        int count=0;
        for(int i=0;i<nums.length;i++){
            map.computeIfAbsent(nums[i], k->new ArrayList<>()).add(i);
        }
        for(List<Integer> index : map.values()){
            if(index.size() >= 3 && indexcheck(index)){
                count++;
            }
        }
        return count;
    }
}
