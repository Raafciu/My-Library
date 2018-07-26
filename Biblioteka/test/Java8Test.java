import business.book.Book;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Java8Test {

    /**
     * Zadanka do oceny na jutro
     * (bez pomocy, tylko internet i Twoja głowa :P):
     * <p>
     * 1. Przy użyciu streamów wybrać książki powyżej 400 stron i tylko takie, które są dostępne oraz wypisać pierwsze 5 książek w kolejności od największej liczby stron,
     * 2. Wybrać książki tylko Dana Brown'a i policzyć sumę wszystkich jego książek,
     * 3. Dla książek E. L. James'a ustawić kwotę 9.99,
     * 4. Sprawdzić czy w zbiorze istnieje książka Erica Carle'a, jeśli nie to ją dodać do zbioru (parametry jakie chcesz oprócz autora!),
     * 5. Zmapować dostępną listę wszystkich książek na mapę, gdzie kluczem będzie ISBN, a wartością obiekt Book.
     * 6. Przefiltrować książki powyżej 300 stron alfabetycznie (za pomocą streamów)
     * <p>
     * Tutaj głównie będziesz korzystał ze streamów, pamiętaj o Unit Testach, które będą przy okazji sprawdzały rezultat Twojego działania.
     * Do każdego zadania (oddziel je sobie w metodach z adnotacją @Test) napisz komentarz, dlaczego tak zrobiłeś, a jeśli czegoś nie umiesz
     * to napisz pytania. Tam gdzie jest możliwość sprawdzenia czegoś Optional'em to możesz go również wykorzystać.
     * <p>
     * POWODZENIA!
     */

    private final List<Book> bookList = Arrays.asList(
            new Book("a", "Brown, Dan", 412, 33.22, true),
            new Book("b", "Rowling, J.K.", 33, 33.22, false),
            new Book("c", "Rowling, J.K.", 786, 33.22, false),
            new Book("d", "Rowling, J.K.", 12, 33.22, true),
            new Book("e", "James, E. L.", 41, 33.22, false),
            new Book("f", "Rowling, J.K.", 124, 33.22, false),
            new Book("g", "Rowling, J.K.", 232, 33.22, true),
            new Book("h", "Rowling, J.K.", 654, 33.22, true),
            new Book("i", "Brown, Dan", 432, 33.22, false),
            new Book("j", "Rowling, J.K.", 11, 33.22, false),
            new Book("k", "James, E. L.", 326, 33.22, true),
            new Book("l", "Meyer, Stephenie", 454, 33.22, true),
            new Book("m", "Larsson, Stieg", 311, 33.22, false),
            new Book("n", "James, E. L.", 45, 33.22, true),
            new Book("o", "The	Brown, Dan", 32, 33.22, false),
            new Book("p", "Meyer, Stephenie", 863, 33.22, true),
            new Book("q", "Deception Point", 1119, 33.22, false),
            new Book("r", "Haddon, Mark", 123, 33.22, false)
    );

    @Test
    public void zad1() {
//        posortowac jeszcze trzeba
        bookList
                .stream()
                .filter(book -> book.getPages() > 400)
                .filter(Book::isAvailable)
                .map(bookList -> bookList.toString())
                .limit(5)
                .forEach(System.out::println);

    }

    @Test
    public void zad2() {
         String expectedAuthor  = "Brown, Dan";
        long list = bookList
                .stream()
                .filter(book -> book.getAuthor().equals(expectedAuthor))
                .map(bookList -> bookList.toString())
                .count();
        Assert.assertEquals(2, list);
    }

//    public void zad3() {
////        String expectedAuthor = "James, E. L.";
////        bookList
////                .stream()
////                .filter(book -> book.getAuthor().equals(expectedAuthor))
////                .map(book -> book.setPrice(9.99))
////
////    }
}