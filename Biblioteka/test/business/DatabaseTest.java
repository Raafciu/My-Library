package business;

import business.book.Book;
import business.employee.Employee;
import business.user.User;
import control.exception.KeyNotFoundException;
import org.junit.Before;
import org.junit.Test;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    private Book book;
    private User user;
    private Employee employee;

    @Before
    public void init() throws NoSuchFieldException, IllegalAccessException {
        Field instance = Database.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

        book = new Book();
        book.setIsbn("123456789");
        book.setAvailable(true);
        book.setPrice(55.34);
        book.setCategory("dramat");
        book.setPages(180);
        book.setTitle("Między młotem a kowadłem");
        book.setAuthor("Rafal");

        user = new User();
        user.setPesel("96122112432");
        user.setFirstName("Andrzej");
        user.setLastName("Andrzejowski");
        user.setAge(50);
        user.setNumberOfBorrowBooks(0);

        employee = new Employee();
        employee.setPesel("96122112432");
        employee.setFirstName("mietek");
        employee.setLastName("mietkowsi");
        employee.setAge(50);
        employee.setPosition("Kierownik");


    }

    @Test
    public void shouldPersistBookTest() {
        Database.getInstance().persistBook(book);
        Book expectedBook = Database.getInstance().getBooks().get(book.getIsbn());
        assertEquals(expectedBook, book);
    }

    @Test
    public void shouldReturnBookTest() throws KeyNotFoundException {
        Database.getInstance().persistBook(book);
        Book expectedBook = Database.getInstance().getBookById(book.getIsbn());
        assertEquals(expectedBook, book);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldThrowExceptionDuringGetBookByIdTest() throws KeyNotFoundException {
        Database.getInstance().persistBook(book);
        Book expectedBook = Database.getInstance().getBookById("dupa");
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void shouldThrowExceptionDuringPersistBookTest() {
        Database.getInstance().persistBook(book);
        Database.getInstance().persistBook(book);
    }

    @Test
    public void shouldRemoveBookTest() throws KeyNotFoundException {
        Database.getInstance().persistBook(book);
        Database.getInstance().removeBook(book);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldThrowExceptionDuringRemoveBookTest() throws KeyNotFoundException {
        Database.getInstance().removeBook(book);
    }

    @Test
    public void shouldMergeBookTest() throws KeyNotFoundException {
        Database.getInstance().persistBook(book);
        Database.getInstance().mergeBook(book);
        Book expectedBook = Database.getInstance().getBooks().get(book.getIsbn());
        assertEquals(expectedBook, book);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldThrowExceptionDuringMergeBookTest() throws KeyNotFoundException {
        Database.getInstance().mergeBook(book);
    }

    @Test
    public void shouldPersistUserTest() {
        Database.getInstance().persistUser(user);
        User expectedUser = Database.getInstance().getUsers().get(user.getPesel());
        assertEquals(expectedUser, user);
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void shouldThrowExceptionDuringPersistUserTest() {
        Database.getInstance().persistUser(user);
        Database.getInstance().persistUser(user);
    }

    @Test
    public void shouldRemoveUserTest() throws KeyNotFoundException {
        Database.getInstance().persistUser(user);
        Database.getInstance().removeUser(user);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldThrowExceptionRemoveUserTest() throws KeyNotFoundException {
        Database.getInstance().removeUser(user);
    }

    @Test
    public void shouldMergeUserTest() throws KeyNotFoundException {
        Database.getInstance().persistUser(user);
        Database.getInstance().mergeUser(user);
        User expectedUser = Database.getInstance().getUsers().get(user.getPesel());
        assertEquals(expectedUser, user);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldThrowExceptionMergeUserTest() throws KeyNotFoundException {
        Database.getInstance().mergeUser(user);

    }

    @Test
    public void shouldPersistEmployeeTest() {
        Database.getInstance().persistEmployee(employee);
        Employee expectedEmployee = Database.getInstance().getEmployees().get(employee.getPesel());
        assertEquals(expectedEmployee, employee);
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void shouldThrowExceptionPersistEmployeeTest() {
        Database.getInstance().persistEmployee(employee);
        Database.getInstance().persistEmployee(employee);
    }

    @Test
    public void shouldRemoveEmployeeTest() throws KeyNotFoundException {
        Database.getInstance().persistEmployee(employee);
        Database.getInstance().removeEmployee(employee);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldThrowExceptionRemoveTest() throws KeyNotFoundException {
        Database.getInstance().removeEmployee(employee);
    }

    @Test
    public void shouldMergeEmployeeTest() throws KeyNotFoundException {
        Database.getInstance().persistEmployee(employee);
        Database.getInstance().mergeEmployee(employee);
        Employee expectedEmployee = Database.getInstance().getEmployees().get(employee.getPesel());
        assertEquals(expectedEmployee, employee);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldThrowExceptionMergeEmployeeTest() throws KeyNotFoundException {
        Database.getInstance().mergeEmployee(employee);
    }

}