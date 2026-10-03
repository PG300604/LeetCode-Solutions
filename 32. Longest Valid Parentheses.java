class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack=new Stack<>();
        stack.push(-1);
        char[] c=s.toCharArray();
        int current_length=0;
        int max_length=current_length;
        for(int i=0;i<c.length;i++){
            if(c[i]=='('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    current_length=i-stack.peek();
                    max_length=Math.max(current_length,max_length);
                }
            }
        }
        return max_length;
    }
}
