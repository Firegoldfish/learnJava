public class ZigzagConversion {
    public String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
            for(int j=1;j<=numRows;j++){
                 int index = j;
                 while(index<s.length()){
                    sb.append(""+s.charAt(index));
                    if(j>0&&j<numRows-1){
                        int index2=index+(numRows-j-1)*2;
                        if(index2<s.length()){
                            sb.append(""+s.charAt(index2));
                        }
                    }
                    index+=numRows+numRows-2;
                 }
            }
            return sb.toString();
        }
}
