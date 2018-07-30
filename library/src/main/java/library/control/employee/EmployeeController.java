package library.control.employee;

import library.business.Database;
import library.business.employee.Employee;
import library.control.IController;
import library.control.exception.KeyNotFoundException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Map;
import java.util.Optional;

public class EmployeeController implements IController<Employee> {

    @Override
    public Map<String, Employee> getAll() {
        return Database.getInstance().getEmployees();
    }

    @Override
    public Optional<Employee> getById(String id) {
        return null;
    }

    @Override
    public void persist(Employee employee) {
        try {
            Database.getInstance().persistEmployee(employee);
        } catch (KeyAlreadyExistsException e) {
            System.out.println("Nie mozna dodac uzytkownika");
        }
    }

    @Override
    public void merge(Employee employee){
        try {
            Database.getInstance().mergeEmployee(employee);
        }catch(KeyNotFoundException e){
            System.out.println("Nie mozna edytowac pracownika");
        }
    }

    @Override
    public void remove(Employee employee) {
        try {
            Database.getInstance().removeEmployee(employee);
        } catch (KeyNotFoundException e) {
            System.out.println("Nie mozna usunac pracownika");
        }
    }
}
