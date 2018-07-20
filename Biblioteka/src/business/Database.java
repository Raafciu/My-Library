package business;

import business.book.Book;
import business.employee.Employee;
import business.user.User;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Map;
import java.util.TreeMap;

public class Database {

    private static Database instance;
    private final Map<String, Book> bookMap = new TreeMap<>();
    private final Map<String, User> userMap = new TreeMap<>();
    private final Map<String, Employee> employeeMap = new TreeMap<>();

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public void persist(Book book) throws KeyAlreadyExistsException {
        if (bookMap.containsKey(book.getIsbn()))
            throw new KeyAlreadyExistsException("Nie mozna dodac Ksiazki, poniewaz istnieje juz ksiazka o podanym kluczu");
        else
            bookMap.put(book.getIsbn(), book);
    }

    public Map<String, Book> getBooks() {
        return bookMap;
    }

    public void remove(Book book) throws KeyAlreadyExistsException {
        if(!bookMap.containsKey(book.getIsbn()))
            throw new KeyAlreadyExistsException("Nie mozna usunac Ksiazki, poniewaz nie istnieje");
        else
            bookMap.remove(book.getIsbn(), book);
    }

    public void merge(Book book) {
        bookMap.replace(book.getIsbn(), book);
    }


    public void persist(User user) {
        if (bookMap.containsKey(user.getPesel()))
            throw new KeyAlreadyExistsException("Nie mozna dodac uzy, poniewaz istnieje juz ksiazka o podanym kluczu");
        else
            userMap.put(user.getPesel(), user);

    }

    public Map<String , User> getUsers() {
        return userMap;
    }

    public void remove(User user) {
        userMap.remove(user.getPesel(), user);
    }

    public void merge(User user) {
        userMap.replace(user.getPesel(),user);
    }


    public void persist(Employee employee) {
        employeeMap.put(employee.getPesel(), employee);
    }

    public Map<String, Employee> getEmployees() {
        return employeeMap;
    }

    public void remove(Employee employee) {
        employeeMap.remove(employee.getPesel(), employee);
    }

    public void merge(Employee employee) {
        employeeMap.replace(employee.getPesel(),employee);
    }
}
