public class Student implements Comparable<Student>{
    private String name;
    private String lastName;
    private StudentCondition studentCondition;
    private Integer yearOfBirth;
    private String albumNumber;
    private double points;

    public String getName() {
        return name;
    }

    public void setStudentCondition(StudentCondition studentCondition) {
        this.studentCondition = studentCondition;
    }

    public StudentCondition getStudentCondition() {
        return studentCondition;
    }

    public double getPoints() {
        return points;
    }

    public String getLastName() {
        return lastName;
    }

    public Student(String name, String lastName, StudentCondition studentCondition, Integer yearOfBirth, double points) {
        this.name = name;
        this.lastName = lastName;
        this.studentCondition = studentCondition;
        this.yearOfBirth = yearOfBirth;
        this.points = points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentCondition=" + studentCondition +
                ", yearOfBirth=" + yearOfBirth +
                ", points=" + points +
                '}';
    }

    @Override
    public int compareTo(Student student) {
        return this.lastName.compareTo(student.lastName); //zwraca 0 jesli sa rowne, ujemne gdy obecny jest mneijszy
    }
}
