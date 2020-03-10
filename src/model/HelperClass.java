package model;

import entities.Employee;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HelperClass {

    private String in;
    private int br;
    static JFrame f = new JFrame();

    public static String listAllHQL() {
        return "select e from Employee e"; // HQL code for listing all employees from database
    }

    public static String listByIdHQL() {
        return "select e from Employee as e where e.employeeId = :u"; //HQL code for selectin employee by id from database
    }

    public static String changeNameHQL() {
        return "update Employee as e set e.name = :p where e.employeeId = :u"; //HQL code for alternig employee's name in database
    }

    public static String changeAgeHQL() {
        return "update Employee as e set e.age= :p where e.employeeId= :u"; //HQL code for alternig employee's age in database
    }

    public static String changeAddressHQL() {
        return "update Employee as e set e.address= :p where e.employeeId= :u"; //HQL code for alternig employee's address in database
    }

    public static String changeSalaryHQL() {
        return "update Employee as e set e.salary= :p where e.employeeId= :u"; //HQL code for alternig employee's salary in database
    }

    public static Employee changeEmployeeStr(int id, String input, String hql) { // method for changing employee's informanion (String type)
        Session session = Sesija.createSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query q = session.createQuery(hql);
            q.setParameter("u", id);
            q.setParameter("p", input);
            q.executeUpdate();
            tr.commit();
        } catch (HibernateException exception) {
            tr.rollback();
            JOptionPane.showMessageDialog(f, "Error: " + exception.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    public static Employee changeEmployeeNum(int id, int input, String hql) { // method for changing employee's informanion (int type)
        Session session = Sesija.createSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query q = session.createQuery(hql);
            q.setParameter("u", id);
            q.setParameter("p", input);
            q.executeUpdate();
            tr.commit();
            JOptionPane.showMessageDialog(f, "Employe information successfully changed.");
        } catch (HibernateException exception) {
            tr.rollback();
            JOptionPane.showMessageDialog(f, "Error: " + exception.getMessage());

        } finally {
            session.close();
        }
        return null;
    }

    public static Employee findEmployee(int id) { // method for selecting employee by employee's id 
        Session session = Sesija.createSession();
        Transaction tr = null;
        try {
            Query q = session.createQuery("select e from Employee as e where e.employeeId = :u");
            q.setParameter("u", id);
            return (Employee) (q.list().get(0));
        } catch (HibernateException exception) {
            tr.rollback();
            JOptionPane.showMessageDialog(f, "Error: " + exception.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    public static List<Employee> listAllEmployees() { // method for listing all employees 
        Session session = Sesija.createSession();
        try {
            Query q = session.createQuery("select e from Employee e");
            return q.list();
        } catch (HibernateException exception) {
            JOptionPane.showMessageDialog(f, "Error: " + exception.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    public static void insertEmloyee(Employee k) { // method inesrting employee's informanion 
        Session session = Sesija.createSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.persist(k);
            tr.commit();
            JOptionPane.showMessageDialog(f, "Employe by name: " + k.getName() + ", age: " + k.getAge() + ", address: " + k.getAddress() + " and salary: " + k.getSalary() + " successfully added to database.");
        } catch (HibernateException exception) {
            tr.rollback();
            JOptionPane.showMessageDialog(f, "Error: " + exception.getMessage());
        } finally {
            session.close();
        }
    }

    public static void deleteEmployee(int id) { // method for deleting employee buy it's id
        Session session = Sesija.createSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query q = session.createQuery("delete Employee as e where e.employeeId = :u");
            q.setParameter("u", id);
            q.executeUpdate();
            tr.commit();
            JOptionPane.showMessageDialog(f, "Employe successfully deleted.");
        } catch (HibernateException exception) {
            JOptionPane.showMessageDialog(f, "Error: " + exception.getMessage());
        } finally {
            session.close();
        }
    }

}
