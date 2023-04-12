package main.GUI;

import main.java.Group;
import main.java.Student;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.List;

public class MyGUI {
        private JList<Student> studentList;
        private JList<Group> groupList;
        private JTable table;

        public MyGUI(List<Student> students, List<Group> groups){
            JFrame frame = new JFrame("Student Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //modele

            StudentTableModel studentTableModel = new StudentTableModel(students);
            GroupTableModel groupTableModel = new GroupTableModel(groups);

            //tabele

            JTable studentTable = new JTable(studentTableModel);
            JTable groupTable = new JTable(groupTableModel);

            // dodaj kolumnę z checkboxami do wyboru w tabeli studentTable
            TableColumn checkboxColumn = studentTable.getColumnModel().getColumn(0);
            checkboxColumn.setCellEditor(studentTable.getDefaultEditor(Boolean.class));
            checkboxColumn.setCellRenderer(studentTable.getDefaultRenderer(Boolean.class));

            // stwórz listy
            studentList = new JList<>(students.toArray(new Student[0]));
            groupList = new JList<>(groups.toArray(new Group[0]));
            // stwórz panel z listami
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.add(new JLabel("Students"));
            listPanel.add(new JScrollPane(studentList));
            listPanel.add(Box.createVerticalStrut(20));
            listPanel.add(new JLabel("Groups"));
            listPanel.add(new JScrollPane(groupList));

            // stwórz panel z tabelami
            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
            tablePanel.add(new JLabel("Students"));
            tablePanel.add(new JScrollPane(studentTable));
            tablePanel.add(Box.createVerticalStrut(20));
            tablePanel.add(new JLabel("Groups"));
            tablePanel.add(new JScrollPane(groupTable));

            // stwórz panel z krótkimi informacjami na temat studentów
            JPanel studentInfoPanel = new JPanel();
            studentInfoPanel.setLayout(new BoxLayout(studentInfoPanel, BoxLayout.Y_AXIS));
            studentInfoPanel.setBorder(BorderFactory.createTitledBorder("Selected Student"));
            JLabel firstNameLabel = new JLabel("First Name: ");
            JLabel lastNameLabel = new JLabel("Last Name: ");
            JLabel ageLabel = new JLabel("Age: ");
            studentInfoPanel.add(firstNameLabel);
            studentInfoPanel.add(lastNameLabel);
            studentInfoPanel.add(ageLabel);


            // po wybraniu studenta z listy aktualizuj krótkie informacje
            studentList.addListSelectionListener(e -> {
                Student selectedStudent = studentList.getSelectedValue();
                if (selectedStudent != null) {
                    firstNameLabel.setText("First Name: " + selectedStudent.getName());
                    lastNameLabel.setText("Last Name: " + selectedStudent.getLastName());
                    ageLabel.setText("Age: " + selectedStudent.getYearOfBirth());
                }
            });

            // stwórz główny panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
            mainPanel.add(listPanel);
            mainPanel.add(Box.createHorizontalStrut(20));
            mainPanel.add(tablePanel);
            mainPanel.add(Box.createHorizontalStrut(20));
            mainPanel.add(studentInfoPanel);

            // dodaj główny panel do ramki
            frame.getContentPane().add(mainPanel);

            // ustaw rozmiar ramki i wyświetl ją na ekranie
            frame.pack();
            frame.setVisible(true);

        }

}
