public class temp {
    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        int f = -1;
        if(n==0){
            return f;
        }
        int[] front = new int[n];
        int[] behind = new int[n];
        front[0] = 0;
        behind[n-1] = 0;
        for(int i = 1;i < n;i++ ){
            front[i] = front[i-1]+nums[i-1];
        }
        for(int i = n-2;i>=0;i--){
            behind[i] = behind[i+1]+nums[i+1];
        }
        for (int i = 0; i < n; i++) {
            if(behind[i]==front[i]){
                f = i;
                return f;
            }
        }
        return f;
    }

    public static void main(String[] args) {
        int[] nums = {-1,-1,0,0,-1,-1};
        int a = pivotIndex(nums);
        System.out.println(a);
    }
}
