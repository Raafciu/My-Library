package business;

import business.book.Book;
import business.employee.Employee;
import business.user.User;
import org.junit.Before;
import org.junit.Test;

import javax.management.openmbean.KeyAlreadyExistsException;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    private Book book;
    private User user;
    private Employee employee;

    @Before
    public void init() {
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
        Database.getInstance().persist(book);
        Book expectedBook = Database.getInstance().getBooks().get(book.getIsbn());
        assertEquals(expectedBook, book);
        System.out.println(Database.getInstance().getBooks());
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void shouldThrowExceptionDuringPersistTest() {
        Database.getInstance().persist(book);
        Database.getInstance().persist(book);
    }

    @Test
    public void shouldRemoveBookTest() {
        Database.getInstance().persist(book);
        Database.getInstance().remove(book);
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void shouldThrowExceptionDuringRemoveTest() {
        Database.getInstance().persist(book);
        Database.getInstance().remove(book);
        Database.getInstance().remove(book);
    }

    @Test
    public void shouldMergeBookTest() {

    }
    @Test
    public void shouldPersistUserTest() {
        Database.getInstance().persist(user);
        User expectedUser = Database.getInstance().getUsers().get(user.getPesel());
        assertEquals(expectedUser,user);
    }
    @Test
    public void shouldPersistEmployeeTest(){
        Database.getInstance().persist(employee);
        Employee expectedEmployee = Database.getInstance().getEmployees().get(employee.getPesel());
        assertEquals(expectedEmployee,employee);
    }

}