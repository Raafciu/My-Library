package control.employee;

import business.Database;
import business.employee.Employee;
import control.IController;

import java.util.List;
import java.util.Map;

public class EmployeeController implements IController<Employee> {
    @Override
    public Map<String,Employee> getAll() {
        return Database.getInstance().getEmployees();
    }

    @Override
    public void persist(Employee employee) {
        Database.getInstance().persist(employee);
    }

    @Override
    public void merge(Employee employee) {
        Database.getInstance().merge(employee);

    }

    @Override
    public void remove(Employee employee) {
        Database.getInstance().remove(employee);
    }
}
