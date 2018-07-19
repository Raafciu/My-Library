package control.employee;

import business.employee.Employee;
import control.IController;

import java.util.List;

public class EmployeeController implements IController<Employee> {
    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public void persist(Employee employee) {

    }

    @Override
    public void merge(Employee employee) {

    }

    @Override
    public void remove(Employee employee) {

    }
}
