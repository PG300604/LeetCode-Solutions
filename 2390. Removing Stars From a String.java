class Solution {
    public String removeStars(String s) {
        Deque<Character> stack=new ArrayDeque<>();
        for(char ch : s.toCharArray()){
            if(ch=='*'){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(ch);
            }
        }
        StringBuilder sb=new StringBuilder();
        for(char c : stack){
            sb.append(c);
        }
        sb.reverse();
        String result= sb.toString();
        return result;
    }
}
