package library.business;

import library.business.book.Book;
import library.business.employee.Employee;
import library.business.user.User;
import library.control.exception.KeyNotFoundException;

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

    public void persistBook(Book book) {
        if (bookMap.containsKey(book.getIsbn()))
            throw new KeyAlreadyExistsException("Nie mozna dodac ksiazki, poniewaz istnieje juz taka ksiazka");
        else
            bookMap.put(book.getIsbn(), book);
    }

    public Map<String, Book> getBooks() {
        return bookMap;
    }

    public Book getBookById(String isbn) throws KeyNotFoundException {
        return bookMap.entrySet().stream()
                .filter(bookEntry -> isbn.equals(bookEntry.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(KeyNotFoundException::new);
    }

    public void removeBook(Book book) throws KeyNotFoundException {
        if (!bookMap.containsKey(book.getIsbn()))
            throw new KeyNotFoundException();
        else
            bookMap.remove(book.getIsbn(), book);
    }

    public void mergeBook(Book book) throws KeyNotFoundException {
        if (!bookMap.containsKey(book.getIsbn()))
            throw new KeyNotFoundException();
        else
            bookMap.replace(book.getIsbn(), book);

    }


    public void persistUser(User user) {
        if (userMap.containsKey(user.getPesel()))
            throw new KeyAlreadyExistsException("Nie mozna dodac uzytkownika, poniewaz istnieje juz taki pracownik");
        else
            userMap.put(user.getPesel(), user);

    }

    public Map<String, User> getUsers() {
        return userMap;
    }

    public void removeUser(User user) throws KeyNotFoundException {
        if (!userMap.containsKey(user.getPesel()))
            throw new KeyNotFoundException();
        else
            userMap.remove(user.getPesel(), user);
    }

    public void mergeUser(User user) throws KeyNotFoundException {
        if (!userMap.containsKey(user.getPesel()))
            throw new KeyNotFoundException();
        else
            userMap.replace(user.getPesel(), user);
    }


    public void persistEmployee(Employee employee) {
        if (employeeMap.containsKey(employee.getPesel()))
            throw new KeyAlreadyExistsException("Nie mozna dodac pracownika, poniewaz istnieje juz taki pracownik");
        else
            employeeMap.put(employee.getPesel(), employee);
    }

    public Map<String, Employee> getEmployees() {
        return employeeMap;
    }

    public void removeEmployee(Employee employee) throws KeyNotFoundException {
        if (!employeeMap.containsKey(employee.getPesel()))
            throw new KeyNotFoundException();
        else
            employeeMap.remove(employee.getPesel(), employee);
    }

    public void mergeEmployee(Employee employee) throws KeyNotFoundException {
        if (!employeeMap.containsKey(employee.getPesel()))
            throw new KeyNotFoundException();
        else
            employeeMap.replace(employee.getPesel(), employee);
    }
}
