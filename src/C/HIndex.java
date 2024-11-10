package C;

import java.util.Arrays;
public class HIndex {
    public int hIndex(int[] citations) {
        int h = 1 ;
        int n=citations.length;
        Arrays.sort(citations);
        for(int i=n-1;i>=0;i--){
            if(h>citations[i]){
                break;
            }
            h++;
        }
        return h-1;
    }
}
