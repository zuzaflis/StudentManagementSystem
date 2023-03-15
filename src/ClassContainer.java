import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {
   private final Map<String,Class> groups;

    public ClassContainer(){
        groups=new HashMap<>();
    }
    public void addClass(String name, int maxNumber){
        Class newClass= new Class();
        newClass.setMaxNumberOfStudents(maxNumber);

        groups.put(name,newClass);
    }
    public void removeClass(String nameOfClassToRemove){
        groups.remove(nameOfClassToRemove);
    }
    public List<String> findEmpty(){
        List<String> emptyClasses = new ArrayList<>();
        for(String groupName : groups.keySet()) {
            Class group = groups.get(groupName);
            if(group.getNumberOfStudents()==0){
                emptyClasses.add(groupName);
            }
        }
        return emptyClasses;
    }

}
