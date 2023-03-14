import java.util.*;

public class Class {
    private String groupName;
    private int maxNumberOfStudents;
    private List<Student> listOfStudents;

    public void addStudent(Student newStudent){
        if(listOfStudents.contains(newStudent)){
            System.err.println("Podany student już istnieje na liscie");
        }
        if(listOfStudents.size()!=maxNumberOfStudents) {
            listOfStudents.add(newStudent);
        } else System.err.println("Brak miejsca na liscie studentów");
    }
    public void getStudent(Student student){
        if(student.getPoints()==0){
            listOfStudents.remove(student);
        }
    }
    public void addPoints(Student student, double points){
        student.setPoints(points);
    }
    public void removePoints(Student student, double points){
        student.setPoints(-points);
    }
    public void changeCondition(Student student, StudentCondition newCondition){
        student.setStudentCondition(newCondition);
    }
   public Student search(String lastName){
       Comparator<Student> lastNameComparator = Comparator.comparing(Student::getLastName);
       Collections.sort(listOfStudents,lastNameComparator);
      Student studentToFind= new Student("",lastName,StudentCondition.CATCH_UP,0,0);
       int index =Collections.binarySearch(listOfStudents, studentToFind,lastNameComparator);

       if(index>=0){
           return listOfStudents.get(index);
       } else return null;
   }
   public int countByCondition(StudentCondition condition){
        int count =0;
        for (Student student : listOfStudents){
            if(student.getStudentCondition()==condition) {
                count++;
            }
        }
        return count;
   }
   public void summary(){
        for(Student student : listOfStudents){
            student.toString();
        }
   }
   public void sortByName(){
        Comparator<Student> nameComparator= Comparator.comparing(Student::getName);
        Collections.sort(listOfStudents,nameComparator);
   }
   public List<Student> sortPartial(String part){
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student: listOfStudents){
            if(student.getName().contains(part)||student.getLastName().contains(part)){
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
   }
   public void sortByPoints(){
        Comparator<Student> pointsComparator = Comparator.comparing((Student::getPoints)).reversed();//dzięki reversed sortuje malejąco
        Collections.sort(listOfStudents,pointsComparator);
   }
public void maxPoints(){
        Comparator<Student> pointsComparator = Comparator.comparing((Student::getPoints));
        Student studentWithMaxPoints= Collections.max(listOfStudents,pointsComparator);
        System.out.println("Student with max points: "+ studentWithMaxPoints);
    }
}
