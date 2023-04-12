package main.java;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
      Student student = new Student("Karol", " Kawa", StudentCondition.PRESENT, 2022,200,"");
      List<Student> studentsList = new ArrayList<>();
    //st.add(student);
    Group klasa = new Group("klasa",studentsList,6);
    klasa.addStudent(student);
    klasa.addPoints(student,30.0);
    klasa.removePoints(student,15.0);
      //klasa.summary();


      ClassContainer classContainer = new ClassContainer();
        classContainer.addClass("klasa1",6);
        Group grupa1 = classContainer.getGroups().get("klasa1");

        grupa1.addStudent(student);
        grupa1.summary();
        classContainer.summary();

    }

}
