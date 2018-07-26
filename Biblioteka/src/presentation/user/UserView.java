package presentation.user;

import business.book.Book;
import control.book.BookController;
import control.exception.KeyNotFoundException;
import control.validation.PagesValidator;
import control.validation.PriceValidator;
import control.validation.Validator;
import presentation.Library;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserView {

    private static final BookController bookController = new BookController();

    private static Scanner scValue;

    public static void userView() throws KeyNotFoundException, InterruptedException {
        Book book;
        int choose;

        do {

            System.out.println("1.Dodaj książkę do biblioteki");
            System.out.println("2.Przejrzyj ksiazki");
            System.out.println("3.Usun ksiazke");
            System.out.println("4.Powrot do glownego menu");
            System.out.println("5.Koniec");

            Scanner scChoose = new Scanner(System.in);
            choose = scChoose.nextInt();

            switch (choose) {
                case 1:

                    book = new Book();
                    System.out.println("Podaj ISBN:");
                    scValue = new Scanner(System.in);
                    String ISBN = scValue.nextLine();
                    book.setIsbn(ISBN);

                    System.out.println("Podaj tytul:");
                    scValue = new Scanner(System.in);
                    String title = scValue.nextLine();
                    book.setTitle(title);

                    System.out.println("Podaj autora:");
                    scValue = new Scanner(System.in);
                    String author = scValue.nextLine();
                    book.setAuthor(author);

                    boolean pagesFlag = false;
                    do {
                        try {
                            System.out.println("Podaj ilość stron:");
                            scValue = new Scanner(System.in);
                            int pages = scValue.nextInt();
                            Validator validator = new PagesValidator();
                            pagesFlag = validator.validate(pages);
                            book.setPages(pages);
                        } catch (InputMismatchException e) {
                            System.out.println("Integers only, please");
                            scValue.nextLine();
                        }
                    } while (!pagesFlag);

                    System.out.println("Podaj kategorie:");
                    scValue = new Scanner(System.in);
                    String category = scValue.nextLine();
                    book.setCategory(category);

                    pagesFlag = false;
                    do {
                        try {
                            System.out.println("Podaj cenę: x,xx");
                            scValue = new Scanner(System.in);
                            double price = scValue.nextDouble();
                            Validator validator = new PriceValidator();
                            pagesFlag = validator.validate(price);
                            book.setPrice(price);
                        } catch (InputMismatchException e) {
                            System.out.println("Check format");
                            scValue.nextLine();
                        }
                    } while (!pagesFlag);

                    book.setAvailable(true);
                    bookController.persist(book);
                    Library.timeSeparator();
                    break;
                case 2:
                    System.out.println(bookController.getAll());
                    Library.timeSeparator();
                    break;
                case 3:
                    System.out.println("Podaj ISBN ksiazki, którą chcesz usunąć");
                    scValue = new Scanner(System.in);
                    String scISBN = scValue.nextLine();
                    Optional<Book> optionalBook = bookController.getById(scISBN);
                    optionalBook.ifPresent(bookController::remove);
                    break;
                case 4:
                    Library.main(null);
                    break;
                case 5:
                    System.out.println("Do widzenia!");
                    System.exit(-1);
                    break;
                default:
                    System.out.println("Nie ma takiej operacji! Wybierz operacje 1-5");
            }
        } while (true);

    }

}



