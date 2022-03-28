package algorithm.jiuzhang.sort;

public class M143SortColorsII {
	
	
    public void sortColors2(int[] colors, int k) {
        if(colors == null || colors.length == 0){
            return;
        }
        sortColors(colors, 1, k, 0, colors.length - 1);
    }
    
    private void sortColors(int[] colors, int ks, int ke, int start, int end){
        if(ks > ke){
            return;
        }
        if(start > end){
            return;
        }
        int k = (ks + ke) / 2;
        int left = start;
        int right = end;
        int cursor = start;
        while(cursor <= right){
            if(colors[cursor] < k){
                swap(colors, cursor, left);
                left++;
                cursor++;
            }else if(colors[cursor] > k){
                swap(colors, cursor, right);
                right--;
            }else{
                cursor++;
            }
        }
        sortColors(colors, ks, k - 1, start, left - 1);
        sortColors(colors, k + 1, ke, right + 1, end);
    }
    
    private void swap(int[] colors, int a, int b){
        int temp = colors[a];
        colors[a] = colors[b];
        colors[b] = temp; 
    }
	
//    public void sortColors2(int[] colors, int k) {
//        if(colors == null || colors.length == 0){
//            return;
//        }
//        sortColors(colors, k, 1, 0, colors.length - 1);
//    }
//    
//    private void sortColors(int[] colors,int k, int ki, int start, int end){
//        if(start > end || ki > k){
//            return;
//        }
//        int pointer = start;
//        for(int i = start; i <= end; i++){
//            if(colors[i] <= ki){
//                if(i != pointer){
//                    int temp = colors[pointer];
//                    colors[pointer] = colors[i];
//                    colors[i] = temp;
//                }
//                pointer++;
//            }
//        }
//        sortColors(colors, k, ki++, pointer, end);
//    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M143SortColorsII one = new M143SortColorsII();
		int[] arr = {2,1,3,4,2,4};
		int k = 4;
		one.sortColors2(arr, k);
	}

}
