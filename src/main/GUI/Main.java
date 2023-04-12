package main.GUI;

import main.java.Group;
import main.java.Student;
import main.java.StudentCondition;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student st = new Student("Karol", " Kawa", StudentCondition.PRESENT, 2022,200);
        Student stt = new Student("Karol", " Kawa", StudentCondition.PRESENT, 2022,200);
        List<Student> studentsList = new ArrayList<>();
        Group klasa = new Group("klasa",studentsList,6);
        klasa.addStudent(st);klasa.addStudent(stt);
    List<Group> groups = new ArrayList<>();
    groups.add(klasa);
        MyGUI gui = new MyGUI(studentsList,groups);

    }
}
