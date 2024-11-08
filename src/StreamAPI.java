import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {
    public void withoutAPI(){
        List<String> originalList = Arrays.asList("Apple", "Fig", "Banana", "Kiwi");
        List<String> filterList = new ArrayList<String>();
        for(String item : originalList){
            if(item.length() > 3){
                filterList.add(item);
            }
        }
    }
    public void withAPI(){
        List<String> originalList = Arrays.asList("Apple", "Fig", "Banana", "Kiwi");
        List<String> filterList = originalList.stream().filter(s->s.length()>3).collect(Collectors.toList());
    }
}
