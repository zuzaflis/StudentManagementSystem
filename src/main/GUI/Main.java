package main.GUI;

import main.java.Group;
import main.java.Student;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> studentsList = DataGenerator.getInstance().generateStudents();
        List<Group> groups = DataGenerator.getInstance().generateGroups();
        MyGUI gui = new MyGUI(studentsList,groups);

    }
}
