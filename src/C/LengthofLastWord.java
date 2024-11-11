public class LengthofLastWord {
    public int lengthOfLastWord(String s) {
        int num = 0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' ') num++;
            if(num!=0&&s.charAt(i)==' ') break;
        }
        return num;
    }
}
