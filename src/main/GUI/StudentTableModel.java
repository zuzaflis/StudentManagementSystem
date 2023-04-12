package main.GUI;

import main.java.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private List<Student> students;
    private String[] columnNames={"First Name", "Last Name", "Year Of Birth", "Album Number", "Points","Student Condition"};

    public StudentTableModel(List<Student> students){
        this.students=students;
    }
    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student= students.get(rowIndex);

        switch (columnIndex){
            case 0:
                return student.getName();
            case 1:
                return student.getLastName();
            case 2:
                return student.getYearOfBirth();
            case 3:
                return student.getAlbumNumber();
            case 4:
                return student.getPoints();
            case 5:
                return student.getStudentCondition();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }
}
