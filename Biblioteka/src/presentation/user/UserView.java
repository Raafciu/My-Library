package presentation.user;

import business.book.Book;
import control.book.BookController;
import control.exception.KeyNotFoundException;
import presentation.Library;

import java.util.Optional;
import java.util.Scanner;

public class UserView {

    private static final BookController bookController = new BookController();

    private static Scanner scValue;

    public static void userView() throws KeyNotFoundException {



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

                    System.out.println("1");
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

                    System.out.println("Podaj ilość stron:");
                    scValue = new Scanner(System.in);
                    int pages = scValue.nextInt();
                    book.setPages(pages);

                    System.out.println("Podaj kategorie:");
                    scValue = new Scanner(System.in);
                    String category = scValue.nextLine();
                    book.setCategory(category);

                    System.out.println("Podaj cenę:");
                    scValue = new Scanner(System.in);
                    double price = scValue.nextDouble();
                    book.setPrice(price);

                    book.setAvailable(true);

                    bookController.persist(book);
                    break;

                case 2:
                    System.out.println("2");
                    System.out.println(bookController.getAll());

                    break;
                case 3:
                    System.out.println("3");
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



