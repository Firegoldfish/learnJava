public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String s = strs[0];
        for(String str:strs){
            while(!str.startsWith(s)){
                if(s.length()==0) return "";
                s=s.substring(0,s.length()-1);
            }
        }
        return s;
    }
}
