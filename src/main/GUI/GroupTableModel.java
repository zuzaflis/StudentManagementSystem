package main.GUI;

import main.java.Group;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GroupTableModel extends AbstractTableModel {
    private List<Group> groups;

    public Group getGroups(int index) {
        return groups.get(index);
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    private String[] columnNames = {"Group Name","Max number of Students", "Number of Students"};
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
                return group.getMaxNumberOfStudents();
            case 2:
                return group.getListOfStudents().size();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }
    @Override
    public void setValueAt( Object value, int row, int col) {
        Group group = getGroups(row);
        switch (col){
            case 0:
                group.setGroupName((String) value);
                break;
            case 1:
                group.setMaxNumberOfStudents(Integer.parseInt((String) value));
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
        fireTableCellUpdated(row, col); // odświeżenie widoku

    }

}
