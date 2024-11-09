import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {
    public void filterWithoutAPI(){
        List<String> originalList = Arrays.asList("Apple", "Fig", "Banana", "Kiwi");
        List<String> filterList = new ArrayList<String>();
        for(String item : originalList){
            if(item.length() > 3){
                filterList.add(item);
            }
        }
    }
    public void filterWithAPI(){
        List<String> originalList = Arrays.asList("Apple", "Fig", "Banana", "Kiwi");
        List<String> filterList = originalList.stream().filter(s->s.length()>3).collect(Collectors.toList());
    }

    public void sumWithoutAPI(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = 0;
        for(Integer number : numbers){
            sum += number;
        }
    }
    public void sumWithAPI(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
    }
}
