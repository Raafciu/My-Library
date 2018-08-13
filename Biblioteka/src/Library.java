import business.book.Book;

import java.util.*;

public class Library {

    private List<Book> bookList ;

    public Library() {
        bookList = new LinkedList<>();
    }
    public void addBook(Book book) {
        Scanner scValue = new Scanner(System.in);

        bookList.add(book);
    }

    public static void main(String[] args) {




        System.out.println("*********WITAMY W NASZEJ BIBLIOTECE*********");
        System.out.println("1.Dodaj książkę do biblioteki");
        System.out.println("2.Przejrzyj dostepne ksiazki do wypozyczenia");
        System.out.println("3.Wypozyczone ksiazki");
        System.out.println("4.Koniec");

        int choose;
        do{
            Scanner scChoose = new Scanner(System.in);
            choose = scChoose.nextInt();

            switch (choose) {
                case 1:
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("Do widzenia!");
                    break;
                default:
                    System.out.println("Nie ma takiej operacji! Wybierz operacje 1-4:");
            }
        }while(choose!=4);
    }
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
}
