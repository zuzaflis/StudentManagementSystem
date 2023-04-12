package main.GUI;

import main.java.Group;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GroupTableModel extends AbstractTableModel {
    private List<Group> groups;
    private String[] columnNames = {"Group Name", "Number of Students"};
    public GroupTableModel(List<Group> groups) {
        this.groups = groups;
    }
    @Override
    public int getRowCount() {
        return groups.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Group group = groups.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return group.getGroupName();
            case 1:
                return group.getListOfStudents().size();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }
}
