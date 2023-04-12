package main.GUI;

import main.java.Group;
import main.java.Student;
import main.java.StudentCondition;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataGenerator {
    private static DataGenerator instance = new DataGenerator();
    private DataGenerator(){};

    public static DataGenerator getInstance() {
        return instance;
    }

    public List<Student> generateStudents(){
        Student st1 = new Student("Karol", "Flis", StudentCondition.PRESENT, 2002, 200, "232323");
        Student st2 = new Student("Gaja", "Kawa", StudentCondition.PRESENT, 2002, 0, "3232132");
        Student st3 = new Student("Ania", "Wanki", StudentCondition.PRESENT, 2002, 260, "3232132");
        Student st4 = new Student("Tomasz", "Kłotki", StudentCondition.PRESENT, 2002, 560, "372132");
        Student st5 = new Student("Zuzanna", "Asunki", StudentCondition.PRESENT, 2002, 290, "872132");
        Student st6 = new Student("Jan", "Kowalski", StudentCondition.PRESENT, 2003, 350, "111111");
        Student st7 = new Student("Aleksandra", "Nowak", StudentCondition.ABSENT, 2002, 0, "222222");
        Student st8 = new Student("Jakub", "Sokół", StudentCondition.PRESENT, 2002, 450, "333333");
        Student st9 = new Student("Magdalena", "Pietruszka", StudentCondition.PRESENT, 2002, 170, "444444");
        Student st10 = new Student("Mateusz", "Lis", StudentCondition.ABSENT, 2002, 0, "555555");


        List<Student> students1 = new ArrayList<>();
        students1.add(st1);
        students1.add(st2);
        students1.add(st3);
        students1.add(st6);
        students1.add(st7);

        List<Student> students2 = new ArrayList<>();
        students2.add(st4);
        students2.add(st5);
        students2.add(st8);
        students2.add(st9);
        students2.add(st10);

        List<Student> allStudents = new ArrayList<>();
        allStudents.addAll(students1);
        allStudents.addAll(students2);

        return allStudents;
    }
    public List<Group> generateGroups(){
        List<Group> groups = new ArrayList<>();

        List<Student> allStudents= generateStudents();
        List<Student> students1 = new ArrayList<>();
        students1.add(allStudents.get(0));
        students1.add(allStudents.get(1));
        students1.add(allStudents.get(2));
        students1.add(allStudents.get(5));
        students1.add(allStudents.get(6));

        List<Student> students2 = new ArrayList<>();
        students2.add(allStudents.get(3));
        students2.add(allStudents.get(4));
        students2.add(allStudents.get(7));
        students2.add(allStudents.get(8));
        students2.add(allStudents.get(9));


        Group group1 = new Group("Grupa 1", students1, 6);
        Group group2 = new Group("Grupa 2", students2, 10);

        groups.add(group1);
        groups.add(group2);

        return groups;
    }
}
