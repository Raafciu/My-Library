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
}
