import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      Student student = new Student("zuzia", " flis", StudentCondition.PRESENT, 2022,200);
List<Student> st = new ArrayList<>();
//st.add(student);
Class klasap = new Class("klaska",st,6);
klasap.addStudent(student);
     // klasap.summary();


      ClassContainer classContainer = new ClassContainer();
        classContainer.addClass("klasa1",6);
        Class grupa1 = classContainer.getGroups().get("klasa1");

        grupa1.addStudent(student);
        grupa1.summary();
        classContainer.summary();


    }
}