package main.GUI;

import main.java.Group;
import main.java.Student;
import main.java.StudentCondition;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyGUI {
    private JList<Student> studentList;
    private JList<Group> groupList;
    private JTable studentTable;
    private JTable groupTable;

    public MyGUI(List<Student> students, List<Group> groups){

        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // modele
        StudentTableModel studentTableModel = new StudentTableModel(students);
        GroupTableModel groupTableModel = new GroupTableModel(groups);

        // tabele
        studentTable = new JTable(studentTableModel);
        groupTable = new JTable(groupTableModel);

        // listy
        studentList = new JList<>(students.toArray(new Student[0]));
        groupList = new JList<>(groups.toArray(new Group[0]));

        //  panel z listami
        JPanel listPanel = new JPanel(new GridLayout(2, 1));
        listPanel.add(new JScrollPane(studentList));
        listPanel.add(new JScrollPane(groupList));

//  modele do tabel
        studentTable.setModel(studentTableModel);
        groupTable.setModel(groupTableModel);

        // panel z tabelami
        JPanel tablePanel = new JPanel(new GridLayout(2, 1));
        tablePanel.add(new JScrollPane(studentTable));
        tablePanel.add(new JScrollPane(groupTable));

        //Panel z filtrem
        JPanel filterPanel = new JPanel();
        JTextField filterTextBox = new JTextField(20); // 20 to liczba kolumn pola tekstowego
        filterPanel.add(new JLabel("Filter: "));
        filterPanel.add(filterTextBox);
        JButton resetButton = new JButton("Reset");
        filterPanel.add(resetButton);

        filterTextBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = filterTextBox.getText().trim().toLowerCase();
                List<Student> filteredStudents = new ArrayList<>();
                for (Student student : students) {
                    if (student.getName().toLowerCase().startsWith(searchText)) {
                        filteredStudents.add(student);
                    }
                }
                studentTableModel.setStudents(filteredStudents);
                studentTableModel.fireTableDataChanged();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterTextBox.setText(""); // wyzeruj
                studentTableModel.setStudents(students); // usuń
                studentTableModel.fireTableDataChanged(); // odśwież tabelę
            }
        });

        // panel z krótkimi informacjami na temat studentów
        JPanel studentInfoPanel = new JPanel();
        studentInfoPanel.setLayout(new BoxLayout(studentInfoPanel, BoxLayout.Y_AXIS));
        studentInfoPanel.setBorder(BorderFactory.createTitledBorder("Selected Student"));
        JLabel firstNameLabel = new JLabel("First Name: ");
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JLabel albumLabel = new JLabel("Album Number: ");
        JLabel pointsLabel = new JLabel("Points: ");
        studentInfoPanel.add(firstNameLabel);
        studentInfoPanel.add(lastNameLabel);
        studentInfoPanel.add(albumLabel);
        studentInfoPanel.add(pointsLabel);

        // przyciski
        JButton removeButton = new JButton("Remove");
        JButton changeInfoButton = new JButton("Change Info");
        JButton addStudentButton = new JButton("Add Student");
        JButton addGroupButton = new JButton("Add Group");
        JButton sortButton = new JButton("Sort by name");
        JButton sortPointsButton = new JButton("Sort by points");

        //wyswitlanie studentow z grupy na liscie i w tabeli
        groupList.addListSelectionListener(e -> {
            Group selectedGroup = groupList.getSelectedValue();
            if(selectedGroup!=null) {
                List<Student> groupStudents = selectedGroup.getStudents();
                studentList.setListData(groupStudents.toArray(new Student[0]));
            }
        });

        groupTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = groupTable.getSelectedRow();
            if (selectedRowIndex != -1) {
                Group selectedGroup = groupTableModel.getGroups(selectedRowIndex);
                List<Student> groupStudents = selectedGroup.getStudents();
                studentTableModel.setStudents(groupStudents);
                studentTableModel.fireTableDataChanged();
            }
        });

        changeInfoButton.addActionListener(e->{
            int selectedRowIndex = studentTable.getSelectedRow();
            int selectedColIndex = studentTable.getSelectedColumn();

            int sRowIndex = groupTable.getSelectedRow();
            int sColIndex = groupTable.getSelectedColumn();

            if (selectedRowIndex >= 0) {
                //Student selectedStudent = studentTableModel.getStudent(selectedRowIndex);
                Object value = JOptionPane.showInputDialog("Enter value:");
                studentTableModel.setValueAt(value,selectedRowIndex,selectedColIndex);
                studentTableModel.fireTableDataChanged();
            }
            if(sRowIndex>=0){
                Object value = JOptionPane.showInputDialog("Enter value: ");
                groupTableModel.setValueAt(value,sRowIndex,sColIndex);
                groupTableModel.fireTableDataChanged();
            }
        });

        removeButton.addActionListener(e -> {
            int selectedRowIndex = studentTable.getSelectedRow();
            if (selectedRowIndex >= 0) {
                Student selectedStudent = studentTableModel.getStudent(selectedRowIndex);
                students.remove(selectedStudent);
                studentTableModel.fireTableRowsDeleted(selectedRowIndex, selectedRowIndex);
            }

            selectedRowIndex = groupTable.getSelectedRow();
            if (selectedRowIndex >= 0) {
                Group selectedGroup = groupTableModel.getGroups(selectedRowIndex);
                groups.remove(selectedGroup);
                groupTableModel.fireTableRowsDeleted(selectedRowIndex, selectedRowIndex);
            }
        });

        addGroupButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter group name");
            int maxNumberOfStudents = Integer.parseInt(JOptionPane.showInputDialog("Enter maximum number of students in group"));
            List<Student> studentsList = new ArrayList<>();
                Group newGroup = new Group(name,studentsList,maxNumberOfStudents);
            groups.add(newGroup);
            groupTableModel.fireTableDataChanged();
            groupList.setListData(groups.toArray(new Group[0]));
            groupList.setSelectedValue(newGroup, true);
        });

        addStudentButton.addActionListener(e -> {
            // okno dialogowe z formularzem do dodawania nowego studenta
            String name = JOptionPane.showInputDialog("Enter student name:");
            String lastName = JOptionPane.showInputDialog("Enter student last name:");
            StudentCondition studentCondition = StudentCondition.valueOf(JOptionPane.showInputDialog("Enter Student Condition:"));
            Integer year = Integer.valueOf(JOptionPane.showInputDialog("enter age"));
            double points = Double.parseDouble(JOptionPane.showInputDialog("Enter points"));
            String albumNumber = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("Enter student album number:")));

            Group selectedGroup = groupList.getSelectedValue();

            // tworzenie nowego studenta i dodanie go do listy oraz tabeli
            Student newStudent = new Student(name, lastName,studentCondition,year,points, albumNumber);
           if(selectedGroup!=null){
               selectedGroup.addStudent(newStudent);
               groupTableModel.fireTableDataChanged();
           }
            studentTableModel.fireTableDataChanged();
            students.add(newStudent);
            studentList.setListData(students.toArray(new Student[0]));
            studentList.setSelectedValue(newStudent, true);
        });

        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = groupTable.getSelectedRow();
                Group selectedGroup = groupTableModel.getGroups(selectedRowIndex);
                selectedGroup.sortByName();
                groupTableModel.fireTableDataChanged();
                groupTableModel.setGroups(groups);
                groupList.setListData(groups.toArray(new Group[0]));
                // odświeżanie wyświetlanego listy grup
            }
        });

        sortPointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = groupTable.getSelectedRow();
                Group selectedGroup = groupTableModel.getGroups(selectedRowIndex);
                selectedGroup.sortByPoints();
                groupTableModel.fireTableDataChanged();
                groupTableModel.setGroups(groups);
                groupList.setListData(groups.toArray(new Group[0]));

            }
        });


        studentTable.getSelectionModel().addListSelectionListener(e -> {
            int row = studentTable.getSelectedRow();
            if (row >= 0) {
                Student selectedStudent = studentTableModel.getStudent(row);
                firstNameLabel.setText("First Name: " + selectedStudent.getName());
                lastNameLabel.setText("Last Name: " + selectedStudent.getLastName());
                albumLabel.setText("Album number: " + selectedStudent.getAlbumNumber());
                pointsLabel.setText("Points: " + selectedStudent.getPoints());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addGroupButton);
        buttonPanel.add(addStudentButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(sortPointsButton);
        buttonPanel.add(changeInfoButton);


        // główny panel
        JPanel mainPanel = new JPanel(new GridLayout(2, 3));
       // mainPanel.add(listPanel);
        mainPanel.add(tablePanel);
        mainPanel.add(studentInfoPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(filterPanel);



        // dodanie głównego panelu do ramki
        frame.getContentPane().add(mainPanel);

        // rozmiar ramki/ wyswietlenie
        frame.pack();
        frame.setSize(1200,800);
        frame.setVisible(true);
    }
}