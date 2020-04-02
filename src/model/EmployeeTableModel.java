package model;

import entities.Employee;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class EmployeeTableModel extends AbstractTableModel {

    private ArrayList<Employee> employeList;

    public EmployeeTableModel(String hql) {
        super();
        Session session = Sesija.createSession();
        try {
            Query q = session.createQuery(hql);
            employeList = new ArrayList<Employee>(q.list());
        } catch (HibernateException exception) {
            System.out.println("Error: " + exception.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public int getRowCount() {
        return employeList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee e = employeList.get(rowIndex);
        Object[] values = new Object[]{
            e.getEmployeeId(), e.getName(), e.getAge(), e.getAddress(), e.getSalary()};
        return values[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        String[] columnNames = new String[]{"Id", "Name", "Age", "Address", "Salary"};
        return columnNames[column];
    }

}
