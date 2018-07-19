package control.book;

import business.book.Book;
import control.IController;

import java.util.List;
import java.util.Scanner;

public class BookController implements IController<Book> {

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void persist(Book book) {
//
//        Scanner scValue;
//
//        System.out.println("Podaj ISBN:");
//        scValue = new Scanner(System.in);
//        String ISBN =scValue.nextLine();
//        book.setIsbn(ISBN);
//
//        System.out.println("Podaj tytul:");
//        scValue = new Scanner(System.in);
//        String title = scValue.nextLine();
//        book.setTitle(title);
//
//        System.out.println("Podaj autora:");
//        scValue = new Scanner(System.in);
//        String author = scValue.nextLine();
//        book.setAuthor(author);
//
//        System.out.println("Podaj ilość stron:");
//        scValue = new Scanner(System.in);
//        int pages = scValue.nextInt();
//        book.setPages(pages);
//
//        System.out.println("Podaj kategorie:");
//        scValue = new Scanner(System.in);
//        String category = scValue.nextLine();
//        book.setCategory(category);
//
//        System.out.println("Podaj cenę:");
//        scValue = new Scanner(System.in);
//        double price = scValue.nextDouble();
//        book.setPrice(price);
//
//        book.setAvailable(true);



        System.out.println("Dodałeś książkę: \n" + book.toString());
    }

    @Override
    public void merge(Book book) {

    }

    @Override
    public void remove(Book book) {

    }
}
