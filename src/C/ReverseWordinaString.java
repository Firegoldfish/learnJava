import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWordinaString {
    public String reverseWords(String s) {
        s =s.trim();
        List<String> word = Arrays.asList(s.split("\\s+"));
        Collections.reverse(word);
        return String.join(" ", word);
    }
}